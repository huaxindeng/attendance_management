package ncu.huaxin.attendancemanagement.constant;

/**
 * @Author huaxin
 * @Date 2020/7/6
 */
public interface Constant {

    /**
     * 申请的四种类型
     */
    String APPLICATION_TYPE_GONGXIU = "公休";
    String APPLICATION_TYPE_BUXIU = "补休";
    String APPLICATION_TYPE_QINGJIA = "请假";
    String APPLICATION_TYPE_JIABAN = "加班";

    /**
     * 申请的状态:提交、修改、取消、同意、拒绝
     */

    String APPLICATION_STATE_SUBMIT = "提交";
    String APPLICATION_STATE_MODIFY = "修改";
    String APPLICATION_STATE_CANCEL = "取消";
    String APPLICATION_STATE_AGREE = "同意";
    String APPLICATION_STATE_DENY = "拒绝";

    /**
     * 员工类型
     */

    String EMPLOYEE_POSITION_STAFF = "员工";
    String EMPLOYEE_POSITION_MONITOR = "班长";
    String EMPLOYEE_POSITION_LEADER = "领导";

    /**
     * 假期申请类型
     */

    String HOLIDAY_TYPE_GONGXIU = "公休";
    String HOLIDAY_TYPE_BUXIU = "补休";
    String HOLIDAY_TYPE_QINGJIA = "请假";

    /**
     * 假期申请状态
     */
    String HOLIDAY_STATE_SUBMIT = "申请";
    String HOLIDAY_STATE_MODIFY = "修改";
    String HOLIDAY_STATE_CANCEL = "取消";

    /**
     * 登入登出类型
     */
    String INOUT_TYPE_IN = "登入";
    String INOUT_TYPE_OUT = "登出";

    /**
     * 其他
     * 1. 页面显示条数
     */
    Integer PAGE_SIZE=10;
}
