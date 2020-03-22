package top.hooya.shop.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.hooya.shop.pojo.PictureInfo;
import top.hooya.shop.pojo.PictureInfoExample;

@Repository
public interface PictureInfoDAO {
    long countByExample(PictureInfoExample example);

    int deleteByExample(PictureInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PictureInfo record);

    int insertSelective(PictureInfo record);

    List<PictureInfo> selectByExample(PictureInfoExample example);

    PictureInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PictureInfo record, @Param("example") PictureInfoExample example);

    int updateByExample(@Param("record") PictureInfo record, @Param("example") PictureInfoExample example);

    int updateByPrimaryKeySelective(PictureInfo record);

    int updateByPrimaryKey(PictureInfo record);
}