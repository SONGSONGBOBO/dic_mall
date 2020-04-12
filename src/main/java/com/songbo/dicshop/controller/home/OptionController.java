package com.songbo.dicshop.controller.home;

import com.songbo.dicshop.entity.DsOption;
import com.songbo.dicshop.service.DsOptionService;
import com.songbo.dicshop.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName OptionController
 * @Description TODO
 * @Author songbo
 * @Date 2020/3/11 下午1:18
 **/
@RestController
@Api("网站信息")
public class OptionController {

    @Autowired
    private DsOptionService dsOptionService;

    @GetMapping("/getOption")
    @ApiOperation("获取轮播图")
    public ResultUtil getOption() {
        try {
            String imgs = dsOptionService.getImgs();
            return ResultUtil.result200("success", imgs);
        } catch (Exception e) {
            return ResultUtil.result500("fail", e.getMessage());
        }
    }

    @GetMapping("/getOptionInfo")
    @ApiOperation("获取系统设置")
    public ResultUtil getOptionInfo() {
        try {
            DsOption dsOption = dsOptionService.getDsOption();
            return ResultUtil.result200("success", dsOption);
        } catch (Exception e) {
            return ResultUtil.result500("fail", e.getMessage());
        }
    }

    @GetMapping("/updateOptionInfo")
    @ApiOperation("更新系统设置")
    public ResultUtil updateOptionInfo(@RequestBody DsOption dsOption) {
        try {
            dsOption.setDsOptionId(1);
            dsOptionService.updateById(dsOption);
            return ResultUtil.result200("success", null);
        } catch (Exception e) {
            return ResultUtil.result500("fail", e.getMessage());
        }
    }
}
