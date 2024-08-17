package rs.webshop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import rs.webshop.domain.Category;
import rs.webshop.dto.category.CategoryInfo;
import rs.webshop.dto.category.CategoryResult;
import rs.webshop.dto.category.CreateCategoryCmd;
import rs.webshop.dto.category.UpdateCategoryCmd;
import rs.webshop.exception.ServiceException;
import rs.webshop.service.CategoryService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping()
    public Category save(@RequestBody @Valid CreateCategoryCmd cmd) throws ServiceException {
        return categoryService.save(cmd);
    }

    @GetMapping()
    @ResponseBody
    public List<CategoryResult> findAll() {
        return categoryService.findAll();
    }

    @GetMapping(value = "/{id}")
    public CategoryInfo findById(@PathVariable long id) {
        return categoryService.findById(id);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody @Valid UpdateCategoryCmd cmd) throws ServiceException {
        categoryService.update(cmd);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) throws ServiceException {
        categoryService.delete(id);
    }

}
