package cn.dox.oauth2.mapper;

import cn.dox.oauth2.model.Permission;
import cn.dox.oauth2.model.RolePermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: weidx
 * @date: 2019/6/1
 */
@Repository
@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {

    @Select("select role.name as roleName, permission.url, permission.name as permissionName from role " +
            "left join role_permission on role.id=role_permission.role_id " +
            "left join permission on permission.id=role_permission.permission_id")
    List<RolePermission> selectRolePermissionList();
}
