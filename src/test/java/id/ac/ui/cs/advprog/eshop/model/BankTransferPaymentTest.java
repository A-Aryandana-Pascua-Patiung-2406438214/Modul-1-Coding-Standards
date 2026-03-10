package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BankTransferPaymentTest {

    @Test
    void testCreateBankTransferPaymentValid() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", "Bank Bengkulu");
        paymentData.put("referenceCode", "REF12345678");
        Payment payment = new BankTransferPayment("payment-1", "BANK_TRANSFER", paymentData);

        assertEquals("payment-1", payment.getId());
        assertEquals("BANK_TRANSFER", payment.getMethod());
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
    }

    @Test
    void testCreateBankTransferPaymentEmptyBankName() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", "");
        paymentData.put("referenceCode", "REF12345678");
        Payment payment = new BankTransferPayment("payment-2", "BANK_TRANSFER", paymentData);

        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testCreateBankTransferPaymentEmptyReferenceCode() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", "Bank Bengkulu");
        paymentData.put("referenceCode", "");
        Payment payment = new BankTransferPayment("payment-3", "BANK_TRANSFER", paymentData);

        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testCreateBankTransferPaymentNullData() {
        Map<String, String> paymentData = new HashMap<>();
        Payment payment = new BankTransferPayment("payment-4", "BANK_TRANSFER", paymentData);

        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }
}