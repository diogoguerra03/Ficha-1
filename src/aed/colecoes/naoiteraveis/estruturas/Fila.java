package aed.colecoes.naoiteraveis.estruturas;

import aed.colecoes.iteraveis.lineares.naoordenadas.estruturas.ListaDuplaCircularBaseNaoOrdenada;
import aed.colecoes.naoiteraveis.ColecaoNaoIteravel;

/**
 * @author Actual code:
 * Carlos Urbano<carlos.urbano@ipleiria.pt>
 * Catarina Reis<catarina.reis@ipleiria.pt>
 * Marco Ferreira<marco.ferreira@ipleiria.pt>
 * João Ramos<joao.f.ramos@ipleiria.pt>
 * Original code: José Magno<jose.magno@ipleiria.pt>
 */
public class Fila<T> implements ColecaoNaoIteravel<T> {
    private static final long serialVersionUID = 1L;

    protected ListaDuplaCircularBaseNaoOrdenada<T> elementos;

    public Fila() {
        elementos = new ListaDuplaCircularBaseNaoOrdenada<>();
    }

    @Override
    public void inserir(T elem) {
        elementos.inserirNoFim(elem);
    }

    @Override
    public T remover() {
        return elementos.removerDoInicio();
    }

    @Override
    public T consultar() {
        try {
            return elementos.consultar(0);
        } catch (IndexOutOfBoundsException ex) {
            return null;
        }
    }

    @Override
    public boolean isVazia() {
        return elementos.isVazia();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("Fila = {\n");
        for (T elemento : elementos) {
            s.append(elemento).append("\n");
        }
        s.append("}\n");
        return s.toString();
    }
}
