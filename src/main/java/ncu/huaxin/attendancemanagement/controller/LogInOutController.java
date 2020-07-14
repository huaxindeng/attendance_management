package ncu.huaxin.attendancemanagement.controller;

import lombok.extern.slf4j.Slf4j;
import ncu.huaxin.attendancemanagement.constant.Constant;
import ncu.huaxin.attendancemanagement.entity.Employee;
import ncu.huaxin.attendancemanagement.entity.LogInOut;
import ncu.huaxin.attendancemanagement.mapper.LogInOutMapper;
import ncu.huaxin.attendancemanagement.service.ClassService;
import ncu.huaxin.attendancemanagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author huaxin
 * @Date 2020/7/11
 */
@Controller
@Slf4j
public class LogInOutController {

    @Autowired
    private LogInOutMapper logInOutMapper;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ClassService classService;

    @GetMapping("/inoutLog/getClassInOut/{inOutState}")
    public String getClassInOut(@PathVariable("inOutState")Integer inOutState, HttpSession session){

        Employee employee = (Employee) session.getAttribute("emp");
        List<LogInOut> logInOutList = logInOutMapper.selectByClassId(employee.getClassId());


        if(inOutState==1){
            for(int i=0;i<logInOutList.size();i++){
                LogInOut inOut = logInOutList.get(i);

                if(inOut.getInoutType().equals(Constant.INOUT_TYPE_OUT)){
                    logInOutList.remove(i);
                    i--;
                }
            }
        }
        else if(inOutState==2){
            for(int i=0;i<logInOutList.size();i++){
                LogInOut inOut = logInOutList.get(i);

                if(inOut.getInoutType().equals(Constant.INOUT_TYPE_IN)){
                    logInOutList.remove(i);
                    i--;
                }
            }
        }


        for(int i=0;i<logInOutList.size();i++){
            logInOutList.get(i).setEmployee(employeeService.getEmployeeById(logInOutList.get(i).getUserId()));
            logInOutList.get(i).setClassOf(classService.selectById(logInOutList.get(i).getClassId()));
        }

        session.setAttribute("logInOutList",logInOutList);
        return "inOutLog/classLog";
    }
}
