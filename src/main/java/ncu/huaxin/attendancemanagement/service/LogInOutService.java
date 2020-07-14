package ncu.huaxin.attendancemanagement.service;

import ncu.huaxin.attendancemanagement.entity.Employee;
import ncu.huaxin.attendancemanagement.entity.LogInOut;

import java.util.List;

/**
 * @Author huaxin
 * @Date 2020/7/6
 */
public interface LogInOutService {

    /**
     * 增加登入登出记录
     * @param employee 员工
     * @param type
     */
    void addLog(Employee employee, String type);

    /**
     * 根据班组查询所有登入登出记录
     * @param classId
     * @return
     */
    List<LogInOut> selectByClassId(Integer classId);

    /**
     * 查询所有登入登出记录
     * @return
     */
    List<LogInOut> selectAll();
}
