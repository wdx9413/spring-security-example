package cn.dox.jwt.model.entity;

import lombok.Data;
import lombok.experimental.FieldNameConstants;
import org.springframework.security.core.GrantedAuthority;
/**
 * @author: weidx
 * @date: 2019/6/3
 */
@Data
@FieldNameConstants
public class Role implements GrantedAuthority {
    private Long id;
    private String name;

    @Override
    public String getAuthority() {
        return name;
    }
}
