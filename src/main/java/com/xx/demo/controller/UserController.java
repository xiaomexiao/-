package com.xx.demo.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.xx.demo.entity.User;
import com.xx.demo.mapper.UserMapper;
import com.xx.demo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author admin
 * @since 2019-01-22
 */
@Controller
@RequestMapping("/user")
@Api(value = "user",  tags = "用户模块")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    public static HttpSession session;

    public static String phone;

    @ApiOperation(value = "用户添加")
    @RequestMapping(value = "addUser",method = RequestMethod.POST)
    @ResponseBody
    public String addUser(@RequestBody User user){
        //这里的id用了mysql的主键自增长,不是oracle那种，所以没必要再给id赋值了
        return userService.insert(user) ? "{\"msg\":\"成功\"}": "{\"msg\":\"失败\"}";
    }

    @ApiOperation(value = "用户查询(phone)")
    @RequestMapping(value = "selectByPhone",method = RequestMethod.POST)
    @ResponseBody
    public Object selectByPhone(@RequestBody User user, HttpServletRequest request){
        User user1 = userService.selectByPhone(user);
        if (user1 != null){
            if(user1.getPassword().equals(user.getPassword())){
                session = request.getSession();
                session.setAttribute("user",user1);
                //获取当前登陆用户的名字
                phone = user.getPhone();
                //  System.out.println("xx"+session.getAttribute("username"));
                return "{\"msg\":\"登陆成功\"}";
            }else {
                return "{\"msg\":\"密码错误\"}";
            }
        }else {
            return "{\"msg\":\"账号不存在\"}";
        }
    }

    @ApiOperation(value = "用户注册验证,手机号是否已经重复")
    @RequestMapping(value = "registerUser",method = RequestMethod.POST)
    @ResponseBody
    public Object registerUser(@RequestBody User user){
        List<User> userList = userService.registerUser(user);
        if(userList.size() > 0){
            return "{\"msg\":\"该手机号已经被注册!\"}";
        }else {
                  userMapper.insert(user);
                  return "{\"msg\":\"yes\"}";
        }
    }

    @RequestMapping(value = "getName")
    @ResponseBody
    public String getName(HttpServletRequest request){
        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            return user.getName();
        }else {
            return "no";
        }
    }

    @RequestMapping(value = "list")
    @ResponseBody
    public List<User> getUserList(@RequestBody User user){
        if (user.getName() == null || user.getName().equals("")){
            return userMapper.selectList(null);
        }else {
            EntityWrapper<User> ew = new EntityWrapper<>();
            ew.like("name",user.getName());
            return userMapper.selectList(ew);
        }
    }

    @RequestMapping(value = "deleteUser")
    @ResponseBody
    public String deleteUser(@RequestBody User user){
        userMapper.deleteById(user.getId());
        return "{\"msg\":\"删除成功!\"}";
    }
}

