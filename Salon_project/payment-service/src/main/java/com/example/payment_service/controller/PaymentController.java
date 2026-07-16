package com.example.payment_service.controller;

import com.example.payment_service.domain.PaymentMethod;
import com.example.payment_service.dto.BookingDto;
import com.example.payment_service.dto.UserDto;
import com.example.payment_service.model.PaymentOrder;
import com.example.payment_service.payload.response.PaymentLinkResponse;
import com.example.payment_service.service.PaymentService;
import com.razorpay.RazorpayException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
@Slf4j
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("/create")
    public ResponseEntity<PaymentLinkResponse> createPaymentLink(@RequestBody BookingDto bookingDto,
                                                                 @RequestParam PaymentMethod paymentMethod) {
        UserDto user = new UserDto();
        user.setEmail("ashok");
        user.setEmail("ashok@gmail.com");
        user.setId(1L);

        PaymentLinkResponse paymentLinkResponse = paymentService.createOrder(user, bookingDto, paymentMethod);
        return ResponseEntity.ok(paymentLinkResponse);

    }

    @GetMapping("/{paymentOrderId}")
    public ResponseEntity<PaymentOrder> getPaymentOrderById(@PathVariable Long paymentOrderId
                                                                 ) {
        PaymentOrder res= paymentService.getPaymentOrderById(paymentOrderId);
        return ResponseEntity.ok(res);

    }
    @GetMapping("/payment-id/{paymentId}")
    public ResponseEntity<PaymentOrder> getPaymentOrderById(@PathVariable String paymentId) {
        PaymentOrder res= paymentService.getPaymentOrderByPaymentId(paymentId);
        return ResponseEntity.ok(res);
    }

    @PutMapping("/proceed")
    public ResponseEntity<Boolean> proceedPayment(
            @RequestParam String paymentId,
            @RequestParam String paymentLinkId
    ) throws RazorpayException {
        PaymentOrder paymentOrder= paymentService.getPaymentOrderByPaymentId(paymentId);

        Boolean res= paymentService.proceedPayment(paymentOrder, paymentId,
                paymentLinkId);
        return ResponseEntity.ok(res);


    }

}
