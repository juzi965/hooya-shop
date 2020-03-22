package top.hooya.shop.pojo.extend;

import top.hooya.shop.pojo.ClothingAttr;
import top.hooya.shop.pojo.ClothingInfo;
import top.hooya.shop.pojo.PictureInfo;

import java.util.List;


/**
 * @author juzi9
 * @date 2020-03-01 21:18
 */
public class ClothingInfoExtend extends ClothingInfo {


    private List<ClothingAttr> clothingAttrList;

    private List<PictureInfo> pictureInfoList;


    public List<ClothingAttr> getClothingAttrList() {
        return clothingAttrList;
    }

    public void setClothingAttrList(List<ClothingAttr> clothingAttrList) {
        this.clothingAttrList = clothingAttrList;
    }

    public List<PictureInfo> getPictureInfoList() {
        return pictureInfoList;
    }

    public void setPictureInfoList(List<PictureInfo> pictureInfoList) {
        this.pictureInfoList = pictureInfoList;
    }
}
