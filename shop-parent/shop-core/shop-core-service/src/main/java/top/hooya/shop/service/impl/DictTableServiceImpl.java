package top.hooya.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.hooya.shop.common.utils.PropertiesUtil;
import top.hooya.shop.dao.DictTableDAO;
import top.hooya.shop.pojo.DictTable;
import top.hooya.shop.pojo.DictTableExample;
import top.hooya.shop.service.DictTableService;

import java.util.List;

/**
 * @author juzi9
 * @date 2020-03-05 17:30
 */
@Service
public class DictTableServiceImpl implements DictTableService {

    @Autowired
    private DictTableDAO dictTableDAO;

    @Override
    public List<DictTable> getCategoryList() {
        DictTableExample example = new DictTableExample();
        example.createCriteria().andTypeEqualTo(PropertiesUtil.DICT_CATEGORY).andDelFlagNotEqualTo(PropertiesUtil.DEL_FLAG);
        return dictTableDAO.selectByExample(example);
    }
}
