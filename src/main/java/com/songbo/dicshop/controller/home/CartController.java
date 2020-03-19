package com.songbo.dicshop.controller.home;

import com.songbo.dicshop.entity.DsCart;
import com.songbo.dicshop.entity.DsGoodsInfo;
import com.songbo.dicshop.service.DsCartService;
import com.songbo.dicshop.service.DsGoodsInfoService;
import com.songbo.dicshop.service.DsGoodsService;
import com.songbo.dicshop.utils.ResultUtil;
import com.songbo.dicshop.utils.TimeUtil;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName cartController
 * @Description TODO
 * @Author songbo
 * @Date 2020/2/19 下午11:48
 **/
@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private DsCartService dsCartService;
    @Autowired
    private DsGoodsInfoService dsGoodsInfoService;

    @PostMapping("/addCart")
    public ResultUtil addcart(@RequestHeader("userId") @ApiParam(value = "请求头中用户id") int id,
                              @RequestParam("goodsInfoId") @ApiParam(value = "商品详情id")int gid,
                              @RequestParam("num") @ApiParam(value = "商品数量")int num) {
        DsGoodsInfo infoById = dsGoodsInfoService.getInfoById(gid);
        if (infoById.getDsGoodsInfoNum() < num) {
            return ResultUtil.result400("库存不足", num);
        }
        DsCart dsCart = new DsCart(num, num * infoById.getDsGoodsInfoPrice(), 1, TimeUtil.getNow(), gid, id);
        if (dsCartService.savecart(dsCart)) {
            return ResultUtil.result200("yes", dsCart);
        } else {
            return ResultUtil.result500("添加失败！", null);
        }
    }
    @PostMapping("/deleteCart")
    public ResultUtil addcart(@RequestHeader("userId") @ApiParam(value = "请求头中用户id") int id,
                              @RequestParam("cartId") @ApiParam(value = "商品数量")int caetId) {

        /*if (dsCartService.savecart(caetId)) {
            return ResultUtil.result200("yes", caetId);
        } else {
            return ResultUtil.result500("添加失败！", null);
        }*/
        return null;
    }
}
