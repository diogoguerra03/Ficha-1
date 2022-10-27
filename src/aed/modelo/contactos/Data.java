package aed.modelo.contactos;

public class Data {
    private int dia;
    private int mes;
    private int ano;

    public Data(int dia, int mes, int ano) {
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }

    public int getDia() {
        return dia;
    }

    public int getMes() {
        return mes;
    }

    public int getAno() {
        return ano;
    }

    public int compareTo(Data data) {

        // ano
        int cp = Integer.compare(ano, data.ano);
        if (cp != 0){
            return cp;
        }

        // mes
        cp = Integer.compare(mes, data.mes);
        if (cp != 0){
            return cp;
        }

        // dia
        return Integer.compare(dia, data.dia);
    }

    @Override
    public String toString() {
        return dia + "/" + mes + "/" + ano;
    }
}
