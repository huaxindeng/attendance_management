package ncu.huaxin.attendancemanagement.entity;

/**
 * @Author huaxin
 * @Date 2020/7/6
 */
public class ApplyType {

    private Integer applyTypeId;
    private String applyType;

    @Override
    public String toString() {
        return "ApplyType{" +
                "applyTypeId=" + applyTypeId +
                ", applyType='" + applyType + '\'' +
                '}';
    }

    public Integer getApplyTypeId() {
        return applyTypeId;
    }

    public void setApplyTypeId(Integer applyTypeId) {
        this.applyTypeId = applyTypeId;
    }

    public String getApplyType() {
        return applyType;
    }

    public void setApplyType(String applyType) {
        this.applyType = applyType;
    }
}
