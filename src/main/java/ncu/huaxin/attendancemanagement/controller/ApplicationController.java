package ncu.huaxin.attendancemanagement.controller;

import lombok.extern.slf4j.Slf4j;
import ncu.huaxin.attendancemanagement.constant.Constant;
import ncu.huaxin.attendancemanagement.entity.Application;
import ncu.huaxin.attendancemanagement.entity.Employee;
import ncu.huaxin.attendancemanagement.service.ApplicationService;
import ncu.huaxin.attendancemanagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping("/application/listOneEmp/{applyState}")
    public String listOneEmp(@PathVariable("applyState")Integer applyState,HttpSession session){
        Employee employee = (Employee)session.getAttribute("emp");
        List<Application> applicationList = applicationService.selectByUserId(employee.getUserId());
        //全部
        session.setAttribute("applyState",0);

        if(applyState==1){
            //未审核
            for(int i=0;i<applicationList.size();i++){
                Application a = applicationList.get(i);
                log.info("***********a.applyState"+a.getApplyState());
                if(a.getApplyState().equals(Constant.APPLICATION_STATE_AGREE)||
                        a.getApplyState().equals(Constant.APPLICATION_STATE_DENY) ||
                        a.getApplyState().equals(Constant.APPLICATION_STATE_CANCEL)) {
                    applicationList.remove(a);
                    i--;
                }
            }
            session.setAttribute("applyState",1);
        }
        else if(applyState==2) {
            //已审核
            log.info("********ApplicationController.listOneEmp.applicationList:"+applicationList.toString());
            for(int i=0;i<applicationList.size();i++){
                Application a = applicationList.get(i);
                log.info("***********a.applyState"+a.getApplyState()+"applyId"+a.getApplyId());
                if (a.getApplyState().equals(Constant.APPLICATION_STATE_SUBMIT) ||
                        a.getApplyState().equals(Constant.APPLICATION_STATE_MODIFY) ||
                        a.getApplyState().equals(Constant.APPLICATION_STATE_CANCEL)) {
                    applicationList.remove(a);
                    i--;
                }
            }
            session.setAttribute("applyState",2);
            log.info("********ApplicationController.listOneEmp.applicationList.已审核:"+applicationList.toString());

        }
        else if(applyState==3){
            //已取消
            for(int i=0;i<applicationList.size();i++){
                Application a = applicationList.get(i);
                log.info("***********a.applyState"+a.getApplyState()+"applyId"+a.getApplyId());
                if (!a.getApplyState().equals(Constant.APPLICATION_STATE_CANCEL)) {
                    applicationList.remove(a);
                    i--;
                }
            }
            session.setAttribute("applyState",3);
        }

        log.info("**********ApplicationController.submit.userId-->"+employee.toString());
        session.setAttribute("applicationList",applicationList);
        log.info("**********ApplicationController.submit.applicationList-->"+applicationList.toString());
        return "emp/list";
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

    @GetMapping("/application/getDepartApply/{applyState}")
    public String getDepartApply(@PathVariable("applyState") Integer applyState,HttpSession session){
        Employee employee = (Employee) session.getAttribute("emp");

        List<Application> applicationDepartList = applicationService.selectByDepartId(employee.getDepartId());

        for(int i=0;i<applicationDepartList.size();i++){
            applicationDepartList.get(i).setEmployee(employeeService.getEmployeeById(applicationDepartList.get(i).getUserId()));
        }

        session.setAttribute("applyState",0);

        if(applyState==1){
            //未审核
            for(int i=0;i<applicationDepartList.size();i++){
                Application a = applicationDepartList.get(i);
                log.info("***********a.applyState"+a.getApplyState());
                if(a.getApplyState().equals(Constant.APPLICATION_STATE_AGREE)||
                        a.getApplyState().equals(Constant.APPLICATION_STATE_DENY) ||
                        a.getApplyState().equals(Constant.APPLICATION_STATE_CANCEL)) {
                    applicationDepartList.remove(a);
                    i--;
                }
            }
            session.setAttribute("applyState",1);
        }
        else if(applyState==2) {
            //已审核
            for(int i=0;i<applicationDepartList.size();i++){
                Application a = applicationDepartList.get(i);
                log.info("***********a.applyState"+a.getApplyState()+"applyId"+a.getApplyId());
                if (a.getApplyState().equals(Constant.APPLICATION_STATE_SUBMIT) ||
                        a.getApplyState().equals(Constant.APPLICATION_STATE_MODIFY) ||
                        a.getApplyState().equals(Constant.APPLICATION_STATE_CANCEL)) {
                    applicationDepartList.remove(a);
                    i--;
                }
            }
            session.setAttribute("applyState",2);

        }
        else if(applyState==3){
            //已取消
            for(int i=0;i<applicationDepartList.size();i++){
                Application a = applicationDepartList.get(i);
                if (!a.getApplyState().equals(Constant.APPLICATION_STATE_CANCEL)) {
                    applicationDepartList.remove(a);
                    i--;
                }
            }
            session.setAttribute("applyState",3);
        }

        log.info("**********ApplicationController.submit.userId-->"+employee.toString());

        log.info("***************ApplicationController.getDepartApply.applicationDepartList:"+applicationDepartList.toString());

        session.setAttribute("applicationDepartList",applicationDepartList);
        return "emp/departApply";

    }

    @GetMapping("/application/getClassApply/{applyState}")
    public String getClassApply(@PathVariable("applyState") Integer applyState,HttpSession session){
        Employee employee = (Employee) session.getAttribute("emp");

        List<Application> applicationClassList = applicationService.selectByClassId(employee.getClassId());

        log.info("***************ApplicationController.getClassApply.applicationClassList(NoEmployee):"+applicationClassList.toString());


         //去除班长自己的申请
        for(int i=0;i<applicationClassList.size();i++){
            applicationClassList.get(i).setEmployee(employeeService.getEmployeeById(applicationClassList.get(i).getUserId()));
            if(applicationClassList.get(i).getUserId().equals(employee.getUserId())){
                log.info("************************ApplicationController.getClassApply." +
                        "applicationClassList("+i+")"+applicationClassList.get(i).toString());
                applicationClassList.remove(i);
                i--;
            }
        }
        log.info("********************ApplicationController.getClassApply.applicationClassList"+applicationClassList.toString());

        session.setAttribute("applyState",0);

        if(applyState==1){
            //未审核
            for(int i=0;i<applicationClassList.size();i++){
                Application a = applicationClassList.get(i);
                log.info("***********a.applyState"+a.getApplyState());
                if(a.getApplyState().equals(Constant.APPLICATION_STATE_AGREE)||
                        a.getApplyState().equals(Constant.APPLICATION_STATE_DENY) ||
                        a.getApplyState().equals(Constant.APPLICATION_STATE_CANCEL)) {
                    applicationClassList.remove(a);
                    i--;
                }
            }
            session.setAttribute("applyState",1);
        }
        else if(applyState==2) {
            //已审核
            for(int i=0;i<applicationClassList.size();i++){
                Application a = applicationClassList.get(i);
                log.info("***********a.applyState"+a.getApplyState()+"applyId"+a.getApplyId());
                if (a.getApplyState().equals(Constant.APPLICATION_STATE_SUBMIT) ||
                        a.getApplyState().equals(Constant.APPLICATION_STATE_MODIFY) ||
                        a.getApplyState().equals(Constant.APPLICATION_STATE_CANCEL)) {
                    applicationClassList.remove(a);
                    i--;
                }
            }
            session.setAttribute("applyState",2);

        }
        else if(applyState==3){
            //已取消
            for(int i=0;i<applicationClassList.size();i++){
                Application a = applicationClassList.get(i);
                if (!a.getApplyState().equals(Constant.APPLICATION_STATE_CANCEL)) {
                    applicationClassList.remove(a);
                    i--;
                }
            }
            session.setAttribute("applyState",3);
        }

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
}
