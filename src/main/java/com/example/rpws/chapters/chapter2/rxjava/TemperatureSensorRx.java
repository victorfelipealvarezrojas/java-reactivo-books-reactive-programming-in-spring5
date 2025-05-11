package com.example.rpws.chapters.chapter2.rxjava;

import org.springframework.stereotype.Component;
import rx.Observable;

import java.util.Random;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

@Component
public class TemperatureSensorRx {
    private final Random rnd = new Random();

    private final Observable<Temperature> dataStream =
            Observable
                    .range(0, Integer.MAX_VALUE)
                    .concatMap(tick -> Observable // recibe una funcion (f) que transforma el tick en un flujo observable de elementos
                            .just(tick)
                            .delay(rnd.nextInt(5000), MILLISECONDS)
                            .map(tickValue -> this.probe()))
                    .publish()
                    .refCount();


    private Temperature probe() {
        return new Temperature(16 + rnd.nextGaussian() * 10);
    }

    public Observable<Temperature> temperatureStream() {
        return dataStream;
    }

}
