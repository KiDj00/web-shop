package rs.saga.obuka.sagashop.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import rs.saga.obuka.sagashop.domain.PayPalAccount;
import rs.saga.obuka.sagashop.domain.User;
import rs.saga.obuka.sagashop.dto.payPalAccount.CreatePayPalAccountCmd;
import rs.saga.obuka.sagashop.dto.payPalAccount.PayPalAccountInfo;
import rs.saga.obuka.sagashop.dto.payPalAccount.PayPalAccountResult;
import rs.saga.obuka.sagashop.dto.payPalAccount.UpdatePayPalAccountCmd;
import rs.saga.obuka.sagashop.dto.payPalAccount.UserInfo;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-17T14:16:41+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.10 (Oracle Corporation)"
)
public class PayPalAccountMapperImpl implements PayPalAccountMapper {

    @Override
    public PayPalAccount createPayPalAccountCmdToPayPalAccount(CreatePayPalAccountCmd cmd) {
        if ( cmd == null ) {
            return null;
        }

        PayPalAccount payPalAccount = new PayPalAccount();

        payPalAccount.setBillingAddress( cmd.getBillingAddress() );
        payPalAccount.setAccountNumber( cmd.getAccountNumber() );
        payPalAccount.setBudget( cmd.getBudget() );
        payPalAccount.setLanguage( cmd.getLanguage() );
        payPalAccount.setExpiresOn( cmd.getExpiresOn() );

        return payPalAccount;
    }

    @Override
    public List<PayPalAccountResult> listPayPalToListPayPalAccountResult(List<PayPalAccount> list) {
        if ( list == null ) {
            return null;
        }

        List<PayPalAccountResult> list1 = new ArrayList<PayPalAccountResult>( list.size() );
        for ( PayPalAccount payPalAccount : list ) {
            list1.add( payPalAccountToPayPalAccountResult( payPalAccount ) );
        }

        return list1;
    }

    @Override
    public PayPalAccountInfo paypalToPayPalInfo(PayPalAccount payPalAccount) {
        if ( payPalAccount == null ) {
            return null;
        }

        PayPalAccountInfo payPalAccountInfo = new PayPalAccountInfo();

        payPalAccountInfo.setUserInfo( userToUserInfo( payPalAccount.getUser() ) );
        payPalAccountInfo.setId( payPalAccount.getId() );
        payPalAccountInfo.setAccountNumber( payPalAccount.getAccountNumber() );
        payPalAccountInfo.setBudget( payPalAccount.getBudget() );
        payPalAccountInfo.setLanguage( payPalAccount.getLanguage() );
        payPalAccountInfo.setExpiresOn( payPalAccount.getExpiresOn() );
        payPalAccountInfo.setBillingAddress( payPalAccount.getBillingAddress() );

        return payPalAccountInfo;
    }

    @Override
    public void updatePayPalAccountCmd(PayPalAccount paypal, UpdatePayPalAccountCmd cmd) {
        if ( cmd == null ) {
            return;
        }

        paypal.setId( cmd.getId() );
        paypal.setAccountNumber( cmd.getAccountNumber() );
        paypal.setBudget( cmd.getBudget() );
        paypal.setLanguage( cmd.getLanguage() );
        paypal.setExpiresOn( cmd.getExpiresOn() );
        paypal.setBillingAddress( cmd.getBillingAddress() );
    }

    protected PayPalAccountResult payPalAccountToPayPalAccountResult(PayPalAccount payPalAccount) {
        if ( payPalAccount == null ) {
            return null;
        }

        PayPalAccountResult payPalAccountResult = new PayPalAccountResult();

        payPalAccountResult.setId( payPalAccount.getId() );
        payPalAccountResult.setAccountNumber( payPalAccount.getAccountNumber() );
        payPalAccountResult.setBudget( payPalAccount.getBudget() );
        payPalAccountResult.setLanguage( payPalAccount.getLanguage() );
        payPalAccountResult.setExpiresOn( payPalAccount.getExpiresOn() );
        payPalAccountResult.setBillingAddress( payPalAccount.getBillingAddress() );

        return payPalAccountResult;
    }

    protected UserInfo userToUserInfo(User user) {
        if ( user == null ) {
            return null;
        }

        UserInfo userInfo = new UserInfo();

        userInfo.setId( user.getId() );
        userInfo.setFirstName( user.getFirstName() );
        userInfo.setLastName( user.getLastName() );

        return userInfo;
    }
}
