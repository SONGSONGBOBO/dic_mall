package com.songbo.dicshop.controller.home;

import com.songbo.dicshop.entity.DsUser;
import com.songbo.dicshop.service.DsUserService;
import com.songbo.dicshop.utils.BcryptUtil;
import com.songbo.dicshop.utils.JwtUtil;
import com.songbo.dicshop.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName UserController
 * @Description TODO
 * @Author songbo
 * @Date 2020/2/13 下午10:05
 **/
@RestController
@RequestMapping("/user")
@Slf4j
@Api(value = "用户操作", tags = {"用户"})
public class UserController {

    @Autowired
    private DsUserService dsUserService;


    @PostMapping("/login")
    @ApiOperation(value = "login")
    public ResultUtil login(@RequestParam("tel") String tel,
                            @RequestParam("pwd") String pwd) {

        DsUser userByTel = dsUserService.getUserByTel(tel);
        if (null == userByTel) {
            return ResultUtil.result400("当前用户未注册！", tel);
        }

        if (BcryptUtil.decryption(pwd, userByTel.getDsUserPwd())) {
            try {
                String jwt = JwtUtil.createJWT(userByTel.getDsUserName());
                return ResultUtil.result200("验证通过", jwt);
            } catch (Exception e) {
                log.error("create jwt fail", e);
                return ResultUtil.result500("生成token失败！", e);
            }
        } else {
            return ResultUtil.result400("密码错误！", pwd);
        }
    }

    @PostMapping("/register")
    @ApiOperation(value = "register")
    public ResultUtil register(@RequestParam("tel") String tel,
                            @RequestParam("pwd") String pwd) {

        DsUser userByTel = dsUserService.getUserByTel(tel);
        if (null != userByTel) {
            return ResultUtil.result400("当前用户已注册！", tel);
        }

        /** 手机短信验证*/

        DsUser dsUser = new DsUser(
                tel, tel, BcryptUtil.encryption(pwd), 1, 0);

        if (dsUserService.saveUser(dsUser)) {
            return ResultUtil.result200("注册成功!", dsUser);
        } else {
            return ResultUtil.result500("注册失败！", null);
        }
    }
}
