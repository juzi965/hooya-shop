package top.hooya.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.hooya.shop.common.pojo.ClothingInfoVo;
import top.hooya.shop.common.utils.PropertiesUtil;
import top.hooya.shop.dao.ClothingAttrDAO;
import top.hooya.shop.dao.ClothingInfoDAO;
import top.hooya.shop.dao.PictureInfoDAO;
import top.hooya.shop.dao.extend.ClothingInfoExtendDAO;
import top.hooya.shop.pojo.*;
import top.hooya.shop.pojo.extend.ClothingInfoExtend;
import top.hooya.shop.service.ClothingInfoService;

import java.util.Date;
import java.util.List;

/**
 * @author juzi9
 * @date 2020-03-01 20:57
 */
@Service
public class ClothingInfoServiceImpl implements ClothingInfoService {

    @Autowired
    private ClothingInfoDAO clothingInfoDAO;
    @Autowired
    private PictureInfoDAO pictureInfoDAO;
    @Autowired
    private ClothingAttrDAO clothingAttrDAO;

    @Autowired
    private ClothingInfoExtendDAO clothingInfoExtendDAO;
    @Override
    public List<ClothingInfoExtend> getClothingByCategory(String name) {
        if ("全部".equals(name)){
            name = null;
        }
        return clothingInfoExtendDAO.selectClothingByCategory(name);
    }

    @Override
    public ClothingInfoExtend getClothingById(Integer id) {
        return clothingInfoExtendDAO.selectClothing(id).get(0);
    }

    @Override
    public int savePicture(Integer clothingId, String fileName,String filePath) {
        PictureInfo pictureInfo = new PictureInfo();
        pictureInfo.setClothingId(clothingId);
        pictureInfo.setName(fileName);
        pictureInfo.setFileDomain(PropertiesUtil.FILE_DOMAIN);
        pictureInfo.setPath(filePath+"/"+fileName);
        PictureInfoExample example = new PictureInfoExample();
        example.createCriteria().andClothingIdEqualTo(clothingId).andPathEqualTo(pictureInfo.getPath()).andDelFlagNotEqualTo(PropertiesUtil.DEL_FLAG);
        long count = pictureInfoDAO.countByExample(example);
        // 判断文件是否已存在
        if (count>0){
            return 1;
        }
        return pictureInfoDAO.insertSelective(pictureInfo);
    }

    @Override
    public ClothingInfo saveClothing(ClothingInfoVo vo) {
        ClothingInfo clothingInfo = new ClothingInfo();
        clothingInfo.setClothingName(vo.getClothingName());
        clothingInfo.setCategory(vo.getCategory());
        clothingInfo.setClothingContent(vo.getClothingContent());
        clothingInfo.setCreateTime(new Date());
        int count = clothingInfoDAO.insertSelective(clothingInfo);
        if (count>0) {
            for (ClothingAttr clothingAttr : vo.getAttrList()) {
                clothingAttr.setClothingId(clothingInfo.getId());
                clothingAttr.setCreateTime(new Date());
                clothingAttrDAO.insertSelective(clothingAttr);
            }

        }
        return clothingInfo;
    }

    @Override
    public int deleteClothingInfoById(Integer id) {
        // 删除服装信息
        ClothingInfo clothingInfo = new ClothingInfo();
        clothingInfo.setId(id);
        clothingInfo.setDelFlag(PropertiesUtil.DEL_FLAG);
        int count = clothingInfoDAO.updateByPrimaryKeySelective(clothingInfo);
        // 删除相关属性
        ClothingAttrExample attrExample = new ClothingAttrExample();
        attrExample.createCriteria().andClothingIdEqualTo(id);
        ClothingAttr clothingAttr = new ClothingAttr();
        clothingAttr.setDelFlag(PropertiesUtil.DEL_FLAG);
        clothingAttrDAO.updateByExampleSelective(clothingAttr,attrExample);
        // 删除相关图片
        PictureInfoExample pictureInfoExample = new PictureInfoExample();
        pictureInfoExample.createCriteria().andClothingIdEqualTo(id);
        PictureInfo pictureInfo = new PictureInfo();
        pictureInfo.setDelFlag(PropertiesUtil.DEL_FLAG);
        pictureInfoDAO.updateByExampleSelective(pictureInfo,pictureInfoExample);

        return count;
    }

    @Override
    public int editClothing(ClothingInfoVo vo) {
        ClothingInfo clothingInfo = new ClothingInfo();
        clothingInfo.setId(vo.getClothingId());
        clothingInfo.setClothingName(vo.getClothingName());
        clothingInfo.setCategory(vo.getCategory());
        clothingInfo.setClothingContent(vo.getClothingContent());
        int count = clothingInfoDAO.updateByPrimaryKeySelective(clothingInfo);
        if (count>0) {
            for (ClothingAttr clothingAttr : vo.getAttrList()) {
                if (clothingAttr.getId()==null){
                    clothingAttr.setClothingId(vo.getClothingId());
                    clothingAttr.setCreateTime(new Date());
                    clothingAttrDAO.insertSelective(clothingAttr);
                }else{
                    clothingAttrDAO.updateByPrimaryKeySelective(clothingAttr);
                }
            }
        }
        return count;
    }

    @Override
    public int deletePicture(Integer picId) {
        PictureInfo pictureInfo = new PictureInfo();
        pictureInfo.setId(picId);
        pictureInfo.setDelFlag(PropertiesUtil.DEL_FLAG);
        return pictureInfoDAO.updateByPrimaryKeySelective(pictureInfo);
    }


}
