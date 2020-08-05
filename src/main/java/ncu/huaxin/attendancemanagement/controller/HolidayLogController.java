package ncu.huaxin.attendancemanagement.controller;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import lombok.extern.slf4j.Slf4j;
import ncu.huaxin.attendancemanagement.constant.Constant;
import ncu.huaxin.attendancemanagement.entity.Employee;
import ncu.huaxin.attendancemanagement.entity.HolidayLog;
import ncu.huaxin.attendancemanagement.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/holidayLog/getClassLog")
    public String getClassLog(@RequestParam(value = "holidayState",defaultValue = "0") Integer holidayState,
                              @RequestParam(value = "pn",defaultValue = "0") Integer pn,
                              Model model, HttpSession session){

        Employee employee = (Employee) session.getAttribute("emp");

        PageMethod.startPage(pn,10);
        List<HolidayLog> holidayLogList = new LinkedList<>();
        if(employee.getPosition().equals(Constant.EMPLOYEE_POSITION_MONITOR)){
            holidayLogList = holidayLogService.selectByClassId(employee.getClassId(),holidayState);
            log.info("班长，假期申请记录");
        }
        else if(employee.getPosition().equals(Constant.EMPLOYEE_POSITION_LEADER)){
            holidayLogList = holidayLogService.selectAll(holidayState);
            log.info("领导，假期申请记录");
        }
        for(int i=0;i<holidayLogList.size();i++){
            holidayLogList.get(i).setClassOf(classService.selectById(holidayLogList.get(i).getClassId()));
            holidayLogList.get(i).setEmployee(employeeService.getEmployeeById(holidayLogList.get(i).getUserId()));
            holidayLogList.get(i).setApplication(applicationService.selectById(holidayLogList.get(i).getApplyId()));
            holidayLogList.get(i).setDepartment(departmentService.selectById(holidayLogList.get(i).getDepartId()));
        }

        PageInfo<HolidayLog> pageInfo = new PageInfo<>(holidayLogList,5);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("holidayState",holidayState);


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
