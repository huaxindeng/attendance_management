package ncu.huaxin.attendancemanagement.mapper;

import ncu.huaxin.attendancemanagement.entity.ApplyType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author huaxin
 * @Date 2020/7/6
 */
@Mapper
public interface ApplyTypeMapper {

    List<ApplyType> getAll();
}
