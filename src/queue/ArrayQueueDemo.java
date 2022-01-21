package queue;


import java.util.Scanner;

public class ArrayQueueDemo {

    public static void main(String[] args) {

        ArrayQueue arrayQueue = new ArrayQueue(3);
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;

        while (loop){

            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add): 添加数据到队列");
            System.out.println("g(get): 从队列取出数据");
            System.out.println("h(head): 查看队列头的数据");
            key = scanner.next().charAt(0);
            switch (key){
                case 's':
                    arrayQueue.showQueue();
                    break;
                case 'a':
                    System.out.println("输出一个数:");
                    int value = scanner.nextInt();
                    arrayQueue.addQueue(value);
                    break;
                case 'g':
                    try {
                        int res = arrayQueue.getQueue();
                        System.out.printf("取出的数据%d\n",res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int res = arrayQueue.headQueue();
                        System.out.printf("队列头的数据%d\n",res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    return;
                default:
                    break;
            }
        }
        System.out.println("程序退出...");

    }



    static class ArrayQueue{
        private int maxSize;
        private int front;
        private int rear;
        private int[] arr;

        // 创建队列的构造器
        public ArrayQueue(int arrMaxSize){
            maxSize = arrMaxSize;
            arr = new int[maxSize];
            front = -1; // 指向队列头部的前一个位置
            rear = -1; // 指向队列尾部的数据(最后一个)
        }

        // 判断队列是否满
        public boolean isFull(){
            return rear == maxSize - 1;
        }

        // 判断队列为空
        public boolean isEmpty(){
            return rear == front;
        }
        // 添加数据到队列
        public void addQueue(int n){
            // 判断队列是否满
            if(isFull()){
                System.out.println("队列满，不能加入队列");
                return;
            }
            rear++;
            arr[rear] = n;
        }

        // 获取队列中数据
        public int getQueue(){
            //判断队列是否空
            if(isEmpty()){
                throw new RuntimeException("队列空，不能取数据");
            }
            front++;
            return arr[front];
        }

        //显示队列中所有数据
        public void showQueue(){
            // 遍历
            if(isEmpty()){
                System.out.println("队列空，不能遍历");
                return;
            }
            for (int i = 0; i< arr.length;i++){
                System.out.printf("arr[%d] %d\n",i,arr[i]);

            }
        }

        // 显示队列的头数据，注意不是取出数据
        public int headQueue(){
            // 判断
            if(isEmpty()){
                throw new RuntimeException("队列空，没有数据");
            }
            return arr[front + 1];
        }

    }

}
