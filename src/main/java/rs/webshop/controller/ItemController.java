package rs.webshop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rs.webshop.domain.Item;
import rs.webshop.dto.item.CreateItemCmd;
import rs.webshop.dto.item.ItemInfo;
import rs.webshop.dto.item.ItemResult;
import rs.webshop.dto.item.UpdateItemCmd;
import rs.webshop.exception.ServiceException;
import rs.webshop.service.ItemService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {

    private ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping()
    @PreAuthorize("hasAuthority('ADMIN')")
    public Item save(@RequestBody @Valid CreateItemCmd cmd) throws ServiceException {
        return itemService.save(cmd);
    }

    @GetMapping()
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @ResponseBody
    public List<ItemResult> findAll() {
        return itemService.findAll();
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ItemInfo findById(@PathVariable Long id) {
        return itemService.findById(id);
    }

    @PutMapping()
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody @Valid UpdateItemCmd cmd) throws ServiceException {
        itemService.update(cmd);
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) throws ServiceException {
        itemService.delete(id);
    }
}
