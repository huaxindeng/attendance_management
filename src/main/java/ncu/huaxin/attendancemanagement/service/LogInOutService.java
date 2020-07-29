package ncu.huaxin.attendancemanagement.service;

import ncu.huaxin.attendancemanagement.entity.Employee;
import ncu.huaxin.attendancemanagement.entity.LogInOut;
import org.apache.ibatis.annotations.Param;

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
    List<LogInOut> selectByClassId(Integer classId,Integer inOutState);

    /**
     * 查询所有登入登出记录
     * @return
     */
    List<LogInOut> selectAll(Integer inOutState);

    /**
     * 根据员工查询操作记录
     * @param employee
     * @return
     */
    List<LogInOut> selectByEmpName(@Param("employee") Employee employee );

    /**
     * 通过日期查询操作记录
     * @param date
     * @return
     */
    List<LogInOut> selectByDate(String date);
}
