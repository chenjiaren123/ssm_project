package cn.itcast.dao;

import cn.itcast.domain.Orders;
import cn.itcast.domain.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface OrdersDao {
    /**
     * 查询所有订单信息
     *
     * @return
     * @throws Exception
     */
    @Select("select * from orders")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "orderNum", property = "orderNum"),
            @Result(column = "orderTime", property = "orderTime"),
            @Result(column = "orderStatus", property = "orderStatus"),
            @Result(column = "peopleCount", property = "peopleCount"),
            @Result(column = "payType", property = "payType"),
            @Result(column = "orderDesc", property = "orderDesc"),
            @Result(column = "productId", property = "product", javaType = Product.class,
                    one = @One(select = "cn.itcast.dao.ProductDao.findById"))
    })
    List<Orders> findAll() throws Exception;

    /**
     * 根据id查询订单
     * @param id
     * @return
     */
    @Select("select * from orders where id = #{id}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "orderNum", property = "orderNum"),
            @Result(column = "orderTime", property = "orderTime"),
            @Result(column = "orderStatus", property = "orderStatus"),
            @Result(column = "peopleCount", property = "peopleCount"),
            @Result(column = "payType", property = "payType"),
            @Result(column = "orderDesc", property = "orderDesc"),
            @Result(column = "productId",property = "product" ,javaType = Product.class,
                    one = @One(select = "cn.itcast.dao.ProductDao.findById")),
            @Result(column = "id",property = "travellers",
                    many = @Many(select = "cn.itcast.dao.TravellerDao.findByOrdersId")),
            @Result(column = "memberId",property = "member" ,
                    one = @One(select = "cn.itcast.dao.MemberDao.findById"))
    })
    Orders findById(String id) throws Exception;
}
