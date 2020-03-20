package top.hooya.shop.common.pojo;


import java.util.Date;

/**
 * @author juzi9
 * @date 2020-03-16 13:08
 */
public class StatisticalData {
    private Date currentDate;
    private Integer orderVolume;
    private Integer salesAmount;
    private Integer salesVolume;

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    public Integer getOrderVolume() {
        return orderVolume;
    }

    public void setOrderVolume(Integer orderVolume) {
        this.orderVolume = orderVolume;
    }

    public Integer getSalesAmount() {
        return salesAmount;
    }

    public void setSalesAmount(Integer salesAmount) {
        this.salesAmount = salesAmount;
    }

    public Integer getSalesVolume() {
        return salesVolume;
    }

    public void setSalesVolume(Integer salesVolume) {
        this.salesVolume = salesVolume;
    }

    @Override
    public String toString() {
        return "StatisticalData{" +
                "currentDate=" + currentDate +
                ", orderVolume=" + orderVolume +
                ", salesAmount=" + salesAmount +
                ", salesVolume=" + salesVolume +
                '}';
    }
}
