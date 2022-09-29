package aed.recursividade.algoritmos;

public class Fibonacci {

    public static long executar(int n){
        if (n < 0){
            throw new IllegalArgumentException("fibonacci(" + n + ") = argumento inválido : n >= 0");
        }

        return executarRecursivo(n);
    }

    private static long executarRecursivo(int n) {
        if (n < 2){
            return n;
        }

        return executarRecursivo(n - 2) + executarRecursivo(n-1);
    }

    public static void apresentar(int n){
        try {
            System.out.println("Fibonacci(" + n + ")= " + executar(n));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
