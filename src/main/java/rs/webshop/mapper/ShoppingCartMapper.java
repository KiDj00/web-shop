package rs.webshop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import rs.webshop.domain.ShoppingCart;
import rs.webshop.dto.shoppingCart.CreateShoppingCartCmd;
import rs.webshop.dto.shoppingCart.ShoppingCartInfo;
import rs.webshop.dto.shoppingCart.ShoppingCartResult;
import rs.webshop.dto.shoppingCart.UpdateShoppingCartCmd;

import java.util.List;

@Mapper
public interface ShoppingCartMapper {
    ShoppingCartMapper INSTANCE = Mappers.getMapper(ShoppingCartMapper.class);

    ShoppingCart createShoppingCartCmdToShoppingCart(CreateShoppingCartCmd cmd);

    List<ShoppingCartResult> listShoppingCartToListShoppingCartResult(List<ShoppingCart> shoppingCarts);

    @Mapping(target = "userDto", source = "user")
    ShoppingCartInfo shoppingCartToShoppingCartInfo(ShoppingCart shoppingCart);

    void updateShoppingCartCmdToShoppingCart(@MappingTarget ShoppingCart shoppingCart, UpdateShoppingCartCmd cmd);

}
