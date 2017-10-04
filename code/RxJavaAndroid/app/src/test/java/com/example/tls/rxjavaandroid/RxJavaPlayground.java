package com.example.tls.rxjavaandroid;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

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


}
