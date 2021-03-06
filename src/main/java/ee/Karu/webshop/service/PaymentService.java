package ee.Karu.webshop.service;

import ee.Karu.webshop.model.request.output.EveryPayUrl;

public interface PaymentService {

    EveryPayUrl getPaymentLink(double amount, Long orderId);

    Boolean checkIfOrderPaid(Long orderId, String paymentRef);
}

