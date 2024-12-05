//package com.example.ticketbooker.Service.OutSource;
//
//import java.net.InetAddress;
//import java.net.NetworkInterface;
//import java.net.SocketException;
//import java.util.Enumeration;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Component;
//
//@Component
//public class EmailService {
//    @Autowired
//    private JavaMailSender mailSender;
//
//    private String getIPv4() throws SocketException {
//        // lay tat ca interface mang
//        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
//
//        // lap qua tung mang
//        while (networkInterfaces.hasMoreElements()) {
//            NetworkInterface networkInterface = networkInterfaces.nextElement();
//            if (networkInterface.isUp() && !networkInterface.isLoopback()) {
//                Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
//
//                // lap qua tung ip cua mang
//                while (inetAddresses.hasMoreElements()) {
//                    InetAddress inetAddress = inetAddresses.nextElement();
//                    if (inetAddress instanceof java.net.Inet4Address) {
//                        return inetAddress.getHostAddress();
//                    }
//                }
//            }
//        }
//        return null;
//    }
//
//    public void emailTemplate(String reciever, String bareid) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(reciever);
//        message.setSubject("Reset password response");
//        try {
//            message.setText(getIPv4() == null ? "" : "Please click the link below to reset your password: \nhttp://" + getIPv4() + ":8080/request_reset?bareid="+bareid);
//        } catch (SocketException e) {
//            System.out.println(e);
//        }
//        message.setFrom("matauhu174@gmail.com");
//        mailSender.send(message);
//    }
//
////    @Override
////    public int sendEmail(int id, String newpass) {
////        try {
////            thanhVien member = this.thanhVienRepository.findByMatv(id);
////            if (member != null) {
////                if (member.getEmail() != null && member.getEmail() != "") {
////                    this.emailSender.emailTemplate(member.getEmail(),
////                            bareEncode(id) + "&password=" + bareEncodeStr(newpass));
////                    return 1;
////                }else{
////                    return 2;
////                }
////            }
////        } catch (Exception e) {
////            System.out.println(e);
////        }
////        return 0;
////    }
//}
