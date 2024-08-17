package rs.webshop.service;

import rs.webshop.domain.ShoppingCart;
import rs.webshop.dto.item.CreateItemCmd;
import rs.webshop.dto.shoppingCart.CreateShoppingCartCmd;
import rs.webshop.dto.shoppingCart.ShoppingCartInfo;
import rs.webshop.dto.shoppingCart.ShoppingCartResult;
import rs.webshop.dto.shoppingCart.UpdateShoppingCartCmd;
import rs.webshop.exception.BudgetExceededException;
import rs.webshop.exception.DAOException;
import rs.webshop.exception.ServiceException;

import java.util.List;

public interface ShoppingCartService {
    ShoppingCart save(CreateShoppingCartCmd cmd) throws ServiceException;

    List<ShoppingCartResult> findAll();

    ShoppingCartInfo findById(Long id);

    void update(UpdateShoppingCartCmd updateShoppingCartCmd) throws ServiceException;

    void delete(Long id) throws ServiceException;

    ShoppingCart addItem(CreateItemCmd cmd) throws ServiceException, DAOException;

    ShoppingCart removeItem(Long itemId) throws ServiceException, DAOException;

    ShoppingCart checkout(Long id) throws ServiceException, DAOException, BudgetExceededException;

    ShoppingCart closeShoppingCart(ShoppingCart cart) throws ServiceException, DAOException;
}
