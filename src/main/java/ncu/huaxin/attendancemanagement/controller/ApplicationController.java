package ncu.huaxin.attendancemanagement.controller;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import lombok.extern.slf4j.Slf4j;
import ncu.huaxin.attendancemanagement.constant.Constant;
import ncu.huaxin.attendancemanagement.entity.Application;
import ncu.huaxin.attendancemanagement.entity.Employee;
import ncu.huaxin.attendancemanagement.service.ApplicationService;
import ncu.huaxin.attendancemanagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author huaxin
 * @Date 2020/7/6
 */
@Controller
@Slf4j
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/application/submit")
    public String submit(Application application, HttpSession session){

        Employee employee = (Employee)session.getAttribute("emp");
        //绑定员工信息
        application.setEmployee(employee);
        //修改申请状态
        application.setApplyState(Constant.APPLICATION_STATE_SUBMIT);
        application.setClassId(employee.getClassId());
        application.setDepartId(employee.getDepartId());
        //保存申请
        applicationService.submit(application);
        log.info("**********ApplicationController.submit.application-->"+application.toString());
        session.removeAttribute("applyTypeList");
        return "redirect:/emp/list/0";
    }

    @GetMapping("/application/listOneEmp")
    public String listOneEmp(@RequestParam(value = "applyState",defaultValue = "0") Integer applyState,
                             @RequestParam(value = "pn",defaultValue = "1") Integer pn,
                             HttpSession session, Model model){
        Employee employee = (Employee)session.getAttribute("emp");
        log.info("**********ApplicationController.listOneEmp.applyState-->"+applyState);


        PageMethod.startPage(pn,10);
        List<Application> applicationList = applicationService.selectByUserId(employee.getUserId(),applyState);
        PageInfo<Application> pageInfo = new PageInfo<>(applicationList,5);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("applyState",applyState);

        log.info("**********ApplicationController.listOneEmp.userId-->"+employee.toString());
        session.setAttribute("applicationList",applicationList);
        log.info("**********ApplicationController.listOneEmp.applicationList-->"+applicationList.toString());
        return "emp/myApply";
    }

    @PostMapping("/application/modify")
    public String modify(Application application,HttpSession session){
        Application a = (Application) session.getAttribute("applicationNow");
        Employee employee = (Employee) session.getAttribute("emp");
        application.setApplyId(a.getApplyId());
        application.setEmployee(employee);
        application.setApplyState(Constant.APPLICATION_STATE_MODIFY);
        applicationService.modify(application);
        log.info("***************ApplicationController.modify.application:"+application.toString());
        session.removeAttribute("applicationNow");
        session.removeAttribute("applyTypeList");
        return "redirect:/emp/list/0";
    }

    @GetMapping("/application/cancel/{applyId}")
    public String cancel(@PathVariable("applyId") Integer applyId, HttpSession session){

        Application application = applicationService.selectById(applyId);
        Employee employee = (Employee) session.getAttribute("emp");
        application.setEmployee(employee);
        application.setApplyState(Constant.APPLICATION_STATE_CANCEL);
        applicationService.cancel(application);
        return "redirect:/emp/list/0";
    }

    @GetMapping("/application/getDepartApply")
    public String getDepartApply(@RequestParam(value = "applyState",defaultValue = "0") Integer applyState,
                                 @RequestParam(value = "pn",defaultValue = "0") Integer pn,
                                 Model model,HttpSession session){
        Employee employee = (Employee) session.getAttribute("emp");



        PageMethod.startPage(pn,10);
        List<Application> applicationDepartList = applicationService.selectByDepartId(employee,applyState);

        for(int i=0;i<applicationDepartList.size();i++){
            applicationDepartList.get(i).setEmployee(employeeService.getEmployeeById(applicationDepartList.get(i).getUserId()));
        }
        PageInfo<Application> pageInfo = new PageInfo<>(applicationDepartList,5);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("applyState",applyState);


        log.info("**********ApplicationController.submit.userId-->"+employee.toString());

        log.info("***************ApplicationController.getDepartApply.applicationDepartList:"+applicationDepartList.toString());

        session.setAttribute("applicationDepartList",applicationDepartList);
        return "emp/departApply";

    }

    @GetMapping("/application/getClassApply")
    public String getClassApply(@RequestParam(value = "applyState",defaultValue = "0") Integer applyState,
                                @RequestParam(value = "pn",defaultValue = "0") Integer pn,
                                Model model,HttpSession session){
        Employee employee = (Employee) session.getAttribute("emp");



        PageMethod.startPage(pn,10);
        List<Application> applicationClassList = applicationService.selectByClassId(employee,applyState);

        log.info("***************ApplicationController.getClassApply.applicationClassList(NoEmployee):"+applicationClassList.toString());


        //添加员工对象
        for(int i=0;i<applicationClassList.size();i++){
            applicationClassList.get(i).setEmployee(employeeService.getEmployeeById(applicationClassList.get(i).getUserId()));
        }
        log.info("********************ApplicationController.getClassApply.applicationClassList"+applicationClassList.toString());
        PageInfo<Application> pageInfo = new PageInfo<>(applicationClassList,5);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("applyState",applyState);

        log.info("**********ApplicationController.getClassApply.userId-->"+employee.toString());

        log.info("***************ApplicationController.getClassApply.applicationClassList:"+applicationClassList.toString());

        session.setAttribute("applicationClassList",applicationClassList);
        return "emp/classApply";

    }

    @GetMapping("/application/applyDetail/{applyId}")
    public String applyDetail(@PathVariable("applyId") Integer applyId,HttpSession session){

        Employee employee = (Employee)session.getAttribute("emp");

        Application application = applicationService.selectById(applyId);
        application.setEmployee(employee);

        session.setAttribute("applicationTo", application);
        return "apply/detail";
    }

    @GetMapping("/application/agree/{applyId}")
    public String agree(@PathVariable("applyId") Integer applyId,HttpSession session){
        Employee employee = (Employee) session.getAttribute("emp");

        Application application = applicationService.selectById(applyId);
        log.info("************************ApplicationController.agree.application"+application.toString());
        application.setApplyState(Constant.APPLICATION_STATE_AGREE);
        applicationService.agree(application);
        return "redirect:/application/getClassApply/"+employee.getClassId();
    }
    @PostMapping("/application/deny")
    public String deny(Application application,String denyReason,HttpSession session){
        Employee employee = (Employee) session.getAttribute("emp");

        application = applicationService.selectById(application.getApplyId());
        application.setApplyState(Constant.APPLICATION_STATE_DENY);
        application.setApplyReason(denyReason);
        applicationService.deny(application);
        return "redirect:/application/getClassApply/"+employee.getClassId();
    }


    @GetMapping("/application/getRecent")
    public String getRecentApplyByDepartId(@RequestParam(value = "applyState",defaultValue = "0") Integer applyState,
                                           @RequestParam(value = "pn",defaultValue = "0") Integer pn,
                                           Model model,HttpSession session){
        Employee employee = (Employee) session.getAttribute("emp");

        PageMethod.startPage(pn,10);
        List<Application> recentDepartList = applicationService.getEmployeesByDepartId(employee,applyState);

        for(int i=0;i<recentDepartList.size();i++){
            recentDepartList.get(i).setEmployee(employeeService.getEmployeeById(recentDepartList.get(i).getUserId()));
        }
        PageInfo<Application> pageInfo = new PageInfo<>(recentDepartList,5);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("applyState",applyState);

        log.info("**********ApplicationController.submit.userId-->"+employee.toString());

        log.info("***************ApplicationController.getDepartApply.applicationDepartList:"+recentDepartList.toString());

        session.setAttribute("recentDepartList",recentDepartList);
        return "emp/recentEmp";

    }
}
