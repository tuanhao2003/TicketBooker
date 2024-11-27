package com.example.ticketbooker.Util;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RedirectToPasswordCreationException.class)
    @ResponseStatus(HttpStatus.FOUND) // HTTP 302 Redirect
    public String handleRedirectToPasswordCreationException(RedirectToPasswordCreationException ex, Model model) {
        System.out.println("Check vào ham global exception handler");
        // Trả về view hoặc thực hiện redirect đến trang tạo mật khẩu mới
        return "redirect:" + ex.getRedirectUrl(); // Redirect tới /new-password
    }
    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception ex, Model model) {
        // Nếu là lỗi không tìm thấy trang (404)
        if (ex instanceof org.springframework.web.servlet.NoHandlerFoundException) {
            // Trả về trang 404
            model.addAttribute("errorMessage", "Page not found");
            return new ModelAndView("View/Util/404Page", "error", model);
        }

        // Các ngoại lệ khác có thể được xử lý ở đây
        model.addAttribute("errorMessage", "Something went wrong");
        return new ModelAndView("View/Util/404Page", "error", model);
    }
}