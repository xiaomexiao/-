package com.xx.demo.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.xx.demo.controller.UserController;
import com.xx.demo.entity.User;
import com.xx.demo.mapper.UserMapper;
import com.xx.demo.service.UserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2019-01-22
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User selectByPhone(User user) {
        return userMapper.selectByPhone(user);
    }

    @Override
    public List<User> registerUser(User user) {
        EntityWrapper<User> ew = new EntityWrapper<>();
        ew.eq("phone",user.getPhone());
        return userMapper.selectList(ew);
    }

    @Override
    public String getSessionName() {
        if (UserController.session != null){
            User user = (User)UserController.session.getAttribute("user");
            return user.getName();
        }else {
            return null;
        }
    }

    @Override
    public User getUserByPhone(String phone) {
        EntityWrapper<User> ew = new EntityWrapper<>();
        ew.eq("phone",phone);
        return userMapper.selectList(ew).get(0);
    }
}
