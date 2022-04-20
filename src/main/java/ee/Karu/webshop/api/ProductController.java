package ee.Karu.webshop.api;

import ee.Karu.webshop.cache.ProductCache;
import ee.Karu.webshop.dao.ProductRepository;
import ee.Karu.webshop.model.database.Product;
import org.springframework.beans.factory.annotation.Autowired;
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
        return ResponseEntity.ok()
                .body(productRepository.findAll());
    }

    @PostMapping("products")
    public List<Product> addProduct(@RequestBody Product product) {
        productRepository.save(product);
        return productRepository.findAll();
    }

    @DeleteMapping("products/{id}")
    public List<Product> deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
        productCache.emptyCache();
        return productRepository.findAll();
    }

    @GetMapping("products/{id}")
    public Product getProduct(@PathVariable Long id) throws ExecutionException {
        return  productCache.getProduct(id);
        //return productRepository.findById(id).get();
    }

    @PutMapping("products")
    public List<Product> editProduct(@RequestBody Product product) {
        productRepository.save(product);
        productCache.emptyCache();
        return productRepository.findAll();
    }

    @DeleteMapping("products")
    public String deleteAllProduct() {
        productRepository.flush();
        productCache.emptyCache();
        return "Kõik tooted kustutatud";
    }
}