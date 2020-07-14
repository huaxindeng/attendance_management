package ncu.huaxin.attendancemanagement.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;

/**
 * @Author huaxin
 * @Date 2020/7/5
 */
@Component
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class Department implements Serializable {

    private Integer departId;
    private String departName;
    private String description;

    @Override
    public String toString() {
        return "Department{" +
                "departId=" + departId +
                ", departName='" + departName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public Integer getDepartId() {
        return departId;
    }

    public void setDepartId(Integer departId) {
        this.departId = departId;
    }

    public String getDepartName() {
        return departName;
    }

    public void setDepartName(String departName) {
        this.departName = departName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
