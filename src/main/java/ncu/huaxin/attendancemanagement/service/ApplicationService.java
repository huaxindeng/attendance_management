package ncu.huaxin.attendancemanagement.service;

import ncu.huaxin.attendancemanagement.entity.Application;
import ncu.huaxin.attendancemanagement.entity.Employee;

import java.util.List;

/**
 * @Author huaxin
 * @Date 2020/7/6
 */
public interface ApplicationService {


    /**
     * 提交申请
     * @param application
     */
    void submit(Application application);

    /**
     * 取消申请
     * @param application
     */
    void cancel(Application application);

    /**
     * 修改申请
     * @param application
     */
    void modify(Application application);

    /**
     * 查看员工个人申请
     * @param userId
     */
    List<Application> selectByUserId(Integer userId,Integer applyState);

    /**
     * 查看班级申请
     * @param employee
     */
    List<Application> selectByClassId(Employee employee,Integer applyState);


    /**
     * 查看部门申请
     * @param
     */
    List<Application> selectByDepartId(Employee employee, Integer applyState);

    /**
     * 同意申请
     * @param application
     */
    void agree(Application application);

    /**
     * 拒绝申请
     * @param application
     */
    void deny(Application application);

    /**
     * 查询当前申请id
     * @param applyId
     * @return
     */
    Application selectById(Integer applyId);

    /**
     * 查询今明两天的申请人员
     * @param employee
     * @return
     */
    List<Application> getEmployeesByDepartId(Employee employee,Integer applyState);
}
