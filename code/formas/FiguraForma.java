package formas;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 * Escreva uma descrição da classe FiguraCirculos aqui.
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */
public class FiguraForma
{
    public static ArrayList Forma = new ArrayList(); //estrutura de dados que armazena todas as formas desenhados pelo usuario
    
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
    public static void desenharForma(Graphics g, int x1, int y1, int x2, int y2, String nome, int esp, Color cor1, Color cor2){
        FormaGr r = new FormaGr(x1, y1, x2, y2, nome, esp, cor1, cor2);
        r.desenharForma(g);
    }
    
    public static void salvaPrimitivo(int x1, int y1, int x2, int y2, String nome, int esp, Color cor1, Color cor2){
        FormaGr r = new FormaGr(x1, y1, x2, y2, nome, esp, cor1, cor2);
        Forma.add(r);
    }
    
    public static void redesenharForma(Graphics g, FormaGr rg){
        int x1 = (int)rg.getP1().getX();
        int y1 = (int)rg.getP1().getY();
        int x2 = (int)rg.getP2().getX();
        int y2 = (int)rg.getP2().getY();
        
        Color cor1 = rg.getCor1Forma();
        Color cor2 = rg.getCor2Forma();
        String nome = rg.getNomeForma();
        int esp = rg.getEspForma();
        
        FormaGr r = new FormaGr(x1, y1, x2, y2, nome, esp, cor1, cor2);
        r.desenharForma(g);
    }
    
    public static void deletarTudo(){
        Forma.removeAll(Forma);
    }
    
    /**
     * Desenha varias retas na area de desenho
     *
     * @param g biblioteca grafica para desenhar os primitivos
     * @param qtde quantidade de retas
     * @param esp espessura das retas
     */
    public static void desenharForma(Graphics g, int qtde, int esp){
        for(int i=0; i < qtde; i++) {
            int x1 = (int) (Math.random() * 801);
            int y1 = (int) (Math.random() * 801);
            int x2 = (int) (Math.random() * 801);
            int y2 = (int) (Math.random() * 801);

            // Cor (R, G e B) aleatorio
            Color cor1= new Color((int) (Math.random() * 256),  
                    (int) (Math.random() * 256),  
                    (int) (Math.random() * 256));
                    
            Color cor2 = new Color((int) (Math.random() * 256),  
                    (int) (Math.random() * 256),  
                    (int) (Math.random() * 256));
                    
            FormaGr r = new FormaGr(x1, y1, x2, y2, "", esp, cor1, cor2);
            r.desenharForma(g);
        }
    }
}
