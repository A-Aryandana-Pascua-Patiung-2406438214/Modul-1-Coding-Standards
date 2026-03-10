package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import java.util.Map;

public class BankTransferPayment extends Payment {

    public BankTransferPayment(String id, String method, Map<String, String> paymentData) {
        super(id, method, paymentData);
        validateBankTransfer();
    }

    private void validateBankTransfer() {
        Map<String, String> data = this.getPaymentData();
        String bankName = data.get("bankName");
        String referenceCode = data.get("referenceCode");

        if (isInvalid(bankName) || isInvalid(referenceCode)) {
            this.setStatus(PaymentStatus.REJECTED.getValue());
        } else {
            this.setStatus(PaymentStatus.SUCCESS.getValue());
        }
    }

    private boolean isInvalid(String value) {
        return value == null || value.isBlank();
    }
}