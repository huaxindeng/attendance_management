package ncu.huaxin.attendancemanagement.controller;

import lombok.extern.slf4j.Slf4j;
import ncu.huaxin.attendancemanagement.entity.Application;
import ncu.huaxin.attendancemanagement.entity.ApplyType;
import ncu.huaxin.attendancemanagement.service.ApplicationService;
import ncu.huaxin.attendancemanagement.service.ApplyTypeService;
import ncu.huaxin.attendancemanagement.service.EmployeeService;
import ncu.huaxin.attendancemanagement.service.LogInOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author huaxin
 * @Date 2020/7/5
 */
@Controller
@Slf4j
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private LogInOutService logInOutService;

    @Autowired
    private ApplyTypeService applyTypeService;

    @Autowired
    private ApplicationService applicationService;


    //跳转到申请页面
    @GetMapping("/emp/apply")
    public String apply(HttpSession session){

        List<ApplyType> applyTypeList = applyTypeService.getAll();
        session.setAttribute("applyTypeList",applyTypeList);
        log.info("********************apply:applyTypeList-->"+applyTypeList.toString());

        if(session.getAttribute("applicationNow")!=null){
            log.info("********************apply:applyicatioNow-->"+session.getAttribute("applicationNow").toString());
            log.info("********************apply.applicationNow!=null");

            return "emp/modify";
        }
        return "emp/apply";
    }

    //显示申请
    @GetMapping("/emp/list/{applyState}")
    public String listMyApplication(@PathVariable("applyState") Integer applyState, HttpSession session){
        return "redirect:/application/listOneEmp?applyState="+applyState;
    }

    @GetMapping("/emp/modify/{applyId}")
    public String modify(@PathVariable("applyId") Integer applyId,HttpSession session){

        Application application = applicationService.selectById(applyId);
        log.info("***************EmployeeController.modify.application"+application);
        session.setAttribute("applicationNow",application);
        return "redirect:/emp/apply";
    }

    @GetMapping("/emp/deny/{applyId}")
    public String deny(@PathVariable("applyId") Integer applyId,HttpSession session){
        Application application = applicationService.selectById(applyId);
        application.setEmployee(employeeService.getEmployeeById(application.getUserId()));
        log.info("***************EmployeeController.deny.application"+application);
        session.setAttribute("applicationTo",application);
        return "apply/deny";
    }

}
