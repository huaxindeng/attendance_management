package ncu.huaxin.attendancemanagement.service.impl;

import lombok.extern.slf4j.Slf4j;
import ncu.huaxin.attendancemanagement.constant.Constant;
import ncu.huaxin.attendancemanagement.entity.Application;
import ncu.huaxin.attendancemanagement.entity.Employee;
import ncu.huaxin.attendancemanagement.entity.HolidayLog;
import ncu.huaxin.attendancemanagement.entity.PageBean;
import ncu.huaxin.attendancemanagement.mapper.ApplicationMapper;
import ncu.huaxin.attendancemanagement.mapper.HolidayLogMapper;
import ncu.huaxin.attendancemanagement.service.ApplicationService;
import ncu.huaxin.attendancemanagement.service.HolidayLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author huaxin
 * @Date 2020/7/6
 */
@Service
@Slf4j
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    private ApplicationMapper applicationMapper;

    @Autowired
    private HolidayLogService holidayLogService;

    @Override
    public void save(Application application) {

    }

    @Override
    public void submit(Application application) {
        applicationMapper.submit(application);
        //获取当前用户
        Employee employee = application.getEmployee();
        //封装假期记录，并保存
        HolidayLog holidayLog = new HolidayLog();
        holidayLog.setEmployee(employee);
        holidayLog.setHolidayType(application.getApplyType());
        holidayLog.setHolidayState(Constant.HOLIDAY_STATE_SUBMIT);

        log.info("************HolidayLogServiceImpl.submit:application-->"+application.toString());
        log.info("************HolidayLogServiceImpl.submit:holidayLog-->"+holidayLog.toString());

        holidayLogService.addHolidayLog(holidayLog);

        log.info("************ApplicationServiceImpl.submit:application-->"+application.toString());
    }

    @Override
    public void cancel(Application application) {
        applicationMapper.cancel(application);

        //获取当前用户
        Employee employee = application.getEmployee();
        //封装假期记录，并保存
        HolidayLog holidayLog = new HolidayLog();
        holidayLog.setEmployee(employee);
        holidayLog.setHolidayType(application.getApplyType());
        holidayLog.setHolidayState(Constant.HOLIDAY_STATE_CANCEL);

        log.info("************HolidayLogServiceImpl.cancel:application-->"+application.toString());
        log.info("************HolidayLogServiceImpl.cancel:holidayLog-->"+holidayLog.toString());

        holidayLogService.addHolidayLog(holidayLog);

        log.info("************ApplicationServiceImpl.cancel:application-->"+application.toString());
    }

    @Override
    public void modify(Application application) {
        applicationMapper.modify(application);

        //获取当前用户
        Employee employee = application.getEmployee();
        //封装假期记录，并保存
        HolidayLog holidayLog = new HolidayLog();
        holidayLog.setEmployee(employee);
        holidayLog.setHolidayType(application.getApplyType());
        holidayLog.setHolidayState(Constant.HOLIDAY_STATE_MODIFY);

        log.info("************HolidayLogServiceImpl.modify:application-->"+application.toString());
        log.info("************HolidayLogServiceImpl.modify:holidayLog-->"+holidayLog.toString());

        holidayLogService.addHolidayLog(holidayLog);
        log.info("************ApplicationServiceImpl.modify:application-->"+application.toString());
    }

    @Override
    public List<Application> selectByUserId(Integer userId, Integer applyState) {
        return applicationMapper.selectByUserId(userId,applyState);
    }

    @Override
    public List<Application> selectByClassId(Employee employee,Integer applyState) {
        return applicationMapper.selectByClassId(employee, applyState);
    }

//    @Override
//    public List<Application> selectByDepartId(Employee employee,Integer applyState) {
//        return applicationMapper.selectByDepartId(employee,applyState);
//    }

    @Override
    public List<Application> selectByDepartId(Employee employee, Integer applyState) {
        return applicationMapper.selectByDepartId(employee,applyState);
    }

    @Override
    public void agree(Application application) {
        applicationMapper.agree(application);
    }

    @Override
    public void deny(Application application) {
        applicationMapper.deny(application);
    }

    @Override
    public Application selectById(Integer applyId) {
        return applicationMapper.selectById(applyId);
    }

    @Override
    public List<Application> getEmployeesByDepartId(Employee employee,Integer applyState) {
        return applicationMapper.getEmployeesByDepartId(employee,applyState);
    }
}
