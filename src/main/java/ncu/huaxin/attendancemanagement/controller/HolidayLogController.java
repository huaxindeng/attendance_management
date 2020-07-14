package ncu.huaxin.attendancemanagement.controller;

import lombok.extern.slf4j.Slf4j;
import ncu.huaxin.attendancemanagement.constant.Constant;
import ncu.huaxin.attendancemanagement.entity.Application;
import ncu.huaxin.attendancemanagement.entity.Employee;
import ncu.huaxin.attendancemanagement.entity.HolidayLog;
import ncu.huaxin.attendancemanagement.service.ApplicationService;
import ncu.huaxin.attendancemanagement.service.ClassService;
import ncu.huaxin.attendancemanagement.service.EmployeeService;
import ncu.huaxin.attendancemanagement.service.HolidayLogService;
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
public class HolidayLogController {

    @Autowired
    private HolidayLogService holidayLogService;

    @Autowired
    private ClassService classService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ApplicationService applicationService;

    @GetMapping("/holidayLog/getClassLog/{holidayState}")
    public String getClassLog(@PathVariable("holidayState")Integer holidayState, HttpSession session){

        Employee employee = (Employee) session.getAttribute("emp");

        List<HolidayLog> holidayLogClassList = holidayLogService.selectByClassId(employee.getClassId());


        if(holidayState==1){
            //申请记录
            for(int i=0;i<holidayLogClassList.size();i++){
                HolidayLog holidayLog = holidayLogClassList.get(i);
//                log.info("***********a.applyState"+a.getApplyState());
                if(holidayLog.getHolidayState().equals(Constant.HOLIDAY_STATE_MODIFY)||
                        holidayLog.getHolidayState().equals(Constant.HOLIDAY_STATE_CANCEL)) {
                    holidayLogClassList.remove(holidayLog);
                    i--;
                }
            }
        }
        else if(holidayState==2) {
            //修改记录
            for(int i=0;i<holidayLogClassList.size();i++){
                HolidayLog holidayLog = holidayLogClassList.get(i);
//                log.info("***********a.applyState"+a.getApplyState());
                if(holidayLog.getHolidayState().equals(Constant.HOLIDAY_STATE_SUBMIT)||
                        holidayLog.getHolidayState().equals(Constant.HOLIDAY_STATE_CANCEL)) {
                    holidayLogClassList.remove(holidayLog);
                    i--;
                }
            }

        }
        else if(holidayState==3){
            //取消记录
            for(int i=0;i<holidayLogClassList.size();i++){
                HolidayLog holidayLog = holidayLogClassList.get(i);
//                log.info("***********a.applyState"+a.getApplyState());
                if(holidayLog.getHolidayState().equals(Constant.HOLIDAY_STATE_MODIFY)||
                        holidayLog.getHolidayState().equals(Constant.HOLIDAY_STATE_SUBMIT)) {
                    holidayLogClassList.remove(holidayLog);
                    i--;
                }
            }
        }


        for(int i=0;i<holidayLogClassList.size();i++){
            holidayLogClassList.get(i).setClassOf(classService.selectById(holidayLogClassList.get(i).getClassId()));
            holidayLogClassList.get(i).setEmployee(employeeService.getEmployeeById(holidayLogClassList.get(i).getUserId()));
            holidayLogClassList.get(i).setApplication(applicationService.selectById(holidayLogClassList.get(i).getApplyId()));
        }
        session.setAttribute("holidayLogClassList",holidayLogClassList);
        return "holidayLog/classLog";
    }

}
