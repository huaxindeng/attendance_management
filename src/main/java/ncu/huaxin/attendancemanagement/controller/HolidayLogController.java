package ncu.huaxin.attendancemanagement.controller;

import lombok.extern.slf4j.Slf4j;
import ncu.huaxin.attendancemanagement.constant.Constant;
import ncu.huaxin.attendancemanagement.entity.Application;
import ncu.huaxin.attendancemanagement.entity.Employee;
import ncu.huaxin.attendancemanagement.entity.HolidayLog;
import ncu.huaxin.attendancemanagement.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author huaxin
 * @Date 2020/7/11
 */
@Controller
@Slf4j
public class HolidayLogController {

    @Autowired
    private HolidayLogService holidayLogService;

    @Autowired
    private ClassService classService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ApplicationService applicationService;

    @GetMapping("/holidayLog/getClassLog/{holidayState}")
    public String getClassLog(@PathVariable("holidayState")Integer holidayState, HttpSession session){

        Employee employee = (Employee) session.getAttribute("emp");

        List<HolidayLog> holidayLogList = new LinkedList<>();
        if(employee.getPosition().equals(Constant.EMPLOYEE_POSITION_MONITOR)){
            holidayLogList = holidayLogService.selectByClassId(employee.getClassId());
            log.info("班长，假期申请记录");
        }
        else if(employee.getPosition().equals(Constant.EMPLOYEE_POSITION_LEADER)){
            holidayLogList = holidayLogService.selectAll();
            log.info("领导，假期申请记录");
        }



        if(holidayState==1){
            //申请记录
            for(int i=0;i<holidayLogList.size();i++){
                HolidayLog holidayLog = holidayLogList.get(i);
//                log.info("***********a.applyState"+a.getApplyState());
                if(holidayLog.getHolidayState().equals(Constant.HOLIDAY_STATE_MODIFY)||
                        holidayLog.getHolidayState().equals(Constant.HOLIDAY_STATE_CANCEL)) {
                    holidayLogList.remove(holidayLog);
                    i--;
                }
            }
        }
        else if(holidayState==2) {
            //修改记录
            for(int i=0;i<holidayLogList.size();i++){
                HolidayLog holidayLog = holidayLogList.get(i);
//                log.info("***********a.applyState"+a.getApplyState());
                if(holidayLog.getHolidayState().equals(Constant.HOLIDAY_STATE_SUBMIT)||
                        holidayLog.getHolidayState().equals(Constant.HOLIDAY_STATE_CANCEL)) {
                    holidayLogList.remove(holidayLog);
                    i--;
                }
            }

        }
        else if(holidayState==3){
            //取消记录
            for(int i=0;i<holidayLogList.size();i++){
                HolidayLog holidayLog = holidayLogList.get(i);
//                log.info("***********a.applyState"+a.getApplyState());
                if(holidayLog.getHolidayState().equals(Constant.HOLIDAY_STATE_MODIFY)||
                        holidayLog.getHolidayState().equals(Constant.HOLIDAY_STATE_SUBMIT)) {
                    holidayLogList.remove(holidayLog);
                    i--;
                }
            }
        }


        for(int i=0;i<holidayLogList.size();i++){
            holidayLogList.get(i).setClassOf(classService.selectById(holidayLogList.get(i).getClassId()));
            holidayLogList.get(i).setEmployee(employeeService.getEmployeeById(holidayLogList.get(i).getUserId()));
            holidayLogList.get(i).setApplication(applicationService.selectById(holidayLogList.get(i).getApplyId()));
            holidayLogList.get(i).setDepartment(departmentService.selectById(holidayLogList.get(i).getDepartId()));
        }
        session.setAttribute("holidayLogList",holidayLogList);
        return "holidayLog/classLog";
    }

    @PostMapping("/holidayLog/search")
    public String searchByType(HttpServletRequest request,HttpSession session){
        //获取查询框内容
        String searchBy = request.getParameter("searchBy");
        log.info("*********************HolidayLog.selectByEmpName.searchBy"+searchBy);
        //判断查询框类型
        String searchType = request.getParameter("searchType");
        log.info("*********************HolidayLog.selectByEmpName.searchType"+searchType);
        //
        List<HolidayLog> searchList = new LinkedList<>();


        if(searchType.equals("员工")){
            List<Employee> employees= employeeService.selectEmpByName("%"+searchBy+"%");
            log.info("*********************HolidayLog.selectByEmpName.employees"+employees.toString());
            for(int i=0;i<employees.size();i++){
                searchList.addAll(holidayLogService.selectByEmpName(employees.get(i))) ;
            }
        }
        else if(searchType.equals("日期")){
            searchList = holidayLogService.selectByDate(searchBy);
        }
        //绑定班级和用户
        for(int j=0;j<searchList.size();j++){
            searchList.get(j).setEmployee(employeeService.getEmployeeById(searchList.get(j).getUserId()));
            searchList.get(j).setClassOf(classService.selectById(searchList.get(j).getClassId()));
            searchList.get(j).setApplication(applicationService.selectById(searchList.get(j).getApplyId()));
            searchList.get(j).setDepartment(departmentService.selectById(searchList.get(j).getDepartId()));
        }
        log.info("*********************HolidayLog.selectByEmpName.searchList"+searchList.toString());
        session.setAttribute("searchList",searchList);
        return "holidayLog/search";
    }

}
