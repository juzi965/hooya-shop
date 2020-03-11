package top.hooya.shop.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.hooya.shop.pojo.ClothingInfo;
import top.hooya.shop.pojo.ClothingInfoExample;

@Repository
public interface ClothingInfoDAO {
    long countByExample(ClothingInfoExample example);

    int deleteByExample(ClothingInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ClothingInfo record);

    int insertSelective(ClothingInfo record);

    List<ClothingInfo> selectByExample(ClothingInfoExample example);

    ClothingInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ClothingInfo record, @Param("example") ClothingInfoExample example);

    int updateByExample(@Param("record") ClothingInfo record, @Param("example") ClothingInfoExample example);

    int updateByPrimaryKeySelective(ClothingInfo record);

    int updateByPrimaryKey(ClothingInfo record);
}