package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;

import java.util.List;

@Controller
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/admin/list")
    public String paymentListPage(Model model) {
        List<Payment> payments = paymentService.getAllPayments();
        model.addAttribute("payments", payments);
        return "payment/admin/list";
    }

    @GetMapping("/detail")
    public String paymentDetailPage(Model model) {
        model.addAttribute("payment", new Payment("", "", new HashMap<>()));
        return "payment/detail";
    }

    @PostMapping("/admin/set-status/{paymentId}")
    public String setStatus(@PathVariable String paymentId, @RequestParam String status) {
        paymentService.setStatus(paymentId, status);
        return "redirect:/payment/admin/list";
    }
}