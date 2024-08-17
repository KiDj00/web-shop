package rs.webshop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import rs.webshop.domain.ShoppingCart;
import rs.webshop.dto.shoppingCart.CreateShoppingCartCmd;
import rs.webshop.dto.shoppingCart.ShoppingCartInfo;
import rs.webshop.dto.shoppingCart.ShoppingCartResult;
import rs.webshop.dto.shoppingCart.UpdateShoppingCartCmd;
import rs.webshop.exception.BudgetExceededException;
import rs.webshop.exception.DAOException;
import rs.webshop.exception.ServiceException;
import rs.webshop.service.ShoppingCartService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/shoppingCart")
public class ShoppingCartController {

    private ShoppingCartService shoppingCartService;

    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @PostMapping()
    public ShoppingCart save(@RequestBody @Valid CreateShoppingCartCmd cmd) throws ServiceException {
        return shoppingCartService.save(cmd);
    }

    @GetMapping()
    @ResponseBody
    public List<ShoppingCartResult> findAll() {
        return shoppingCartService.findAll();
    }

    @GetMapping(value = "/{id}")
    public ShoppingCartInfo findById(@PathVariable Long id) {
        return shoppingCartService.findById(id);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody @Valid UpdateShoppingCartCmd cmd) throws ServiceException {
        shoppingCartService.update(cmd);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) throws ServiceException {
        shoppingCartService.delete(id);
    }

    @PostMapping(value = "/checkout/{id}")
    public ShoppingCart checkout(@PathVariable Long id) throws DAOException, ServiceException, BudgetExceededException {
        return shoppingCartService.checkout(id);
    }
}
