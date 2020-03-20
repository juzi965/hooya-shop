package top.hooya.shop.dao.extend;

import org.springframework.stereotype.Repository;
import top.hooya.shop.common.pojo.SysRoleVo;

import java.util.List;

/**
 * @author juzi9
 * @date 2020-03-18 10:39
 */
@Repository
public interface SysRoleExtendDAO {
    List<SysRoleVo> selectRoleMenu(String keyWord);
}
