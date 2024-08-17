package rs.webshop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import rs.webshop.domain.Product;
import rs.webshop.dto.product.CreateProductCmd;
import rs.webshop.dto.product.ProductInfo;
import rs.webshop.dto.product.ProductResult;
import rs.webshop.dto.product.UpdateProductCmd;
import rs.webshop.exception.ServiceException;
import rs.webshop.service.ProductService;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping()
    public Product save(@RequestBody @Valid CreateProductCmd cmd) throws ServiceException {
        return productService.save(cmd);
    }

    @GetMapping()
    @ResponseBody
    public List<ProductResult> findAll() {
        return productService.findAll();
    }

    @GetMapping(value = "/{id}")
    public ProductInfo findById(@PathVariable Long id){
        return productService.findById(id);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update (@RequestBody @Valid UpdateProductCmd cmd) throws ServiceException {
        productService.update(cmd);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) throws ServiceException{
        productService.delete(id);
    }

    @GetMapping(value = "/name/{name}")
    @ResponseBody
    public List<ProductResult> findByName(@RequestParam String name) throws ServiceException {
        return productService.findByName(name);
    }

    @GetMapping(value = "/price/{price}")
    @ResponseBody
    public List<ProductResult> findByPrice(@PathVariable BigDecimal price) throws ServiceException {
        return productService.findByPrice(price);
    }

    @GetMapping(value = "/quantity/{quantity}")
    @ResponseBody
    public List<ProductResult> findByQuantity(@PathVariable int quantity) throws ServiceException {
        return productService.findByQuantity(quantity);
    }

    @GetMapping(value = "/category/{category}")
    @ResponseBody
    public List<ProductResult> findByCategory(@PathVariable String category) throws ServiceException {
        return productService.findByCategory(category);
    }
}
