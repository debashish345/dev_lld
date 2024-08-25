package com.deva.lld.design.cache.provider.def;

import lld.lrucache.exception.EmptyCacaheException;
import lld.lrucache.exception.KeyNotFoundException;

/*
* Cache definition
* */
public interface Cache<K, V> {

    void put(K key, V value);

    void remove(K key) throws EmptyCacaheException, KeyNotFoundException;

    V get(K key) throws EmptyCacaheException, KeyNotFoundException;

    void print();
}
