package cn.itcast.service;

import cn.itcast.domain.Orders;

import java.util.List;

public interface OrdersService {
    /**
     * 查询所有订单信息
     *
     * @return
     */
    List<Orders> findAll() throws Exception;

    /**
     * 分页查询
     *
     * @param page
     * @param pageSize
     * @return
     * @throws Exception
     */
    List<Orders> findByPages(int page, int pageSize) throws Exception;

    /**
     * 根据id查询订单
     * @param id
     * @return
     */
    Orders findById(String id) throws Exception;
}
