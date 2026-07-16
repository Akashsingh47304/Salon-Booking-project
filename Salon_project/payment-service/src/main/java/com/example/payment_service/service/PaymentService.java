package com.example.payment_service.service;

import com.example.payment_service.domain.PaymentMethod;
import com.example.payment_service.dto.BookingDto;
import com.example.payment_service.dto.UserDto;
import com.example.payment_service.model.PaymentOrder;
import com.example.payment_service.payload.response.PaymentLinkResponse;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayException;

public interface PaymentService {

    PaymentLinkResponse createOrder(UserDto user,
                                    BookingDto booking,
                                    PaymentMethod paymentMethod);
    PaymentOrder getPaymentOrderById(Long id);

    PaymentOrder getPaymentOrderByPaymentId(String paymentId);

    PaymentLink createRazorPayPaymentLink(UserDto user,
                                          Long amount,
                                          Long orderId
                                          );

    String createStripePaymentLink(UserDto user,
                                  Long amount,
                                  Long orderId
                                  );

    Boolean proceedPayment(PaymentOrder paymentOrder,String paymentId,String paymentLinkId) throws RazorpayException;




}
