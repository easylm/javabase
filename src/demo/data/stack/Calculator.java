package demo.data.stack;

/**
 * @author codegt
 * @version 1.0
 * @Description:
 * @date 2022/1/19
 */
public class Calculator {

    public static void main(String[] args) {
        // 完成表达式运算
        String expression = "3+2*6-2";
        // 创建两个栈，数栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);

        //定义相关变量
        int index = 0;
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        // 将每次扫描到char保存到ch
        char ch = ' ';
        // 循环扫描expression
        while (true){
            // 依次得到expression的每一个字符
            ch = expression.substring(index,index+1).charAt(0);
            // 判断ch是什么
            if(operStack.isOper(ch)){
                //判断当前符号栈是否为空
                if(!operStack.isEmpty()){
                    /**
                     *  //如果符号栈有操作符就进行比较，如果当前操作符的优先级小于或等于栈中的操作符,就需要从数栈中pop出两个数
                     *  // 在从符号栈中pop出一个符号，进行运算，将得到结果，入数栈，然后将当前的操作符入符号栈
                     */
                    if(operStack.priority(ch) <=operStack.priority( operStack.peek())){
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1,num2,oper);
                        // 将运算的结果入数栈
                        numStack.push(res);
                        // 将当前的操作符入符号栈
                        operStack.push(ch);
                    }else {
                        // 如果当前的操作符的优先级大于栈中的操作符，就直接写入符号栈
                        operStack.push(ch);
                    }
                }else {
                    // 如果为空接入符号栈
                    operStack.push(ch);
                }

            }

        }



    }


}

    // 定义
    class ArrayStack2 {
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
         *
         * @param maxSize
         */
        public ArrayStack2(int maxSize) {
            this.maxSize = maxSize;
            stack = new int[maxSize];
        }

        /**
         * 增加一个返回当前栈顶的值，但是不是真正的pop
         */
        public int peek(){
            return stack[top];
        }



        /**
         * 栈满
         *
         * @return
         */
        public boolean isFull() {
            return top == maxSize - 1;
        }

        /**
         * 栈空
         *
         * @return
         */
        public boolean isEmpty() {
            return top == -1;
        }

        /**
         * 入栈-push
         *
         * @param value
         */
        public void push(int value) {
            // 先判断栈是否满
            if (isFull()) {
                System.out.println("栈满");
                return;
            }
            top++;
            stack[top] = value;
        }

        /**
         * 出栈
         *
         * @return
         */
        public int pop() {
            if (isEmpty()) {
                throw new RuntimeException("栈空，没有数据~");
            }
            int value = stack[top];

            top--;
            return value;
        }

        /**
         * 显示栈的情况[遍历栈]，遍历时，需要从栈顶开始显示数据
         */
        public void list() {
            if (isEmpty()) {
                System.out.println("栈空，没有数据~");
                return;
            }
            for (int i = top; i >= 0; i--) {
                System.out.printf("stack[%d] = %d\n", i, stack[i]);
            }

        }

        /**
         * 返回运算符的优先级，优先级式程序员来确定，优先级使用数字表示
         * 数字越大，则优先级就越高
         * @param oper
         * @return
         */
        public int priority(int oper){
            if(oper == '*' || oper =='/' ){
                return 1;
            }else if(oper == '+' || oper == '-'){
                return 0;
            }else {
                // 假定目前的表达式只有加减乘除
                return -1;
            }
        }

        /**
         *  判断是不是一个运算符
         * @param val
         * @return
         */
        public boolean isOper(char val){
            return val == '+' ||  val == '-'||val == '*'||val == '/';
        }

        /**
         * 计算方法
         * @param num1
         * @param num2
         * @param oper
         * @return
         */
        public  int cal(int num1,int num2,int oper){

            int res = 0;
            switch (oper){
                case '+':
                    res = num1 + num2;
                    break;

                case '-':
                    res = num1-num2;
                    break;
                case '*':
                    res = num1 * num2;
                    break;
                case '/':
                    res = num2/num1;
                    break;
            }
            return res;

        }


    }

