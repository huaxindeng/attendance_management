package ncu.huaxin.attendancemanagement.service;

import ncu.huaxin.attendancemanagement.entity.Application;
import ncu.huaxin.attendancemanagement.entity.HolidayLog;

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

    List<HolidayLog> selectByClassId(Integer classId);
}
