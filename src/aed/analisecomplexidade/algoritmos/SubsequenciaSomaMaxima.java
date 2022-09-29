package aed.analisecomplexidade.algoritmos;

import aed.utils.Par;
import aed.utils.VetorDeInteiros;

import java.util.Arrays;

/**
 * @author Actual code:
 * Carlos Urbano<carlos.urbano@ipleiria.pt>
 * Catarina Reis<catarina.reis@ipleiria.pt>
 * Marco Ferreira<marco.ferreira@ipleiria.pt>
 * João Ramos<joao.f.ramos@ipleiria.pt>
 * Original code: José Magno<jose.magno@ipleiria.pt>
 */
public abstract class SubsequenciaSomaMaxima {

    public abstract long executar(Par<Integer, Integer> indicesInicialEFinal, int... elementos);

    public void apresentar(int... elementos) {
        Par<Integer, Integer> indicesInicialEFinal = new Par<>(0, 0);
        System.out.println("Subsequência da soma máxima calculada com " + getClass().getSimpleName() + ": ");
        long resultado = executar(indicesInicialEFinal, elementos);
        System.out.println("Índice inicial = " + indicesInicialEFinal.getPrimeiro() +
                " Índice final = " + indicesInicialEFinal.getSegundo() +
                " Soma = " + resultado);
        VetorDeInteiros.apresentar(10,
                Arrays.copyOfRange(elementos, indicesInicialEFinal.getPrimeiro(), indicesInicialEFinal.getSegundo() + 1));
    }
}
