package com.songbo.dicshop.controller.home;

import com.alibaba.fastjson.JSONObject;
import com.songbo.dicshop.entity.DsAddr;
import com.songbo.dicshop.entity.DsUser;
import com.songbo.dicshop.entity.DsUserInfo;
import com.songbo.dicshop.exception.AddrException;
import com.songbo.dicshop.mapper.DsAddrMapper;
import com.songbo.dicshop.service.DsUserService;
import com.songbo.dicshop.service.TransactionService;
import com.songbo.dicshop.utils.BcryptUtil;
import com.songbo.dicshop.utils.JwtUtil;
import com.songbo.dicshop.utils.NumberUtil;
import com.songbo.dicshop.utils.ResultUtil;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

import javax.annotation.Resource;
import java.util.List;

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
    @Autowired
    private TransactionService transactionService;
    @Resource
    private DsAddrMapper dsAddrMapper;



    @PostMapping("/login")
    @ApiOperation(value = "login， 成功返回Token字符串")
    public ResultUtil login(@RequestParam("tel") @ApiParam("手机号") String tel,
                            @RequestParam("pwd") @ApiParam("密码") String pwd) {

        DsUser userByTel = dsUserService.getUserByTel(tel);
        if (null == userByTel) {
            return ResultUtil.result400("当前用户未注册！", tel);
        }

        if (BcryptUtil.decryption(pwd, userByTel.getDsUserPwd())) {
            try {
                String jwt = JwtUtil.createJWT(userByTel.getDsUserName());
                String addr = transactionService.createNewAddr(userByTel.getDsUserId());
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("token", jwt);
                jsonObject.put("userId", userByTel.getDsUserId());
                jsonObject.put("addr", addr);
                return ResultUtil.result200("验证通过", jsonObject);
            } catch (AddrException a){
                return ResultUtil.result200("验证通过,积分账号未生成", a.getMessage());
            }
            catch (Exception e) {
                log.error("create jwt fail", e);
                return ResultUtil.result500("生成token失败！", e);
            }
        } else {
            return ResultUtil.result400("密码错误！", pwd);
        }
    }



    @PostMapping("/register")
    @ApiOperation(value = "register")
    public ResultUtil register(@RequestParam("tel") @ApiParam("手机号") String tel,
                            @RequestParam("pwd") @ApiParam("密码") String pwd) {

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
    /**
      *@Description TODO createNewAddr
      *@param
      *@return
    **/
    @GetMapping("/createNewAddr")
    @ApiOperation(value = "创建新地址，已有地址返回原地址,返回{”addr“：”“}")
    public ResultUtil createNewAddr(@RequestHeader("userId") @ApiParam("header中的userid") int userId) {

        try {
            String newAddr = transactionService.createNewAddr(userId);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("addr", newAddr);
            return ResultUtil.result200("success", jsonObject);
        } catch (Exception e) {
            return ResultUtil.result500("节点服务器错误,请联系管理员",e.getMessage());
        }
    }

    /**
     *@Description TODO 创建地址
     *@param
     *@return
     **/
    @GetMapping("/recharge")
    @ApiOperation(value = "充值，显示地址和余额给用户进行充值，弄个页面刷新,返回{”addr“：”“，“amount”：“”}")
    public ResultUtil recharge(@RequestHeader("userId") @ApiParam("header中的userid") int userId) {

        try {

            String addr = transactionService.getAddr(userId);
            if (addr == null) {
                return ResultUtil.result400("用户未设置addr", null);
            }
            //Double amount = transactionService.getAmount(userId);
            DsAddr dsAddr = dsUserService.getByUserId(userId);
            String s = NumberUtil.formatDouble(dsAddr.getDsAddrAmount());
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("addr", addr);
            jsonObject.put("amount", s);
            return ResultUtil.result200("success", jsonObject);
        } catch (AddrException e) {
            return ResultUtil.result500(e.getMessage(),e.getMessage());
        } catch (Exception e) {
            return ResultUtil.result500("节点服务器错误",e.getMessage());
        }
    }

    /**
      *@Description TODO
      *@param
      *@return
    **/
    @GetMapping("/getUserInfo")
    @ApiOperation(value = "获取用户详细信息，地址等")
    public ResultUtil getUserInfo(@RequestHeader("userId") @ApiParam("header中的userid") int userId){
        try {
            List<DsUserInfo> userInfoByUserId = dsUserService.getUserInfoByUserId(userId);
            return ResultUtil.result200("success", userInfoByUserId);
        } catch (Exception e) {
            return ResultUtil.result500("fail", e);
        }
    }

    @PostMapping("/setUserInfo")
    @ApiOperation(value = "设置用户详细信息，地址等")
    public ResultUtil setUserInfo(@RequestHeader("userId") @ApiParam("header中的userid") int userId,
                                  @RequestBody DsUserInfo dsUserInfo){
        try {
            dsUserInfo.setDsUserInfoUserId(userId);
            dsUserService.setUserInfo(dsUserInfo);
            return ResultUtil.result200("success", null);
        } catch (Exception e) {
            return ResultUtil.result500("fail", e);
        }
    }
    @PostMapping("/deleteUserInfo")
    @ApiOperation(value = "删除用户详细信息，地址等")
    public ResultUtil deleteUserInfo(@RequestHeader("userId") @ApiParam("header中的userid") int userId,
                                  @RequestParam("userInfoId") @ApiParam("需要删除的userInfoId") int userInfoId){
        try {
            DsUserInfo dsUserInfo = dsUserService.getUserInfoByUserInfoId(userInfoId);
            if (dsUserInfo.getDsUserInfoUserId() == userId) {
                dsUserService.deleteUserInfo(userInfoId);
                return ResultUtil.result200("success", null);
            }
            return ResultUtil.result400("所删除的信息不属于当前用户", null);
        } catch (Exception e) {
            return ResultUtil.result500("fail", e);
        }
    }


    @PostMapping("/updateUserInfo")
    @ApiOperation(value = "更新用户详细信息，地址等")
    public ResultUtil updateUserInfo(@RequestHeader("userId") @ApiParam("header中的userid") int userId,
                                     @RequestBody DsUserInfo dsUserInfo){
        try {
            dsUserInfo.setDsUserInfoUserId(userId);
            dsUserService.updateUserInfoByUserInfoId(dsUserInfo);
            return ResultUtil.result200("success", null);
        } catch (Exception e) {
            return ResultUtil.result500("fail", e);
        }
    }

}
