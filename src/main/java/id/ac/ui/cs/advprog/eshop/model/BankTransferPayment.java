package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import java.util.Map;

public class BankTransferPayment extends Payment {

    public BankTransferPayment(String id, String method, Map<String, String> paymentData) {
        super(id, method, paymentData);
        validateBankTransfer();
    }

    private void validateBankTransfer() {
        String bankName = this.getPaymentData().get("bankName");
        String referenceCode = this.getPaymentData().get("referenceCode");

        if (bankName == null || bankName.trim().isEmpty() ||
                referenceCode == null || referenceCode.trim().isEmpty()) {
            this.setStatus(PaymentStatus.REJECTED.getValue());
        } else {
            this.setStatus(PaymentStatus.SUCCESS.getValue());
        }
    }
}