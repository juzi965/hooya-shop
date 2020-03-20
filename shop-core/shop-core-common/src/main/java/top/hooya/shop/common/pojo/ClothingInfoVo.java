package top.hooya.shop.common.pojo;

import top.hooya.shop.pojo.ClothingAttr;

import java.util.List;

/**
 * @author juzi9
 * @date 2020-03-13 13:57
 */
public class ClothingInfoVo {
    private Integer clothingId;
    private String clothingName;
    private String category;
    private String clothingContent;

    private List<ClothingAttr> attrList;


    public Integer getClothingId() {
        return clothingId;
    }

    public void setClothingId(Integer clothingId) {
        this.clothingId = clothingId;
    }

    public String getClothingName() {
        return clothingName;
    }

    public void setClothingName(String clothingName) {
        this.clothingName = clothingName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getClothingContent() {
        return clothingContent;
    }

    public void setClothingContent(String clothingContent) {
        this.clothingContent = clothingContent;
    }

    public List<ClothingAttr> getAttrList() {
        return attrList;
    }

    public void setAttrList(List<ClothingAttr> attrList) {
        this.attrList = attrList;
    }
}
