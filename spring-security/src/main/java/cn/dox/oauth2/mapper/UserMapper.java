package cn.dox.oauth2.mapper;

import cn.dox.oauth2.model.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author: weidx
 * @date: 2019/6/1
 */
@Repository
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
