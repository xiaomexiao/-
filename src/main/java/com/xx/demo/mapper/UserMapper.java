package com.xx.demo.mapper;

import com.xx.demo.entity.User;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author admin
 * @since 2019-01-22
 */
public interface UserMapper extends BaseMapper<User> {

    public User selectByPhone(User user);
}
