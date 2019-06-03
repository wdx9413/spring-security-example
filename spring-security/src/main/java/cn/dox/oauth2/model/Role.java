package cn.dox.oauth2.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author: weidx
 * @date: 2019/6/1
 */
@Data
public class Role implements GrantedAuthority {
    private Long id;
    private String name;

    @Override
    public String getAuthority() {
        return name;
    }
}
