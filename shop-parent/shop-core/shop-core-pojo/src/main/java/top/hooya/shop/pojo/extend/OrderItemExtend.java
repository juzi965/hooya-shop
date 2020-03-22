package top.hooya.shop.pojo.extend;

import top.hooya.shop.pojo.OrderItem;

/**
 * @author juzi9
 * @date 2020-03-04 16:40
 */
public class OrderItemExtend extends OrderItem {

    private String clothingName;

    private String clothingContent;

    private String category;

    public String getClothingName() {
        return clothingName;
    }

    public void setClothingName(String clothingName) {
        this.clothingName = clothingName;
    }

    public String getClothingContent() {
        return clothingContent;
    }

    public void setClothingContent(String clothingContent) {
        this.clothingContent = clothingContent;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
