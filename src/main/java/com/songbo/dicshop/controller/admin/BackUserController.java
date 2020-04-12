package com.songbo.dicshop.controller.admin;

import com.songbo.dicshop.entity.DsAddr;
import com.songbo.dicshop.entity.DsUser;
import com.songbo.dicshop.service.DsUserService;
import com.songbo.dicshop.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName BackUserController
 * @Description TODO
 * @Author songbo
 * @Date 2020/3/26 下午3:31
 **/
@RestController
@RequestMapping("/admin")
@Api("后台用户管理")
public class BackUserController {

    @Autowired
    private DsUserService dsUserService;

    @PostMapping("/ban")
    public ResultUtil banUser(@RequestParam("userId") @ApiParam("需要封禁的用户") int userId) {
        try {
            DsUser user = dsUserService.getUserById(userId);
            user.setDsUserStatus(0);
            dsUserService.updateById(user);
            return ResultUtil.result200("封禁成功！", userId);
        } catch (Exception e) {
            return ResultUtil.result500("服务器出错！", null);
        }
    }

}
