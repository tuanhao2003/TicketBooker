package com.example.ticketbooker.Controller.OutSource;

import com.example.ticketbooker.Config.VNPAYService;

import com.example.ticketbooker.DTO.Invoice.AddInvoiceDTO;
import com.example.ticketbooker.Service.InvoiceService;
import com.example.ticketbooker.Util.Enum.PaymentMethod;
import com.example.ticketbooker.Util.Enum.PaymentStatus;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@org.springframework.stereotype.Controller
public class VNPayController {
    @Autowired
    private VNPAYService vnPayService;

    @Autowired
    private InvoiceService invoiceService;

//    private HttpSession session;
//
//    public VNPayController(InvoiceService invoiceService, HttpSession session){
//        this.invoiceService = invoiceService;
//        this.session = session;
//    }

    @GetMapping("/vnpay")
    public String home() {
        return "createOrder";
    }

    @PostMapping("/submitOrder")
    public String submitOrder(@RequestParam("amount") int orderTotal,
                              @RequestParam("orderInfo") String orderInfo,
                              HttpServletRequest request) {

        String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        String vnpayUrl = vnPayService.createOrder(request, orderTotal, orderInfo, baseUrl);
        // Lấy session từ request
        HttpSession session = request.getSession();

        // Giả sử bạn lưu trữ thông tin người dùng trong session trước đó
        String username = (String) session.getAttribute("customerName");

        // Kiểm tra xem username có tồn tại trong session không
        if (username != null) {
            System.out.println("User is logged in: " + username);
        } else {
            System.out.println("No user is logged in.");
        }
        return "redirect:" + vnpayUrl;
    }


    @GetMapping("/vnpay-payment-return")
    public String paymentCompleted(HttpServletRequest request, Model model) {
        // Lấy thông tin thanh toán từ request
        int paymentStatus = vnPayService.orderReturn(request);

        String orderInfo = request.getParameter("vnp_OrderInfo");
        String paymentTime = request.getParameter("vnp_PayDate");
        String transactionId = request.getParameter("vnp_TransactionNo");
        String totalPrice = request.getParameter("vnp_Amount");

        // Convert giá trị cần thiết
        LocalDateTime paymentDateTime = parsePaymentTime(paymentTime);
        Integer totalAmount = Integer.valueOf(totalPrice) / 100; // VNPay trả về giá trị x100

        // Tạo hóa đơn mới
        AddInvoiceDTO addInvoiceDTO = new AddInvoiceDTO(
                totalAmount,
                paymentStatus == 1 ? PaymentStatus.PAID : PaymentStatus.PENDING,
                paymentDateTime,
                PaymentMethod.EWALLET
        );

        // Lưu hóa đơn
        boolean invoiceCreated = invoiceService.addInvoice(addInvoiceDTO);

        // Thêm các giá trị vào model
        model.addAttribute("orderId", orderInfo);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("paymentTime", paymentTime);
        model.addAttribute("transactionId", transactionId);

        // Kiểm tra tình trạng thanh toán và trả về view tương ứng
        return paymentStatus == 1 ? "ordersuccess" : "orderfail";
    }

    // Helper method để parse thời gian thanh toán từ VNPay
    private LocalDateTime parsePaymentTime(String paymentTime) {
        // VNPay trả về thời gian dạng yyyyMMddHHmmss
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        return LocalDateTime.parse(paymentTime, formatter);
    }
}