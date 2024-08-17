package rs.webshop.dao.impl;

import org.springframework.stereotype.Repository;
import rs.webshop.dao.CategoryDAO;
import rs.webshop.domain.Category;

@Repository
public class CategoryDAOImpl extends AbstractDAOImpl<Category, Long> implements CategoryDAO {
}
