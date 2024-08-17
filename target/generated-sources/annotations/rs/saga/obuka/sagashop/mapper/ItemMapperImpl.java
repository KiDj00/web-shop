package rs.saga.obuka.sagashop.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import rs.saga.obuka.sagashop.domain.Item;
import rs.saga.obuka.sagashop.domain.Product;
import rs.saga.obuka.sagashop.dto.item.CreateItemCmd;
import rs.saga.obuka.sagashop.dto.item.ItemInfo;
import rs.saga.obuka.sagashop.dto.item.ItemResult;
import rs.saga.obuka.sagashop.dto.item.ProductDto;
import rs.saga.obuka.sagashop.dto.item.UpdateItemCmd;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-17T14:16:39+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.10 (Oracle Corporation)"
)
public class ItemMapperImpl implements ItemMapper {

    @Override
    public Item createItemCmdToItem(CreateItemCmd cmd) {
        if ( cmd == null ) {
            return null;
        }

        Item item = new Item();

        item.setQuantity( cmd.getQuantity() );

        return item;
    }

    @Override
    public List<ItemResult> listItemToListItemResult(List<Item> items) {
        if ( items == null ) {
            return null;
        }

        List<ItemResult> list = new ArrayList<ItemResult>( items.size() );
        for ( Item item : items ) {
            list.add( itemToItemResult( item ) );
        }

        return list;
    }

    @Override
    public ItemInfo itemToItemInfo(Item item) {
        if ( item == null ) {
            return null;
        }

        ItemInfo itemInfo = new ItemInfo();

        itemInfo.setProductDto( productToProductDto( item.getProduct() ) );
        itemInfo.setId( item.getId() );
        itemInfo.setQuantity( item.getQuantity() );

        return itemInfo;
    }

    @Override
    public void updateItemCmdToItem(Item item, UpdateItemCmd cmd) {
        if ( cmd == null ) {
            return;
        }

        item.setId( cmd.getId() );
        item.setQuantity( cmd.getQuantity() );
    }

    protected ItemResult itemToItemResult(Item item) {
        if ( item == null ) {
            return null;
        }

        ItemResult itemResult = new ItemResult();

        itemResult.setId( item.getId() );
        itemResult.setQuantity( item.getQuantity() );

        return itemResult;
    }

    protected ProductDto productToProductDto(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDto productDto = new ProductDto();

        productDto.setName( product.getName() );
        productDto.setPrice( product.getPrice() );

        return productDto;
    }
}
