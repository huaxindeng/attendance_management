package ncu.huaxin.attendancemanagement.service;

import ncu.huaxin.attendancemanagement.entity.Employee;

import java.util.List;

/**
 * @Author huaxin
 * @Date 2020/7/5
 */
public interface EmployeeService {

    Employee getEmpByLogin(String username,String password);

    Employee getEmployeeById(Integer id);

    void addEmployee(Employee employee);

    /**
     * 通过名字模糊查询员工
     * @param username
     * @return
     */
    List<Employee> selectEmpByName(String username);
}
