package ee.Karu.webshop.service;

import ee.Karu.webshop.model.database.Person;
import ee.Karu.webshop.model.database.Product;

import java.util.List;

public interface OrderService {

    double calculateOrderSum(List<Product> products);

    Long saveToDatabase(List<Product> products, double orderSum, Person person);

    List<Product> getAllProductsFromDb(List<Product> products);
}
