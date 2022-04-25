package ee.Karu.webshop.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import ee.Karu.webshop.dao.ProductRepository;
import ee.Karu.webshop.model.database.Product;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import com.google.common.cache.LoadingCache;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Log4j2
@Component
public class ProductCache {
    // Google Guava Cache
    @Autowired
    ProductRepository productRepository;

    // cahce loading otsustab, kas v6tab cacheist (ulikiire ja vahaese j6udlusega v6i v6tab andmebaasist
    private LoadingCache<Long, Product> productLoadingCache = CacheBuilder
            .newBuilder()
            .expireAfterAccess(10, TimeUnit.SECONDS)
            .build(new CacheLoader<Long, Product>() {
                @Override
                public Product load(Long id) throws Exception {
                    log.info("v6tan andmebaasist");
                    return productRepository.findById(id).get();
                }
            });

    // avalik funktsioon, mis v6tab cache-ist
    public Product getProduct(Long id) throws ExecutionException {
        log.info("v6tan cacheist");
        return productLoadingCache.get(id);
    }

    public void emptyCache() {
        productLoadingCache.invalidateAll();
    }

    public void updateCache(Product product) {
        productLoadingCache.put(product.getId(), product);

    }
}
