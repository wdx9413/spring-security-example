package cn.dox.jwt.mapper;

import cn.dox.jwt.model.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: weidx
 * @date: 2019/6/4
 */

@Repository
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 根据userId获取拥有的角色
     * @param id
     * @return
     */
    @Select("select id, name from user_role inner join role on user_role.user_id=#{id} and user_role.role_id=role.id ")
    List<Role> selectListByUserId(Long id);
}
