package rs.webshop.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.webshop.dao.CategoryDAO;
import rs.webshop.dao.ProductDAO;
import rs.webshop.domain.Category;
import rs.webshop.domain.Product;
import rs.webshop.dto.product.CreateProductCmd;
import rs.webshop.dto.product.ProductInfo;
import rs.webshop.dto.product.ProductResult;
import rs.webshop.dto.product.UpdateProductCmd;
import rs.webshop.exception.DAOException;
import rs.webshop.exception.ErrorCode;
import rs.webshop.exception.ServiceException;
import rs.webshop.mapper.CategoryMapper;
import rs.webshop.mapper.ProductMapper;
import rs.webshop.service.ProductService;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final static Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductDAO productDAO;
    private final CategoryDAO categoryDAO;

    public ProductServiceImpl(ProductDAO productDAO, CategoryDAO categoryDAO) {
        this.productDAO = productDAO;
        this.categoryDAO = categoryDAO;
    }

    @Override
    public Product save(CreateProductCmd cmd) throws ServiceException {
        final Product product = ProductMapper.INSTANCE.createProductCmdToProduct(cmd);

        if (cmd.getCategories() != null) {
            cmd.getCategories().forEach(e -> {
                Category cat;
                if (e.getId() != null) { //ako je kategorija postojeca, onda je ucitati u sesiju
                    cat = categoryDAO.findOne(e.getId());
                } else {
                    cat = CategoryMapper.INSTANCE.createCategoryFromCategoryDto(e); //ako kategorija ne postoji, onda ce se kreirati
                }
                product.getCategories().add(cat);
                cat.getProducts().add(product);
            });
        }
        try {
            return productDAO.save(product);
        } catch (DAOException e) {
            LOGGER.error(null, e);
            throw new ServiceException(ErrorCode.ERR_GEN_001, "Saving of product failed!", e);
        }

    }

    @Override
    public List<ProductResult> findAll() {
        return ProductMapper.INSTANCE.listProductToListProductResult(productDAO.findAll());
    }

    @Override
    public ProductInfo findById(Long id) {
        return ProductMapper.INSTANCE.productToProductInfo(productDAO.findOne(id));
    }

    @Override
    public void update(UpdateProductCmd cmd) throws ServiceException {
        Product product;
        try {
            product = productDAO.findOne(cmd.getId());
            if (product == null) throw new ServiceException(ErrorCode.ERR_GEN_002);

            ProductMapper.INSTANCE.updateProductCmdToProduct(product, cmd);
            productDAO.update(product);
        } catch (DAOException e) {
            LOGGER.error(null, e);
            throw new ServiceException(ErrorCode.ERR_GEN_001, "Update of product failed!", e);
        }

    }

    @Override
    public void delete(Long id) throws ServiceException {
        Product product = productDAO.findOne(id);
        if (product != null) {
            try {
                productDAO.delete(product);
            } catch (DAOException e) {
                LOGGER.error(null, e);
                throw new ServiceException(ErrorCode.ERR_CAT_001, "Product does not exist!");
            }
        }
    }

    @Override
    public List<ProductResult> findByName(String name) throws ServiceException {
        return ProductMapper.INSTANCE.listProductToListProductResult(productDAO.findProductByName(name));
    }

    @Override
    public List<ProductResult> findByPrice(BigDecimal price) throws ServiceException {
        return ProductMapper.INSTANCE.listProductToListProductResult(productDAO.findProductByPrice(price));
    }

    @Override
    public List<ProductResult> findByQuantity(int quantity) throws ServiceException {
        return ProductMapper.INSTANCE.listProductToListProductResult(productDAO.findProductByQuantity(quantity));
    }

    @Override
    public List<ProductResult> findByCategory(String categoryName) throws ServiceException {
        return ProductMapper.INSTANCE.listProductToListProductResult(productDAO.findProductByCategory(categoryName));
    }
}
