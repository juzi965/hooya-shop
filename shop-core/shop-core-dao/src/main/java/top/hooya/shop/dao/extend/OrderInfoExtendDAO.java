package top.hooya.shop.dao.extend;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.hooya.shop.pojo.extend.OrderInfoExtend;

import java.util.List;

/**
 * @author juzi9
 * @date 2020-03-04 16:46
 */
@Repository
public interface OrderInfoExtendDAO {
    List<OrderInfoExtend> selectOrderByUserId(@Param("userId")Integer userId);

    OrderInfoExtend selectOrderByOrderId(@Param("orderId")String orderId);
}
