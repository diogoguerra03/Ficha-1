package aed.pesquisa.algoritmos;

import aed.Comparacao;

public class PesquisaTernariaInterativa<T> extends AlgoritmoPesquisa<T>{

    public PesquisaTernariaInterativa(Comparacao<T> criterio) {
        super(criterio);
    }

    @Override
    public int pesquisar(T elemento, T... elementos) {
        int esq = 0;
        int dir = elementos.length - 1;
        if (elementos.length == 0 ||
                criterio.comparar(elemento, elementos[esq]) < 0 ||
                criterio.comparar(elemento, elementos[dir]) > 0) {
            return NAO_ENCONTRADO;
        }

         do {
            int terco = (dir - esq) / 3;
            int terco1 = esq + terco;

            int cp = criterio.comparar(elemento, elementos[terco1]);

            if (cp < 0) {
                //return pesquisarRecursivo(elemento, esq, terco1 - 1, elementos);
                dir = terco1 - 1;
                continue;
            }

            if (cp > 0) {
                int terco2 = dir - terco;
                cp = criterio.comparar(elemento, elementos[terco2]);
                if (cp < 0){
                    //return pesquisarRecursivo(elemento, terco1 + 1, terco2 - 1, elementos);
                    esq = terco1 + 1;
                    dir = terco2 - 1;
                    continue;
                }
                if (cp > 0){
                    //return pesquisarRecursivo(elemento, terco2 + 1, dir, elementos);
                    esq = terco2 + 1;
                    continue;
                }
                return terco2;
            }
            return terco1;
        }while (esq <= dir);

        return NAO_ENCONTRADO;
    }
}
