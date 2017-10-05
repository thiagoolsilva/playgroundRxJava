package com.example.tls.rxjavaandroid;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class CompositeDisposablePlayground {

    @Test
    public void createSimpleCompositeDisposable() throws InterruptedException {
        CompositeDisposable poolObservable = new CompositeDisposable();

        // creating the first observable
        Disposable firstDisposable = Observable.interval(1, TimeUnit.SECONDS).subscribe(System.out::println);
        // creating the first observable
        Disposable secondDisposable = Observable.interval(1, TimeUnit.SECONDS).subscribe(System.out::println);

        // Inserting the observables into the list
        poolObservable.addAll(firstDisposable, secondDisposable);

        Thread.sleep(5000);
        ;

        // Release all disposable
        poolObservable.dispose();

        // Wait more 5 seconds to all subscribe do not show up any data
        Thread.sleep(5000);
        ;
    }
}
