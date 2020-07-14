package ncu.huaxin.attendancemanagement.mapper;

import ncu.huaxin.attendancemanagement.entity.HolidayLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author huaxin
 * @Date 2020/7/6
 */
@Mapper
public interface HolidayLogMapper {

    /**
     * 增加假期记录表记录
     * @param holidayLog
     */
    void addHolidayLog(@Param("holidayLog") HolidayLog holidayLog);

    /**
     * 查找某一班组的申请记录
     * @param classId
     * @return
     */
    List<HolidayLog> selectByClassId(Integer classId);

}
