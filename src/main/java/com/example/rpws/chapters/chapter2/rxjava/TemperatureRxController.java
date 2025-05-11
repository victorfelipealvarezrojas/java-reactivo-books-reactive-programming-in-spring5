package com.example.rpws.chapters.chapter2.rxjava;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
public class TemperatureRxController {

    private final TemperatureSensorRx temperatureSensor;

    public TemperatureRxController(TemperatureSensorRx temperatureSensor) {
        this.temperatureSensor = temperatureSensor;
    }

    @GetMapping("/temperature-stream-rx")
    public SseEmitter events(HttpServletRequest request) {
        RxSeeEmitter emitter = new RxSeeEmitter();

        temperatureSensor.temperatureStream()
                .subscribe(emitter.getSubscriber());


        return emitter;
    }
}
