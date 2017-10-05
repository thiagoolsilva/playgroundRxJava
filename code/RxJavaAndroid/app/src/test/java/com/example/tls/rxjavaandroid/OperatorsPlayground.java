package com.example.tls.rxjavaandroid;


import org.junit.Test;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

public class OperatorsPlayground {

    ///////////////////////////////////////////////////////////////////////////
    // Filter
    ///////////////////////////////////////////////////////////////////////////
    @Test
    public void usingFilterOperator() {
        Observable.just("Thiago", "Lopes", "da", "silva", "hi")
                .filter(s -> s.length() == 2)
                .subscribe(System.out::println);
    }

    ///////////////////////////////////////////////////////////////////////////
    // Take
    ///////////////////////////////////////////////////////////////////////////
    @Test
    public void usingTakeOperator() {
        Observable.just("Thiago", "Lopes", "da", "silva", "hi")
                .take(2)
                .subscribe(System.out::println);
    }

    ///////////////////////////////////////////////////////////////////////////
    // Take
    // It will take the first n entries of stream
    ///////////////////////////////////////////////////////////////////////////
    @Test
    public void usingTakeWithTime() throws InterruptedException {
        System.out.println("Time : " + System.currentTimeMillis());
        Observable.interval(300, TimeUnit.MILLISECONDS)
                .take(1, TimeUnit.SECONDS)
                .subscribe(System.out::println);

        Thread.sleep(5000);
    }

    ///////////////////////////////////////////////////////////////////////////
    // Skip
    // It will skip the n entries of stream
    ///////////////////////////////////////////////////////////////////////////
    @Test
    public void usingSkipOperator() throws InterruptedException {
        Observable.range(1, 100)
                .skip(90)
                .subscribe(System.out::println);
        Thread.sleep(3000);
    }
}
