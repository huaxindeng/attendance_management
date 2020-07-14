package ncu.huaxin.attendancemanagement.mapper;

import ncu.huaxin.attendancemanagement.entity.Class;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author huaxin
 * @Date 2020/7/11
 */
@Mapper
public interface ClassMapper {
    /**
     * 通过classId查找指定class
     * @param classId
     * @return
     */
    Class selectById(Integer classId);
}
