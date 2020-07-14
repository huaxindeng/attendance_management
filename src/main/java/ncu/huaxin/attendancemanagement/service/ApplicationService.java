package ncu.huaxin.attendancemanagement.service;

import ncu.huaxin.attendancemanagement.entity.Application;

import java.util.List;

/**
 * @Author huaxin
 * @Date 2020/7/6
 */
public interface ApplicationService {

    /**
     * 保存申请，暂不提交
     * @param application
     */
    void save(Application application);

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
    List<Application> selectByUserId(Integer userId);

    /**
     * 查看班级申请
     * @param classId
     */
    List<Application> selectByClassId(Integer classId);

    /**
     * 查看部门申请
     * @param departId
     */
    List<Application> selectByDepartId(Integer departId);

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
}
