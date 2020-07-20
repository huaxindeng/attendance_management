package ncu.huaxin.attendancemanagement.service.impl;

import ncu.huaxin.attendancemanagement.entity.Class;
import ncu.huaxin.attendancemanagement.entity.Department;
import ncu.huaxin.attendancemanagement.mapper.ClassMapper;
import ncu.huaxin.attendancemanagement.mapper.DepartmentMapper;
import ncu.huaxin.attendancemanagement.service.ClassService;
import ncu.huaxin.attendancemanagement.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author huaxin
 * @Date 2020/7/11
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;
    @Override
    public Department selectById(Integer departId) {
        return departmentMapper.selectById(departId);
    }
}
