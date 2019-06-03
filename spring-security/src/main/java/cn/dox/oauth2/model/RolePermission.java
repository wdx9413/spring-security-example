package cn.dox.oauth2.model;

import lombok.Data;

/**
 * @author: weidx
 * @date: 2019/6/2
 */
@Data
public class RolePermission {
    private String roleName;
    private String url;
    private String permissionName;
}
