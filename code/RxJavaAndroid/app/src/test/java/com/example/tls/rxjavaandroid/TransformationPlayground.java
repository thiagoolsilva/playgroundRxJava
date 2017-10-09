package com.example.tls.rxjavaandroid;


import org.junit.Test;

import io.reactivex.Observable;

public class TransformationPlayground {

    ///////////////////////////////////////////////////////////////////////////
    // Map
    // The map transformation will generate a transformation on each stream
    ///////////////////////////////////////////////////////////////////////////
    @Test
    public void usingMap() throws InterruptedException {
        Observable.just("Thiago", "lopes")
                .map(s -> s + " Silva")
                .subscribe(System.out::println);
        Thread.sleep(1000);
    }

    @Test
    public void usingStartWith() throws InterruptedException {
        Observable.just("1", "2", "3", "4")
                .startWith("First element")
                .subscribe(System.out::println);
        Thread.sleep(1000);
    }

    @Test
    public void usingStartWithArray() throws InterruptedException {
        Observable.just("1", "2", "3", "4")
                .startWithArray("First element", "Second element")
                .subscribe(System.out::println);
        Thread.sleep(1000);
    }

    @Test
    public void usingSorted() throws InterruptedException {
        Observable.just(1, 2, 8, 9, 7, 6, 5, 3)
                .sorted()
                .subscribe(System.out::println);
        Thread.sleep(1000);
    }
}
