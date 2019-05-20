package cn.itcast.controller;

import cn.itcast.domain.SysLog;
import cn.itcast.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/sysLog")
public class SysLogController {
    @Autowired
    private SysLogService sysLogService;

    @RequestMapping("/findAll")
    public ModelAndView findAll() throws Exception  {
        ModelAndView mv = new ModelAndView();
        List<SysLog> logList = sysLogService.findAll();
        mv.addObject("sysLogs", logList);
        mv.setViewName("syslog-list");
        return mv;
    }
}
