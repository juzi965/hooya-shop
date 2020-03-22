package top.hooya.shop.dao.extend;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.hooya.shop.pojo.SysMenu;
import top.hooya.shop.pojo.extend.UserInfoExtend;

import java.util.List;

/**
 * @author juzi9
 * @date 2020-03-17 18:17
 */
@Repository
public interface UserInfoExtendDao {
    List<UserInfoExtend> selectUserInfoByKeyWord(@Param("keyWord")String keyWord);

    List<SysMenu> selectMenuListByUserId(@Param("userId")Integer userId);
}
