package demo.data.linked;

import java.util.Stack;

/**
 * @author git
 * @version 1.0
 * @Description: 单链表
 * @date 2022/1/10
 */
public class SingleLinked {

    public static void main(String[] args) {
        HearNode node1 = new HearNode(1, "宋江", "及时雨");
        HearNode node2 = new HearNode(2, "卢俊义", "玉麒麟");
        HearNode node3 = new HearNode(3, "吴用", "智多星");
        HearNode node4 = new HearNode(4, "林冲", "豹子头");

        SingleLinkedList singleLinked = new SingleLinkedList();
        singleLinked.addByOrder(node1);
        singleLinked.addByOrder(node2);
        singleLinked.addByOrder(node3);
        singleLinked.addByOrder(node4);

        singleLinked.list();
        // 测试修改
        /*HearNode newHearNode = new HearNode(2, "小卢", "玉麒麟~~");
        singleLinked.update(newHearNode);

        singleLinked.list();
        //测试单链表节点个数
        System.out.println(getLength(singleLinked.getHead()));

        HearNode res = findLastIndexNode(singleLinked.getHead(),2);
        System.out.println(res);*/

        //reversetList(singleLinked.getHead());
        // 采用栈的模式 逆序打印单链表
        reversePrint(singleLinked.getHead());
        // singleLinked.list();

    }


    // 方法1:获取到单链表的节点的个数
    public static int getLength(HearNode head) {
        if (head.next == null) {
            return 0;
        }
        int length = 0;
        //定义一个辅助变量
        HearNode cur = head.next;
        while (cur != null) {
            length++;
            cur = cur.next;
        }
        return length;
    }

    /**
     * 方法2: 查找单链表中的倒数第K个节点
     * 1.编写方法接受head节点同时接受 index
     * 2.index 表示倒数的第index个节点
     * 3.先把链表从头到尾遍历，得到链表总的长度getlength
     * 4.得到size后，我们从链表的第一个开始遍历(size-index)个，就可以得到
     * 5.如果找到了，则返回该节点，否则返回null
     */
    public static HearNode findLastIndexNode(HearNode hearNode, int index) {
        if (hearNode.next == null) {
            return null;
        }
        int size = getLength(hearNode);
        if (index <= 0 || index > size) {
            return null;
        }
        // 定义辅助变量，for循环
        HearNode cur = hearNode.next;
        for (int i= 0;i<size-index;i++){
            cur = cur.next;
        }
        return cur;
    }

    /**
     * 方法3:单链表的反转
     *
     *
     */
    public static void reversetList(HearNode hearNode){

        if(hearNode.next == null || hearNode.next.next == null){
            return;
        }
        //帮助遍历原来的链表
        HearNode cur = hearNode.next;
        HearNode next = null; // 指向当前节点的下一个节点
        HearNode reverseHead = new HearNode(0,"","");
        // 遍历原来的链表，每遍历一个节点，就将其取出并放入新的链表reverHead的最前面
        while(cur != null){
            next = cur.next; // 先暂时保存当前节点的下一个节点，因为后面要用
            cur.next = reverseHead.next; // 将cur的下一个节点指向新的链表的最前端
            reverseHead.next = cur; // 将cur链接到新的链表
            cur = next; // 让cur后移

        }
        // 将head.next 指向reverseHead.next,实现单链表的反转
        hearNode.next = reverseHead.next;


    }

    /**
     * 逆序打印
     * @param hearNode
     */
    public static void reversePrint(HearNode hearNode){

        if (hearNode.next == null){
            return;
        }
        Stack<HearNode> stack = new Stack<HearNode>();

        HearNode cur = hearNode.next;
        // 将链表的所有节点压入栈
        while(cur != null){
            stack.push(cur);
            cur = cur.next; // cur 后移可以压入下一个节点
        }
        // 将栈中的节点进行打印，pop出栈
        while(stack.size() > 0){
            System.out.println(stack.pop());
        }


    }


}

// 定义singleLinkedList 管理我们的英雄
class SingleLinkedList {
    // 先定义Head节点，表示单链表的头
    private HearNode head = new HearNode(0, "", "");

    //返回头节点
    public HearNode getHead() {
        return head;
    }

    /**
     * 1.添加节点到单向链表
     * 思路：当不考虑编号顺序时
     * 1.找到当前链表的最后节点
     * 2.将最后这个节点的next指向新的节点
     */
    public void add(HearNode hearNode) {
        HearNode temp = head;
        // 遍历链表，找到最后
        while (true) {
            // 找到链表的最后
            if (temp.next == null) {
                break;
            }
            // 如果没有找到最后，将temp后移
            temp = temp.next;
        }
        // 当退出循环后，temp指向了链表的最后
        // 将最后这个节点的next指向新的节点
        temp.next = hearNode;
    }

    // 第二种添加英雄的方式，根据排名将英雄插入指定位置
    // 如果有这个排名，则添加失败，并给出提示
    public void addByOrder(HearNode hearNode) {
        // 因为头节点不能动，因此我们仍然通过一个辅助指针(变量)来帮助添加的位置
        // 因为单链表，我们找到的temp
        HearNode temp = head;
        boolean flag = false;

        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no > hearNode.no) {
                // 位置找到，就在temp的后面插入
                break;
            } else if (temp.next.no == hearNode.no) {
                // 说明希望添加的hearNode的编号已然存在
                flag = true; //
                break;
            }
            temp = temp.next; // 后移遍历当前链表
        }
        // 判断flag的值
        if (flag) {
            System.out.printf("准备插入的英雄编号已存在，无法插入\n", hearNode.no);
        } else {
            hearNode.next = temp.next;
            temp.next = hearNode;
        }
    }

    /**
     * 删除节点
     * 1.head 不能动，因此我们temp
     */
    public void del(int no) {
        HearNode temp = head;

        boolean flag = false;

        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no == no) {
                // 待删除节点的前一个节点temp
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.next = temp.next.next;
        } else {
            System.out.printf("要删除的%d,节点不存在\n", no);
        }
    }


    /**
     * 修改节点信息，根据编号来修改
     */
    public void update(HearNode newHearNode) {
        // 判断是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        HearNode temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.no == newHearNode.no) {
                // 找到
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //根据flag，判断是否找到要修改的节点
        if (flag) {
            temp.name = newHearNode.name;
            temp.nikeName = newHearNode.nikeName;
        } else {
            System.out.printf("没有找到编号 %d的节点，不能修改\n", newHearNode.no);
        }

    }


    // 显示链表(遍历)
    public void list() {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        // 因为头节点不能动，因此我们需要一个辅助变量来遍历
        HearNode temp = head.next;
        while (true) {
            // 判断是否到链表最后
            if (temp == null) {
                break;
            }
            // 输出节点信息
            System.out.println(temp);
            // 将next后移
            temp = temp.next;
        }
    }


}

class HearNode {
    public int no;
    public String name;
    public String nikeName;
    public HearNode next; // 指向下一个节点

    // 构造器
    public HearNode(int no, String name, String nikeName) {
        this.no = no;
        this.name = name;
        this.nikeName = nikeName;
    }

    @Override
    public String toString() {
        return "HearNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nikeName='" + nikeName + '\'' +
                '}';
    }
}