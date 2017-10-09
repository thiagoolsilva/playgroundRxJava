package com.example.tls.rxjavaandroid;


import org.junit.Test;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MultiThreadPlayground {

    // Low performance
    @Test
    public void showingLowPerformanceWithFakeRequest() throws InterruptedException {
        Observable.just("Alpha", "Beta")
                .map(MultiThreadPlayground::intenseCalculation)
                .subscribe(System.out::println);

        Observable.range(1, 6)
                .map(MultiThreadPlayground::intenseCalculation)
                .subscribe(System.out::println);


    }

    @Test
    public void usingSubscribeOn() throws InterruptedException {
        Observable.just("Alpha", "Beta")
                .subscribeOn(Schedulers.computation())
                .map(MultiThreadPlayground::intenseCalculation)
                .subscribe(System.out::println);

        Observable.range(1, 6)
                .subscribeOn(Schedulers.computation())
                .map(MultiThreadPlayground::intenseCalculation)
                .subscribe(System.out::println);

        Thread.sleep(20000);
        ;
    }

    @Test
    public void usingSubscribeOnWithZip() throws InterruptedException {
        Observable<String> coldSourceOne = Observable.just("Alpha", "Beta")
                .subscribeOn(Schedulers.computation())
                .map(MultiThreadPlayground::intenseCalculation);

        Observable<Integer> coldSourceTwo = Observable.range(1, 6)
                .subscribeOn(Schedulers.computation())
                .map(MultiThreadPlayground::intenseCalculation);

        // Zip
        Observable.zip(coldSourceOne, coldSourceTwo, (s, i) -> s + "-" + i)
                .subscribe(System.out::println);

        Thread.sleep(20000);
    }

    private static <T> T intenseCalculation(T value) throws InterruptedException {
        Thread.sleep(3000);
        return value;
    }
}
