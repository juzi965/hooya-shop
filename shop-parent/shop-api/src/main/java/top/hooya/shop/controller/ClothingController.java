package top.hooya.shop.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.hooya.shop.common.pojo.ClothingInfoVo;
import top.hooya.shop.common.pojo.UserLoginToken;
import top.hooya.shop.common.result.Result;
import top.hooya.shop.common.utils.DateUtils;
import top.hooya.shop.common.utils.PropertiesUtil;
import top.hooya.shop.pojo.ClothingAttr;
import top.hooya.shop.pojo.ClothingInfo;
import top.hooya.shop.pojo.extend.ClothingInfoExtend;
import top.hooya.shop.pojo.extend.OrderInfoExtend;
import top.hooya.shop.service.ClothingInfoService;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author juzi9
 * @date 2020-03-01 20:52
 */
@RestController
@RequestMapping("/api/clothing")
public class ClothingController {
    @Autowired
    private ClothingInfoService clothingInfoService;

    @GetMapping("/category/{name}/{pageNum}/{pageSize}")
    public Result getClothing(@PathVariable("name") String name, @PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize){
        PageHelper.startPage(pageNum,pageSize,"create_time desc");
        List<ClothingInfoExtend> clothingInfoList = clothingInfoService.getClothingByCategory(name);
        PageInfo<ClothingInfoExtend> pageInfo = new PageInfo<>(clothingInfoList);
        return Result.success(pageInfo);
    }
    @GetMapping("/{id}")
    public Result getClothingById(@PathVariable("id") Integer id){
        ClothingInfoExtend clothingInfo = clothingInfoService.getClothingById(id);
        return Result.success(clothingInfo);
    }
    @UserLoginToken
    @PostMapping("/add")
    public Result saveClothing(@RequestBody @Validated  ClothingInfoVo vo) {
        ClothingInfo clothingInfo = clothingInfoService.saveClothing(vo);
        return clothingInfo!=null?Result.success(clothingInfo.getId()):Result.error("服装信息保存失败");
    }
    @UserLoginToken
    @PostMapping("/edit")
    public Result editClothing(@RequestBody @Validated ClothingInfoVo vo) {
        int count = clothingInfoService.editClothing(vo);
        return count>0?Result.success(count):Result.error("服装信息保存失败");
    }
    @UserLoginToken
    @PostMapping("/delete")
    public Result deleteClothingInfoById(Integer id) {
        int count = clothingInfoService.deleteClothingInfoById(id);
        return count>0?Result.success(count):Result.error("删除失败");
    }
    @UserLoginToken
    @PostMapping("/delete-file")
    public Result deleteFile(Integer picId){
        int count = clothingInfoService.deletePicture(picId);
        return count>0?Result.success(count):Result.error("删除失败");
    }
    @PostMapping("/upload")
    public Result saveFile(Integer clothingId,MultipartFile[] files) throws IOException {
        int count = 0;
        String dirPath = DateUtils.getCurrDate("yyyy-MM-dd");
        String filePath = "";//自定义上传路径
        //遍历文件
        if(files != null && files.length>0){
            for(MultipartFile item : files){
                String fileName = item.getOriginalFilename();//获取文件名
                String ext = "";
                if (fileName.contains(".")){
                    ext = fileName.substring(fileName.lastIndexOf(".")+1);
                    if ("jpg".equals(ext)||"png".equals(ext)||"jpeg".equals(ext)){
                        filePath= "/picture/"+dirPath;
                    }
                }else{
                    filePath= "/other/"+dirPath;
                }
                File file = new File(PropertiesUtil.FILE_PATH+filePath,fileName);
                if(!file.exists()){//判断文件是否存在，如果不存在则创建，存在则删除
                    file.mkdirs();
                }else{
                    file.delete();
                }
                item.transferTo(file);//上传文件
                count += clothingInfoService.savePicture(clothingId,fileName,filePath);
            }
        }
        return count>0? Result.success(count):Result.error("上传文件失败");
    }


}
