package cn.dox.jwt.config;

import cn.dox.jwt.mapper.PermissionMapper;
import cn.dox.jwt.model.RolePermission;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author: weidx
 * @date: 2019/6/4
 */

@Component
public class MyInvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private PermissionMapper permissionMapper;

    /**
     * 每一个资源所需要的角色 Collection<ConfigAttribute>决策器会用到
     */
    private static HashMap<String, Collection<ConfigAttribute>> map = Maps.newHashMap();


    /**
     * 返回请求的资源需要的角色
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        //object 中包含用户请求的request 信息
        HttpServletRequest request = ((FilterInvocation) o).getHttpRequest();
        for (Map.Entry<String, Collection<ConfigAttribute>> entry : map.entrySet()) {
            String url = entry.getKey();
            if (new AntPathRequestMatcher( url ).matches( request )) {
                return entry.getValue();
            }
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        //初始化 所有资源 对应的角色
        loadResourceDefine();
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

    /**
     * 初始化 所有资源 对应的角色
     */
    public void loadResourceDefine() {
        //权限资源 和 角色对应的表  也就是 角色权限 中间表
        List<RolePermission> rolePermissons = permissionMapper.getRolePermissions();

        //某个资源 可以被哪些角色访问
        for (RolePermission rolePermisson : rolePermissons) {

            String url = rolePermisson.getUrl();
            String roleName = rolePermisson.getRoleName();
            ConfigAttribute role = new SecurityConfig(roleName);

            if(map.containsKey(url)){
                map.get(url).add(role);
            }else{
                List<ConfigAttribute> list =  new ArrayList<>();
                list.add( role );
                map.put( url , list );
            }
        }
    }


}
