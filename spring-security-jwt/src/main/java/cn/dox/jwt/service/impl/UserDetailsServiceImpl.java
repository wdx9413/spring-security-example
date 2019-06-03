package cn.dox.jwt.service.impl;

import cn.dox.jwt.mapper.RoleMapper;
import cn.dox.jwt.mapper.UserMapper;
import cn.dox.jwt.model.entity.Role;
import cn.dox.jwt.model.entity.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: weidx
 * @date: 2019/6/4
 */

@Service("userDetailService")
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    RoleMapper roleMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username != null && username.length() > 0) {
            User user = userMapper.selectOne(new QueryWrapper<User>().lambda().eq(User::getUsername, username));
            if (user != null) {
                List<Role> roleList = roleMapper.selectListByUserId(user.getId());
                user.setAuthorities(roleList);
            }
            return user;
        }
        return null;
    }
}
