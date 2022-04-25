package ee.Karu.webshop.api;

import ee.Karu.webshop.cache.ProductCache;
import ee.Karu.webshop.dao.ProductRepository;
import ee.Karu.webshop.model.database.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class ProductController {
    //List<Product> products = new ArrayList<>();

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductCache productCache;

    @GetMapping("products") // localhost:8080/products
    public ResponseEntity<List<Product>> getProducts() {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productRepository.findAll());
    }

    @PostMapping("products")
    public ResponseEntity<List<Product>> addProduct(@RequestBody Product product) {
        productRepository.save(product);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productRepository.findAll());
    }

    @DeleteMapping("products/{id}")
    public ResponseEntity<List<Product>> deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
        productCache.emptyCache();
        return ResponseEntity.ok()
                .body(productRepository.findAll());
    }

    @GetMapping("products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) throws ExecutionException {
        return  ResponseEntity.ok()
                .body(productCache.getProduct(id));
        //return productRepository.findById(id).get();
    }

    @PutMapping("products")
    public ResponseEntity<List<Product>> editProduct(@RequestBody Product product) {
        productRepository.save(product);
        productCache.emptyCache();
        return ResponseEntity.ok()
                .body(productRepository.findAll());
    }

    @DeleteMapping("products")
    public ResponseEntity<String> deleteAllProduct() {
        productRepository.flush();
        productCache.emptyCache();
        return ResponseEntity.ok()
                .body("Kõik tooted kustutatud");
    }
}