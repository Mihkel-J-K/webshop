package ee.Karu.webshop.service;

import ee.Karu.webshop.cache.ProductCache;
import ee.Karu.webshop.dao.OrderRepository;
import ee.Karu.webshop.dao.ProductRepository;
import ee.Karu.webshop.model.database.Order;
import ee.Karu.webshop.model.database.Product;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
@Log4j2
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductCache productCache;

    public double calculateOrderSum(List<Product> products) {
        return products.stream()
                .mapToDouble(Product::getPrice) // map (asendab) k6ik tooted hinnaga (double kujul) | mapToDouble(p -> p.getPrice()) on sama
                .sum();


        /*
        double sum = 0;
        for ( Product p: products) {
            sum += p.getPrice();               Sama asi mis stream
        }
        return sum;
         */
    }

    public Long saveToDatabase(List<Product> products, double orderSum) {
        Order order = new Order();
        order.setOrderSum(orderSum);
        order.setProducts(products);
        Order savedOrder = orderRepository.save(order);
        return savedOrder.getId();
    }

    public List<Product> getAllProductsFromDb(List<Product> products) {
        return products.stream()
                .map(p -> {
                    try {
                        return productCache.getProduct(p.getId());
                    } catch (ExecutionException e) {
                        log.error("Cache error {}", e.getMessage());
                        return null;
                    }
                })
                .collect(Collectors.toList());
    }
}
