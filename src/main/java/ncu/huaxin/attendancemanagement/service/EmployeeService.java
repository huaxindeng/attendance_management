package ncu.huaxin.attendancemanagement.service;

import ncu.huaxin.attendancemanagement.entity.Employee;

/**
 * @Author huaxin
 * @Date 2020/7/5
 */
public interface EmployeeService {

    Employee getEmpByLogin(String username,String password);

    Employee getEmployeeById(Integer id);

    void addEmployee(Employee employee);
}
