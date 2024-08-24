package com.opencc.collectiontest;

public class SimpleLinkedList {
    private class Node {
        Node(Object o) {
            this.o = o;
        }

        Object o;
        Node next;
    }


    private Node first; //第一个节点

    public void add(Object elem) {
        Node node = new Node(elem); //新增node对象，并由上一个node的next参考
        if (first == null) {
            first = node;
        }
        append(node);
    }

    /**
     * 新增的对象添加到链表的尾部，尾插法
     *
     * @param node
     */
    private void append(Node node) {
        Node last = first;//每次新增数据都要重新指定last到first的位置，从头遍历到尾部
        while (last.next != null) {//依次从头遍历到尾部，然后得到最后的node节点的参考
            last = last.next;
        }
        last.next = node;//将最后的node节点的参考的next指向新增的node，链接到尾部，这种也叫尾插法。
    }


    /**
     * 返回链表的总长度
     *
     * @return
     */
    public int size() {
        int count = 0;
        Node last = first;
        while (last != null) {
            last = last.next;
            count++;
        }
        return count;
    }

    /**
     * 根据索引获取链表中的对象实例
     *
     * @param index
     * @return
     */
    public Object get(int index) {
        checkSize(index);
        return findElemOf(index);
    }

    /**
     * 检查index是否超出链表总长
     * @param index
     */
    private void checkSize(int index) {
        if (index >= size()) {
            throw new IndexOutOfBoundsException(String.format("Index: %d, Size %d", index, size()));
        }
    }

    /**
     * 从头遍历到index的对象实例处
     * @param index
     * @return
     */
    private Object findElemOf(int index) {
        int count = 0;
        Node last = first;
        while (count < index) {
            last = last.next;
            count++;
        }
        return last.o;
    }
}
