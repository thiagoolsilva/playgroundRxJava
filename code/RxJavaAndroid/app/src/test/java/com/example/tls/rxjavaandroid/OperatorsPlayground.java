package com.example.tls.rxjavaandroid;


import org.junit.Test;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

import static java.lang.Thread.sleep;

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

        sleep(5000);
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
        sleep(3000);
    }

    @Test
    public void usingTakeWhile() throws InterruptedException {
        Observable.range(1,100)
                .takeWhile(s -> s < 6)
                .subscribe(System.out::println);
        sleep(2000);
    }

    @Test
    public void usingDistinct() throws InterruptedException {
        Observable.just("Lopes", "Hi", "It")
                .map(String::length)
                .distinct()
                .subscribe(System.out::println);
        sleep(2000);
    }

    @Test
    public void usingDistinctWithLambda() throws InterruptedException {
        Observable.just("Lopes", "Hi", "It")
                .distinct(String::length)
                .subscribe(System.out::println);
        sleep(2000);
    }

    @Test
    public void usingElementAt() throws InterruptedException {
        Observable.range(1,10)
                .elementAt(1)
                .subscribe(System.out::println);
        sleep(2000);
    }

    @Test
    public void usingFlatMap() throws InterruptedException {
        Observable.just("Thiago", "Lopes","da", "Silva")
                .flatMap(s-> Observable.fromArray(s.split("")))
                .subscribe(System.out::println);
        sleep(2000);
    }

    @Test
    public void usingOnNext() {
        Observable.just("Thiago", "Lopes","da", "Silva")
                .doOnNext(s -> System.out.println("---> Before:: "+s))
                .doOnComplete(() -> System.out.println("---> onComplete<---"))
                .subscribe(System.out::println);
    }
}
