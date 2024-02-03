package interview.hash;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 一致性hash算法（不含虚拟节点）
 */
public class ConsistentHashNoVirtual {

    public static void main(String[] args) {
        // step1：初始化：把服务器节点ip的哈希值对应到哈希环上
        //定义服务器ip
        String[] tomcatServers = {"123.111.0.0","123.101.3.1","111.20.35.2","123.98.26.3"};
        SortedMap<Integer, String> hashServerMap = new TreeMap<>();

        for(String tomcatServer:tomcatServers ){
            // 求出每一个ip的hash值，对应hash环上，存储hash值和ip的对应关系
            int serverHash = Math.abs(tomcatServer.hashCode());
            hashServerMap.put(serverHash,tomcatServer);
        }
        // step2:针对客户端ip求出hash值
        // 定义客户端ip




    }

}
