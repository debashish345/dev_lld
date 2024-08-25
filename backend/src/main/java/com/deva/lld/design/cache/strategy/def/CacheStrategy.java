package com.deva.lld.design.cache.strategy.def;


import com.deva.lld.design.cache.exception.EmptyCacheException;
import com.deva.lld.design.cache.exception.KeyNotFoundException;

public interface CacheStrategy<K, V> {

    void put(K key, V value);

    void remove(K key) throws EmptyCacheException, KeyNotFoundException;

    V get(K key) throws EmptyCacheException, KeyNotFoundException;

    void print();
}
