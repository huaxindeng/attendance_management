package ncu.huaxin.attendancemanagement.service;

import ncu.huaxin.attendancemanagement.entity.Class;
import ncu.huaxin.attendancemanagement.entity.Department;

/**
 * @Author huaxin
 * @Date 2020/7/11
 */
public interface DepartmentService {

    Department selectById(Integer departId);
}
