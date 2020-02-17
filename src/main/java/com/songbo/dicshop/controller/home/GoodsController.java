package com.songbo.dicshop.controller.home;

import com.songbo.dicshop.bean.entityResults.GoodsInfosResult;
import com.songbo.dicshop.entity.DsGoods;
import com.songbo.dicshop.entity.DsGoodsInfo;
import com.songbo.dicshop.service.DsGoodsInfoService;
import com.songbo.dicshop.service.DsGoodsService;
import com.songbo.dicshop.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName GoodsController
 * @Description TODO
 * @Author songbo
 * @Date 2020/1/14 下午2:57
 **/
@RestController
@RequestMapping("/goods")
@Slf4j
public class GoodsController {
    @Autowired
    private DsGoodsService dsGoodsService;
    @Autowired
    private DsGoodsInfoService dsGoodsInfoService;

    @PostMapping("/getGoodsInfos/{id}")
    public ResultUtil getDoodsInfos(@PathVariable("id") int id){
        try {
            DsGoods goodsById = dsGoodsService.getDoodsById(id);
            List<DsGoodsInfo> goodsInfosByPid = dsGoodsInfoService.getGoodsInfosByPid(id);
            int views = (int) (System.currentTimeMillis()/1000000-1578000)+id;
            int nums = 0,init=0;
            for (DsGoodsInfo dsGoodsInfo : goodsInfosByPid){
                nums += dsGoodsInfo.getDsGoodsInfoNum();
                init += dsGoodsInfo.getDsGoodsInfoInit();
            }
            int sale = init - nums;
            return ResultUtil.result200("查询成功！", new GoodsInfosResult(goodsById, goodsInfosByPid, sale, nums, views));
        } catch (Exception e){
            log.error("getGoodsInfos {}", e);
            return ResultUtil.result500("服务器错误！", e.getMessage());
        }
    }
}
