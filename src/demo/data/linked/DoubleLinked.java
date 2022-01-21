package demo.data.linked;

/**
 * @author git
 * @version 1.0
 * @Description: 双向链表
 * @date 2022/1/13
 */
public class DoubleLinked {
    public static void main(String[] args) {
        System.out.println("双向链表的测试。。。。。");
        HeroNode node1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode node2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode node3 = new HeroNode(3, "吴用", "智多星");
        HeroNode node4 = new HeroNode(4, "林冲", "豹子头");

        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(node1);
        doubleLinkedList.add(node2);
        doubleLinkedList.add(node3);
        doubleLinkedList.add(node4);
        doubleLinkedList.list();

        // 修改
        HeroNode node5 = new HeroNode(4, "公孙胜", "入云龙");
        System.out.println("修改后操作。。。。。。。。。。。。。。");
        doubleLinkedList.update(node5);
        doubleLinkedList.list();

        // 删除
        doubleLinkedList.del(2);
        System.out.println("删除后操作。。。。。。。。。。。。。。");
        doubleLinkedList.list();

    }


}


// 创建一个双向链表
class DoubleLinkedList{
    // 初始化一个头节点，头节点不要动，不存放具体的数据
    private HeroNode head =new HeroNode(0,"","");

    // 返回头节点
    public HeroNode getHeroNode(){
        return head;
    }

    // 遍历
    // 显示链表(遍历)
    public void list() {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        // 因为头节点不能动，因此我们需要一个辅助变量来遍历
        HeroNode temp = head.next;
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

    /**
     * 1.添加节点到单向链表
     * 思路：当不考虑编号顺序时
     * 1.找到当前链表的最后节点
     * 2.将最后这个节点的next指向新的节点
     */
    public void add(HeroNode heroNode) {
        HeroNode temp = head;
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
        // 形成一个双向链表
        temp.next = heroNode;
        heroNode.pre = temp;
    }

    /**
     * 修改节点信息，根据编号来修改
     */
    public void update(HeroNode newHeroNode) {
        // 判断是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.no == newHeroNode.no) {
                // 找到
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //根据flag，判断是否找到要修改的节点
        if (flag) {
            temp.name = newHeroNode.name;
            temp.nikeName = newHeroNode.nikeName;
        } else {
            System.out.printf("没有找到编号 %d的节点，不能修改\n", newHeroNode.no);
        }

    }

    /**
     * 删除节点
     * 1.head 不能动，因此我们temp
     */
    public void del(int no) {

        if (head.next == null){
            System.out.println("链表为空，无法删除");
            return;
        }
        HeroNode temp = head.next;
        boolean flag = false; //标识是否找到待删除节点

        while (true) {
            if (temp == null) { //已经到链表的最后
                break;
            }
            if (temp.no == no) {
                // 待删除节点的前一个节点temp
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            //temp.next = temp.next.next; // 单向链表
            temp.pre.next = temp.next;
            // 如果是否是最后一个节点 是不执行
            if(temp.next != null){
                temp.next.pre = temp.pre;
            }
        } else {
            System.out.printf("要删除的%d,节点不存在\n", no);
        }
    }

}

class HeroNode {
    public int no;
    public String name;
    public String nikeName;
    public HeroNode next; // 指向下一个节点，默认为null
    public HeroNode pre;  // 指向上一个节点,默认为null

    // 构造器
    public HeroNode(int no, String name, String nikeName) {
        this.no = no;
        this.name = name;
        this.nikeName = nikeName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nikeName='" + nikeName + '\'' +
                '}';
    }
}



