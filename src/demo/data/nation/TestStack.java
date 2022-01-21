package demo.data.nation;

import java.util.Stack;

/**
 * @author codegt
 * @version 1.0
 * @Description:
 * @date 2022/1/13
 */
public class TestStack {

    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();

        stack.add("jack");
        stack.add("tom");
        stack.add("smith");

        while (stack.size() > 0){
            System.out.println(stack.pop());
        }


    }


}
