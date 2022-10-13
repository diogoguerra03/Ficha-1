package aed.analisecomplexidade.algoritmos;

import java.util.Arrays;

public class Maximo {
    public static int executar(int... valores){
        if (valores.length < 1){
            throw new IllegalArgumentException("Pelo menos 1 valor");
        }
        int indiceMaximo = 0;
        for (int i = 0; i < valores.length; i++) {
            if (valores[i] > valores[indiceMaximo]){
                indiceMaximo = i;
            }
        }
        
        return valores[indiceMaximo];
    }

    public static void apresentar(int... valores){
        try {
            System.out.println("máximo(" + Arrays.toString(valores) + ") = " + executar(valores));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
