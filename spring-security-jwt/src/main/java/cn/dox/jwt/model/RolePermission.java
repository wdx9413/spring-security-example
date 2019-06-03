package cn.dox.jwt.model;

import lombok.Data;

/**
 * @author: weidx
 * @date: 2019/6/4
 */

@Data
public class RolePermission {
    private String roleName;
    private String url;
    private String permissionName;
}
