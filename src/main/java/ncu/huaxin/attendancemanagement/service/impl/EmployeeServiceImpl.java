package ncu.huaxin.attendancemanagement.service.impl;

import ncu.huaxin.attendancemanagement.entity.Employee;
import ncu.huaxin.attendancemanagement.mapper.EmployeeMapper;
import ncu.huaxin.attendancemanagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author huaxin
 * @Date 2020/7/5
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public Employee getEmpByLogin(String username, String password) {
        return employeeMapper.getEmpByLogin(username,password);
    }

    @Override
    public Employee getEmployeeById(Integer id) {
        return employeeMapper.getEmpById(id);
    }

    @Override
    public void addEmployee(Employee employee) {
        employeeMapper.addEmp(employee);
    }

    @Override
    public List<Employee> selectEmpByName(String username) {
        return employeeMapper.selectEmpByName(username);
    }
}
