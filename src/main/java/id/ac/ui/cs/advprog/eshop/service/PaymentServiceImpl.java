package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.VoucherPayment;
import id.ac.ui.cs.advprog.eshop.model.BankTransferPayment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public Payment addPayment(Order order, String method, Map<String, String> paymentData) {
        String id = UUID.randomUUID().toString();
        Payment payment;

        if (method.equals("VOUCHER")) {
            payment = new VoucherPayment(id, method, paymentData);
        } else if (method.equals("BANK_TRANSFER")) {
            payment = new BankTransferPayment(id, method, paymentData);
        } else {
            throw new IllegalArgumentException();
        }

        return paymentRepository.save(payment);
    }

    @Override
    public Payment setStatus(String paymentId, String status) {
        Payment payment = paymentRepository.findById(paymentId);
        if (payment != null) {
            payment.setStatus(status);
            return payment;
        }
        throw new IllegalArgumentException();
    }

    @Override
    public Payment getPayment(String paymentId) {
        return paymentRepository.findById(paymentId);
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }
}