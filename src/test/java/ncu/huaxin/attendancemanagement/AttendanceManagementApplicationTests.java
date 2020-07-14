package ncu.huaxin.attendancemanagement;

import ncu.huaxin.attendancemanagement.constant.Constant;
import ncu.huaxin.attendancemanagement.entity.Application;
import ncu.huaxin.attendancemanagement.service.ApplicationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AttendanceManagementApplicationTests {

    @Autowired
    private ApplicationService applicationService;

    @Test
    void contextLoads() {
    }

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
}
