package demo.data;

import java.util.HashMap;
import java.util.Map;

/**
 * 民族枚举类
 * @author ext.wangfuqiang5
 * @date 2022-01-12 17：04
 */
public enum NationEnum {

    /**
     * 请求失败
     */
    HAN_NATIONALITY(1, "汉族"),
    ACHANG_NATIONLITY(2,"阿昌族"),
    EWENKI_NATIONLITY(3,"鄂温克族"),
    LISU_NATIONLITY(4,"傈僳族"),
    SHUI_NATIONLITY(5,"水族"),
    BAI_NATIONLITY(6,"白族"),
    GAOSHAN_NATIONLITY(7,"高山族"),
    LHOBA_NATIONLITY(8,"珞巴族"),
    TAJIK_NATIONLITY(9,"塔吉克族"),
    BAOAN_NATIONLITY(10,"保安族"),
    GELAO_NATIONLITY(11,"仡佬族"),
    MAN_NATIONLITY(12,"满族"),
    TATAR_NATIONLITY(13,"塔塔尔族"),
    BRAUN_NATIONLITY(14,"布朗族"),
    HANI_NATIONLITY(15, "哈尼族"),
    MAONAN_NATIONLITY(16,"毛南族"),
    TUJIA_NATIONLITY(17,"土家族"),
    BUYI_NATIONLITY(18,"布依族"),
    KAZAK_NATIONLITY(19, "哈萨克族"),
    MENBA_NATIONLITY(20, "门巴族"),
    TU_NATIONLITY(21,"土族"),
    KOREAN_NATIONLITY(22,"朝鲜族"),
    MENGGU_NATIONLITY(23,"蒙古族"),
    WA_NATIONLITY(24, "佤族"),
    DAUR_NATIONLITY(25,"达斡尔族"),
    HEZHE_NATIONLITY(26,"赫哲族"),
    UYGUR_NATIONLITY(27,"维吾尔族"),
    DAI_NATIONLITY(28,"傣族"),
    HUI_NATIONLITY(29,"回族"),
    MULAO_NATIONLITY(30,"仫佬族"),
    UZBEK_NATIONLITY(31,"乌孜别克族"),
    DEANG_NATIONLITY(32,"德昂族"),
    JINO_NATIONLITY(33, "基诺族"),
    NAXI_NATIONLITY(34,"纳西族"),
    XIBO_NATIONLITY(35,"锡伯族"),
    DONGXIANG_NATIONLITY(36,"东乡族"),
    JING_NATIONLITY(37,"京族"),
    NU_NATIONLITY(38,"怒族"),
    YAO_NATIONLITY(39,"瑶族"),
    DONG_NATIONLITY(40,"侗族"),
    JINGPO_NATIONLITY(41,"景颇族"),
    PUMI_NATIONLITY(42,"普米族"),
    YI_NATIONLITY(43,"彝族"),
    DULONG_NATIONLITY(44,"独龙族"),
    KIRGIZ_NATIONLITY(45,"柯尔克孜族"),
    QIANG_NATIONLITY(46,"羌族"),
    YUGUR_NATIONLITY(47,"裕固族"),
    RUSSIAN_NATIONLITY(48,"俄罗斯族"),
    LAHU_NATIONLITY(49, "拉祜族"),
    SALAR_NATIONLITY(50,"撒拉族"),
    ZANG_NATIONLITY(51,"藏族"),
    OROQEN_NATIONLITY(52,"鄂伦春族"),
    LI_NATIONLITY(53,"黎族"),
    SHE_NATIONLITY(54,"畲族"),
    ZHUANG_NATIONLITY(55,"壮族"),
    MIAO_NATIONLITY(56,"苗族"),
    ;


    /**
     * key
     */
    private final Integer key;
    /**
     * value
     */
    private final String value;

    public Integer getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    /**
     * 全参构造
     *
     * @param key
     * @param value
     */
    NationEnum(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    /**
     * 所有枚举
     */
    public static Map<String, String> nationMap = new HashMap<String, String>();

    static {
        NationEnum[] enums = NationEnum.values();
        for (NationEnum nationEnum : enums) {
            nationMap.put(nationEnum.key.toString(), nationEnum.value);
        }
    }
}
