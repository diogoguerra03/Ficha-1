package aed.recursividade.algoritmos;

public class JogoTorresHanoiV2 {

    public static void executar(int nr, Torre origem, Torre auxilixar, Torre destino){
        if (nr < 1){
            throw new IllegalArgumentException("Torres Hanoi(" + nr + ") = argumento inválido : n >= 0");
        }

        executarRecursivo(nr, origem, auxilixar , destino);
    }

    private static void executarRecursivo(int numeroDiscos, Torre origem, Torre auxiliar, Torre destino) {
        if (numeroDiscos == 1){
            System.out.println(origem + " => " + destino);
            origem.moverDisco(destino);
            return;
        }

        executarRecursivo(numeroDiscos - 1, origem, destino ,auxiliar);
        System.out.println(origem + " => " + destino);
        origem.moverDisco(destino);
        executarRecursivo(numeroDiscos - 1, auxiliar, origem ,destino);
    }

    public static void apresentar(int numeroDiscos){
        try {
            System.out.println("Torres Hanoi com " + numeroDiscos + " discos");
            Torre torreA = new Torre('A', numeroDiscos);
            Torre torreB = new Torre('B', numeroDiscos);
            Torre torreC = new Torre('C', numeroDiscos);
            torreA.preencherComDiscos();

            executar(numeroDiscos,torreA,torreB,torreC);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
