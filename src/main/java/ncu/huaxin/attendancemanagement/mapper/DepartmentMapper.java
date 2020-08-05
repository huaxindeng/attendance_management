package ncu.huaxin.attendancemanagement.mapper;

import ncu.huaxin.attendancemanagement.entity.Department;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author huaxin
 * @Date 2020/7/11
 */
@Mapper
public interface DepartmentMapper {
    /**
     * 通过classId查找指定class
     * @param classId
     * @return
     */
    Department selectById(Integer classId);
}
