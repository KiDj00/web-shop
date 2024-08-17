package rs.webshop.service;

import rs.webshop.domain.PayPalAccount;
import rs.webshop.dto.payPalAccount.CreatePayPalAccountCmd;
import rs.webshop.dto.payPalAccount.PayPalAccountInfo;
import rs.webshop.dto.payPalAccount.PayPalAccountResult;
import rs.webshop.dto.payPalAccount.UpdatePayPalAccountCmd;
import rs.webshop.exception.ServiceException;

import java.util.List;

public interface PayPalAccountService {

    PayPalAccount save(CreatePayPalAccountCmd cmd) throws ServiceException;

    List<PayPalAccountResult> findAll();

    PayPalAccountInfo findById(Long id);

    void update(UpdatePayPalAccountCmd cmd) throws ServiceException;

    void delete(Long id) throws ServiceException;
}
