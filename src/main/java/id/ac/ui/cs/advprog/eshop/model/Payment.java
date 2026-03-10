package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import lombok.Getter;
import java.util.Map;

@Getter
public class Payment {
    String id;
    String method;
    String status;
    Map<String, String> paymentData;
    Order order;

    public Payment(String id, String method, Map<String, String> paymentData) {
        this.id = id;
        this.method = method;
        this.paymentData = paymentData;
        this.order = order;
    }

    public Payment(String id, String method, Map<String, String> paymentData, String status) {
        this(id, method, paymentData);
        this.setStatus(status);
    }

    public void setStatus(String status) {
        if (PaymentStatus.contains(status)) {
            this.status = status;
            if (this.status.equals(PaymentStatus.SUCCESS.getValue()) && this.order != null) {
                this.order.setStatus("SUCCESS");
            } else if (this.status.equals(PaymentStatus.REJECTED.getValue()) && this.order != null) {
                this.order.setStatus("FAILED");
            }
        } else {
            throw new IllegalArgumentException();
        }
    }
}