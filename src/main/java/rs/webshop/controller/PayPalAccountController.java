package rs.webshop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rs.webshop.domain.PayPalAccount;
import rs.webshop.dto.payPalAccount.CreatePayPalAccountCmd;
import rs.webshop.dto.payPalAccount.PayPalAccountInfo;
import rs.webshop.dto.payPalAccount.PayPalAccountResult;
import rs.webshop.dto.payPalAccount.UpdatePayPalAccountCmd;
import rs.webshop.exception.ServiceException;
import rs.webshop.service.PayPalAccountService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/paypalaccount")
public class PayPalAccountController {

    private final PayPalAccountService payPalAccountService;

    public PayPalAccountController(PayPalAccountService payPalAccountService) {
        this.payPalAccountService = payPalAccountService;
    }

    @PostMapping()
    @PreAuthorize("hasAuthority('ADMIN')")
    public PayPalAccount save(@RequestBody @Valid CreatePayPalAccountCmd cmd) throws ServiceException {
        return payPalAccountService.save(cmd);
    }

    @GetMapping()
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @ResponseBody
    public List<PayPalAccountResult> findAll() {
        return payPalAccountService.findAll();
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public PayPalAccountInfo findById(@PathVariable Long id) {
        return payPalAccountService.findById(id);
    }

    @PutMapping()
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody @Valid UpdatePayPalAccountCmd cmd) throws ServiceException {
        payPalAccountService.update(cmd);
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) throws ServiceException {
        payPalAccountService.delete(id);
    }
}
