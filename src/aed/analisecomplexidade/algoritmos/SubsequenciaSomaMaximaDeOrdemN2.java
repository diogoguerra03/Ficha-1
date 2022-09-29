package aed.analisecomplexidade.algoritmos;

import aed.utils.Par;

/**
 * @author Actual code:
 * Carlos Urbano<carlos.urbano@ipleiria.pt>
 * Catarina Reis<catarina.reis@ipleiria.pt>
 * Marco Ferreira<marco.ferreira@ipleiria.pt>
 * João Ramos<joao.f.ramos@ipleiria.pt>
 * Original code: José Magno<jose.magno@ipleiria.pt>
 */
public class SubsequenciaSomaMaximaDeOrdemN2 extends SubsequenciaSomaMaxima {

    @Override
    public long executar(Par<Integer, Integer> indicesInicialEFinal, int... elementos) {
        long somaMaxima = 0, somaAtual;
        int inicio = 0, fim = 0;
        for (int i = 0; i < elementos.length; i++) {
            if (elementos[i] <= 0) {
                continue;
            }
            somaAtual = 0;
            for (int j = i; j < elementos.length; j++) {
                somaAtual += elementos[j];
                if (somaAtual > somaMaxima) {
                    inicio = i;
                    fim = j;
                    somaMaxima = somaAtual;
                }
            }
        }
        indicesInicialEFinal.setPrimeiro(inicio);
        indicesInicialEFinal.setSegundo(fim);
        return somaMaxima;
    }
}
