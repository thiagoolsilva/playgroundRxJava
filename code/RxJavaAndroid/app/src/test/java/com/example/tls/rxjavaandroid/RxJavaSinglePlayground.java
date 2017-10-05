package com.example.tls.rxjavaandroid;


import org.junit.Test;

import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.internal.operators.maybe.MaybeError;

public class RxJavaSinglePlayground {

    @Test
    public void createSingleObserver() {
        Single.just("Hello")
                .map(String::length)
                .subscribe(System.out::println);
    }


}
