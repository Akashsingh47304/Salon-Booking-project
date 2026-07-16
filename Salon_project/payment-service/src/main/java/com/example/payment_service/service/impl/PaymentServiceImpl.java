package com.example.payment_service.service.impl;

import com.example.payment_service.domain.PaymentMethod;
import com.example.payment_service.domain.PaymentOrderStatus;
import com.example.payment_service.dto.BookingDto;
import com.example.payment_service.dto.UserDto;
import com.example.payment_service.model.PaymentOrder;
import com.example.payment_service.payload.response.PaymentLinkResponse;
import com.example.payment_service.repository.PaymentRepository;
import com.example.payment_service.service.PaymentService;
import com.razorpay.Payment;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;

    @Value("${stripe.secret.key}")
    private String stripeSecretKey;

    @Value("${razorpay.key.id}")
    private String razorpayKeyId;

    @Value("${razorpay.key.secret}")
    private String razorpayKeySecret;

    @Override
    public PaymentLinkResponse createOrder(UserDto user, BookingDto booking, PaymentMethod paymentMethod) {
        Long amount = (long) booking.getTotalPrice();

        PaymentOrder order = new PaymentOrder();
        order.setAmount(amount);
        order.setPaymentMethod(paymentMethod);
        order.setUserId(user.getId());
        order.setBookingId(booking.getId());
        order.setSalonId(booking.getSalonId());
        PaymentOrder savedOrder = paymentRepository.save(order);
        log.info("Payment order created successfully inside the create Payment method");

        PaymentLinkResponse paymentLinkResponse = new PaymentLinkResponse();

        if (paymentMethod.equals(PaymentMethod.RAZORPAY)) {
            PaymentLink payment = createRazorPayPaymentLink(user, savedOrder.getAmount(), savedOrder.getId());
            String paymentUrl = payment.get("short_url");
            String paymentUrlId = payment.get("id");
            paymentLinkResponse.setPayment_link_url(paymentUrl);
            paymentLinkResponse.setGetPayment_link_id(paymentUrlId);

            savedOrder.setPaymentLinkId(paymentUrlId);
            log.info("using razorpay payment link created successfully");
            paymentRepository.save(savedOrder);

        } else {
            String paymentUrl = createStripePaymentLink(user, savedOrder.getAmount(), savedOrder.getId());
            paymentLinkResponse.setPayment_link_url(paymentUrl);
            log.info("using stripe payment link created successfully");
        }
        log.info("Payment link created successfully");

        return paymentLinkResponse;

    }

    @Override
    public PaymentOrder getPaymentOrderById(Long id) {
        return paymentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Payment Order not found with id : " + id));
    }

    @Override
    public PaymentOrder getPaymentOrderByPaymentId(String paymentId) {
        PaymentOrder paymentOrder = paymentRepository.findByPaymentLinkId(paymentId);

        if (paymentOrder == null) {
            throw new RuntimeException("Payment Order not found");
        }

        return paymentOrder;
    }

    @Override
    public PaymentLink createRazorPayPaymentLink(UserDto user, Long amount, Long orderId) {
        Long amountInPaise = amount * 100;

        try {
            RazorpayClient razorpayClient = new RazorpayClient(razorpayKeyId, razorpayKeySecret);

            JSONObject paymentLinkRequest = new JSONObject();
            paymentLinkRequest.put("amount", amountInPaise);
            paymentLinkRequest.put("currency", "INR");


            JSONObject customer = new JSONObject();
            customer.put("name", user.getFullName());
            customer.put("email", user.getEmail());
            paymentLinkRequest.put("customer", customer);

            JSONObject notify = new JSONObject();
            notify.put("email", true);

            paymentLinkRequest.put("notify", notify);

            paymentLinkRequest.put("remainder_enable", true);

            paymentLinkRequest.put("callback_url", "http://localhost:3000/payment-success/" + orderId);

            paymentLinkRequest.put("callback_method", "get");


            return razorpayClient.paymentLink.create(paymentLinkRequest);


        } catch (com.razorpay.RazorpayException e) {
            log.error("Error creating Razorpay payment link: {}", e.getMessage());
            throw new RuntimeException("Failed to create Razorpay payment link: " + e.getMessage(), e);
        }
    }

    @Override
    public String createStripePaymentLink(UserDto user, Long amount, Long orderId) {
        Stripe.apiKey = stripeSecretKey;
        try {

            Stripe.apiKey = stripeSecretKey;

            SessionCreateParams params = SessionCreateParams.builder()
                    .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                    .setMode(SessionCreateParams.Mode.PAYMENT)
                    .setSuccessUrl("http://localhost:3000/payment-success/" + orderId)
                    .setCancelUrl("http://localhost:3000/payment-failure")
                    .addLineItem(
                            SessionCreateParams.LineItem.builder()
                                    .setQuantity(1L)
                                    .setPriceData(
                                            SessionCreateParams.LineItem.PriceData.builder()
                                                    .setCurrency("inr")
                                                    .setUnitAmount(amount * 100)
                                                    .setProductData(
                                                            SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                                    .setName("Salon Booking")
                                                                    .setDescription("Payment for salon booking")
                                                                    .build()
                                                    )
                                                    .build()
                                    )
                                    .build()
                    )
                    .build();

            Session session = Session.create(params);

            return session.getUrl();

        } catch (StripeException e) {
            throw new RuntimeException("Failed to create Stripe Checkout Session", e);
        }


    }

    @Override
    public Boolean proceedPayment(PaymentOrder paymentOrder, String paymentId, String paymentLinkId) throws RazorpayException {
        if (paymentOrder.getStatus().equals(PaymentOrderStatus.PENDING)) {
            if (paymentOrder.getPaymentMethod().equals(PaymentMethod.RAZORPAY)) {
                RazorpayClient razorpayClient = new RazorpayClient(razorpayKeyId, razorpayKeySecret);
                    Payment payment = razorpayClient.payments.fetch(paymentId);
                    Integer amount= payment.get("amount");
                    String status = payment.get("status");

                    if(status.equals("captured")){
//                  produce kafka event
                        paymentOrder.setStatus(PaymentOrderStatus.COMPLETED);
                        paymentRepository.save(paymentOrder);
                        return true;

                    }
                    return false;
            }
            else{
                paymentOrder.setStatus(PaymentOrderStatus.COMPLETED);
                paymentRepository.save(paymentOrder);
                return true;
            }
        }
        return false;
    }
}
