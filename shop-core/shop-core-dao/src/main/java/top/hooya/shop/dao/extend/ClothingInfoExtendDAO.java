package top.hooya.shop.dao.extend;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.hooya.shop.pojo.extend.ClothingInfoExtend;

import java.util.List;

@Repository
public interface ClothingInfoExtendDAO {
    List<ClothingInfoExtend> selectClothing(@Param("id")Integer id);

    List<ClothingInfoExtend> selectClothingByCategory(@Param("name")String name);
}