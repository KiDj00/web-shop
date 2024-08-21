package rs.webshop.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import rs.webshop.domain.Role;
import rs.webshop.dto.role.CreateRoleCmd;
import rs.webshop.dto.role.RoleInfo;
import rs.webshop.dto.role.RoleResult;
import rs.webshop.dto.role.UpdateRoleCmd;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-21T14:35:13+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.11 (Amazon.com Inc.)"
)
public class RoleMapperImpl implements RoleMapper {

    @Override
    public Role createRoleCmdToRole(CreateRoleCmd cmd) {
        if ( cmd == null ) {
            return null;
        }

        Role role = new Role();

        role.setName( cmd.getName() );
        role.setDescription( cmd.getDescription() );

        return role;
    }

    @Override
    public List<RoleResult> listRoleToListRoleResult(List<Role> roles) {
        if ( roles == null ) {
            return null;
        }

        List<RoleResult> list = new ArrayList<RoleResult>( roles.size() );
        for ( Role role : roles ) {
            list.add( roleToRoleResult( role ) );
        }

        return list;
    }

    @Override
    public RoleInfo roleToRoleInfo(Role role) {
        if ( role == null ) {
            return null;
        }

        RoleInfo roleInfo = new RoleInfo();

        roleInfo.setId( role.getId() );
        roleInfo.setName( role.getName() );
        roleInfo.setDescription( role.getDescription() );

        return roleInfo;
    }

    @Override
    public void updateRoleCmdToRole(Role role, UpdateRoleCmd cmd) {
        if ( cmd == null ) {
            return;
        }

        role.setId( cmd.getId() );
        role.setName( cmd.getName() );
        role.setDescription( cmd.getDescription() );
    }

    protected RoleResult roleToRoleResult(Role role) {
        if ( role == null ) {
            return null;
        }

        RoleResult roleResult = new RoleResult();

        roleResult.setId( role.getId() );
        roleResult.setName( role.getName() );
        roleResult.setDescription( role.getDescription() );

        return roleResult;
    }
}
