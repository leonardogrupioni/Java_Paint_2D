package triangulo;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 * Escreva uma descrição da classe FiguraTriangulos aqui.
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */
public class FiguraTriangulos
{
    public static ArrayList Triangulos = new ArrayList(); //estrutura de dados que armazena todos os triangulos desenhados pelo usuario
    
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
    public static void desenharTriangulo(Graphics g, int x1, int y1, int x2, int y2,int x3, int y3, String nome, int esp, Color cor){
        TrianguloGr r = new TrianguloGr(x1, y1, x2, y2, x3, y3, nome, esp, cor);
        r.desenharTriangulo(g);
    }
    
    public static void salvaPrimitivo(int x1, int y1, int x2, int y2,int x3, int y3, String nome, int esp, Color cor){
        TrianguloGr r = new TrianguloGr(x1, y1, x2, y2, x3, y3, nome, esp, cor);
        Triangulos.add(r);
    }
    
    public static void redesenharTriangulo(Graphics g, TrianguloGr rg){
        int x1 = (int)rg.getP1().getX();
        int y1 = (int)rg.getP1().getY();
        int x2 = (int)rg.getP2().getX();
        int y2 = (int)rg.getP2().getY();
        int x3 = (int)rg.getP3().getX();
        int y3 = (int)rg.getP3().getY();
        
        Color cor = rg.getCorTriangulo();
        String nome = rg.getNomeTriangulo();
        int esp = rg.getEspTriangulo();
        
        TrianguloGr r = new TrianguloGr(x1, y1, x2, y2, x3, y3, nome, esp, cor);
        r.desenharTriangulo(g);
    }
    
    public static void deletarTudo(){
        Triangulos.removeAll(Triangulos);
    }
    
    /**
     * Desenha varias retas na area de desenho
     *
     * @param g biblioteca grafica para desenhar os primitivos
     * @param qtde quantidade de retas
     * @param esp espessura das retas
     */
    public static void desenharRetangulo(Graphics g, int qtde, int esp){
        for(int i=0; i < qtde; i++) {
            int x1 = (int) (Math.random() * 801);
            int y1 = (int) (Math.random() * 801);
            int x2 = (int) (Math.random() * 801);
            int y2 = (int) (Math.random() * 801);
            int x3 = (int) (Math.random() * 801);
            int y3 = (int) (Math.random() * 801);

            // Cor (R, G e B) aleatorio
            Color cor = new Color((int) (Math.random() * 256),  
                    (int) (Math.random() * 256),  
                    (int) (Math.random() * 256));
            TrianguloGr r = new TrianguloGr(x1, y1, x2, y2, x3, y3, "", esp, cor);
            r.desenharTriangulo(g);
        }
    }
}
