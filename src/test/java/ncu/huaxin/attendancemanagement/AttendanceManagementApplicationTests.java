package ncu.huaxin.attendancemanagement;

import ncu.huaxin.attendancemanagement.constant.Constant;
import ncu.huaxin.attendancemanagement.entity.Application;
import ncu.huaxin.attendancemanagement.entity.ApplyType;
import ncu.huaxin.attendancemanagement.mapper.ApplyTypeMapper;
import ncu.huaxin.attendancemanagement.service.ApplicationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class AttendanceManagementApplicationTests {

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private ApplyTypeMapper applyTypeMapper;



    @Test
    void constantTest()
    {
        Application application = null;
        for(int i=0;i<8;i++){
            application = applicationService.selectById(i+1);

            System.out.println(application.getApplyState().equals(Constant.APPLICATION_STATE_SUBMIT));
//            System.out.println(Constant.APPLICATION_STATE_SUBMIT.equals("提交"));
        }
    }

    @Test
    void testGetAllType(){
        List<ApplyType> types = applyTypeMapper.getAll();
        System.out.println("-------start------");
        for(ApplyType applyType : types){
            System.out.println(applyType);
        }
        System.out.println("-------end------");
    }
}
