package org.pn.real.simple.caching;

import java.util.concurrent.TimeUnit;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.AccessedExpiryPolicy;
import javax.cache.expiry.Duration;
import javax.cache.spi.CachingProvider;

import org.pn.real.simple.model.Book;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableCaching
@ComponentScan({ "org.pn.real.simple" })
public class ApplicationConfig {

    @Bean("CachingProvider")
    public CachingProvider getCachingProvider() {
        CachingProvider provider = Caching.getCachingProvider();
        return provider;
    }

    @Bean("CacheManager")
    public CacheManager getCacheManager(CachingProvider provider) {
        CacheManager cacheManager = provider.getCacheManager();
        return cacheManager;
    }

    @Bean("bookCache")
    public Cache<String, Book> getBookCache(CachingProvider provider) {
        CacheManager cacheManager = provider.getCacheManager();
        MutableConfiguration<String, Book> configuration = new MutableConfiguration<String, Book>()
            .setTypes(String.class, Book.class)
            .setStoreByValue(false)
            .setExpiryPolicyFactory(AccessedExpiryPolicy.factoryOf(new Duration(TimeUnit.SECONDS, 1)));
        Cache<String, Book> cache = cacheManager.createCache("books", configuration);
        return cache;
    }

}
