package aed.recursividade.algoritmos;

import static java.lang.Thread.sleep;

public class Fatorial {

    public static long executar(int n){
        if (n < 0){
            throw new IllegalArgumentException("fatorial(" + n + ") = argumento inválido : n >= 0");
        }

        return executarRecursivo(n);
    }

    private static long executarRecursivo(int n) {
        if (n < 2){
            return 1;
        }

        return n * executarRecursivo(n - 1);
    }

    public static void apresentar(int n){
        try {
            System.out.println("Fatorial(" + n + ")= " + executar(n));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
