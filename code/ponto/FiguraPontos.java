package ponto;
import java.awt.Color;
import java.awt.Graphics;
import java.util.*;

/**
 * Contem metodos para desenhar figuras com ponto
 * 
 * @author Julio Arakaki
 * @version 20220815
 */
public class FiguraPontos {
    public static ArrayList Pontos = new ArrayList(); //estrutura de dados que armazena todos os pontos desenhados pelo usuario

    /**
     * Desenha um ponto na tela
     * @param g biblioteca grafica para desenhar elementos gráficos
     * @param x coordena x do ponto
     * @param y coordenada y do ponto
     * @param nome nome do ponto
     * @param diametro diametro do ponto
     * @param cor cor do ponto
     */
    public static void desenharPonto(Graphics g, int x, int y, String nome, int diametro, Color cor){
        PontoGr p = new PontoGr(x, y, cor, nome, diametro);
        p.desenharPonto(g);
    }
    
    public static void salvaPrimitivo(int x, int y, String nome, int diametro, Color cor){
        PontoGr p = new PontoGr(x, y, cor, nome, diametro);
        Pontos.add(p);
    }
    
    public static void deletarTudo(){
        Pontos.removeAll(Pontos);
    }
    
    public static void redesenharPonto(Graphics g, PontoGr p){
        int x = (int)p.getX();
        int y = (int)p.getY();
        Color cor = p.getCorPto();
        String nome = p.getNomePto();
        int diametro = p.getDiametro();

        PontoGr pg = new PontoGr(x, y, cor, nome, diametro);
        pg.desenharPonto(g);
    }
    
    /**
     * Desenha variso pontos na tela com cores diferentes
     * @param g biblioteca grafica para desenhar elementos gráficos
     * @param qtde quantidade de pontos
     * @param diametro diametro do pontos
     */
    public static void desenharPontos(Graphics g, int qtde, int diametro){

        for(int i=0; i < qtde; i++) {
            int x = (int) (Math.random() * 801);
            int y = (int) (Math.random() * 801);

            // R, G e B aleatorio
            Color cor = new Color((int) (Math.random() * 256),  
                    (int) (Math.random() * 256),  
                    (int) (Math.random() * 256));
            PontoGr p = new PontoGr(x, y, cor, diametro);
            p.desenharPonto(g);
        }
    }
}
