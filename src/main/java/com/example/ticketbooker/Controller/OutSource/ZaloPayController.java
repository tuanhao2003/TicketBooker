package com.example.ticketbooker.Controller.OutSource;

import com.example.ticketbooker.DTO.OutSource.ZaloPaymentRequest;
import com.example.ticketbooker.DTO.OutSource.ZaloPaymentResponse;
import com.example.ticketbooker.DTO.OutSource.ZaloPaymentStatusResponse;
import com.example.ticketbooker.Service.OutSource.ZaloPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/payment")
public class ZaloPayController {

    @Autowired
    private ZaloPayService zaloPayService;

    @PostMapping("/zalo-payment")
    public ZaloPaymentResponse zaloPayment(@RequestBody ZaloPaymentRequest request) {
        ZaloPaymentResponse response = new ZaloPaymentResponse();
        try {
            return zaloPayService.requestPayment(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @PostMapping("/zalo-payment-status")
    public ZaloPaymentStatusResponse zaloPaymentStatus(@RequestBody ZaloPaymentResponse response){
        if (response.getReturnCode() == 1) {
            while (true) {
                ZaloPaymentStatusResponse paymentStatus = null;
                try {
                    paymentStatus = zaloPayService.requestPaymentStatus(response.getPaymentId());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (!paymentStatus.isProcessing()) {
                    if (paymentStatus.getReturnCode() == 1) {
                        System.out.println("Thanh toán thành công!");
                    } else if (paymentStatus.getReturnCode() == 2) {
                        System.out.println("Thanh toán thất bại!");
                    }
                    return paymentStatus;
                } else {
                    try {
                        Thread.sleep(10000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return new ZaloPaymentStatusResponse();
    }
}
