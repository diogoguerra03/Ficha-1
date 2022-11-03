package aed.pesquisa.algoritmos;

import aed.Comparacao;

public class PesquisaTernariaRecursiva<T> extends AlgoritmoPesquisa<T>{

    public PesquisaTernariaRecursiva(Comparacao<T> criterio) {
        super(criterio);
    }

    @Override
    public int pesquisar(T elemento, T... elementos) {
        int indiceUltimoElemento = elementos.length - 1;
        if (elementos.length == 0 ||
                criterio.comparar(elemento, elementos[0]) < 0 ||
                criterio.comparar(elemento, elementos[indiceUltimoElemento]) > 0) {
            return NAO_ENCONTRADO;
        }
        return pesquisarRecursivo(elemento, 0, indiceUltimoElemento, elementos);
    }

    public int pesquisarRecursivo(T elemento, int esq, int dir, T... elementos) {
        if (esq > dir) {
            return NAO_ENCONTRADO;
        }
        int terco = (dir - esq) / 3;
        int terco1 = esq + terco;

        int cp = criterio.comparar(elemento, elementos[terco1]);

        if (cp < 0) {
            return pesquisarRecursivo(elemento, esq, terco1 - 1, elementos);
        }

        if (cp > 0) {
            int terco2 = dir - terco;
            cp = criterio.comparar(elemento, elementos[terco2]);
            if (cp < 0){
                return pesquisarRecursivo(elemento, terco1 + 1, terco2 - 1, elementos);
            }
            if (cp > 0){
                return pesquisarRecursivo(elemento, terco2 + 1, dir, elementos);
            }
            return terco2;
        }

        return terco1;
    }
}
