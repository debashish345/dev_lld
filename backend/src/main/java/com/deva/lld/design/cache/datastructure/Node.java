package lld.lrucache.datastructure;

import lld.lrucache.exception.EmptyNodeException;

import java.util.Optional;

/*
* Node has 2 pointers to hold the reference the prev and next node
* `D` is generic type for the data
* */
public class Node<K, V> {

    private Node<K, V> next;
    private Node<K, V> prev;
    private V data;
    private K key;

    private Node(){}

    public Node(Node<K, V> next, Node<K, V> prev, K key, V data) {
        this.next = next;
        this.prev = prev;
        this.data = data;
        this.key = key;
    }

    public void detachNext() {
        this.next = null;
    }

    public void attachNext(Node<K, V> nextNode) {
        Optional.of(nextNode).orElseThrow(() -> new EmptyNodeException("Node can not be empty!"));
        this.next = nextNode;
    }

    public void detachPrev() {
        this.prev = null;
    }

    public void attachPrev(Node<K, V> prevNode) {
        Optional.of(prevNode).orElseThrow(() -> new EmptyNodeException("Node can not be empty!"));
        this.prev = prevNode;
    }

    public void detachNextPrev() {
        this.detachNext();
        this.detachPrev();
    }

    public void updateData(V updatedData) {
        this.data = updatedData;
    }

    public Node<K, V> getNext() {
        return this.next;
    }

    public Node<K, V> getPrev() {
        return this.prev;
    }

    public V getData(){
        return this.data;
    }

    public K getKey() {
        return this.key;
    }
}
