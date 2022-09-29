package aed.recursividade.algoritmos;

public class JogoTorresHanoi {

    public static void executar(int nr, char o, char a, char d){
        if (nr < 1){
            throw new IllegalArgumentException("Torres Hanoi(" + nr + ") = argumento inválido : n >= 0");
        }

        executarRecursivo(nr, o, a ,d);
    }

    private static void executarRecursivo(int nr, char o, char a, char d) {
        if (nr == 1){
            System.out.println(o + " => " + d);
            return;
        }

        executarRecursivo(nr - 1, o, d ,a);
        System.out.println(o + " => " + d);
        executarRecursivo(nr - 1, a, o ,d);
    }

    public static void apresentar(int n){
        try {
            System.out.println("Torres Hanoi com " + n + " discos");
            executar(n,'A','B','C');
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
