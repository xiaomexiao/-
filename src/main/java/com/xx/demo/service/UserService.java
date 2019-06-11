package com.xx.demo.service;

import com.xx.demo.entity.User;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2019-01-22
 */
public interface UserService extends IService<User> {

    /**
     * 登录的验证<br>
     * 根据账号的用户名的phone来查找<br>
     * @param user
     * @return
     */
    public User selectByPhone(User user);

    /**
     * 用户注册时验证
     * @para1m user
     * @return
     */
    public List<User> registerUser(User user);

    /**
     * 取得session中的名字
     * @return
     */
    public String getSessionName();


    User getUserByPhone(String phone);

}
