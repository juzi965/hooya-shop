package top.hooya.shop.common.pojo;

import top.hooya.shop.pojo.ClothingAttr;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author juzi9
 * @date 2020-03-13 13:57
 */
public class ClothingInfoVo {
    private Integer clothingId;

    @NotBlank(message = "{clothingInfoVo.clothingName.null}")
    @Size(min = 2,max = 20,message = "{clothingInfoVo.clothingName.size}")
    private String clothingName;

    @NotBlank(message = "{clothingInfoVo.category.null}")
    @Size(min = 2,max = 20,message = "{clothingInfoVo.category.size}")
    private String category;

    @NotBlank(message = "{clothingInfoVo.clothingContent.null}")
    @Size(min = 2,max = 50,message = "{clothingInfoVo.clothingContent.size}")
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
