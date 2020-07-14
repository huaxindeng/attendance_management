package ncu.huaxin.attendancemanagement.service.impl;

import ncu.huaxin.attendancemanagement.entity.ApplyType;
import ncu.huaxin.attendancemanagement.mapper.ApplyTypeMapper;
import ncu.huaxin.attendancemanagement.service.ApplyTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author huaxin
 * @Date 2020/7/6
 */
@Service
public class ApplyTypeServiceImpl implements ApplyTypeService {

    @Autowired
    private ApplyTypeMapper applyTypeMapper;

    @Override
    public List<ApplyType> getAll() {
        return applyTypeMapper.getAll();
    }
}
