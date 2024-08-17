package rs.saga.obuka.sagashop.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import rs.saga.obuka.sagashop.domain.Role;
import rs.saga.obuka.sagashop.dto.role.CreateRoleCmd;
import rs.saga.obuka.sagashop.dto.role.RoleInfo;
import rs.saga.obuka.sagashop.dto.role.RoleResult;
import rs.saga.obuka.sagashop.dto.role.UpdateRoleCmd;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-17T14:16:41+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.10 (Oracle Corporation)"
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
