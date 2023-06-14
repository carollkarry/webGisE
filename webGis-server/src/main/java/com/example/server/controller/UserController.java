package com.example.server.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.server.mapper.CitycountMapper;
import com.example.server.mapper.UserMapper;
import com.example.server.pojo.User;
import com.example.server.service.ICitycountService;
import com.example.server.service.IUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author carollkarry
 * @since 2023-06-12
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService iUserService;

    @Autowired
    private UserMapper userMapper;

    @ApiOperation(value = "用户登录")
    @GetMapping("/UserSignIn")
    public User userSignIn(@RequestParam String id, String password){
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("id",id).eq("password", password);
        List<User> theUser= userMapper.selectList(queryWrapper);
        return theUser.get(0);
    }

    @ApiOperation(value = "用户注册")
    @GetMapping("/UserSignUp")
    public boolean userSignUp(@RequestParam String id, String password) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id); // 判断用户名是否存在
        if (userMapper.selectCount(wrapper) > 0) {
            return false;
            //throw new RuntimeException("用户名已存在");
        }
        User user=new User();
        user.setId(id);
        user.setPassword(password);
        userMapper.insert(user); // 插入用户数据
        return true;
    }

    @ApiOperation(value = "用户信息查看")
    @GetMapping("/UserGetInfo")
    public User userGetInfo(@RequestParam String id){
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("id",id);
        List<User> theUser= userMapper.selectList(queryWrapper);
        return theUser.get(0);
    }

    @ApiOperation(value = "用户信息更改")
    @GetMapping("/UserSpaceInfo")
    public boolean userSpaceInfo(@RequestParam String id, String username, String password, String sex, String jianjie) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        User user=new User(id, username, password, sex, jianjie);//更新后的user
        wrapper.eq("id", id);

        int count = userMapper.update(user, wrapper);
        if (count != 1) {
            return false;
            //throw new RuntimeException("用户名已存在");
        }
        return true;
    }

//    @ApiOperation(value = "获取所有用户")
//    @GetMapping("/UserAll")
//    public List<User> userAll(){
//        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
//        List<User> theUser= userMapper.selectList(queryWrapper);
//        return theUser;
//    }

    @ApiOperation(value = "用户密码更改")
    @GetMapping("/UserChangePwd")
    public boolean userChangePwd(@RequestParam String id, String newPwd, String oldPwd) {
        // 创建UpdateWrapper对象，指定更新内容
        UpdateWrapper<User> updateWrapper = Wrappers.update();
        updateWrapper.eq("id", id).eq("password", oldPwd).set("password", newPwd);

        // 调用update方法执行更新操作
        int rows = userMapper.update(null, updateWrapper);

        // 判断更新是否成功
        if (rows > 0) {
            System.out.println("更新成功");
            return true;
        }
        System.out.println("更新失败");
        return false;
    }
}
