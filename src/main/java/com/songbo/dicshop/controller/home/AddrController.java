package com.songbo.dicshop.controller.home;

import com.songbo.dicshop.service.DsUserService;
import com.songbo.dicshop.service.TransactionService;
import com.songbo.dicshop.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName AddrController
 * @Description TODO
 * @Author songbo
 * @Date 2020/3/11 下午3:34
 **/
@RestController
@RequestMapping("/addr")
@Api("用户地址")
public class AddrController {

    @Autowired
    private TransactionService transactionService;
    @Autowired
    private DsUserService dsUserService;

   /* @GetMapping("/getAddr")
    @ApiOperation("获取用户绑定的地址")
    public ResultUtil getAddr(@RequestHeader("userId") @ApiParam("请求头中的userId") int userId) {
        try {
            String addr = transactionService.getAddr(userId);
            return ResultUtil.result200("success", addr);
        } catch (Exception e) {
            return ResultUtil.result500("节点服务器down", null);
        }
    }

    @GetMapping("/getAmount")
    @ApiOperation("获取用户积分余额")
    public ResultUtil getAmount(@RequestHeader("userId") @ApiParam("请求头中的userId") int userId) {
        try {
            String addr = transactionService.getAddr(userId);
            if (addr == null) {
                return ResultUtil.result400("用户未设置addr", null);
            }

            Long amount = transactionService.getAmount(userId);
            Double d = Double.valueOf(amount/100000000);
            return ResultUtil.result200("success", d);
        } catch (Exception e) {
            return ResultUtil.result500(e.getMessage(), null);
        }
    }*/

    @GetMapping("/getAmount")
    @ApiOperation("获取用户积分余额")
    public ResultUtil getAmount(@RequestHeader("userId") @ApiParam("请求头中的userId") int userId) {
        try {
            String addr = transactionService.getAddr(userId);
            if (addr == null) {
                return ResultUtil.result400("用户未设置addr", null);
            }
            Double amount = dsUserService.getByUserId(userId).getDsAddrBalance();
            return ResultUtil.result200("success", amount);
        } catch (Exception e) {
            return ResultUtil.result500(e.getMessage(), null);
        }
    }
}
