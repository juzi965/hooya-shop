package top.hooya.shop.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.hooya.shop.pojo.DictTable;
import top.hooya.shop.pojo.DictTableExample;

@Repository
public interface DictTableDAO {
    long countByExample(DictTableExample example);

    int deleteByExample(DictTableExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DictTable record);

    int insertSelective(DictTable record);

    List<DictTable> selectByExample(DictTableExample example);

    DictTable selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DictTable record, @Param("example") DictTableExample example);

    int updateByExample(@Param("record") DictTable record, @Param("example") DictTableExample example);

    int updateByPrimaryKeySelective(DictTable record);

    int updateByPrimaryKey(DictTable record);
}