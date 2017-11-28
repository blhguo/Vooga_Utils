package voogasalad.example.reflection;

import voogasalad.util.reflection.Reflection;
import voogasalad.util.reflection.ReflectionException;


public class ReflectionTest {
    public static void main (String[] args) {
        try {
            // no arguments
            Counter c = (Counter)Reflection.createInstance("voogasalad.example.reflection.Counter");
            System.out.println(c);
            Reflection.callMethod(c, "next");
            System.out.println(c);
    
            // one argument
            c = (Counter)Reflection.createInstance("voogasalad.example.reflection.Counter", "Hello");
            System.out.println(c);
            Reflection.callMethod(c, "next", 2);
            System.out.println(c);
    
            // arguments of different types
            c = (Counter)Reflection.createInstance("voogasalad.example.reflection.Counter", "Hello", 13);
            System.out.println(c);
            Reflection.callMethod(c, "next", 2, 4, 6, 8);
            System.out.println(c);
    
            // variable number of arguments
            c = (Counter)Reflection.createInstance("voogasalad.example.reflection.Counter", "Hello", 13, 13, 13);
            System.out.println(c);
            Reflection.callMethod(c, "next");
            System.out.println(c);
        }
        catch (ReflectionException e) {
            // nothing useful to do?
            e.printStackTrace();
        }
    }
}
