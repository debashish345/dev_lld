package com.deva.lld.design.cache.provider.def;


import com.deva.lld.design.cache.exception.EmptyCacheException;
import com.deva.lld.design.cache.exception.KeyNotFoundException;

/*
* Cache definition
* */
public interface Cache<K, V> {

    void put(K key, V value);

    void remove(K key) throws EmptyCacheException, KeyNotFoundException;

    V get(K key) throws EmptyCacheException, KeyNotFoundException;

    void print();
}
