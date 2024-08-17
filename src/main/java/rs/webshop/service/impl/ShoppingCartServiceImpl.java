package rs.webshop.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.webshop.dao.*;
import rs.webshop.domain.*;
import rs.webshop.dto.item.CreateItemCmd;
import rs.webshop.dto.shoppingCart.CreateShoppingCartCmd;
import rs.webshop.dto.shoppingCart.ShoppingCartInfo;
import rs.webshop.dto.shoppingCart.ShoppingCartResult;
import rs.webshop.dto.shoppingCart.UpdateShoppingCartCmd;
import rs.webshop.exception.BudgetExceededException;
import rs.webshop.exception.DAOException;
import rs.webshop.exception.ErrorCode;
import rs.webshop.exception.ServiceException;
import rs.webshop.mapper.ItemMapper;
import rs.webshop.mapper.ShoppingCartMapper;
import rs.webshop.service.ShoppingCartService;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final static Logger LOGGER = LoggerFactory.getLogger(ShoppingCartServiceImpl.class);

    private final ShoppingCartDAO shoppingCartDAO;
    private final UserDAO userDAO;
    private final ItemDAO itemDAO;
    private final ProductDAO productDAO;
    private final PayPalAccountDAO payPalAccountDAO;

    public ShoppingCartServiceImpl(ShoppingCartDAO shoppingCartDAO, UserDAO userDAO, ItemDAO itemDAO, ProductDAO productDAO, PayPalAccountDAO payPalAccountDAO) {
        this.shoppingCartDAO = shoppingCartDAO;
        this.userDAO = userDAO;
        this.itemDAO = itemDAO;
        this.productDAO = productDAO;
        this.payPalAccountDAO = payPalAccountDAO;
    }

    @Override
    public ShoppingCart save(CreateShoppingCartCmd cmd) throws ServiceException {
        User user = userDAO.findOne(cmd.getUserId());

        ShoppingCart shoppingCart = ShoppingCartMapper.INSTANCE.createShoppingCartCmdToShoppingCart(cmd);
        shoppingCart.setUser(user);
        shoppingCart.setStatus(Status.NEW);

        try {
            shoppingCart = shoppingCartDAO.save(shoppingCart);
        } catch (DAOException e) {
            LOGGER.error(null, e);
            throw new ServiceException(ErrorCode.ERR_GEN_001, "Saving of shopping cart failed!", e);
        }
        return shoppingCart;
    }

    @Override
    public List<ShoppingCartResult> findAll() {
        return ShoppingCartMapper.INSTANCE.listShoppingCartToListShoppingCartResult(shoppingCartDAO.findAll());
    }

    @Override
    public ShoppingCartInfo findById(Long id) {
        return ShoppingCartMapper.INSTANCE.shoppingCartToShoppingCartInfo(shoppingCartDAO.findOne(id));
    }

    @Override
    public void update(UpdateShoppingCartCmd cmd) throws ServiceException {
        ShoppingCart shoppingCart;
        try {
            shoppingCart = shoppingCartDAO.findOne(cmd.getId());
            if (shoppingCart == null) {
                throw new ServiceException(ErrorCode.ERR_GEN_002);
            }

            ShoppingCartMapper.INSTANCE.updateShoppingCartCmdToShoppingCart(shoppingCart, cmd);
            shoppingCartDAO.update(shoppingCart);
        } catch (DAOException e) {
            LOGGER.error(null, e);
            throw new ServiceException(ErrorCode.ERR_GEN_001, "Update of shopping cart failed!", e);
        }
    }

    @Override
    public void delete(Long id) throws ServiceException {
        ShoppingCart shoppingCart = shoppingCartDAO.findOne(id);
        if (shoppingCart != null) {
            try {
                shoppingCartDAO.delete(shoppingCart);
            } catch (DAOException e) {
                LOGGER.error(null, e);
                throw new ServiceException(ErrorCode.ERR_GEN_001, "Delete of shopping cart failed!", e);
            }
        } else {
            throw new ServiceException(ErrorCode.ERR_CAT_001, "Shopping cart does not exist!");
        }
    }

    @Override
    public ShoppingCart addItem(CreateItemCmd cmd) throws ServiceException, DAOException {
        ShoppingCart cart = shoppingCartDAO.findOne(cmd.getShoppingCartID());
        cart.setStatus(Status.ACTIVE);
        Item item = ItemMapper.INSTANCE.createItemCmdToItem(cmd);
        item.setProduct(productDAO.findOne(cmd.getProductID()));
        item.setShoppingCart(cart);

        cart.getItems().add(item);
        cart.setStatus(Status.ACTIVE);

        if (cart.getPrice() != null) {
            cart.setPrice(cart.getPrice().add(item.getProduct().getPrice()));
        } else {
            cart.setPrice(item.getProduct().getPrice());
        }
        return shoppingCartDAO.update(cart);
    }

    @Override
    public ShoppingCart removeItem(Long itemId) throws ServiceException, DAOException {
        Item item = itemDAO.findOne(itemId);
        ShoppingCart cart = shoppingCartDAO.findOne(item.getShoppingCart().getId());

        if (!cart.getItems().contains(item)) return null;
        else {
            cart.getItems().remove(item);
            ShoppingCart c = shoppingCartDAO.update(cart);
            Item i = itemDAO.findOne(item.getId());
            itemDAO.delete(i);
            return c;
        }
    }

    @Override
    public ShoppingCart checkout(Long id) throws ServiceException, DAOException, BudgetExceededException {
        ShoppingCart cart = shoppingCartDAO.findOne(id);
        if (cart.getStatus().equals(Status.ACTIVE)) {
            List<Item> items = cart.getItems();
            for (Item i : items) {
                if (i.getProduct().getQuantity() < i.getQuantity())
                    throw new ServiceException(ErrorCode.ERR_P_001, "Not enough products in stock!");
                else {
                    if (cart.getUser().getPayPalAccount().getBudget().compareTo(sum(items)) > 0) {
                        Product p = i.getProduct();
                        p.setQuantity(p.getQuantity() - i.getQuantity());
                        productDAO.update(p);
                        PayPalAccount paypal = cart.getUser().getPayPalAccount();
                        paypal.setBudget(paypal.getBudget().subtract(i.getProduct().getPrice()));
                        payPalAccountDAO.update(paypal);
                    } else {
                        throw new BudgetExceededException(ErrorCode.ERR_PP_001, "Not enough budget!");
                    }
                }
                cart.setStatus(Status.COMPLETED);
                return shoppingCartDAO.update(cart);
            }

        } else {
            throw new ServiceException(ErrorCode.ERR_SC_001, "Shopping cart is not active!");
        }
        return null;
    }

    @Override
    public ShoppingCart closeShoppingCart(ShoppingCart cart) throws ServiceException, DAOException {
        cart.setStatus(Status.INACTIVE);
        return shoppingCartDAO.update(cart);
    }

    public BigDecimal sum(List<Item> items) {
        BigDecimal sum = BigDecimal.ZERO;
        for (Item i : items) {
            sum = sum.add(i.getProduct().getPrice());
        }
        return sum;
    }
}
