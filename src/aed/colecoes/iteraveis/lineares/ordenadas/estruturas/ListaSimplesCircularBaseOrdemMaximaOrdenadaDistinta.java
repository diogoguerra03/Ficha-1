package aed.colecoes.iteraveis.lineares.ordenadas.estruturas;

import aed.Comparacao;

/**
 * @author Actual code:
 * Carlos Urbano<carlos.urbano@ipleiria.pt>
 * Catarina Reis<catarina.reis@ipleiria.pt>
 * Marco Ferreira<marco.ferreira@ipleiria.pt>
 * João Ramos<joao.f.ramos@ipleiria.pt>
 * Original code: José Magno<jose.magno@ipleiria.pt>
 */
public class ListaSimplesCircularBaseOrdemMaximaOrdenadaDistinta<T> extends ListaSimplesCircularBaseOrdemMaximaOrdenada<T> {

    private static final long serialVersionUID = 1L;

    public ListaSimplesCircularBaseOrdemMaximaOrdenadaDistinta(Comparacao<T> cp) {
        super(cp);
    }

    // Não permite inserção de 2 elementos iguais
    @Override
    public void inserir(T elem) {
        No ant = getNoAnteriorPorOrdem(elem);
        if (ant.seguinte.compararElemento(elem) == 0) {
            throw new IllegalArgumentException("Elemento duplicado");
        }

        new NoComElemento(elem, ant);
        numeroElementos++;
    }

    public T consultarDistinto(T elem) {
        No no = getNoAnteriorPorOrdem(elem).seguinte;
        return no.compararElemento(elem) == 0 ? no.elemento : null;
    }

}
