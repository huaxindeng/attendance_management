package ncu.huaxin.attendancemanagement.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
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
public class Employee implements Serializable {

    private Integer userId;
    private String username;
    private String sex;

    private String password;
    private Date birthday;
    private String position;

    private Integer departId;
    private Integer classId;
    private String email;

    @Override
    public String toString() {
        return "Employee{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", sex='" + sex + '\'' +
                ", password='" + password + '\'' +
                ", birthday=" + birthday +
                ", position='" + position + '\'' +
                ", departId=" + departId +
                ", classId=" + classId +
                ", email='" + email + '\'' +
                '}';
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getDepartId() {
        return departId;
    }

    public void setDepartId(Integer departId) {
        this.departId = departId;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
