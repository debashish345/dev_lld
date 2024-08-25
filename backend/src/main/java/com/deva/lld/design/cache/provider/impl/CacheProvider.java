package com.deva.lld.design.cache.provider.impl;

import com.deva.lld.design.cache.provider.def.Cache;
import com.deva.lld.design.cache.strategy.def.CacheStrategy;
import lld.lrucache.exception.EmptyCacaheException;
import lld.lrucache.exception.KeyNotFoundException;

public class CacheProvider<K, V> implements Cache<K, V> {

    private final CacheStrategy<K, V> cacheStrategy;

    public CacheProvider(CacheStrategy<K, V> cacheStrategy) {
        this.cacheStrategy = cacheStrategy;
    }

    @Override
    public void put(K key, V value) {
        this.cacheStrategy.put(key, value);
    }

    @Override
    public void remove(K key) throws EmptyCacaheException, KeyNotFoundException {
        this.cacheStrategy.remove(key);
    }

    @Override
    public V get(K key) throws EmptyCacaheException, KeyNotFoundException {
        return this.cacheStrategy.get(key);
    }

    @Override
    public void print() {
        cacheStrategy.print();
    }
}
