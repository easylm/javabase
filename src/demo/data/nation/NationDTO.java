package demo.data.nation;

import java.io.Serializable;

/**
 * @author codegt
 * @version 1.0
 * @Description:
 * @date 2022/1/13
 */

public class NationDTO implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 8238210985250209918L;
    /**
     * 民族名称
     */
    private String nationName;

    /**
     * 民族id
     */
    private Integer nationId;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getNationName() {
        return nationName;
    }

    public void setNationName(String nationName) {
        this.nationName = nationName;
    }

    public Integer getNationId() {
        return nationId;
    }

    public void setNationId(Integer nationId) {
        this.nationId = nationId;
    }

    public NationDTO(String nationName, Integer nationId) {
        this.nationName = nationName;
        this.nationId = nationId;
    }
    public NationDTO(){}

    @Override
    public String toString() {
        return "NationDTO{" +
                "nationName='" + nationName + '\'' +
                ", nationId=" + nationId +
                '}';
    }
}