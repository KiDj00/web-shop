package rs.webshop.service;

import rs.webshop.domain.Product;
import rs.webshop.dto.product.CreateProductCmd;
import rs.webshop.dto.product.ProductInfo;
import rs.webshop.dto.product.ProductResult;
import rs.webshop.dto.product.UpdateProductCmd;
import rs.webshop.exception.ServiceException;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    Product save(CreateProductCmd cmd) throws ServiceException;

    List<ProductResult> findAll();

    ProductInfo findById(Long id);

    void update(UpdateProductCmd ProductDTO) throws ServiceException;

    void delete(Long id) throws ServiceException;

    List<ProductResult> findByName(String name) throws ServiceException;

    List<ProductResult> findByPrice(BigDecimal price) throws ServiceException;

    List<ProductResult> findByQuantity(int quantity) throws ServiceException;

    List<ProductResult> findByCategory(String categoryName) throws ServiceException;
}
