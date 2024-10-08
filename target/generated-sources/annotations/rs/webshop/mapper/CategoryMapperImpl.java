package rs.webshop.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import rs.webshop.domain.Category;
import rs.webshop.domain.Product;
import rs.webshop.dto.category.CategoryInfo;
import rs.webshop.dto.category.CategoryResult;
import rs.webshop.dto.category.CreateCategoryCmd;
import rs.webshop.dto.category.CreateCategoryWithProductsCmd;
import rs.webshop.dto.category.ProductDto;
import rs.webshop.dto.category.UpdateCategoryCmd;
import rs.webshop.dto.category.UpdateCategoryWithProductsCmd;
import rs.webshop.dto.product.CategoryDto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-21T14:35:13+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.11 (Amazon.com Inc.)"
)
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public Category createCategoryCmdToCategory(CreateCategoryCmd cmd) {
        if ( cmd == null ) {
            return null;
        }

        Category category = new Category();

        category.setName( cmd.getName() );
        category.setDescription( cmd.getDescription() );

        return category;
    }

    @Override
    public Category createCategoryWithProductsCmdToCategory(CreateCategoryWithProductsCmd cmd) {
        if ( cmd == null ) {
            return null;
        }

        Category category = new Category();

        category.setName( cmd.getName() );
        category.setDescription( cmd.getDescription() );
        category.setProducts( productDtoListToProductList( cmd.getProducts() ) );

        return category;
    }

    @Override
    public List<CategoryResult> listCategoryToListCategoryResult(List<Category> categories) {
        if ( categories == null ) {
            return null;
        }

        List<CategoryResult> list = new ArrayList<CategoryResult>( categories.size() );
        for ( Category category : categories ) {
            list.add( categoryToCategoryResult( category ) );
        }

        return list;
    }

    @Override
    public CategoryInfo categoryToCategoryInfo(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryInfo categoryInfo = new CategoryInfo();

        categoryInfo.setId( category.getId() );
        categoryInfo.setName( category.getName() );
        categoryInfo.setDescription( category.getDescription() );

        return categoryInfo;
    }

    @Override
    public void updateCategoryCmdToCategory(Category category, UpdateCategoryCmd cmd) {
        if ( cmd == null ) {
            return;
        }

        category.setId( cmd.getId() );
        category.setName( cmd.getName() );
        category.setDescription( cmd.getDescription() );
    }

    @Override
    public void updateCategoryWithProductsCmdToCategory(Category category, UpdateCategoryWithProductsCmd cmd) {
        if ( cmd == null ) {
            return;
        }

        category.setId( cmd.getId() );
        category.setName( cmd.getName() );
        category.setDescription( cmd.getDescription() );
        if ( category.getProducts() != null ) {
            List<Product> list = productDtoListToProductList( cmd.getProducts() );
            if ( list != null ) {
                category.getProducts().clear();
                category.getProducts().addAll( list );
            }
            else {
                category.setProducts( null );
            }
        }
        else {
            List<Product> list = productDtoListToProductList( cmd.getProducts() );
            if ( list != null ) {
                category.setProducts( list );
            }
        }
    }

    @Override
    public CreateCategoryCmd categoryToCreateCategoryCmd(Category category) {
        if ( category == null ) {
            return null;
        }

        CreateCategoryCmd createCategoryCmd = new CreateCategoryCmd();

        createCategoryCmd.setName( category.getName() );
        createCategoryCmd.setDescription( category.getDescription() );

        return createCategoryCmd;
    }

    @Override
    public Category createCategoryFromCategoryDto(CategoryDto categoryDto) {
        if ( categoryDto == null ) {
            return null;
        }

        Category category = new Category();

        category.setId( categoryDto.getId() );
        category.setName( categoryDto.getName() );
        category.setDescription( categoryDto.getDescription() );

        return category;
    }

    protected Product productDtoToProduct(ProductDto productDto) {
        if ( productDto == null ) {
            return null;
        }

        Product product = new Product();

        product.setId( productDto.getId() );
        product.setName( productDto.getName() );
        product.setDescription( productDto.getDescription() );
        product.setPrice( productDto.getPrice() );
        product.setQuantity( productDto.getQuantity() );

        return product;
    }

    protected List<Product> productDtoListToProductList(List<ProductDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Product> list1 = new ArrayList<Product>( list.size() );
        for ( ProductDto productDto : list ) {
            list1.add( productDtoToProduct( productDto ) );
        }

        return list1;
    }

    protected CategoryResult categoryToCategoryResult(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryResult categoryResult = new CategoryResult();

        categoryResult.setId( category.getId() );
        categoryResult.setName( category.getName() );
        categoryResult.setDescription( category.getDescription() );

        return categoryResult;
    }
}
