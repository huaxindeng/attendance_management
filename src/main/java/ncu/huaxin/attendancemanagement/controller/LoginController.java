package ncu.huaxin.attendancemanagement.controller;

import lombok.extern.slf4j.Slf4j;
import ncu.huaxin.attendancemanagement.constant.Constant;
import ncu.huaxin.attendancemanagement.entity.Employee;
import ncu.huaxin.attendancemanagement.service.EmployeeService;
import ncu.huaxin.attendancemanagement.service.LogInOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @Author huaxin
 * @Date 2020/7/9
 */
@Controller
@Slf4j
public class LoginController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private LogInOutService logInOutService;


    //登陆操作
    @PostMapping("/login/in")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String,Object> map, HttpSession session){
        Employee empByLogin = employeeService.getEmpByLogin(username, password);

        if(empByLogin!=null){
            session.setAttribute("emp",empByLogin);
            log.info("*********************/emp/login/,用户存在:"+empByLogin.toString());
            logInOutService.addLog(empByLogin, Constant.INOUT_TYPE_IN);
            return "redirect:/emp/list/0";
        }else {
            map.put("msg","登陆失败");
            log.info("*********************/emp/login/,用户不存在:"+"/(ㄒoㄒ)/~~");
            return "login";
        }
    }

    //登出操作
    @GetMapping("/login/out")
    public String logout(HttpSession session){
        Employee employee = (Employee) session.getAttribute("emp");
        logInOutService.addLog(employee,Constant.INOUT_TYPE_OUT);
        session.removeAttribute("emp");
        return "login";
    }

}
