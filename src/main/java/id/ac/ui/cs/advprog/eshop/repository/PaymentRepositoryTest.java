package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.VoucherPayment;
import id.ac.ui.cs.advprog.eshop.model.BankTransferPayment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentRepositoryTest {
    PaymentRepository paymentRepository;

    @BeforeEach
    void setUp() {
        paymentRepository = new PaymentRepository();
    }

    @Test
    void testSaveCreateVoucher() {
        Map<String, String> data = new HashMap<>();
        data.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment = new VoucherPayment("p-1", "VOUCHER", data);

        Payment result = paymentRepository.save(payment);
        Payment findResult = paymentRepository.findById("p-1");

        assertEquals(payment.getId(), result.getId());
        assertEquals(payment.getId(), findResult.getId());
    }

    @Test
    void testSaveCreateBankTransfer() {
        Map<String, String> data = new HashMap<>();
        data.put("bankName", "BCA");
        data.put("referenceCode", "REF123");
        Payment payment = new BankTransferPayment("p-2", "BANK_TRANSFER", data);

        Payment result = paymentRepository.save(payment);
        Payment findResult = paymentRepository.findById("p-2");

        assertEquals(payment.getId(), result.getId());
        assertEquals(payment.getId(), findResult.getId());
    }

    @Test
    void testFindByIdNotFound() {
        Payment result = paymentRepository.findById("zczc");
        assertNull(result);
    }

    @Test
    void testFindAll() {
        Map<String, String> data = new HashMap<>();
        data.put("voucherCode", "ESHOP1234ABC5678");
        paymentRepository.save(new VoucherPayment("p-1", "VOUCHER", data));
        paymentRepository.save(new VoucherPayment("p-2", "VOUCHER", data));

        List<Payment> payments = paymentRepository.findAll();
        assertEquals(2, payments.size());
    }
}