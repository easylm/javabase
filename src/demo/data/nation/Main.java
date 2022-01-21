package demo.data.nation;

import demo.data.NationEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * @author codegt
 * @version 1.0
 * @Description:
 * @date 2022/1/12
 */
public class Main {

    public static void main(String[] args) {
        List<NationDTO> list = new ArrayList<>();

        NationEnum[] values = NationEnum.values();
        for (int i = 0; i< values.length;i++){

            NationDTO nationDTO = new NationDTO();
            nationDTO.setNationId(values[i].getKey());
            nationDTO.setNationName(values[i].getValue());
            list.add(nationDTO);
            //System.out.println(list);
            //System.out.println(values[i].getKey()+values[i].getValue());
        }
        System.out.println(list);
    }

}
