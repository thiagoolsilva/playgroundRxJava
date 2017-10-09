package com.example.tls.rxjavaandroid;


import org.junit.Test;

import io.reactivex.Observable;

public class CombinationPlayground {

    @Test
    public void usingZip() throws InterruptedException {
        Observable<String> coldSourceOne = Observable.just("Thiago", "lopes");
        Observable<Integer> coldSourceTwo = Observable.just(1, 2);

        Observable.zip(coldSourceOne, coldSourceTwo, (x, y) -> x +" "+ y)
                .subscribe(System.out::println);

        Thread.sleep(2000);
        ;

    }
}
