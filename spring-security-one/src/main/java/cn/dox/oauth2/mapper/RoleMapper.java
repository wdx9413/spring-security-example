package cn.dox.oauth2.mapper;

import cn.dox.oauth2.model.Role;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: weidx
 * @date: 2019/6/1
 */
@Repository
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    @Select("select * from role inner join user_role on role.id=user_role.role_id and user_role.user_id=#{userId}")
    List<Role> selectListByUserId(Long userId);
}
