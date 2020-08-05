package ncu.huaxin.attendancemanagement.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author huaxin
 * @Date 2020/7/5
 */
@Component
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class HolidayLog implements Serializable {

    private Integer holidayId;
    private Employee employee;
    private Integer applyId;

    private String holidayType;
    private String holidayState;
    private Integer classId;

    private Integer departId;
    private ncu.huaxin.attendancemanagement.entity.Class classOf;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date logTime;

    private Integer userId;
    private Application application;
    private Department department;

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "HolidayLog{" +
                "holidayId=" + holidayId +
                ", employee=" + employee +
                ", applyId=" + applyId +
                ", holidayType='" + holidayType + '\'' +
                ", holidayState='" + holidayState + '\'' +
                ", classId=" + classId +
                ", departId=" + departId +
                ", classOf=" + classOf +
                ", logTime=" + logTime +
                ", userId=" + userId +
                ", application=" + application +
                '}';
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public ncu.huaxin.attendancemanagement.entity.Class getClassOf() {
        return classOf;
    }

    public void setClassOf(ncu.huaxin.attendancemanagement.entity.Class classOf) {
        this.classOf = classOf;
    }

    public Date getLogTime() {
        return logTime;
    }

    public void setLogTime(Date logTime) {
        this.logTime = logTime;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Integer getDepartId() {
        return departId;
    }

    public void setDepartId(Integer departId) {
        this.departId = departId;
    }

    public Integer getHolidayId() {
        return holidayId;
    }

    public void setHolidayId(Integer holidayId) {
        this.holidayId = holidayId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Integer getApplyId() {
        return applyId;
    }

    public void setApplyId(Integer applyId) {
        this.applyId = applyId;
    }

    public String getHolidayType() {
        return holidayType;
    }

    public void setHolidayType(String holidayType) {
        this.holidayType = holidayType;
    }

    public String getHolidayState() {
        return holidayState;
    }

    public void setHolidayState(String holidayState) {
        this.holidayState = holidayState;
    }
}
