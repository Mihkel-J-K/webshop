package ee.Karu.webshop.api;

import ee.Karu.webshop.dao.ProductRepository;
import ee.Karu.webshop.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    //List<Product> products = new ArrayList<>();

    @Autowired
    ProductRepository productRepository;

    @GetMapping("get-products")
    public List<Product> getProducts(){
        return productRepository.findAll();
    }

    @GetMapping("get-products/{id}")
    public Product getProduct(@PathVariable Long id){
        return productRepository.findById(id).get();
    }

    // POST


    @PostMapping("add-product")
    public void addProduct(@RequestBody Product product){
        productRepository.save(product);
    }

    @PostMapping("edit-product")
    public void editProduct(@RequestBody Product product){
        productRepository.save(product);
    }

    @DeleteMapping("delete/{id})") // localhost:8080/delete/{id}
    public void deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
    }

    @DeleteMapping("deleteall") // localhost:8080/delete/{id}
    public String deleteAll() {
        productRepository.flush();
        return "All products have been deleted.";
    }
}
