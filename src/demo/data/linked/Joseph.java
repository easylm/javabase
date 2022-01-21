package demo.data.linked;

import javax.sql.rowset.FilteredRowSet;

/**
 * @author git
 * @version 1.0
 * @Description: 双向链表-约瑟夫
 * @date 2022/1/13
 */
public class Joseph {

    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(5);
        circleSingleLinkedList.showBoy();

        //测试小孩出圈
        circleSingleLinkedList.countBoy(1,2,5);


    }

}

// 创建一个环形的单向链表
class CircleSingleLinkedList{
    // 创建一个first节点，当前没有编号
    private Boy first = new Boy(-1);
    // 添加小孩节点，构建成一个环形链表
    public void addBoy(int nums){
        // nums 做数据校验
        if(nums < 1){
            System.out.println("nums的值不正确");
            return;
        }
        Boy curBoy = null;  // 辅助变量，帮助构建环形链表
        // 使用for 来创建我们的环形链表
        for(int i = 1; i <= nums;i++){
            // 根据编号，创建小孩节点
            Boy boy = new Boy(i);
            //
            if(i == 1){
                first = boy;
                first.setNext(first); // 构成环
                curBoy = first; //让curBoy指向第一个孩子
            }else{
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }

    // 遍历当前的环形链表
    public void showBoy(){
        // 判断链表是否为空
        if(first == null){
            System.out.println("没有任何boy");
            return;
        }
        // 因为first不能动，因此我们仍然使用一个辅助指针完成遍历
        Boy curBoy = first;
        while (true){
            System.out.printf("孩子的编号 %d \n",curBoy.getNo());
            if(curBoy.getNext() == first){ //说明已经遍历完毕
                break;
            }
            curBoy = curBoy.getNext(); // curBoy后移
        }
    }

    // 根据用户的输入，计算孩子出圈的顺序

    /**
     *
     * @param startNo 表示从第几个孩子开始数数
     * @param countNum 表示数几下
     * @param nums 表示最初有多少小孩在圈里
     */
    public void countBoy(int startNo,int countNum,int nums){
        // 先对数据进行校验
        if(first == null || startNo < 1 || startNo > nums){

            System.out.println("参数输入有误，请重新输入");
            return;
        }
        // 创建辅助指针，帮助小孩出圈
        Boy helper = first;

        while(true){
            if(helper.getNext() == first){ // 说明helper指向最后小孩节点
                break;
            }
            helper = helper.getNext();
        }
        // 小孩报数前，先让first和helper 移动 k-1 次
        for(int j = 0;j < startNo - 1; j++){
            first = first.getNext();
            helper = helper.getNext();
        }

        // 当小孩报数时，让first和helper 指针同时的移动m -1次，然后出圈
        // 这里
        while (true){
            if (helper == first){
                break;
            }
            // 让first 和helper 指针同时 移动 countNum -1
            for (int j = 0;j < countNum - 1;j++){
                first = first.getNext();
                helper = helper.getNext();
            }
            System.out.printf("小孩 %d 出圈 \n" , first.getNo());
            // 这时将first 指向的小孩节点出圈
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.printf("最后留在圈中的小孩编号 %d \n",helper.getNext().getNo());

    }


}

class Boy{
    private int no;
    private Boy next;
    public Boy(int no){
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}

