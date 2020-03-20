package top.hooya.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.hooya.shop.common.pojo.StatisticalData;
import top.hooya.shop.dao.ClothingAttrDAO;
import top.hooya.shop.dao.OrderInfoDAO;
import top.hooya.shop.dao.OrderItemDAO;
import top.hooya.shop.dao.extend.OrderInfoExtendDAO;
import top.hooya.shop.service.OtherService;

import java.util.List;

/**
 * @author juzi9
 * @date 2020-03-16 13:05
 */
@Service
public class OtherServiceImpl  implements OtherService {


    @Autowired
    private OrderInfoExtendDAO orderInfoExtendDAO;

    @Override
    public StatisticalData getTodayData() {

        return orderInfoExtendDAO.selectTodayStatisticalData();
    }

    @Override
    public List<StatisticalData> getMonthData() {
        return orderInfoExtendDAO.selectMonthStatisticalData();
    }
}
