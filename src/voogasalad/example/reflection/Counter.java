package voogasalad.example.reflection;

import java.util.Arrays;


// simple mutable class to reflect upon
public class Counter {
    private String myLabel;
    private int myValue;

    public Counter () {
        this("Counter", 0);
    }

    public Counter (String label) {
        this(label, 0);
    }

    public Counter (String label, int value) {
        myLabel = label;
        myValue = value;
    }

    public Counter (String label, int ... value) {
        this(label, Arrays.stream(value).sum());
    }

    public void next () {
        next(1);
    }

    public void next (int increment) {
        myValue += increment;
    }

    public void next (int ... increment) {
        next(Arrays.stream(increment).sum());
    }

    public int getValue () {
        return myValue;
    }

    public String toString () {
        return myLabel + ": " + myValue;
    }
}
