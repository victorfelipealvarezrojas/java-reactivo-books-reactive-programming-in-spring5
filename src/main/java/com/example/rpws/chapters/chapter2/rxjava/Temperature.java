package com.example.rpws.chapters.chapter2.rxjava;

public class Temperature {
    private final double value;

    public Temperature(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Temperature{" +
                "value=" + value +
                '}';
    }
}
