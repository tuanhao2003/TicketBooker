package com.example.ticketbooker.Controller;

import com.example.ticketbooker.DTO.Account.AccountDTO;
import com.example.ticketbooker.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Controller
@RequestMapping("/admin/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    //Hiển thị danh sách accounts
    @GetMapping()
    public String listAccounts(
            Model model,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<AccountDTO> accountPage = accountService.getAllAccounts(pageable);

        model.addAttribute("accounts", accountPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", accountPage.getTotalPages());

        return "View/Admin/AccountManagement/Account";
    }

    //Hiển thị form thêm account
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("accountDTO", new AccountDTO());
        return "View/Admin/AccountManagement/AccountForm";
    }

    // Xử lý thêm account
    @PostMapping("/add")
    public String addAccount(@ModelAttribute("accountDTO") AccountDTO accountDTO, RedirectAttributes redirectAttributes) {
        accountService.createAccount(accountDTO);
        redirectAttributes.addFlashAttribute("successMessage", "Thêm tài khoản thành công!");
        return "redirect:/admin/accounts";
    }

    // Hiển thị form sửa account
    @GetMapping("/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        AccountDTO accountDTO = accountService.getAccountById(id);
        if (accountDTO != null) {
            model.addAttribute("accountDTO", accountDTO);
            return "View/Admin/AccountManagement/AccountForm";
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Không tìm thấy tài khoản với ID: " + id);
            return "redirect:/admin/accounts";
        }
    }

    // Xử lý sửa account
    @PostMapping("/update")
    public String updateAccount(@ModelAttribute("accountDTO") AccountDTO accountDTO, RedirectAttributes redirectAttributes) {
        boolean updatedAccount = accountService.updateAccount(accountDTO);
        if (updatedAccount) {
            redirectAttributes.addFlashAttribute("successMessage", "Cập nhật tài khoản thành công!");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Cập nhật tài khoản thất bại!");
        }
        return "redirect:/admin/accounts";
    }
}
