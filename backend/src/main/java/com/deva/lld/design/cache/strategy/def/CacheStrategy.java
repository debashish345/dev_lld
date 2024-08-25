package com.deva.lld.design.cache.strategy.def;

import lld.lrucache.exception.EmptyCacaheException;
import lld.lrucache.exception.KeyNotFoundException;

public interface CacheStrategy<K, V> {

    void put(K key, V value);

    void remove(K key) throws EmptyCacaheException, KeyNotFoundException;

    V get(K key) throws EmptyCacaheException, KeyNotFoundException;

    void print();
}
