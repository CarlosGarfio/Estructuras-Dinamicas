package app;

import Ejercicios.Ejercicio_1;
import Ejercicios.Ejercicio_2;
import Ejercicios.Ejercicio_3;
import Ejercicios.Ejercicio_4;
import Ejercicios.Ejercicio_5;
import List.LinkedList;

public class App {

    // static linkedList<Integer> dobles =  new linkedList<>();
    static LinkedList<Integer> X = new LinkedList<>();
    static LinkedList<Integer> Y = new LinkedList<>();

    static Ejercicio_1<Integer> e1 = new Ejercicio_1<>();
    static Ejercicio_2<Integer> e2 = new Ejercicio_2<>();
    static Ejercicio_3<Integer> e3 = new Ejercicio_3<>();
    static Ejercicio_4<Integer> e4 = new Ejercicio_4<>();
    static Ejercicio_5<Integer> e5 = new Ejercicio_5<>();

    public static void main(String[] args) {
        X.Add(10);
        X.Add(20);
        X.Add(30);
        X.Add(40);
        X.Add(50);
        System.out.println("Arreglo uno");
        for (Integer object : X) {
            System.out.println(object);
        }
        Y.Add(10);
        Y.Add(20);
        Y.Add(30);
        Y.Add(40);
        Y.Add(50);
        System.out.println("Arreglo dos");
        for (Integer object : Y) {
            System.out.println(object);
        }

        //EJERCICIOS!
        if (e1.SonIguales(X, Y)) {
            System.out.println("Son igulaes.");
        } else {
            System.out.println("No son iguales.");
        }

        if (e2.ExisteElemento(10, X)) {
            System.out.println("Si existe.");
        } else {
            System.out.println("No existe.");
        }

        int n = 30;

        System.out.println("La ocurrencia del numero " + n + " es de: " + e3.Ocurrencia(n, X));

        long l = e4.Suma((LinkedList<Integer>) X);

        System.out.println("La sumatoria de los datos en el arreglo es: " + l);

        System.out.println("El numero maximo es: " + e5.Maximo(X));

        //METODOS
        X.Add(60);
        X.AddAfter(30, 30);
        X.AddAt(50, 6);
        X.AddBefore(20, 30);
        X.AddStart(1);
        X.Remove(10);
        X.RemoveAfter(20);
        X.RemoveAll(30);
        X.RemoveBefore(10);

        for (Integer object : X) {
            System.out.println(object);
        }

    }
}
