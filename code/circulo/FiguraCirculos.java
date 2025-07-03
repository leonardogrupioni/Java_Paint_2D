package circulo;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 * Escreva uma descrição da classe FiguraCirculos aqui.
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */
public class FiguraCirculos
{
    public static ArrayList Circulo = new ArrayList(); //estrutura de dados que armazena todos os circulos desenhados pelo usuario
    
    /**
     * Desenha uma reta de acordo com os pontos p1 e p2
     *
     * @param g biblioteca para desenhar o primitivo grafico
     * @param x1 coordenada x de p1
     * @param y1 coordenada y de p1
     * @param x2 coordenada x de p2
     * @param y2 coordenada y de p2
     * @param nome nome da reta
     * @param esp espessura da reta
     * @param cor cor da reta
     */
    public static void desenharCirculo(Graphics g, int x1, int y1, int x2, int y2, String nome, int esp, Color cor){
        CirculoGr r = new CirculoGr(x1, y1, x2, y2, nome, esp, cor);
        r.desenharCirculo(g);
    }
    
    public static void salvaPrimitivo(int x1, int y1, int x2, int y2, String nome, int esp, Color cor){
        CirculoGr r = new CirculoGr(x1, y1, x2, y2, nome, esp, cor);
        Circulo.add(r);
    }
    
    public static void redesenharCirculo(Graphics g, CirculoGr cg){
        int x1 = (int)cg.getP1().getX();
        int y1 = (int)cg.getP1().getY();
        int x2 = (int)cg.getP2().getX();
        int y2 = (int)cg.getP2().getY();
        
        Color cor = cg.getCorCirculo();
        String nome = cg.getNomeCirculo();
        int esp = cg.getEspCirculo();
        
        CirculoGr r = new CirculoGr(x1, y1, x2, y2, nome, esp, cor);
        r.desenharCirculo(g);
    }
    
    public static void deletarTudo(){
        Circulo.removeAll(Circulo);
    }
    
    /**
     * Desenha varias retas na area de desenho
     *
     * @param g biblioteca grafica para desenhar os primitivos
     * @param qtde quantidade de retas
     * @param esp espessura das retas
     */
    public static void desenharCirculo(Graphics g, int qtde, int esp){

        for(int i=0; i < qtde; i++) {
            int x1 = (int) (Math.random() * 801);
            int y1 = (int) (Math.random() * 801);
            int x2 = (int) (Math.random() * 801);
            int y2 = (int) (Math.random() * 801);

            // Cor (R, G e B) aleatorio
            Color cor = new Color((int) (Math.random() * 256),  
                    (int) (Math.random() * 256),  
                    (int) (Math.random() * 256));
            CirculoGr r = new CirculoGr(x1, y1, x2, y2, "", esp, cor);
            r.desenharCirculo(g);
        }
    }
}
