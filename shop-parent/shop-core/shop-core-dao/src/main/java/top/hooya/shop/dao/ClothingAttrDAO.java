package top.hooya.shop.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.hooya.shop.pojo.ClothingAttr;
import top.hooya.shop.pojo.ClothingAttrExample;

@Repository
public interface ClothingAttrDAO {
    long countByExample(ClothingAttrExample example);

    int deleteByExample(ClothingAttrExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ClothingAttr record);

    int insertSelective(ClothingAttr record);

    List<ClothingAttr> selectByExample(ClothingAttrExample example);

    ClothingAttr selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ClothingAttr record, @Param("example") ClothingAttrExample example);

    int updateByExample(@Param("record") ClothingAttr record, @Param("example") ClothingAttrExample example);

    int updateByPrimaryKeySelective(ClothingAttr record);

    int updateByPrimaryKey(ClothingAttr record);
}