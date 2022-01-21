package demo;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class demo1 {

    public static void main(String[] args) {

        Map<String, String> map = new HashMap<>();

        map.put("zonghe","0.46");

        System.out.println(map.get("zonghe"));

        Double d = 114.1;

        Double zonghe1 = Double.valueOf(map.get("zonghe"));

        DecimalFormat df = new DecimalFormat("#.0");

        String str = df.format(zonghe1);
        Double aDouble = Double.valueOf(str);

        System.out.println("String装double后"+str);
        System.out.println(aDouble);





        if(Objects.isNull(map.get("zonghe"))) {
            System.out.println(1111);
        }else{
            String zonghe = map.get("zonghe");

            String substring = zonghe.substring(0, 3);
            //System.out.println("截取后："+substring);

            Double scoreFlag = Double.valueOf(zonghe);
            System.out.println(scoreFlag);
            if (scoreFlag < 3) {
                System.out.println("暂无评分");
            } else {
                System.out.println(zonghe);
            }
            // todo 星级展示
            if (scoreFlag <= 3) {
                System.out.println("grey");
            } else if (3 < scoreFlag && 4 >= scoreFlag) {
                System.out.println("four");
            } else if (4 < scoreFlag && 5 >= scoreFlag) {
                System.out.println("five");
            }
        }
        System.out.println("........");



    }

}
