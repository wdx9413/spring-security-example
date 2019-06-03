package cn.dox.oauth2.service.impl;

import cn.dox.oauth2.mapper.RoleMapper;
import cn.dox.oauth2.mapper.UserMapper;
import cn.dox.oauth2.model.Role;
import cn.dox.oauth2.model.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: weidx
 * @date: 2019/6/1
 */
@Service("userDetailService")
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    RoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.selectOne(new QueryWrapper<User>().lambda().eq(User::getUsername, username));
        if (user != null) {
            List<Role> roles = roleMapper.selectListByUserId(user.getId());
            user.setAuthorities(roles);
        }
        return user;
    }
}
