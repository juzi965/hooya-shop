package top.hooya.shop.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.hooya.shop.pojo.AddressInfo;
import top.hooya.shop.pojo.AddressInfoExample;

@Repository
public interface AddressInfoDAO {
    long countByExample(AddressInfoExample example);

    int deleteByExample(AddressInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AddressInfo record);

    int insertSelective(AddressInfo record);

    List<AddressInfo> selectByExample(AddressInfoExample example);

    AddressInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AddressInfo record, @Param("example") AddressInfoExample example);

    int updateByExample(@Param("record") AddressInfo record, @Param("example") AddressInfoExample example);

    int updateByPrimaryKeySelective(AddressInfo record);

    int updateByPrimaryKey(AddressInfo record);
}