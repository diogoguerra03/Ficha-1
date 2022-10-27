package aed.modelo.contactos.comparadores;

import aed.modelo.contactos.Contacto;
import aed.Comparacao;

public enum ComparacaoContactoPorUltimoNomeAscSegPorDataNascimentoDesc implements Comparacao<Contacto> {
    CRITERIO;

    @Override
    public int comparar(Contacto o1, Contacto o2) {
        int cp = o1.getUltimoNome().compareTo(o2.getUltimoNome());

        if (cp != 0){
            return cp;
        }


        return -o1.getDataNascimento().compareTo(o2.getDataNascimento());
    }
}
