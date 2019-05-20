package cn.itcast.service;

import cn.itcast.domain.Permission;
import cn.itcast.domain.Product;

import java.util.List;

public interface ProductService {
    /**
     * 查询所有商品信息
     * @return
     * @throws Exception
     */
    List<Product> findAll() throws Exception;

    /**
     * 保存商品
     * @param product
     */
    void save(Product product) throws Exception;


}
