
package ee.Karu.webshop.api;

import ee.Karu.webshop.model.database.Product;
import ee.Karu.webshop.service.OrderService;
import ee.Karu.webshop.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PaymentController {

    @Autowired
    PaymentService paymentService;
    // nagu PaymentService paymentService = new PaymentService()
    // aga üks mälukoht koguaeg
    // selleks et @Autowired saaks panna peab olema @Component või laadset

    @Autowired
    OrderService orderService;

    @PostMapping("payment")  // localhost:8080/payment    Body    80   text
    public ResponseEntity<String> getPaymentLink(@RequestBody List<Product> products) {
        // tooted -- nimedega + hindadega
        System.out.println(paymentService);
        // Maksma -- Tellmuse nr-t
        // Salvestan andmebaasi -> maksmata kujul
        // V6ta andmebaasist tema ID (mis on genereeritud)
        // --> l'heb maksma
        List<Product> originalProducts = orderService.getAllProductsFromDb(products);
        double orderSum = orderService.calculateOrderSum(originalProducts);
        Long id = orderService.saveToDatabase(originalProducts, orderSum);
        return ResponseEntity.ok()
        .body(paymentService.getPaymentLink(orderSum, id));
    }

    @PostMapping("check-payment")
    public boolean checkIfPaid() {
        // Kui on makstud, muudan andmebaasis makstuks
        return true;
    }
}