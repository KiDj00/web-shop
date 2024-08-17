package rs.webshop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import rs.webshop.domain.PayPalAccount;
import rs.webshop.dto.payPalAccount.CreatePayPalAccountCmd;
import rs.webshop.dto.payPalAccount.PayPalAccountInfo;
import rs.webshop.dto.payPalAccount.PayPalAccountResult;
import rs.webshop.dto.payPalAccount.UpdatePayPalAccountCmd;

import java.util.List;

@Mapper
public interface PayPalAccountMapper {

    PayPalAccountMapper INSTANCE = Mappers.getMapper(PayPalAccountMapper.class);

    @Mapping(target = "billingAddress", source = "billingAddress")
    PayPalAccount createPayPalAccountCmdToPayPalAccount(CreatePayPalAccountCmd cmd);

    List<PayPalAccountResult> listPayPalToListPayPalAccountResult(List<PayPalAccount> list);

    @Mapping(target = "userInfo", source = "user")
    PayPalAccountInfo paypalToPayPalInfo(PayPalAccount payPalAccount);

    void updatePayPalAccountCmd(@MappingTarget PayPalAccount paypal, UpdatePayPalAccountCmd cmd);
}
