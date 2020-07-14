package ncu.huaxin.attendancemanagement.service.impl;

import ncu.huaxin.attendancemanagement.entity.Employee;
import ncu.huaxin.attendancemanagement.entity.LogInOut;
import ncu.huaxin.attendancemanagement.mapper.LogInOutMapper;
import ncu.huaxin.attendancemanagement.service.LogInOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author huaxin
 * @Date 2020/7/6
 */
@Service
public class LogInOutServiceImpl implements LogInOutService {

    @Autowired
    private LogInOutMapper logInOutMapper;

    @Override
    public void addLog(Employee employee, String type) {
        Date date = new Date();
        logInOutMapper.addLog(employee,type,date);
    }

    @Override
    public List<LogInOut> selectByClassId(Integer classId) {
        return logInOutMapper.selectByClassId(classId);
    }

    @Override
    public List<LogInOut> selectAll() {
        return logInOutMapper.selectAll();
    }
}
