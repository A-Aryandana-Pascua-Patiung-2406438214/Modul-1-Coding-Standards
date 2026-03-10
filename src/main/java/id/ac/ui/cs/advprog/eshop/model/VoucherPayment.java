package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import java.util.Map;

public class VoucherPayment extends Payment {

    public VoucherPayment(String id, String method, Map<String, String> paymentData) {
        super(id, method, paymentData);
        validateVoucher();
    }

    private void validateVoucher() {
        String voucherCode = this.getPaymentData().get("voucherCode");

        if (voucherCode != null && voucherCode.length() == 16 && voucherCode.startsWith("ESHOP")) {
            long digitCount = voucherCode.chars().filter(Character::isDigit).count();
            if (digitCount == 8) {
                this.setStatus(PaymentStatus.SUCCESS.getValue());
                return;
            }
        }

        this.setStatus(PaymentStatus.REJECTED.getValue());
    }
}