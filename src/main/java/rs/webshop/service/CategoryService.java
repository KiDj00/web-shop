package rs.webshop.service;

import rs.webshop.domain.Category;
import rs.webshop.dto.category.*;
import rs.webshop.exception.ServiceException;

import java.util.List;

public interface CategoryService {

    Category save(CreateCategoryCmd cmd) throws ServiceException;

    Category saveWithProducts(CreateCategoryWithProductsCmd cmd) throws ServiceException;

    List<CategoryResult> findAll();

    CategoryInfo findById(Long id);

    void update(UpdateCategoryCmd CategoryDTO) throws ServiceException;

    void updateWithProducts(UpdateCategoryWithProductsCmd CategoryDTO) throws ServiceException;

    void delete(Long id) throws ServiceException;

}
