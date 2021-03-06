package ncu.huaxin.attendancemanagement.controller;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import lombok.extern.slf4j.Slf4j;
import ncu.huaxin.attendancemanagement.constant.Constant;
import ncu.huaxin.attendancemanagement.entity.Employee;
import ncu.huaxin.attendancemanagement.entity.LogInOut;
import ncu.huaxin.attendancemanagement.service.ClassService;
import ncu.huaxin.attendancemanagement.service.DepartmentService;
import ncu.huaxin.attendancemanagement.service.EmployeeService;
import ncu.huaxin.attendancemanagement.service.LogInOutService;
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
public class LogInOutController {

    @Autowired
    private LogInOutService logInOutService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private ClassService classService;

    @GetMapping("/inoutLog/getClassInOut")
    public String getClassInOut(@RequestParam(value = "inOutState",defaultValue = "0") Integer inOutState,
                                @RequestParam(value = "pn",defaultValue = "0") Integer pn,
                                Model model, HttpSession session){

        Employee employee = (Employee) session.getAttribute("emp");
        List<LogInOut> logInOutList = new LinkedList<>();
        PageMethod.startPage(pn,10);
        if(employee.getPosition().equals(Constant.EMPLOYEE_POSITION_MONITOR)){
            logInOutList = logInOutService.selectByClassId(employee.getClassId(),inOutState);
            log.info("班长，登入登出记录");
        }
        else if(employee.getPosition().equals(Constant.EMPLOYEE_POSITION_LEADER)){
            logInOutList = logInOutService.selectAll(inOutState);
            log.info("领导，登入登出记录");
        }


        for(int i=0;i<logInOutList.size();i++){
            logInOutList.get(i).setEmployee(employeeService.getEmployeeById(logInOutList.get(i).getUserId()));
            logInOutList.get(i).setClassOf(classService.selectById(logInOutList.get(i).getClassId()));
            logInOutList.get(i).setDepartment(departmentService.selectById(logInOutList.get(i).getDepartId()));
        }

        PageInfo<LogInOut> pageInfo = new PageInfo<>(logInOutList,5);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("inOutState",inOutState);

        session.setAttribute("logInOutList",logInOutList);
        return "inOutLog/classLog";
    }

    @PostMapping("/inoutLog/search")
    public String searchByType(HttpServletRequest request, HttpSession session){
        //获取查询框内容
        String searchBy = request.getParameter("searchBy");
        log.info("*********************LogInOutController.selectByType.searchBy"+searchBy);
        //判断查询框类型
        String searchType = request.getParameter("searchType");
        log.info("*********************LogInOutController.selectByType.searchType"+searchType);
        //
        List<LogInOut> searchList = new LinkedList<>();


        if(searchType.equals("员工")){
            List<Employee> employees= employeeService.selectEmpByName("%"+searchBy+"%");
            log.info("*********************LogInOutController.selectByType.employees"+employees.toString());
            for(int i=0;i<employees.size();i++){
                searchList.addAll(logInOutService.selectByEmpName(employees.get(i))) ;
            }
        }
        else if(searchType.equals("日期")){
            searchList = logInOutService.selectByDate(searchBy);
        }
        //绑定班级和用户
        for(int j=0;j<searchList.size();j++){
            searchList.get(j).setEmployee(employeeService.getEmployeeById(searchList.get(j).getUserId()));
            searchList.get(j).setClassOf(classService.selectById(searchList.get(j).getClassId()));
            searchList.get(j).setDepartment(departmentService.selectById(searchList.get(j).getDepartId()));
        }
        log.info("*********************LogInOutController.selectByType.searchList"+searchList.toString());
        session.setAttribute("searchList",searchList);
        return "inOutLog/search";
    }
}
