package com.example.tls.rxjavaandroid;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.observables.ConnectableObservable;

public class RxJavaPlayground {

    private String invalidValue = null;

    ///////////////////////////////////////////////////////////////////////////
    // Creating a Observable and iterating over each element
    ///////////////////////////////////////////////////////////////////////////]
    @Test
    public void basicJustSample() {
        // Convert the input to a list of Observable<String>
        Observable<String> source = Observable.just("Alpha", "Beta", "Gamma", "Delta");
        // Subscribe and show up all contents
        source.subscribe(System.out::println);
    }

    ///////////////////////////////////////////////////////////////////////////
    // Creating a Observable with the method just + map
    ///////////////////////////////////////////////////////////////////////////
    @Test
    public void basicJustWithMapSample() {
        // Convert the input to a list of Observable<String>
        Observable<String> source = Observable.just("Alpha", "Beta", "Gamma", "Delta").map(s -> String.valueOf(s.length()));
        // Subscribe and show up all contents
        source.subscribe(System.out::println);
    }

    ///////////////////////////////////////////////////////////////////////////
    // Using the operator interval to generate a stream each 1 seconds
    ///////////////////////////////////////////////////////////////////////////
    @Test
    public void basicJustWithIntervalSample() throws InterruptedException {
        Observable<Long> source = Observable.interval(1, TimeUnit.SECONDS);
        source.subscribe(System.out::println);
        // we put this code because the subscribe is being used on computacional thread insted of main
        Thread.sleep(2000);
    }


    ///////////////////////////////////////////////////////////////////////////
    // Creating a OBservable without using the factory methods
    ///////////////////////////////////////////////////////////////////////////
    @Test
    public void basicCreateSample() {
        Observable<Integer> source = Observable.create(e -> {
            try {
                // inserting the items in the list
                e.onNext("Alpha");
                e.onNext("Beta");

                // invalidValue.equals("Error");
                e.onNext("Gamma");
                e.onNext("Delta");
                e.onNext("$hi");
                // Finishing the push
                e.onComplete();

            } catch (Exception ex) {
                e.onError(ex);
            }
        }).cast(String.class)
                .map(String::length)
                .filter(s -> s > 2);

        // Subscribing in the list
        source.subscribe(s -> System.out.println("lenght: " + s), Throwable::printStackTrace);
    }

    ///////////////////////////////////////////////////////////////////////////
    // Iterating over a collection
    ///////////////////////////////////////////////////////////////////////////
    @Test
    public void basicCreateWithFromIterable() {
        List<String> localSource = Arrays.asList("Alpha", "Beta", "Gamma", "Delta", "Gamma");

        Observable.fromIterable(localSource)
                .map(String::length)
                .filter(s -> s > 2)
                .subscribe(System.out::println);
    }

    ///////////////////////////////////////////////////////////////////////////
    // Showing the behavior of cold Observables
    ///////////////////////////////////////////////////////////////////////////
    @Test
    public void createCustomObserver() {
        Observable<String> observable = Observable.just("Hello", "morning");

        observable.subscribe(value -> System.out.println("Observable[1]" + value), Throwable::printStackTrace);
        observable.subscribe(value -> System.out.println("Observable[2]" + value), Throwable::printStackTrace);
    }

    @Test
    public void createHotObserver() {
        Observable<String> coldSource = Observable.just("Alpha", "Beta", "Gamma", "Delta");
        ConnectableObservable<String> hotSource = coldSource.publish();

        // Subscribing on hotSource
        hotSource.subscribe(s -> System.out.println("observer[1]: " + s + ", timestamp: " + System.currentTimeMillis()));
        hotSource.map(s -> s.length())
                .subscribe(s -> System.out.println("observer[2]: " + s + ", timestamp: " + System.currentTimeMillis()));

        // Fire the strem
        hotSource.connect();

        // The stream already done, so it will lost the data
        hotSource.subscribe(s -> System.out.println("observer[3]: " + s + ", timestamp: " + System.currentTimeMillis()));
    }

    @Test
    public void usingColdInterval() throws InterruptedException {
        // Run on work thread
        Observable<Long> source = Observable.interval(1, TimeUnit.SECONDS);

        // Subscribe on source data
        source.subscribe(s -> System.out.println("Observer[0]" + s));

        System.out.println("Stoping the main thread[0]");
        // Stop the main thread
        Thread.sleep(5000);

        // Subscribe on source data
        source.subscribe(s -> System.out.println("Observer[1]" + s));

        System.out.println("Stoping the main thread[1]");
        // Stop the main thread
        Thread.sleep(5000);
    }

    @Test
    public void usingHotInterval() throws InterruptedException {
        // Run on work thread
        ConnectableObservable<Long> hotSource = Observable.interval(1, TimeUnit.SECONDS).publish();

        // Subscribe on hotSource data
        hotSource.subscribe(s -> System.out.println("Observer[0]" + s));

        System.out.println("Stoping the main thread[0]");

        // connect the stream
        hotSource.connect();

        // Stop the main thread
        Thread.sleep(5000);

        // Subscribe on hotSource data
        hotSource.subscribe(s -> System.out.println("Observer[1]" + s));

        System.out.println("Stoping the main thread[1]");
        // Stop the main thread
        Thread.sleep(5000);
    }

    @Test
    public void test() throws InterruptedException {
        Observable<String> coldSource = Observable.create((ObservableOnSubscribe<String>) observableEmitter -> {
            if (!observableEmitter.isDisposed()) {
                System.out.println("Executing the stream");
                observableEmitter.onNext("hello");
                observableEmitter.onNext("hello2");
                observableEmitter.onNext("hello3");
            }
            observableEmitter.onComplete();
        });

        Thread.sleep(5000);
        ;

        System.out.println("--------");

        Thread.sleep(5000);
        ;

        coldSource.subscribe(System.out::println);
    }
}