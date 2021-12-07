package com.danssion.java.algorithm.lru;

import java.util.HashMap;

/**
 * @DESC Least Recently Used (最近很少  使用)
 */
public class LRUCache {
    private Node head;
    private Node tail;

    private final HashMap<String,Node> nodeHashMap;
    private int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        nodeHashMap = new HashMap<>();
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
    }

    //当前Key 的数据被访问了
    public String get(String key) {
        Node node = nodeHashMap.get(key);
        if(node == null) {
            return null;
        }
        //刷新Key 的位置
        moveNodeToHead(node);
        return node.value;
    }

    /**
     * @desc 如果存在，触发点击，更新位置
     * @param key
     * @param value
     */
    public void put(String key,String value) {
        Node node = nodeHashMap.get(key);
        if(node == null) {
            //大于容量，则需要移除数据
            if(nodeHashMap.size() >= capacity) {
                removeNode(tail);//移除尾部节点
                nodeHashMap.remove(tail.key);//从hashmap 移除 key
            }
            node = new Node(key,value);
            nodeHashMap.put(key,node);
            addNodeToHead(node);
        } else {
            node.value = value;
            moveNodeToHead(node);
        }
    }

    /**
     * @desc 链表的节点删除
     * @param node
     */
    private void removeNode(Node node) {
        if(node == tail) {
            tail = tail.prev;
            tail.next = null;
        } else if (node == head) {
            head = head.next;
            head.prev = null;
        } else {//移除当前node
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
    }

    /**
     * @desc 添加节点到头
     */
    private void addNodeToHead(Node node) {
        //??  head 非单独
        node.next = head;
        node.prev = head.prev;
        head.prev = node;

        // 单独的head
        node.next = head.next.next;
        head.prev.next = node;
        head.next = node;
        node.prev = head;
    }

    private void moveNodeToHead(Node node) {
        removeNode(node);
        addNodeToHead(node);
    }

    class Node {
        private String key;
        private String value;

        Node prev;
        Node next;

        public Node() {
        }

        public Node(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }
}
