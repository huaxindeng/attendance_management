package ncu.huaxin.attendancemanagement.mapper;

import ncu.huaxin.attendancemanagement.entity.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * @Author huaxin
 * @Date 2020/7/5
 */
@Mapper
public interface EmployeeMapper {

    /**
     * 登录验证，查询用户是否存在
     * @param username
     * @param password
     * @return
     */
    Employee getEmpByLogin(String username, String password);

    /**
     * 根据id查询员工
     * @param id
     * @return
     */
    Employee getEmpById(Integer id);

    /**
     * 增加员工
     * @param employee
     * @return
     */
    Employee addEmp(@Param("employee") Employee employee);


}
