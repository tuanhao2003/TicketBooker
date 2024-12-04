package com.example.ticketbooker.Controller.OutSource;

import com.example.ticketbooker.DTO.Seats.AddSeatDTO;
import com.example.ticketbooker.Service.OutSource.VNPAYService;

import com.example.ticketbooker.DTO.Invoice.AddInvoiceDTO;
import com.example.ticketbooker.Service.InvoiceService;
import com.example.ticketbooker.Service.SeatsService;
import com.example.ticketbooker.Util.Enum.PaymentMethod;
import com.example.ticketbooker.Util.Enum.PaymentStatus;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@org.springframework.stereotype.Controller
public class VNPayController {
    @Autowired
    private VNPAYService vnPayService;

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private SeatsService seatsService;

    private HttpSession session;

    public VNPayController(InvoiceService invoiceService, HttpSession session){
        this.invoiceService = invoiceService;
        this.session = session;
    }

    @GetMapping("/vnpay")
    public String home(HttpServletRequest request, Model model) {
        String grandTotal = getCookieValue(request, "grandTotal", "");

        // Loại bỏ dấu phẩy và chữ "đ"
        if (grandTotal != null && !grandTotal.isEmpty()) {
            grandTotal = grandTotal.replace(".", "").replace("đ", "").trim();
        }
        System.out.println(grandTotal);
        model.addAttribute("grandTotal", grandTotal);

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

        // Lấy thông tin từ cookie và giải mã
        int tripId = Integer.parseInt(getCookieValue(request, "tripId", "0"));
        String customerName = getCookieValue(request, "customerName", "");
        String selectedSeats = getCookieValue(request, "selectedSeats", "");
        String[] seats = selectedSeats.split(",\\s*"); // Tách chuỗi bằng dấu ',' và loại bỏ khoảng trắng

        // In ra các giá trị trong mảng seats
        System.out.println("Danh sách ghế đã chọn:");
        for (String seat : seats) {
            System.out.println(seat); // In từng giá trị ghế
        }

        String grandTotal = getCookieValue(request, "grandTotal", "");
        String email = getCookieValue(request, "email", "");
        String customerPhone = getCookieValue(request, "customerPhone", "");

        // Tạo hóa đơn
        LocalDateTime paymentDateTime = parsePaymentTime(paymentTime);
        Integer totalAmount = Integer.valueOf(totalPrice) / 100;

        AddInvoiceDTO addInvoiceDTO = new AddInvoiceDTO(
                totalAmount,
                paymentStatus == 1 ? PaymentStatus.PAID : PaymentStatus.PENDING,
                paymentDateTime,
                PaymentMethod.EWALLET
        );

        // Tạo hóa đơn và lấy ID của hóa đơn
        int invoiceCreated = invoiceService.addInvoice(addInvoiceDTO);
        System.out.println(invoiceCreated);

//        // Gọi service để thêm ghế và nhận danh sách các seatId
//        AddSeatDTO addSeatDTO = new AddSeatDTO();
//        addSeatDTO.setTripId(tripId);
//        addSeatDTO.setSeatCode(selectedSeats);
//
//        List<Integer> seatIds = seatsService.addSeats(addSeatDTO);
//
//        System.out.println("Danh sách Seat ID vừa được tạo:");
//        for (Integer seatId : seatIds) {
//            System.out.println(seatId); // In từng Seat ID
//        }

        // Truyền thông tin vào model
        model.addAttribute("orderId", orderInfo);
        model.addAttribute("totalPrice", totalAmount);
        model.addAttribute("paymentTime", paymentTime);
        model.addAttribute("transactionId", transactionId);
        model.addAttribute("customerName", customerName);
        model.addAttribute("customerPhone", customerPhone);
        model.addAttribute("email", email);
        model.addAttribute("selectedSeats", selectedSeats);
        model.addAttribute("grandTotal", grandTotal);
        model.addAttribute("tripId", tripId);
//        model.addAttribute("seatIds", seatIds); // Truyền danh sách seatIds vào model

        return paymentStatus == 1 ? "ordersuccess" : "orderfail";
    }



    private String getCookieValue(HttpServletRequest request, String cookieName, String defaultValue) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookieName.equals(cookie.getName())) {
                    try {
                        // Giải mã giá trị cookie từ percent-encoding
                        return URLDecoder.decode(cookie.getValue(), "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                        return defaultValue; // Trả về giá trị mặc định nếu giải mã thất bại
                    }
                }
            }
        }
        return defaultValue; // Trả về giá trị mặc định nếu không tìm thấy cookie
    }

    // Helper method để parse thời gian thanh toán từ VNPay
    private LocalDateTime parsePaymentTime(String paymentTime) {
        // VNPay trả về thời gian dạng yyyyMMddHHmmss
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        return LocalDateTime.parse(paymentTime, formatter);
    }
}