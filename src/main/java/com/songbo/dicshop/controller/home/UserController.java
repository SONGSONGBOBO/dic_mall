package com.songbo.dicshop.controller.home;

import com.alibaba.fastjson.JSONObject;
import com.songbo.dicshop.bean.coinpayments.CommonResponse;
import com.songbo.dicshop.bean.payments.CreatePaymentRequest;
import com.songbo.dicshop.bean.payments.CreatePaymentResult;
import com.songbo.dicshop.entity.*;
import com.songbo.dicshop.exception.AddrException;
import com.songbo.dicshop.mapper.DsAddrMapper;
import com.songbo.dicshop.service.*;
import com.songbo.dicshop.utils.BcryptUtil;
import com.songbo.dicshop.utils.JwtUtil;
import com.songbo.dicshop.utils.NumberUtil;
import com.songbo.dicshop.utils.ResultUtil;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.implementation.bind.annotation.Default;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

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
    @Autowired
    private PaymentsService paymentsService;
    @Autowired
    private DsAddrInfoService dsAddrInfoService;
    @Autowired
    private DsOptionService dsOptionService;

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
                DsAddr dsAddr = dsAddrMapper.getByUserId(userByTel.getDsUserId());
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("token", jwt);
                jsonObject.put("userId", userByTel.getDsUserId());
                jsonObject.put("addr", dsAddr.getDsAddrAddr());
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
                            @RequestParam("pwd") @ApiParam("密码") String pwd,
                               @RequestParam(value = "code", required = false) @ApiParam("邀请码") String code,
                               @RequestParam(value = "mail", required = false) @ApiParam("邮箱") String mail) {

        DsUser userByTel = dsUserService.getUserByTel(tel);
        if (null != userByTel) {
            return ResultUtil.result400("当前用户已注册！", tel);
        }

        //积分奖励
        DsOption dsOption = dsOptionService.getDsOption();
        double integralinit = dsOption.getDsOptionRegisterIntegral();
        if (code != null && code != "") {
            if (dsUserService.getUserByCode(code) == null) {
                return ResultUtil.result400("邀请码不正确!", code);
            }
            integralinit += dsOption.getDsOptionInviteIntegral();
        }

        String uuid = UUID.randomUUID().toString().replace("-","" );

        /** 手机短信验证*/
        DsUser dsUser = new DsUser(
                tel, tel, BcryptUtil.encryption(pwd), 1, 0, uuid, code, mail);

        if (dsUserService.saveUser(dsUser)) {
            try {

                transactionService.createNewAddr(dsUserService.getUserByTel(tel).getDsUserId(), integralinit);

            } catch (Exception e) {
                return ResultUtil.result500("创建地址失败！", e.getMessage());
            }
            return ResultUtil.result200("注册成功!", dsUser);
        } else {
            return ResultUtil.result500("注册失败！", null);
        }
    }

    @PostMapping("/sendMessage")
    @ApiOperation(value = "发送短信")
    public ResultUtil sendMessage(@RequestParam("tel") @ApiParam("手机号") String tel) {

        return ResultUtil.result200("发送成功!", tel);
    }

    /**
      *@Description TODO createNewAddr
      *@param
      *@return
    **/
    /*@GetMapping("/createNewAddr")
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
    }*/

    /**
     *@Description TODO 创建地址
     *@param
     *@return
     **/
    /*@GetMapping("/recharge")
    @ApiOperation(value = "dic充值，显示地址和余额给用户进行充值,返回{”addr“：”“，“amount”：“”}")
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
        } catch (Exception e) {
            return ResultUtil.result500("节点服务器错误",e.getMessage());
        }
    }*/

    /**
     *@Description TODO 创建地址
     *@param
     *@return
     **/
    @PostMapping("/recharge")
    @ApiOperation(value = "显示地址和余额给用户进行充值,返回{”addr“：”“，“amount”：“”}")
    public ResultUtil recharge(@RequestHeader("userId") @ApiParam("header中的userid") int userId,
                               @RequestParam("amount") @ApiParam("充值积分数量") double amount,
                                       @RequestParam("tokenCate") @ApiParam("充值代币类型,0为dic，1为usdt") int tokenCate) {

        String addr = transactionService.getAddr(userId);
        if (addr == null) {
            /*double balance = 0.0;

            transactionService.createNewAddr(userId, )*/
            return ResultUtil.result400("用户未设置addr", null);
        }
        switch (tokenCate) {
            case 0:
                try {
                    //Double amount = transactionService.getAmount(userId);
                    DsAddr dsAddr = dsUserService.getByUserId(userId);
                    String s = NumberUtil.formatDouble(dsAddr.getDsAddrAmount());
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("addr", addr);
                    jsonObject.put("amount", s);
                    return ResultUtil.result200("success", jsonObject);
                } catch (Exception e) {
                    return ResultUtil.result500("节点服务器错误",e.getMessage());
                }
            case 1:
                String time = String.valueOf(System.currentTimeMillis());
                try {
                    JSONObject jsonObject = transactionService.rechaege(1, amount, userId);
                    return ResultUtil.result200("success", jsonObject);
                } catch (Exception e) {
                    return ResultUtil.result500("服务器错误",e.getMessage());
                }
            default:
                return ResultUtil.result400("请求有误",null);
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
