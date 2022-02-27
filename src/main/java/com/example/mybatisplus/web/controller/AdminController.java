package com.example.mybatisplus.web.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.service.AdminService;
import com.example.mybatisplus.model.domain.Admin;

import java.util.List;


/**
 *
 *  前端控制器
 *
 *
 * @author lxp
 * @since 2021-04-19
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    private final Logger logger = LoggerFactory.getLogger( AdminController.class );

    @Autowired
    private AdminService adminService;

    /**
    * 描述：根据Id 查询
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse getById(@PathVariable("id") Long id)throws Exception {
        Admin  admin =  adminService.getById(id);
        return JsonResponse.success(admin);
    }

    /**
    * 描述：根据Id删除
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonResponse deleteById(@PathVariable("id") Long id) throws Exception {
        adminService.removeById(id);
        return JsonResponse.success(null);
    }


    /**
    * 描述：根据Id 更新
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public JsonResponse updateAdmin(@PathVariable("id") Long  id,Admin  admin) throws Exception {
        admin.setId(id);
        adminService.updateById(admin);
        return JsonResponse.success(null);
    }


    /**
    * 描述:创建Admin
    *
    */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse create(Admin  admin) throws Exception {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        admin.setPassword(encoder.encode(admin.getPassword()));
        adminService.save(admin);
        return JsonResponse.success(null);
    }
    @RequestMapping("/list")
    @ResponseBody
    public JsonResponse list(Model model,
                             @RequestParam(required = false,defaultValue = "1") Integer pageNo,
                             @RequestParam(required = false,defaultValue = "10")Integer pageSize){
        Page<Admin> page = adminService.page(new Page<>(pageNo, pageSize));

        return JsonResponse.success(page);
    }

    @RequestMapping("/deleteByIds")
    @ResponseBody
    public JsonResponse deleteByIds(@RequestParam("ids[]")List<Integer> ids){
        adminService.removeByIds(ids);
        return JsonResponse.success( null);
    }
}

