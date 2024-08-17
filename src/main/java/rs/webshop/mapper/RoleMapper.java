package rs.webshop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import rs.webshop.domain.Role;
import rs.webshop.dto.role.CreateRoleCmd;
import rs.webshop.dto.role.RoleInfo;
import rs.webshop.dto.role.RoleResult;
import rs.webshop.dto.role.UpdateRoleCmd;

import java.util.List;

@Mapper
public interface RoleMapper {
    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    Role createRoleCmdToRole(CreateRoleCmd cmd);

    List<RoleResult> listRoleToListRoleResult(List<Role> roles);

    RoleInfo roleToRoleInfo(Role role);

    void updateRoleCmdToRole(@MappingTarget Role role, UpdateRoleCmd cmd);
}
