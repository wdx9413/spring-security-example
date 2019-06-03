package cn.dox.oauth2.model;

import lombok.Data;

/**
 * @author: weidx
 * @date: 2019/6/1
 */
@Data
public class Permission {
    private Long id;
    private String url;
    private String name;
    private String description;
    private Long pid;
}
