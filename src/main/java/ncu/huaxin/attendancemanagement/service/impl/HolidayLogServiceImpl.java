package ncu.huaxin.attendancemanagement.service.impl;

import lombok.extern.slf4j.Slf4j;
import ncu.huaxin.attendancemanagement.constant.Constant;
import ncu.huaxin.attendancemanagement.entity.Application;
import ncu.huaxin.attendancemanagement.entity.Employee;
import ncu.huaxin.attendancemanagement.entity.HolidayLog;
import ncu.huaxin.attendancemanagement.mapper.HolidayLogMapper;
import ncu.huaxin.attendancemanagement.service.HolidayLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author huaxin
 * @Date 2020/7/6
 */
@Service
@Slf4j
public class HolidayLogServiceImpl implements HolidayLogService {

    @Autowired
    private HolidayLogMapper holidayLogMapper;

    @Override
    public void addHolidayLog(HolidayLog holidayLog) {
        holidayLog.setLogTime(new Date());
        holidayLogMapper.addHolidayLog(holidayLog);
    }

    @Override
    public List<HolidayLog> selectByClassId(Integer classId) {
        return holidayLogMapper.selectByClassId(classId);
    }
}
