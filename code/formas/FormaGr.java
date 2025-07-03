package formas;
import ponto.*;
import reta.*;
import circulo.*;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 * Escreva uma descrição da classe FormaGr aqui.
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */
public class FormaGr extends Forma {
    public static ArrayList retaForma = new ArrayList(); //estrutura de dados que armazena todas as retas da forma
    public static ArrayList circuloForma = new ArrayList(); //estrutura de dados que armazena todos os circulos do forma
    
    // Atributos da reta grafica
    Color cor1Forma = Color.BLACK;   // cor da reta
    Color cor2Forma = Color.BLACK;
    String nomeForma = ""; // nome da reta
    Color corNomeForma  = Color.BLACK;
    int espForma = 1; // espessura da reta

    /**
     * FormaGr Construtor
     *
     * @param x1 int. Coordenada x1
     * @param y1 int. Coordenada y1
     * @param x2 int. Coordenada x2
     * @param y2 int. Coordenada y2
     * @param nome String. Nome da Forma
     * @param esp int. Espessura da Forma
     * @param cor1 Color. Cor1 da Forma
     * @param cor2 Color. Cor2 da Forma
     */
    public FormaGr(int x1, int y1, int x2, int y2, String nome, int esp, Color cor1, Color cor2){
        super(x1, y1, x2, y2);
        setCor1Forma(cor1);
        setCor2Forma(cor2);
        setNomeForma(nome);
        setEspForma(esp);
    }

    /**
     * Altera a cor da Forma.
     *
     * @param cor Color. Cor da Forma.
     */
    public void setCor1Forma(Color cor) {
        this.cor1Forma = cor;
    }

    /**
     * Altera a cor da Forma.
     *
     * @param cor Color. Cor da Forma.
     */
    public void setCor2Forma(Color cor) {
        this.cor2Forma = cor;
    }

    /**
     * Altera o nome da Forma.
     *
     * @param str String. Nome da Forma.
     */
    public void setNomeForma(String str) {
        this.nomeForma = str;
    }

    /**
     * Altera a espessura da Forma.
     *
     * @param esp int. Espessura da Forma.
     */
    public void setEspForma(int esp) {
        this.espForma = esp;
    }

    /**
     * Retorna a espessura da Forma.
     *
     * @return int. Espessura da Forma.
     */
    public int getEspForma() {
        return(this.espForma);
    }

    /**
     * Retorna a cor da Forma.
     *
     * @return Color. Cor da Forma.
     */
    public Color getCor1Forma() {
        return cor1Forma;
    }

    /**
     * Retorna a cor da Forma.
     *
     * @return Color. Cor da Forma.
     */
    public Color getCor2Forma() {
        return cor2Forma;
    }

    /**
     * Retorna o nome da Forma.
     *
     * @return String. Nome da Forma.
     */
    public String getNomeForma() {
        return nomeForma;
    }

    /**
     * @return the corNomeForma
     */
    public Color getCorNomeForma() {
        return corNomeForma;
    }

    /**
     * @param corNomeForma the corNomeForma to set
     */
    public void setCorNomeForma(Color corNomeForma) {
        this.corNomeForma = corNomeForma;
    }

    public void desenharForma(Graphics g){
        double xC = getP1().getX();
        double yC = getP1().getY();
        double xClick = getP2().getX(); 
        double yClick = getP2().getY();

        //calcula o raio entre o ponto central e o ponto click
        double raio = Math.sqrt( Math.pow(xClick - xC,2) + Math.pow(yClick - yC,2));

        //calcula os pontos de cada extremidade do Hexagono menor
        double xCe = xC - raio;
        double yCe = yC;

        double xCd = xC + raio;
        double yCd = yC;

        double xa = (int) (xC + raio * Math.cos(Math.toRadians(60)));
        double ya = (int) (yC + raio * Math.sin(Math.toRadians(60)));

        double xA = xC - (xa - xC);
        double yA = yC - (ya - yC);

        double xb = xa - raio;
        double yb = ya;

        double xB = xA + raio;
        double yB = yA;
        
        RetaGr rg;
        CirculoGr c;
        //retas centro hexagono pequeno
        FiguraRetas.desenharReta(g, (int)xCe, (int)yCe, (int)xCd, (int)yCd, "", getEspForma(), getCor1Forma());
        rg = new RetaGr((int)xCe, (int)yCe, (int)xCd, (int)yCd, getCor1Forma(), "", getEspForma());  
        retaForma.add(rg);
        
        FiguraRetas.desenharReta(g, (int)xA, (int)yA, (int)xa, (int)ya, "", getEspForma(), getCor1Forma());
        rg = new RetaGr((int)xA, (int)yA, (int)xa, (int)ya, getCor1Forma(), "", getEspForma());  
        retaForma.add(rg);
        
        FiguraRetas.desenharReta(g, (int)xB, (int)yB, (int)xb, (int)yb, "", getEspForma(), getCor1Forma());
        rg = new RetaGr((int)xB, (int)yB, (int)xb, (int)yb, getCor1Forma(), "", getEspForma());
        retaForma.add(rg);

        //retas fechando hexagono pequeno
        FiguraRetas.desenharReta(g, (int)xCe, (int)yCe, (int)xb, (int)yb, "", getEspForma(), getCor1Forma());
        rg = new RetaGr((int)xCe, (int)yCe, (int)xb, (int)yb, getCor1Forma(), "", getEspForma());
        retaForma.add(rg);
        
        FiguraRetas.desenharReta(g, (int)xb, (int)yb, (int)xa, (int)ya, "", getEspForma(), getCor1Forma());
        rg = new RetaGr((int)xb, (int)yb, (int)xa, (int)ya, getCor1Forma(), "", getEspForma());
        retaForma.add(rg);
        
        FiguraRetas.desenharReta(g, (int)xa, (int)ya, (int)xCd, (int)yCd, "", getEspForma(), getCor1Forma());
        rg = new RetaGr((int)xa, (int)ya, (int)xCd, (int)yCd, getCor1Forma(), "", getEspForma());
        retaForma.add(rg);
        
        FiguraRetas.desenharReta(g, (int)xCd, (int)yCd, (int)xB, (int)yB, "", getEspForma(), getCor1Forma());
        rg = new RetaGr((int)xCd, (int)yCd, (int)xB, (int)yB, getCor1Forma(), "", getEspForma());
        retaForma.add(rg);
        
        FiguraRetas.desenharReta(g, (int)xB, (int)yB, (int)xA, (int)yA, "", getEspForma(), getCor1Forma());
        rg = new RetaGr((int)xB, (int)yB, (int)xA, (int)yA, getCor1Forma(), "", getEspForma());
        retaForma.add(rg);
        
        FiguraRetas.desenharReta(g, (int)xA, (int)yA, (int)xCe, (int)yCe, "", getEspForma(), getCor1Forma());
        rg = new RetaGr((int)xA, (int)yA, (int)xCe, (int)yCe, getCor1Forma(), "", getEspForma());
        retaForma.add(rg);

        //calcula os pontos da extremidade do Hexagono grande. Usando de base os pontos encontrados anteriormente
        double xd = xa + raio;
        double yd = ya;

        double xD = xC - (xd - xC);
        double yD = yC - (yd - yC);

        double xe = xD;
        double ye = yd;

        double xE = xd;
        double yE = yD;

        double raioG = Math.sqrt( Math.pow(xd - xC,2) + Math.pow(yd - yC,2));

        double xF = xC;
        double yF = yC + raioG;

        double xf = xC;
        double yf = yC - raioG;

        //retas centro hexagono grande
        FiguraRetas.desenharReta(g, (int)xe, (int)ye, (int)xE, (int)yE, "", getEspForma(), getCor1Forma());
        rg = new RetaGr((int)xe, (int)ye, (int)xE, (int)yE, getCor1Forma(), "", getEspForma());
        retaForma.add(rg);
        
        FiguraRetas.desenharReta(g, (int)xD, (int)yD, (int)xd, (int)yd, "", getEspForma(), getCor1Forma());
        rg = new RetaGr((int)xD, (int)yD, (int)xd, (int)yd, getCor1Forma(), "", getEspForma());
        retaForma.add(rg);
        
        FiguraRetas.desenharReta(g, (int)xF, (int)yF, (int)xf, (int)yf, "", getEspForma(), getCor1Forma());
        rg = new RetaGr((int)xF, (int)yF, (int)xf, (int)yf, getCor1Forma(), "", getEspForma());
        retaForma.add(rg);

        //retas fechando hexagono Grande
        FiguraRetas.desenharReta(g, (int)xe, (int)ye, (int)xF, (int)yF, "", getEspForma(), getCor1Forma());
        rg = new RetaGr((int)xe, (int)ye, (int)xF, (int)yF, getCor1Forma(), "", getEspForma());
        retaForma.add(rg);
        
        FiguraRetas.desenharReta(g, (int)xF, (int)yF, (int)xd, (int)yd, "", getEspForma(), getCor1Forma());
        rg = new RetaGr((int)xF, (int)yF, (int)xd, (int)yd, getCor1Forma(), "", getEspForma());
        retaForma.add(rg);
        
        FiguraRetas.desenharReta(g, (int)xd, (int)yd, (int)xE, (int)yE, "", getEspForma(), getCor1Forma());
        rg = new RetaGr((int)xd, (int)yd, (int)xE, (int)yE, getCor1Forma(), "", getEspForma());
        retaForma.add(rg);
        
        FiguraRetas.desenharReta(g, (int)xE, (int)yE, (int)xf, (int)yf, "", getEspForma(), getCor1Forma());
        rg = new RetaGr((int)xE, (int)yE, (int)xf, (int)yf, getCor1Forma(), "", getEspForma());
        retaForma.add(rg);
        
        FiguraRetas.desenharReta(g, (int)xf, (int)yf, (int)xD, (int)yD, "", getEspForma(), getCor1Forma());
        rg = new RetaGr((int)xf, (int)yf, (int)xD, (int)yD, getCor1Forma(), "", getEspForma());
        retaForma.add(rg);
        
        FiguraRetas.desenharReta(g, (int)xD, (int)yD, (int)xe, (int)ye, "", getEspForma(), getCor1Forma());
        rg = new RetaGr((int)xD, (int)yD, (int)xe, (int)ye, getCor1Forma(), "", getEspForma());
        retaForma.add(rg);

        //retas triangulo grande
        FiguraRetas.desenharReta(g, (int)xD, (int)yD, (int)xF, (int)yF, "", getEspForma(), getCor1Forma());
        rg = new RetaGr((int)xD, (int)yD, (int)xF, (int)yF, getCor1Forma(), "", getEspForma());
        retaForma.add(rg);
        
        FiguraRetas.desenharReta(g, (int)xF, (int)yF, (int)xE, (int)yE, "", getEspForma(), getCor1Forma());
        rg = new RetaGr((int)xF, (int)yF, (int)xE, (int)yE, getCor1Forma(), "", getEspForma());
        retaForma.add(rg);
        
        FiguraRetas.desenharReta(g, (int)xE, (int)yE, (int)xD, (int)yD, "", getEspForma(), getCor1Forma());
        rg = new RetaGr((int)xE, (int)yE, (int)xD, (int)yD, getCor1Forma(), "", getEspForma());
        retaForma.add(rg);

        //retas triangulo grande invertido
        FiguraRetas.desenharReta(g, (int)xe, (int)ye, (int)xd, (int)yd, "", getEspForma(), getCor1Forma());
        rg = new RetaGr((int)xe, (int)ye, (int)xd, (int)yd, getCor1Forma(), "", getEspForma());
        retaForma.add(rg);
        
        FiguraRetas.desenharReta(g, (int)xd, (int)yd, (int)xf, (int)yf, "", getEspForma(), getCor1Forma());
        rg = new RetaGr((int)xd, (int)yd, (int)xf, (int)yf, getCor1Forma(), "", getEspForma());
        retaForma.add(rg);
        
        FiguraRetas.desenharReta(g, (int)xf, (int)yf, (int)xe, (int)ye, "", getEspForma(), getCor1Forma());
        rg = new RetaGr((int)xf, (int)yf, (int)xe, (int)ye, getCor1Forma(), "", getEspForma());
        retaForma.add(rg);

        //desenhar circulos
        FiguraCirculos.desenharCirculo(g, (int)xC, (int)yC, (int)xCd, (int)yCd, "", getEspForma(), getCor2Forma());
        c = new CirculoGr((int)xC, (int)yC, (int)xCd, (int)yCd, "", getEspForma(), getCor2Forma());
        circuloForma.add(c);
        
        FiguraCirculos.desenharCirculo(g, (int)xCd, (int)yCd, (int)xC, (int)yC, "", getEspForma(), getCor2Forma());
        c = new CirculoGr((int)xCd, (int)yCd, (int)xC, (int)yC, "", getEspForma(), getCor2Forma());
        circuloForma.add(c);
        
        FiguraCirculos.desenharCirculo(g, (int)xCe, (int)yCe, (int)xC, (int)yC, "", getEspForma(), getCor2Forma());
        c = new CirculoGr((int)xCe, (int)yCe, (int)xC, (int)yC, "", getEspForma(), getCor2Forma());
        circuloForma.add(c);

        FiguraCirculos.desenharCirculo(g, (int)xa, (int)ya, (int)xd, (int)yd, "", getEspForma(), getCor2Forma());
        c = new CirculoGr((int)xa, (int)ya, (int)xd, (int)yd, "", getEspForma(), getCor2Forma());
        circuloForma.add(c);
        
        FiguraCirculos.desenharCirculo(g, (int)xb, (int)yb, (int)xa, (int)ya, "", getEspForma(), getCor2Forma());
        c = new CirculoGr((int)xb, (int)yb, (int)xa, (int)ya, "", getEspForma(), getCor2Forma());
        circuloForma.add(c);
        
        FiguraCirculos.desenharCirculo(g, (int)xA, (int)yA, (int)xB, (int)yB, "", getEspForma(), getCor2Forma());
        c = new CirculoGr((int)xA, (int)yA, (int)xB, (int)yB, "", getEspForma(), getCor2Forma());
        circuloForma.add(c);
        
        FiguraCirculos.desenharCirculo(g, (int)xB, (int)yB, (int)xE, (int)yE, "", getEspForma(), getCor2Forma());
        c = new CirculoGr((int)xB, (int)yB, (int)xE, (int)yE, "", getEspForma(), getCor2Forma());
        circuloForma.add(c);
    }
}
