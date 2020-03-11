package top.hooya.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.hooya.shop.common.utils.PropertiesUtil;
import top.hooya.shop.dao.ClothingInfoDAO;
import top.hooya.shop.dao.extend.ClothingInfoExtendDAO;
import top.hooya.shop.pojo.ClothingInfo;
import top.hooya.shop.pojo.ClothingInfoExample;
import top.hooya.shop.pojo.extend.ClothingInfoExtend;
import top.hooya.shop.service.ClothingInfoService;

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
}
