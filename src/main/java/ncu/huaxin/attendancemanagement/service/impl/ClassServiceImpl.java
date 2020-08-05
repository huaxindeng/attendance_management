package ncu.huaxin.attendancemanagement.service.impl;

import ncu.huaxin.attendancemanagement.mapper.ClassMapper;
import ncu.huaxin.attendancemanagement.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author huaxin
 * @Date 2020/7/11
 */
@Service
public class ClassServiceImpl implements ClassService {

    @Autowired
    private ClassMapper classMapper;
    @Override
    public ncu.huaxin.attendancemanagement.entity.Class selectById(Integer classId) {
        return classMapper.selectById(classId);
    }
}
