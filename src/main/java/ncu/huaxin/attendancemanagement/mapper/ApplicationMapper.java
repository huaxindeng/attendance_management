package ncu.huaxin.attendancemanagement.mapper;

import ncu.huaxin.attendancemanagement.entity.Application;
import ncu.huaxin.attendancemanagement.entity.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author huaxin
 * @Date 2020/7/6
 */
@Mapper
public interface ApplicationMapper {

    /**
     * 保存申请，暂不提交
     * @param application
     */
    void save(@Param("application") Application application);

    /**
     * 提交申请
     * @param application
     */
    //@Param当传入参数为对象时，需要利用该注解指定名称，因为mapper.xml中不能直接引用对象变量的名称
    void submit(@Param("application") Application application);

    /**
     * 取消申请
     * @param application
     */
    void cancel(@Param("application")Application application);

    /**
     * 修改申请
     * @param application
     */
    void modify(@Param("application") Application application);


    /**
     * 查看员工个人申请
     * @param userId
     */
    List<Application> selectByUserId(Integer userId,Integer applyState);

    /**
     * 查看班级申请
     * @param employee
     */
    List<Application> selectByClassId(@Param("employee") Employee employee,Integer applyState);

    /**
     * 查看部门申请
     * @param employee
     */
    List<Application> selectByDepartId(@Param("employee") Employee employee,Integer applyState);



    /**
     * 同意申请
     * @param application
     */
    void agree(@Param("application") Application application);

    /**
     * 拒绝申请
     * @param application
     */
    void deny(@Param("application") Application application);

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
    List<Application> getEmployeesByDepartId(@Param("employee") Employee employee,Integer applyState);

}


