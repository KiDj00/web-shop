package rs.webshop.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import rs.webshop.domain.Category;
import rs.webshop.domain.Product;
import rs.webshop.dto.product.CategoryDto;
import rs.webshop.dto.product.CreateProductCmd;
import rs.webshop.dto.product.ProductInfo;
import rs.webshop.dto.product.ProductResult;
import rs.webshop.dto.product.UpdateProductCmd;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-17T23:27:23+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.10 (Oracle Corporation)"
)
public class ProductMapperImpl implements ProductMapper {

    @Override
    public Product createProductCmdToProduct(CreateProductCmd cmd) {
        if ( cmd == null ) {
            return null;
        }

        Product product = new Product();

        product.setName( cmd.getName() );
        product.setDescription( cmd.getDescription() );
        product.setPrice( cmd.getPrice() );
        product.setQuantity( cmd.getQuantity() );

        return product;
    }

    @Override
    public List<ProductResult> listProductToListProductResult(List<Product> products) {
        if ( products == null ) {
            return null;
        }

        List<ProductResult> list = new ArrayList<ProductResult>( products.size() );
        for ( Product product : products ) {
            list.add( productToProductResult( product ) );
        }

        return list;
    }

    @Override
    public ProductInfo productToProductInfo(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductInfo productInfo = new ProductInfo();

        productInfo.setCategoryNames( categoryListToCategoryDtoList( product.getCategories() ) );
        productInfo.setId( product.getId() );
        productInfo.setName( product.getName() );
        productInfo.setDescription( product.getDescription() );
        productInfo.setPrice( product.getPrice() );
        productInfo.setQuantity( product.getQuantity() );

        return productInfo;
    }

    @Override
    public void updateProductCmdToProduct(Product product, UpdateProductCmd cmd) {
        if ( cmd == null ) {
            return;
        }

        product.setId( cmd.getId() );
        product.setName( cmd.getName() );
        product.setDescription( cmd.getDescription() );
        product.setPrice( cmd.getPrice() );
        product.setQuantity( cmd.getQuantity() );
    }

    protected ProductResult productToProductResult(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductResult productResult = new ProductResult();

        productResult.setId( product.getId() );
        productResult.setName( product.getName() );
        productResult.setDescription( product.getDescription() );
        productResult.setPrice( product.getPrice() );
        productResult.setQuantity( product.getQuantity() );

        return productResult;
    }

    protected CategoryDto categoryToCategoryDto(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryDto categoryDto = new CategoryDto();

        categoryDto.setId( category.getId() );
        categoryDto.setName( category.getName() );
        categoryDto.setDescription( category.getDescription() );

        return categoryDto;
    }

    protected List<CategoryDto> categoryListToCategoryDtoList(List<Category> list) {
        if ( list == null ) {
            return null;
        }

        List<CategoryDto> list1 = new ArrayList<CategoryDto>( list.size() );
        for ( Category category : list ) {
            list1.add( categoryToCategoryDto( category ) );
        }

        return list1;
    }
}
