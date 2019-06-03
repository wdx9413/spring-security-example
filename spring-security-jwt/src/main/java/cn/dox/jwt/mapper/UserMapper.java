package cn.dox.jwt.mapper;

import cn.dox.jwt.model.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author: weidx
 * @date: 2019/6/4
 */

@Repository
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
