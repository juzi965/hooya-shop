package top.hooya.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.hooya.shop.common.result.Result;
import top.hooya.shop.pojo.DictTable;
import top.hooya.shop.service.DictTableService;

import java.util.List;

/**
 * @author juzi9
 * @date 2020-03-05 17:27
 */
@RestController
@RequestMapping("/api/dict")
public class DictController {

    @Autowired
    private DictTableService dictTableService;

    @GetMapping("/category")
    public Result getCategory() {
        List<DictTable> categoryList = dictTableService.getCategoryList();
        return Result.success(categoryList);
    }
}
