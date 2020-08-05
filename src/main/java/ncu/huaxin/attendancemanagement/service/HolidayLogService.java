package ncu.huaxin.attendancemanagement.service;


import ncu.huaxin.attendancemanagement.entity.Employee;
import ncu.huaxin.attendancemanagement.entity.HolidayLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author huaxin
 * @Date 2020/7/6
 */
public interface HolidayLogService {
    /**
     * 增加假期记录表记录
     * @param holidayLog
     */
    void addHolidayLog(HolidayLog holidayLog);

    List<HolidayLog> selectByClassId(Integer classId, Integer holidayState);

    /**
     * 查找所有的申请记录
     * @return
     */
    List<HolidayLog> selectAll(Integer holidayState);

    /**
     * 根据员工查询操作记录
     * @param employee
     * @return
     */
    List<HolidayLog> selectByEmpName(@Param("employee") Employee employee );

    /**
     * 通过日期查询操作记录
     * @param date
     * @return
     */
    List<HolidayLog> selectByDate(String date);
}
