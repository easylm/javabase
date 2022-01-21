/*
package demo;

import sun.plugin.dom.core.Node;

public class Morris {


    public static void morrisPre(Node head) {
        if(head == null){
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null){
            // cur表示当前节点，mostRight表示cur的左孩子的最右节点
            mostRight = cur.left;
            if(mostRight != null){
                // cur有左孩子，找到cur左子树最右节点
                while (mostRight.right !=null && mostRight.right != cur){
                    mostRight = mostRight.right;
                }
                // mostRight的右孩子指向空，让其指向cur，cur向左移动
                if(mostRight.right == null){
                    mostRight.right = cur;
                    System.out.print(cur.value+" ");
                    cur = cur.left;
                    continue;
                }else {
                    // mostRight的右孩子指向cur，让其指向空，cur向右移动
                    mostRight.right = null;
                }
            }else {
                System.out.print(cur.value + " ");
            }
            cur = cur.right;
        }
        System.out.println();
    }

}
*/
