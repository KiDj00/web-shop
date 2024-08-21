package rs.webshop.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import rs.webshop.domain.PayPalAccount;
import rs.webshop.domain.User;
import rs.webshop.dto.payPalAccount.CreatePayPalAccountCmd;
import rs.webshop.dto.user.CreateUserCmd;
import rs.webshop.dto.user.PayPalAccountInfo;
import rs.webshop.dto.user.UpdateUserCmd;
import rs.webshop.dto.user.UserInfo;
import rs.webshop.dto.user.UserResult;
import rs.webshop.dto.user.UserView;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-21T14:35:13+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.11 (Amazon.com Inc.)"
)
public class UserMapperImpl implements UserMapper {

    @Override
    public User createUserCmdToUser(CreateUserCmd cmd) {
        if ( cmd == null ) {
            return null;
        }

        User user = new User();

        user.setPayPalAccount( createPayPalAccountCmdToPayPalAccount( cmd.getPayPalAccount() ) );
        user.setUsername( cmd.getUsername() );
        user.setPassword( cmd.getPassword() );
        user.setFirstName( cmd.getFirstName() );
        user.setLastName( cmd.getLastName() );

        return user;
    }

    @Override
    public List<UserResult> listUserToListUserResult(List<User> users) {
        if ( users == null ) {
            return null;
        }

        List<UserResult> list = new ArrayList<UserResult>( users.size() );
        for ( User user : users ) {
            list.add( userToUserResult( user ) );
        }

        return list;
    }

    @Override
    public UserInfo userToUserInfo(User user) {
        if ( user == null ) {
            return null;
        }

        UserInfo userInfo = new UserInfo();

        userInfo.setPayPalAccountInfo( payPalAccountToPayPalAccountInfo( user.getPayPalAccount() ) );
        userInfo.setId( user.getId() );
        userInfo.setUsername( user.getUsername() );
        userInfo.setPassword( user.getPassword() );
        userInfo.setFirstName( user.getFirstName() );
        userInfo.setLastName( user.getLastName() );

        return userInfo;
    }

    @Override
    public void updateUserCmdToUser(User user, UpdateUserCmd cmd) {
        if ( cmd == null ) {
            return;
        }

        user.setId( cmd.getId() );
        user.setUsername( cmd.getUsername() );
        user.setPassword( cmd.getPassword() );
        user.setFirstName( cmd.getFirstName() );
        user.setLastName( cmd.getLastName() );
        if ( cmd.getPayPalAccount() != null ) {
            if ( user.getPayPalAccount() == null ) {
                user.setPayPalAccount( new PayPalAccount() );
            }
            payPalAccountInfoToPayPalAccount( cmd.getPayPalAccount(), user.getPayPalAccount() );
        }
        else {
            user.setPayPalAccount( null );
        }
    }

    @Override
    public UserView userToUserView(User user) {
        if ( user == null ) {
            return null;
        }

        UserView userView = new UserView();

        userView.setId( user.getId() );
        userView.setUsername( user.getUsername() );

        return userView;
    }

    @Override
    public CreateUserCmd userToCreateUserCmd(User user) {
        if ( user == null ) {
            return null;
        }

        CreateUserCmd createUserCmd = new CreateUserCmd();

        createUserCmd.setUsername( user.getUsername() );
        createUserCmd.setPassword( user.getPassword() );
        createUserCmd.setFirstName( user.getFirstName() );
        createUserCmd.setLastName( user.getLastName() );
        createUserCmd.setPayPalAccount( payPalAccountToCreatePayPalAccountCmd( user.getPayPalAccount() ) );

        return createUserCmd;
    }

    protected PayPalAccount createPayPalAccountCmdToPayPalAccount(CreatePayPalAccountCmd createPayPalAccountCmd) {
        if ( createPayPalAccountCmd == null ) {
            return null;
        }

        PayPalAccount payPalAccount = new PayPalAccount();

        payPalAccount.setAccountNumber( createPayPalAccountCmd.getAccountNumber() );
        payPalAccount.setBudget( createPayPalAccountCmd.getBudget() );
        payPalAccount.setLanguage( createPayPalAccountCmd.getLanguage() );
        payPalAccount.setExpiresOn( createPayPalAccountCmd.getExpiresOn() );
        payPalAccount.setBillingAddress( createPayPalAccountCmd.getBillingAddress() );

        return payPalAccount;
    }

    protected UserResult userToUserResult(User user) {
        if ( user == null ) {
            return null;
        }

        UserResult userResult = new UserResult();

        userResult.setId( user.getId() );
        userResult.setUsername( user.getUsername() );
        userResult.setPassword( user.getPassword() );
        userResult.setFirstName( user.getFirstName() );
        userResult.setLastName( user.getLastName() );

        return userResult;
    }

    protected PayPalAccountInfo payPalAccountToPayPalAccountInfo(PayPalAccount payPalAccount) {
        if ( payPalAccount == null ) {
            return null;
        }

        PayPalAccountInfo payPalAccountInfo = new PayPalAccountInfo();

        payPalAccountInfo.setId( payPalAccount.getId() );
        payPalAccountInfo.setAccountNumber( payPalAccount.getAccountNumber() );
        payPalAccountInfo.setBudget( payPalAccount.getBudget() );
        payPalAccountInfo.setLanguage( payPalAccount.getLanguage() );
        payPalAccountInfo.setExpiresOn( payPalAccount.getExpiresOn() );
        payPalAccountInfo.setBillingAddress( payPalAccount.getBillingAddress() );

        return payPalAccountInfo;
    }

    protected void payPalAccountInfoToPayPalAccount(PayPalAccountInfo payPalAccountInfo, PayPalAccount mappingTarget) {
        if ( payPalAccountInfo == null ) {
            return;
        }

        mappingTarget.setId( payPalAccountInfo.getId() );
        mappingTarget.setAccountNumber( payPalAccountInfo.getAccountNumber() );
        mappingTarget.setBudget( payPalAccountInfo.getBudget() );
        mappingTarget.setLanguage( payPalAccountInfo.getLanguage() );
        mappingTarget.setExpiresOn( payPalAccountInfo.getExpiresOn() );
        mappingTarget.setBillingAddress( payPalAccountInfo.getBillingAddress() );
    }

    protected CreatePayPalAccountCmd payPalAccountToCreatePayPalAccountCmd(PayPalAccount payPalAccount) {
        if ( payPalAccount == null ) {
            return null;
        }

        CreatePayPalAccountCmd createPayPalAccountCmd = new CreatePayPalAccountCmd();

        createPayPalAccountCmd.setAccountNumber( payPalAccount.getAccountNumber() );
        createPayPalAccountCmd.setBudget( payPalAccount.getBudget() );
        createPayPalAccountCmd.setLanguage( payPalAccount.getLanguage() );
        createPayPalAccountCmd.setExpiresOn( payPalAccount.getExpiresOn() );
        createPayPalAccountCmd.setBillingAddress( payPalAccount.getBillingAddress() );

        return createPayPalAccountCmd;
    }
}
