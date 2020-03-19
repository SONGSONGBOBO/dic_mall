package com.songbo.dicshop.controller.admin;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.songbo.dicshop.entity.DsGoods;
import com.songbo.dicshop.entity.DsGoodsInfo;
import com.songbo.dicshop.exception.UnpermittedFormatException;
import com.songbo.dicshop.service.DsGoodsInfoService;
import com.songbo.dicshop.service.DsGoodsService;
import com.songbo.dicshop.utils.CommonUtil;
import com.songbo.dicshop.utils.FileUtil;
import com.songbo.dicshop.utils.ImagesUtil;
import com.songbo.dicshop.utils.ResultUtil;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName BackGoodsController
 * @Description TODO
 * @Author songbo
 * @Date 2020/1/14 下午2:57
 **/
@RestController
@RequestMapping("/admin/goods")
@Api(value = "后台商品管理", tags = {"后台管理-商品"})
public class BackGoodsController {
    @Autowired
    private DsGoodsService dsGoodsService;
    @Autowired
    private DsGoodsInfoService dsGoodsInfoService;


    @ApiOperation(value = "添加商品", notes = "@RequestBody")
    /**@ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "商品名(非重复)", required = true, dataType = "String", paramType = "JSONObject"),
            @ApiImplicitParam(name = "info", value = "商品图片", required = true, dataType = "JSONArray", paramType = "JSONObject"),
            @ApiImplicitParam(name = "message", value = "商品描述参数等", required = true, dataType = "JSONArray", paramType = "JSONObject"),
            @ApiImplicitParam(name = "status", value = "商品状态(是否显示)", required = true, dataType = "Integer", paramType = "JSONObject")
    })*/
    @PostMapping("insertGoods")
    public ResultUtil insertDsGoods(@RequestBody DsGoods dsGoods){

        final DsGoods goodsByName = dsGoodsService.getGoodsByName(dsGoods.getDsGoodsName());
        if (goodsByName != null) {
            return ResultUtil.result400("Name already exists", goodsByName);
        }


        if (dsGoodsService.insertGoods(dsGoods)) {
            return ResultUtil.result200("insert success!", dsGoods);
        } else {
            return ResultUtil.result500("insert fail!", dsGoods);
        }
    }

    @PostMapping("/insertImgs")
    @ApiOperation(value = "上传图片，可多张上传", notes = "@MultipartRequest")
    public ResultUtil insertImgs(MultipartRequest file){
        List<MultipartFile> files = file.getFiles("file");
        for (MultipartFile f : files) {
            try {
                FileUtil.upload(f,CommonUtil.IMGS_URL);
            } catch (IOException e) {
                return ResultUtil.result500(e.getMessage(), f.getOriginalFilename());
            }
        }

        return ResultUtil.result200("success", null);
    }

    @GetMapping("/getImgs")
    @ApiOperation(value = "获取所有图片")
    public ResultUtil getImgs(){
        try {
            String[] fileNameListByPath = FileUtil.getFileNameListByPath(CommonUtil.IMGS_URL);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("fileList", fileNameListByPath);
            jsonObject.put("path", CommonUtil.IMGS_URL+"/");
            return ResultUtil.result200("success", jsonObject);
        } catch (Exception e) {
            return ResultUtil.result500(e.getMessage(),CommonUtil.IMGS_URL);
        }
    }

    @GetMapping("/deleteImgs")
    @ApiOperation(value = "删除图片s")
    public ResultUtil deleteImgs(@RequestParam("imgs") @ApiParam("图片数组") String imgs){
        try {
            List<String> list = Collections.singletonList(imgs);
            for (String s : list) {
                FileUtil.deleteFile(s);
            }
            return ResultUtil.result200("success", list);
        } catch (Exception e) {
            return ResultUtil.result500(e.getMessage(),e.getMessage());
        }
    }


   /* @ApiOperation(value = "添加商品图片和详情", notes = "file以map形式，key分别为index和info，value以数组保存file")
    public ResultUtil insertGoodsImgs(@RequestParam("pid") @ApiParam(value = "所属父商品id") String id, MultipartRequest file){
        List<MultipartFile> index = file.getFiles("index");
        List<MultipartFile> info = file.getFiles("info");
        String url = "/"+id+"/";

        try {
            List<String> imagesList = ImagesUtil.saveImagesList(index, CommonUtil.IMAGEURL + url);
            return ResultUtil.result200("上传成功！", imagesList);
        } catch (UnpermittedFormatException e) {
            return ResultUtil.result500(e.getMessage(), e);
        } catch (Exception e) {
            return ResultUtil.result500("上传失败!", null);
        }

    }

    @PostMapping("/insertGoodstest")
    public void insertGoodstest(@RequestParam("dsGoods") DsGoods dsGoods, MultipartRequest file){

        System.out.println(dsGoods);
        System.out.println(file.getFile("file").getOriginalFilename());
    }
*/
    //@PostMapping("/insertGoodsInfo")
    public ResultUtil insertGoodsInfo(@RequestBody DsGoodsInfo dsGoodsInfo){

        if (dsGoodsInfoService.getInfoByName(dsGoodsInfo.getDsGoodsInfoName()) != null) {
            return ResultUtil.result400("GoodsInfoName already exists", dsGoodsInfo.getDsGoodsInfoName());
        }

        if (dsGoodsInfoService.insertGoodsInfo(dsGoodsInfo)) {
            return ResultUtil.result200("insert success!", dsGoodsInfo);
        } else {
            return ResultUtil.result500("insert fail!", dsGoodsInfo);
        }
    }

    @PostMapping("/updateGoods")
    public ResultUtil updateGoods(@RequestBody DsGoods dsGoods){


        if (dsGoodsService.updateGoods(dsGoods)) {
            return ResultUtil.result200("update success!", dsGoods);
        } else {
            return ResultUtil.result500("update fail!", dsGoods);
        }
    }



    @PostMapping("/deleteGoods")
    public ResultUtil deleteGoods(@RequestParam("id") @ApiParam("商品id") int id){

        if (dsGoodsService.deleteGoods(id)) {
            return ResultUtil.result200("delete success!", id);
        } else {
            return ResultUtil.result500("delete fail!", id);
        }
    }

}
