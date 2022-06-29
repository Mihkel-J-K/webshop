package ee.Karu.webshop.model.request.input;

import ee.Karu.webshop.model.database.Product;
import lombok.Data;

@Data
public class CartProduct {
    private Product product;
    private int quantity;
}
