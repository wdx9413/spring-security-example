package cn.dox.oauth2.config;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @author: weidx
 * @date: 2019/6/2
 */

@Component
public class MyAccessDecisionManager implements AccessDecisionManager {
    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
        if (collection == null || collection.size() <= 0) {
            return;
        } else {
            String needRole;
            for (ConfigAttribute configAttribute : collection) {
                needRole = configAttribute.getAttribute();
                for (GrantedAuthority authority : authentication.getAuthorities()) {
                    if (needRole.trim().equals(authentication.getAuthorities())) {
                        return;
                    }
                }
            }
            throw new AccessDeniedException("当前访问没有权限");
        }
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
