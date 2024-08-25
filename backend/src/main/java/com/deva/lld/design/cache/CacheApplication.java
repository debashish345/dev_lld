package com.deva.lld.design.cache;

import com.deva.lld.design.cache.exception.EmptyCacheException;
import com.deva.lld.design.cache.exception.KeyNotFoundException;
import com.deva.lld.design.cache.provider.def.Cache;
import com.deva.lld.design.cache.provider.impl.CacheProvider;
import com.deva.lld.design.cache.strategy.def.CacheStrategy;
import com.deva.lld.design.cache.strategy.impl.LRUCacheStrategy;

public class CacheApplication {

    public static void main(String[] args) throws EmptyCacheException, KeyNotFoundException {
        CacheStrategy<Integer, String> lruCacheStrategy = new LRUCacheStrategy<>(5);
        Cache<Integer, String> cache = new CacheProvider<>(lruCacheStrategy);

        cache.put(10, "India");
        cache.put(2, "kolkata");
        cache.put(4, "Hyderabad");
        cache.print();
        cache.put(66, "Chennai");
        cache.print();
        cache.remove(2);
        String data = cache.get(10);
        System.out.println(data);
        cache.print();
        cache.put(22, "Mumbai");
        cache.put(12, "Kolkata");
        cache.put(12, "Bharat");
        cache.get(10);
        cache.put(88, "Assam");
        cache.print();
        cache.put(88, "Mizoram");
        cache.print();
    }
}
