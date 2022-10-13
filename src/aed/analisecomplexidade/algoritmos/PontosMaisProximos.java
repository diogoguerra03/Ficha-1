package aed.analisecomplexidade.algoritmos;

import aed.utils.Par;

import java.awt.geom.Point2D;
import java.util.Arrays;

public class PontosMaisProximos {
    public static Par<Point2D, Point2D> executar(Point2D... pontos){
        if (pontos.length < 2){
            throw new IllegalArgumentException("Pelo menos 2 pontos");
        }

        if (pontos.length == 2){
            return new Par<>(pontos[0], pontos[1]);
        }

        double distanciaMinima = Double.MAX_VALUE;
        int i1 = 0;
        int i2 = 1;
        for (int i = 0; i < pontos.length - 1; i++) {
            for (int j = i + 1; j < pontos.length; j++) {
                double distancia = pontos[i].distanceSq(pontos[j]);
                if (distancia < distanciaMinima){
                    i1 = i;
                    i2 = j;
                }
            }
        }

        return new Par<>(pontos[i1], pontos[i2]);
    }

    public static void apresentar(Point2D... pontos){
        try {
            System.out.println("Pontos mais proximos (" + Arrays.toString(pontos) + ") = " + executar(pontos));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
