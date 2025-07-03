package triangulo;
import ponto.*;
import reta.*;
import java.awt.Color;
import java.awt.Graphics;

/**
 * Escreva uma descrição da classe TrianguloGr aqui.
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */
public class TrianguloGr extends Triangulo
{
    // Atributos da reta grafica
    Color corTriangulo = Color.BLACK;   // cor da reta
    String nomeTriangulo = ""; // nome da reta
    Color corNomeTriangulo  = Color.BLACK;
    int espTriangulo = 1; // espessura da reta
    
    //Construtor
    /**
     * TrianguloGr - Constroi um triangulo grafico
     *
     * @param x1 int. Coordenada x1
     * @param y1 int. Coordenada y2
     * @param x2 int. Coordenada x2
     * @param y2 int. Coordenada y2
     * @param x3 int. Coordenada x3
     * @param y3 int. Coordenada y3
     * @param nome String. Nome do Triangulo
     * @param esp int. Espessura do Triangulo
     * @param cor Color. Cor do Triangulo
     */
    public TrianguloGr(int x1, int y1, int x2, int y2, int x3, int y3, String nome, int esp, Color cor){
        super(x1, y1, x2, y2, x3, y3);
        setCorTriangulo(cor);
        setNomeTriangulo(nome);
        setEspTriangulo(esp);
    }
    
    /**
     * Altera a cor da Triangulo.
     *
     * @param cor Color. Cor da Triangulo.
     */
    public void setCorTriangulo(Color cor) {
        this.corTriangulo = cor;
    }

    /**
     * Altera o nome da Triangulo.
     *
     * @param str String. Nome da Triangulo.
     */
    public void setNomeTriangulo(String str) {
        this.nomeTriangulo = str;
    }

    /**
     * Altera a espessura da Triangulo.
     *
     * @param esp int. Espessura da Triangulo.
     */
    public void setEspTriangulo(int esp) {
        this.espTriangulo = esp;
    }

    /**
     * Retorna a espessura da Triangulo.
     *
     * @return int. Espessura da Triangulo.
     */
    public int getEspTriangulo() {
        return(this.espTriangulo);
    }

    /**
     * Retorna a cor da Triangulo.
     *
     * @return Color. Cor da Triangulo.
     */
    public Color getCorTriangulo() {
        return corTriangulo;
    }

    /**
     * Retorna o nome da Triangulo.
     *
     * @return String. Nome da Triangulo.
     */
    public String getNomeTriangulo() {
        return nomeTriangulo;
    }

    /**
     * @return the corNomeTriangulo
     */
    public Color getCorNomeTriangulo() {
        return corNomeTriangulo;
    }

    /**
     * @param corNomeTriangulo the corNomeTriangulo to set
     */
    public void setCorNomeTriangulo(Color corNomeTriangulo) {
        this.corNomeTriangulo = corNomeTriangulo;
    }
    
    public void desenharTriangulo(Graphics g){
        double x1 = getP1().getX();
        double x2 = getP2().getX();
        double x3 = getP3().getX();
        double y1 = getP1().getY();
        double y2 = getP2().getY();
        double y3 = getP3().getY();
        
        FiguraRetas.desenharReta(g, (int)x1, (int)y1, (int)x2, (int)y2, "", getEspTriangulo(), getCorTriangulo());
        FiguraRetas.desenharReta(g, (int)x2, (int)y2, (int)x3, (int)y3, "", getEspTriangulo(), getCorTriangulo());
        FiguraRetas.desenharReta(g, (int)x1, (int)y1, (int)x3, (int)y3, "", getEspTriangulo(), getCorTriangulo());
        
    }
}
