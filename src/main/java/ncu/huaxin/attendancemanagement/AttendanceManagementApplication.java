package ncu.huaxin.attendancemanagement;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("ncu.huaxin.attendancemanagement.mapper")
public class AttendanceManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(AttendanceManagementApplication.class, args);
    }

}
