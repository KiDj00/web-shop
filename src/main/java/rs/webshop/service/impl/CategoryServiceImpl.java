package rs.webshop.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.webshop.dao.CategoryDAO;
import rs.webshop.domain.Category;
import rs.webshop.domain.Product;
import rs.saga.obuka.sagashop.dto.category.*;
import rs.webshop.dto.category.*;
import rs.webshop.exception.DAOException;
import rs.webshop.exception.ErrorCode;
import rs.webshop.exception.ServiceException;
import rs.webshop.mapper.CategoryMapper;
import rs.webshop.service.CategoryService;

import java.util.List;

@SuppressWarnings("Duplicates")
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final static Logger LOGGER = LoggerFactory.getLogger(CategoryServiceImpl.class);

    private final CategoryDAO categoryDAO;

    public CategoryServiceImpl(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    @Override
    public Category save(CreateCategoryCmd cmd) throws ServiceException {
        Category category = CategoryMapper.INSTANCE.createCategoryCmdToCategory(cmd);
        try {
            category = categoryDAO.save(category);
        } catch (DAOException e) {
            LOGGER.error(null, e);
            throw new ServiceException(ErrorCode.ERR_GEN_001, "Saving of category failed!", e);
        }
        return category;
    }

    @Override
    public Category saveWithProducts(CreateCategoryWithProductsCmd cmd) throws ServiceException {
        Category category = CategoryMapper.INSTANCE.createCategoryWithProductsCmdToCategory(cmd);
        try {
            if (category.getProducts() != null) {
                for (Product p : category.getProducts()) {
                    p.getCategories().add(category);
                }
            }
            category = categoryDAO.save(category);
        } catch (DAOException e) {
            LOGGER.error(null, e);
            throw new ServiceException(ErrorCode.ERR_GEN_001, "Saving of category failed!", e);
        }
        return category;
    }

    @Override
    public List<CategoryResult> findAll() {
        return CategoryMapper.INSTANCE.listCategoryToListCategoryResult(categoryDAO.findAll());
    }

    @Override
    public CategoryInfo findById(Long id) {
        return CategoryMapper.INSTANCE.categoryToCategoryInfo(categoryDAO.findOne(id));
    }

    @Override
    public void update(UpdateCategoryCmd cmd) throws ServiceException {
        Category category;
        try {
            // check if entity still exists
            category = categoryDAO.findOne(cmd.getId());
            if (category == null) {
                throw new ServiceException(ErrorCode.ERR_GEN_002);
            }

            CategoryMapper.INSTANCE.updateCategoryCmdToCategory(category, cmd);
            categoryDAO.update(category);
        } catch (DAOException e) {
            LOGGER.error(null, e);
            throw new ServiceException(ErrorCode.ERR_GEN_001, "Update of category failed!", e);
        }
    }

    @Override
    public void updateWithProducts(UpdateCategoryWithProductsCmd cmd) throws ServiceException {
        Category category;
        try {
            // check if entity still exists
            category = categoryDAO.findOne(cmd.getId());
            if (category == null) {
                throw new ServiceException(ErrorCode.ERR_GEN_002);
            }

            CategoryMapper.INSTANCE.updateCategoryWithProductsCmdToCategory(category, cmd);

            if (category.getProducts()!=null){
                for (Product p: category.getProducts()) {
                    p.getCategories().add(category);
                }
            }

            categoryDAO.update(category);
        } catch (DAOException e) {
            LOGGER.error(null, e);
            throw new ServiceException(ErrorCode.ERR_GEN_001, "Update of category failed!", e);
        }
    }

    @Override
    public void delete(Long id) throws ServiceException {
        Category category = categoryDAO.findOne(id);
        if (category != null) {
            try {
                categoryDAO.delete(category);
            } catch (DAOException e) {
                LOGGER.error(null, e);
                throw new ServiceException(ErrorCode.ERR_GEN_001, "Delete of category failed!", e);
            }
        } else {
            throw new ServiceException(ErrorCode.ERR_CAT_001, "Category does not exist!");
        }
    }

}
