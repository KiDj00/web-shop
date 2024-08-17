package rs.webshop.dao;

import rs.webshop.domain.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductDAO extends AbstractDAO<Product, Long> {
    List<Product> findProductByName(String name);

    List<Product> findProductByPrice(BigDecimal price);

    List<Product> findProductByQuantity(int quantity);

    List<Product> findProductByCategory(String categoryName);
}
