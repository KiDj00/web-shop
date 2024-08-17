package rs.webshop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import rs.webshop.domain.Category;
import rs.webshop.dto.category.*;
import rs.webshop.dto.product.CategoryDto;

import java.util.List;

@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    Category createCategoryCmdToCategory(CreateCategoryCmd cmd);

    Category createCategoryWithProductsCmdToCategory(CreateCategoryWithProductsCmd cmd);

    List<CategoryResult> listCategoryToListCategoryResult(List<Category> categories);

    CategoryInfo categoryToCategoryInfo(Category category);

    void updateCategoryCmdToCategory(@MappingTarget Category category, UpdateCategoryCmd cmd);

    void updateCategoryWithProductsCmdToCategory(@MappingTarget Category category, UpdateCategoryWithProductsCmd cmd);

    CreateCategoryCmd categoryToCreateCategoryCmd(Category category);

    Category createCategoryFromCategoryDto(CategoryDto categoryDto);
}
