package cn.itcast.dao;

import cn.itcast.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ProductDao {
    /**
     * 查询所有商品信息
     *
     * @return
     * @throws Exception
     */
    @Select("select * from product")
    List<Product> findAll() throws Exception;

    /**
     * 保存商品
     *
     * @param product
     * @throws Exception
     */
    @Insert("insert into product(productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) " +
            "values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void save(Product product) throws Exception;

    /**
     * 通过id查询商品
     *
     * @param productId
     * @return
     */
    @Select("select * from product where id = #{productId}")
    Product findById(String productId) throws Exception;
}
