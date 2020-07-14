package ncu.huaxin.attendancemanagement.controller;

import lombok.extern.slf4j.Slf4j;
import ncu.huaxin.attendancemanagement.constant.Constant;
import ncu.huaxin.attendancemanagement.entity.Application;
import ncu.huaxin.attendancemanagement.entity.ApplyType;
import ncu.huaxin.attendancemanagement.entity.Employee;
import ncu.huaxin.attendancemanagement.service.ApplicationService;
import ncu.huaxin.attendancemanagement.service.ApplyTypeService;
import ncu.huaxin.attendancemanagement.service.EmployeeService;
import ncu.huaxin.attendancemanagement.service.LogInOutService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

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

//    //登陆操作
//    @PostMapping("/emp/login")
//    public String login(@RequestParam("username") String username,
//                          @RequestParam("password") String password,
//                          Map<String,Object> map, HttpSession session){
//        Employee empByLogin = employeeService.getEmpByLogin(username, password);
//
//        if(empByLogin!=null){
//            session.setAttribute("emp",empByLogin);
//            log.info("*********************/emp/login/,用户存在:"+empByLogin.toString());
//            logInOutService.addLog(empByLogin, Constant.INOUT_TYPE_IN);
//            return "redirect:/emp/list/0";
//        }else {
//            map.put("msg","登陆失败");
//            log.info("*********************/emp/login/,用户不存在:"+"/(ㄒoㄒ)/~~");
//            return "login";
//        }
//    }
//
//    //登出操作
//    @GetMapping("/emp/logout")
//    public String logout(HttpSession session){
//        Employee employee = (Employee) session.getAttribute("emp");
//        logInOutService.addLog(employee,Constant.INOUT_TYPE_OUT);
//        session.removeAttribute("emp");
//        return "login";
//    }

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
        return "redirect:/application/listOneEmp/"+applyState;
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
