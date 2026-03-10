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
        boolean isValid = false;

        if (voucherCode != null && voucherCode.length() == 16 && voucherCode.startsWith("ESHOP")) {
            int digitCount = 0;
            for (int i = 0; i < voucherCode.length(); i++) {
                if (Character.isDigit(voucherCode.charAt(i))) {
                    digitCount++;
                }
            }
            if (digitCount == 8) {
                isValid = true;
            }
        }

        if (isValid) {
            this.setStatus(PaymentStatus.SUCCESS.getValue());
        } else {
            this.setStatus(PaymentStatus.REJECTED.getValue());
        }
    }
}