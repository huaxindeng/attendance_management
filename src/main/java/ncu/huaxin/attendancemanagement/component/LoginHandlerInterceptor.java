package ncu.huaxin.attendancemanagement.component;

import lombok.extern.slf4j.Slf4j;
import ncu.huaxin.attendancemanagement.entity.Employee;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Author huaxin
 * @Date 2020/7/6
 */
@Slf4j
public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Employee employee = (Employee) session.getAttribute("emp");
        if(employee == null){
            //未登录。放回登陆页面
            request.setAttribute("msg","请先登录");
            log.info("****************LoginHandlerInterceptor 用户未登录，请先登录");
            request.getRequestDispatcher("/index").forward(request,response);
            return false;
        }else{
            //已登录，放行请求
            log.info("****************LoginHandlerInterceptor 用户已登录，请继续操作");
            return true;
        }
    }
}
