package rs.webshop.dao.impl;

import org.springframework.stereotype.Repository;
import rs.webshop.dao.ProductDAO;
import rs.webshop.domain.Category;
import rs.webshop.domain.Product;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ListJoin;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.List;

@Repository
public class ProductDAOImpl extends AbstractDAOImpl<Product, Long> implements ProductDAO {

    @Override
    public List<Product> findProductByName(String name) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> criteria = cb.createQuery(Product.class);
        Root<Product> product = criteria.from(Product.class);
        criteria.select(product)
                .where(cb.like(product.get("name"), "%" + name + "%"));

        return entityManager.createQuery(criteria).getResultList();
    }

    @Override
    public List<Product> findProductByPrice(BigDecimal price) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> criteria = cb.createQuery(Product.class);
        Root<Product> product = criteria.from(Product.class);
        criteria.select(product)
                .where(cb.equal(product.get("price"), price));

        return entityManager.createQuery(criteria).getResultList();
    }

    @Override
    public List<Product> findProductByQuantity(int quantity) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> criteria = cb.createQuery(Product.class);
        Root<Product> product = criteria.from(Product.class);
        criteria.select(product)
                .where(cb.lessThan(product.get("quantity"), quantity));

        return entityManager.createQuery(criteria).getResultList();
    }

    @Override
    public List<Product> findProductByCategory(String categoryName) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> criteria = cb.createQuery(Product.class);
        Root<Product> product = criteria.from(Product.class);

        ListJoin<Product, Category> category = product.joinList("categories");
        criteria.select(product)
                .where(cb.like(category.get("name"), "%" + categoryName + "%"));

        return entityManager.createQuery(criteria).getResultList();
    }
}
