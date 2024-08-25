package com.deva.lld.design.cache.strategy.impl;

import com.deva.lld.design.cache.strategy.def.CacheStrategy;
import lld.lrucache.datastructure.DoublyLinkedList;
import lld.lrucache.datastructure.Node;
import lld.lrucache.exception.EmptyCacaheException;
import lld.lrucache.exception.KeyNotFoundException;

import java.util.HashMap;
import java.util.Map;

public class LRUCacheStrategy<K, V> implements CacheStrategy<K, V> {

    private final int SIZE;
    private final Map<K, Node<K, V>> cacheMap;
    private final DoublyLinkedList<K, V> doublyLinkedList;

    public LRUCacheStrategy(int size) {
        this.cacheMap = new HashMap<>();
        this.SIZE = size;
        this.doublyLinkedList = new DoublyLinkedList<>();
    }

    @Override
    public void put(K key, V value) {
        if (cacheMap.size() >= SIZE) {
            Node<K, V> removedNode = doublyLinkedList.removeLastNode();
            K keyToBeRemoved = removedNode.getKey();
            cacheMap.remove(keyToBeRemoved);
        }
        cachePut(key, value);
    }

    @Override
    public void remove(K key) throws EmptyCacaheException, KeyNotFoundException {
        if (cacheMap.isEmpty()) throw new EmptyCacaheException("Cache is empty!");
        if (!containsKey(key)) throw new KeyNotFoundException(String.format("%s is not found!", key));
        Node<K, V> toBeRemoved = cacheMap.get(key);
        doublyLinkedList.removeNode(toBeRemoved);
    }

    @Override
    public V get(K key) throws EmptyCacaheException, KeyNotFoundException {
        if (cacheMap.isEmpty()) throw new EmptyCacaheException("Cache is empty!");
        if (!containsKey(key)) throw new KeyNotFoundException(String.format("%s is not found!", key));
        Node<K, V> requestedNode = cacheMap.get(key);
        priortizeNode(requestedNode);
        return requestedNode.getData();
    }

    @Override
    public void print() {
        doublyLinkedList.consolePrint();
    }

    private boolean containsKey(K key) {
        return this.cacheMap.containsKey(key);
    }

    private void cachePut(K key, V value) {
        Node<K, V> newNode = new Node<>(null, null, key, value);
        cacheMap.put(key, newNode);
        doublyLinkedList.addNodeAtFirst(newNode);
    }

    private void priortizeNode(Node<K, V> node) {
        doublyLinkedList.removeNode(node);
        doublyLinkedList.addNodeAtFirst(node);
    }

}
