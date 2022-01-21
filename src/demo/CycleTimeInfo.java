package demo;

/**
 * @author ext.wangfuqiang5
 * @version 1.0
 * @Description:
 * @date 2022/1/6
 */

public class CycleTimeInfo {

    private static final long serialVersionUID = -5378210416860292735L;
    /**
     * 周几
     *
     */
    private Integer cycleWeek;

    /**
     * 时间 8:00
     */
    private String cycleTime;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getCycleWeek() {
        return cycleWeek;
    }

    public void setCycleWeek(Integer cycleWeek) {
        this.cycleWeek = cycleWeek;
    }

    public String getCycleTime() {
        return cycleTime;
    }

    public void setCycleTime(String cycleTime) {
        this.cycleTime = cycleTime;
    }

    public CycleTimeInfo(Integer cycleWeek) {
        this.cycleWeek = cycleWeek;
    }

    public CycleTimeInfo(String cycleTime) {
        this.cycleTime = cycleTime;
    }
}
