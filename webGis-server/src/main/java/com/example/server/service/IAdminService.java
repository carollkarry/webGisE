package com.example.server.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.server.pojo.Admin;
import com.example.server.pojo.RespBean;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author carollkarry
 * @since 2022-06-25
 */

public interface IAdminService extends IService<Admin> {

    /**
     * 登录之后返回token
     * @param name
     * @param password
     * @param code
     * @param request
     * @return
     */
    RespBean login(String name, String password, String code, HttpServletRequest request);


    Admin getAdminByUserName(String name);

    RespBean register(Admin admin, HttpServletRequest request);
}
