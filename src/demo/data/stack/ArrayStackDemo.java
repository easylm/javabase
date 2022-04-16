package demo.data.stack;

import java.util.Scanner;

/**
 * @author code
 * @version 1.0
 * @Description:
 * @date 2022/1/14
 */
public class ArrayStackDemo {

    public static void main(String[] args) {

        ArrayStack stack = new ArrayStack(4);
        String key = "";
        //控制是否退出菜单
        boolean loop = true;

        Scanner scanner = new Scanner(System.in);
        while (loop){
            System.out.println("show: 表示显示栈");
            System.out.println("exit: 表示退出栈");
            System.out.println("push: 表示添加数据到栈(入栈)");
            System.out.println("pop: 表示从栈取出数据(出栈)");
            System.out.println("请输入你的选择");

            key = scanner.nextLine();

            switch (key){
                case "show":
                    stack.list();
                    break;
                case "push":
                    System.out.println("请输入一个数");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try{
                        int res = stack.pop();
                        System.out.println("出栈的数据为："+res);

                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;

                case "exit":
                    scanner.close();
                    loop = false;
                    break;


                default:break;
            }

        }
        System.out.println("程序退出");


    }

}

// 定义
class ArrayStack{
    /**
     * 栈的大小
     */
    private int maxSize;
    /**
     * 数组模拟栈
     */
    private int[] stack;
    /**
     * top表示栈顶，初始化为-1
     */
    private int top = -1;

    /**
     * 构造器
     * @param maxSize
     */
    public ArrayStack(int maxSize){
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    /**
     * 栈满
     * @return
     */
    public boolean isFull(){
        return top == maxSize-1;
    }

    /**
     * 栈空
     * @return
     */
    public boolean isEmpty(){
        return top == -1;
    }

    /**
     * 入栈-push
     * @param value
     */
    public void push(int value){
        // 先判断栈是否满
        if(isFull()){
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = value;
    }

    /**
     * 出栈
     * @return
     */
    public int pop(){
        if (isEmpty()){
            throw new RuntimeException("栈空，没有数据~");
        }
        int value = stack[top];

        top --;
        return value;
    }

    /**
     * 显示栈的情况[遍历栈]，遍历时，需要从栈顶开始显示数据
     */
    public void list(){
        if (isEmpty()){
            System.out.println("栈空，没有数据~");
            return;
        }
        for (int i = top; i >= 0; i--){
            System.out.printf("stack[%d] = %d\n",i,stack[i]);
        }

    }







}
