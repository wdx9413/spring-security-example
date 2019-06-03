package cn.dox.oauth2.service.impl;

import cn.dox.oauth2.mapper.PermissionMapper;
import cn.dox.oauth2.model.Permission;
import cn.dox.oauth2.model.RolePermission;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Max;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * @author: weidx
 * @date: 2019/6/1
 */
@Component
public class InvocationSecurityMetadataSourceServiceImpl implements FilterInvocationSecurityMetadataSource {

    @Autowired
    PermissionMapper permissionMapper;

    public static HashMap<String, Collection<ConfigAttribute>> map = null;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        if (map == null) {
            loadResourceDefine();
        }
        if (o instanceof FilterInvocation) {
            HttpServletRequest request = ((FilterInvocation) o).getHttpRequest();
            for (String key : map.keySet()) {
                String url = key;
                if (new AntPathRequestMatcher(url).matches(request)) {
                    return map.get(url);
                }
            }
        }
        return null;
    }

    public void loadResourceDefine() {
        map = Maps.newHashMap();
        List<RolePermission> rolePermissionList = permissionMapper.selectRolePermissionList();
        rolePermissionList.forEach(rolePermission -> {
            String url = rolePermission.getUrl();
            String roleName = rolePermission.getRoleName();
            ConfigAttribute configAttribute = new SecurityConfig(roleName);
            if (map.containsKey(url)) {
                map.get(url).add(configAttribute);
            } else {
                List<ConfigAttribute> list = Lists.newArrayList(configAttribute);
                map.put(url, list);
            }
        });
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

}
