package retangulo;
import ponto.*;
import reta.*;
import java.awt.Color;
import java.awt.Graphics;

/**
 * Escreva uma descrição da classe RetangulonguloGr aqui.
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */
public class RetanguloGr extends Retangulo{
    
    // Atributos da reta grafica
    Color corRetangulo = Color.BLACK;   // cor da reta
    String nomeRetangulo = ""; // nome da reta
    Color corNomeRetangulo  = Color.BLACK;
    int espRetangulo = 1; // espessura da reta
    
    public RetanguloGr(int x1, int y1, int x2, int y2, String nome, int esp, Color cor){
        super(x1, y1, x2, y2);
        setCorRetangulo(cor);
        setNomeRetangulo(nome);
        setEspRetangulo(esp);
    }
    
    /**
     * Altera a cor da Retangulo.
     *
     * @param cor Color. Cor da Retangulo.
     */
    public void setCorRetangulo(Color cor) {
        this.corRetangulo = cor;
    }

    /**
     * Altera o nome da Retangulo.
     *
     * @param str String. Nome da Retangulo.
     */
    public void setNomeRetangulo(String str) {
        this.nomeRetangulo = str;
    }

    /**
     * Altera a espessura da Retangulo.
     *
     * @param esp int. Espessura da Retangulo.
     */
    public void setEspRetangulo(int esp) {
        this.espRetangulo = esp;
    }

    /**
     * Retorna a espessura da Retangulo.
     *
     * @return int. Espessura da Retangulo.
     */
    public int getEspRetangulo() {
        return(this.espRetangulo);
    }

    /**
     * Retorna a cor da Retangulo.
     *
     * @return Color. Cor da Retangulo.
     */
    public Color getCorRetangulo() {
        return corRetangulo;
    }

    /**
     * Retorna o nome da Retangulo.
     *
     * @return String. Nome da Retangulo.
     */
    public String getNomeRetangulo() {
        return nomeRetangulo;
    }

    /**
     * @return the corNomeRetangulo
     */
    public Color getCorNomeRetangulo() {
        return corNomeRetangulo;
    }

    /**
     * @param corNomeRetangulo the corNomeRetangulo to set
     */
    public void setCorNomeRetangulo(Color corNomeRetangulo) {
        this.corNomeRetangulo = corNomeRetangulo;
    }
    
    public void desenharRetangulo(Graphics g){
        double x1 = getP1().getX();
        double x2 = getP2().getX();
        double y1 = getP1().getY();
        double y2 = getP2().getY();
        
        FiguraRetas.desenharReta(g, (int)x1, (int)y2, (int)x2, (int)y2, "", getEspRetangulo(), getCorRetangulo());
        FiguraRetas.desenharReta(g, (int)x1, (int)y1, (int)x2, (int)y1, "", getEspRetangulo(), getCorRetangulo());
        FiguraRetas.desenharReta(g, (int)x1, (int)y2, (int)x1, (int)y1, "", getEspRetangulo(), getCorRetangulo());
        FiguraRetas.desenharReta(g, (int)x2, (int)y2, (int)x2, (int)y1, "", getEspRetangulo(), getCorRetangulo());
        
    }
}
