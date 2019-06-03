package cn.dox.jwt.model.entity;

import lombok.Data;
import lombok.experimental.FieldNameConstants;
/**
 * @author: weidx
 * @date: 2019/6/3
 */
@Data
@FieldNameConstants
public class Permission {
    private Long id;
    private String url;
    private String name;
    private String description;
    private Long pid;
}
