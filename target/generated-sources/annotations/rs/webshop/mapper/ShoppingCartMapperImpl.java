package rs.webshop.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import rs.webshop.domain.Item;
import rs.webshop.domain.ShoppingCart;
import rs.webshop.domain.User;
import rs.webshop.dto.shoppingCart.CreateShoppingCartCmd;
import rs.webshop.dto.shoppingCart.ItemDto;
import rs.webshop.dto.shoppingCart.ShoppingCartInfo;
import rs.webshop.dto.shoppingCart.ShoppingCartResult;
import rs.webshop.dto.shoppingCart.UpdateShoppingCartCmd;
import rs.webshop.dto.shoppingCart.UserDto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-17T23:27:23+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.10 (Oracle Corporation)"
)
public class ShoppingCartMapperImpl implements ShoppingCartMapper {

    @Override
    public ShoppingCart createShoppingCartCmdToShoppingCart(CreateShoppingCartCmd cmd) {
        if ( cmd == null ) {
            return null;
        }

        ShoppingCart shoppingCart = new ShoppingCart();

        shoppingCart.setName( cmd.getName() );
        shoppingCart.setStatus( cmd.getStatus() );
        shoppingCart.setPrice( cmd.getPrice() );

        return shoppingCart;
    }

    @Override
    public List<ShoppingCartResult> listShoppingCartToListShoppingCartResult(List<ShoppingCart> shoppingCarts) {
        if ( shoppingCarts == null ) {
            return null;
        }

        List<ShoppingCartResult> list = new ArrayList<ShoppingCartResult>( shoppingCarts.size() );
        for ( ShoppingCart shoppingCart : shoppingCarts ) {
            list.add( shoppingCartToShoppingCartResult( shoppingCart ) );
        }

        return list;
    }

    @Override
    public ShoppingCartInfo shoppingCartToShoppingCartInfo(ShoppingCart shoppingCart) {
        if ( shoppingCart == null ) {
            return null;
        }

        ShoppingCartInfo shoppingCartInfo = new ShoppingCartInfo();

        shoppingCartInfo.setUserDto( userToUserDto( shoppingCart.getUser() ) );
        shoppingCartInfo.setId( shoppingCart.getId() );
        shoppingCartInfo.setName( shoppingCart.getName() );
        shoppingCartInfo.setStatus( shoppingCart.getStatus() );
        shoppingCartInfo.setPrice( shoppingCart.getPrice() );
        shoppingCartInfo.setItems( itemListToItemDtoList( shoppingCart.getItems() ) );

        return shoppingCartInfo;
    }

    @Override
    public void updateShoppingCartCmdToShoppingCart(ShoppingCart shoppingCart, UpdateShoppingCartCmd cmd) {
        if ( cmd == null ) {
            return;
        }

        shoppingCart.setId( cmd.getId() );
        shoppingCart.setName( cmd.getName() );
        shoppingCart.setStatus( cmd.getStatus() );
        shoppingCart.setPrice( cmd.getPrice() );
    }

    protected ShoppingCartResult shoppingCartToShoppingCartResult(ShoppingCart shoppingCart) {
        if ( shoppingCart == null ) {
            return null;
        }

        ShoppingCartResult shoppingCartResult = new ShoppingCartResult();

        shoppingCartResult.setId( shoppingCart.getId() );
        shoppingCartResult.setName( shoppingCart.getName() );
        shoppingCartResult.setStatus( shoppingCart.getStatus() );
        shoppingCartResult.setPrice( shoppingCart.getPrice() );

        return shoppingCartResult;
    }

    protected UserDto userToUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setUsername( user.getUsername() );
        userDto.setPassword( user.getPassword() );
        userDto.setFirstName( user.getFirstName() );
        userDto.setLastName( user.getLastName() );

        return userDto;
    }

    protected ItemDto itemToItemDto(Item item) {
        if ( item == null ) {
            return null;
        }

        ItemDto itemDto = new ItemDto();

        itemDto.setId( item.getId() );
        itemDto.setQuantity( item.getQuantity() );

        return itemDto;
    }

    protected List<ItemDto> itemListToItemDtoList(List<Item> list) {
        if ( list == null ) {
            return null;
        }

        List<ItemDto> list1 = new ArrayList<ItemDto>( list.size() );
        for ( Item item : list ) {
            list1.add( itemToItemDto( item ) );
        }

        return list1;
    }
}
