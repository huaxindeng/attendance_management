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
public class LogInOut implements Serializable {

    private Integer inoutId;
    private Employee employee;
    private String inoutType;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date inoutTime;
    private Integer classId;
    private Integer departId;

    private Integer userId;
    private ncu.huaxin.attendancemanagement.entity.Class classOf;
    private Department department;

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "LogInOut{" +
                "inoutId=" + inoutId +
                ", employee=" + employee +
                ", inoutType='" + inoutType + '\'' +
                ", inoutTime=" + inoutTime +
                ", classId=" + classId +
                ", departId=" + departId +
                ", userId=" + userId +
                ", classOf=" + classOf +
                '}';
    }

    public ncu.huaxin.attendancemanagement.entity.Class getClassOf() {
        return classOf;
    }

    public void setClassOf(ncu.huaxin.attendancemanagement.entity.Class classOf) {
        this.classOf = classOf;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public Integer getInoutId() {
        return inoutId;
    }

    public void setInoutId(Integer inoutId) {
        this.inoutId = inoutId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getInoutType() {
        return inoutType;
    }

    public void setInoutType(String inoutType) {
        this.inoutType = inoutType;
    }

    public Date getInoutTime() {
        return inoutTime;
    }

    public void setInoutTime(Date inoutTime) {
        this.inoutTime = inoutTime;
    }
}
