package rs.webshop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import rs.webshop.domain.Product;
import rs.webshop.dto.product.CreateProductCmd;
import rs.webshop.dto.product.ProductInfo;
import rs.webshop.dto.product.ProductResult;
import rs.webshop.dto.product.UpdateProductCmd;

import java.util.List;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(target = "categories", ignore = true)
    Product createProductCmdToProduct(CreateProductCmd cmd);

    List<ProductResult> listProductToListProductResult(List<Product> products);

    @Mapping(target = "categoryNames", source = "categories")
    ProductInfo productToProductInfo(Product product);

    void updateProductCmdToProduct(@MappingTarget Product product, UpdateProductCmd cmd);
}
