package cn.itcast.controller;

import cn.itcast.domain.Orders;
import cn.itcast.service.OrdersService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrdersService ordersService;

    /*@RequestMapping("/findAll.do")
    public ModelAndView findAll() {
        ModelAndView mv = new ModelAndView();
        List<Orders> ordersList = null;
        try {
            ordersList = ordersService.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        mv.addObject("ordersList", ordersList);
        mv.setViewName("orders-list");
        return mv;
    }*/

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(required = true, defaultValue = "1", name = "page") Integer page,
                                @RequestParam(required = true, defaultValue = "4", name = "size") Integer size) throws Exception {
        List<Orders> ordersList = ordersService.findByPages(page, size);
        ModelAndView mv = new ModelAndView();
        PageInfo pageInfo = new PageInfo();
        pageInfo.setList(ordersList);
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("orders-page-list");
        return mv;
    }

    @RequestMapping("/findById")
    public ModelAndView findById(String id) throws Exception {
        ModelAndView mv = new ModelAndView();
        Orders orders = ordersService.findById(id);
        mv.setViewName("orders-show");
        mv.addObject("orders", orders);
        return mv;
    }
}
