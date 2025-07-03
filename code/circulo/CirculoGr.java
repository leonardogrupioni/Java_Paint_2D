package circulo;
import ponto.*;
import reta.*;
import java.awt.Color;
import java.awt.Graphics;

/**
 * Escreva uma descrição da classe CirculoGr aqui.
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */
public class CirculoGr extends Circulo {
    
    // Atributos da reta grafica
    Color corCirculo = Color.BLACK;   // cor da reta
    String nomeCirculo = ""; // nome da reta
    Color corNomeCirculo  = Color.BLACK;
    int espCirculo = 1; // espessura da reta
    Color corFundo = new Color(238, 238, 238);
    double raio;
    
    //Construtor
    /**
     * CirculoGr - Constroi um Circulo grafico
     *
     * @param x1 int. Coordenada x1
     * @param y1 int. Coordenada y1
     * @param x2 int. Coordenada x2
     * @param y2 int. Coordenada y2
     * @param nome String. Nome do Circulo
     * @param esp int. Espessura do Circulo
     * @param cor Color. Cor do Circulo
     */
    public CirculoGr(int x1, int y1, int x2, int y2, String nome, int esp, Color cor){
        super(x1, y1, x2, y2);
        setCorCirculo(cor);
        setNomeCirculo(nome);
        setEspCirculo(esp);
        setRaio(x1,y1,x2,y2);
    }
    
    /**
     * Altera a cor da Circulo.
     *
     * @param cor Color. Cor da Circulo.
     */
    public void setCorCirculo(Color cor) {
        this.corCirculo = cor;
    }

    /**
     * Altera o nome da Circulo.
     *
     * @param str String. Nome da Circulo.
     */
    public void setNomeCirculo(String str) {
        this.nomeCirculo = str;
    }

    /**
     * Altera a espessura da Circulo.
     *
     * @param esp int. Espessura da Circulo.
     */
    public void setEspCirculo(int esp) {
        this.espCirculo = esp;
    }

    /**
     * Retorna a espessura da Circulo.
     *
     * @return int. Espessura da Circulo.
     */
    public int getEspCirculo() {
        return(this.espCirculo);
    }
    
    public void setRaio(int x1, int y1, int x2, int y2){
        this.raio = calcularRaio(x1, x2, y1, y2);
    }
    
    public double getRaio(){
        return this.raio;
    }

    /**
     * Retorna a cor da Circulo.
     *
     * @return Color. Cor da Circulo.
     */
    public Color getCorCirculo() {
        return corCirculo;
    }

    /**
     * Retorna o nome da Circulo.
     *
     * @return String. Nome da Circulo.
     */
    public String getNomeCirculo() {
        return nomeCirculo;
    }

    /**
     * @return the corNomeCirculo
     */
    public Color getCorNomeCirculo() {
        return corNomeCirculo;
    }

    /**
     * @param corNomeCirculo the corNomeCirculo to set
     */
    public void setCorNomeCirculo(Color corNomeCirculo) {
        this.corNomeCirculo = corNomeCirculo;
    }
    
    public void desenharCirculoEq(Graphics g){
        double x1 = getP1().getX();
        double x2 = getP2().getX();
        double y1 = getP1().getY();
        double y2 = getP2().getY();
        PontoGr ponto; 
        raio = getRaio();
        double x,y;
        
        for(double a = 0; a < 360; a = a + 0.1){ //360 graus ou 2pi radianos
            x = calcularX(x1, raio, a);
            y = calcularY(y1, raio, a);
            
            //desenha o ponto
            ponto = new PontoGr((int)x, (int)y, getCorCirculo(), "", getEspCirculo());
            ponto.desenharPonto(g);
        }    
    }
    
    /**
     * Desenha circulo utilizando algoritmo MidPoint (Bresenham)
     * @param g
     */
    void desenharCirculo(Graphics g) {
        if (getRaio() != 0) {
            // Variaveis auxiliares
            PontoGr ponto = new PontoGr(); 

            double x = 0;
            double y = getRaio();
            double d = 5 / 4 - getRaio();

            desenharPontosSimetricos (g, (int)x, (int)y, ponto);

            while (y > x) {
                if (d < 0) {
                    d = d + 2 * x + 3;
                    x++;
                }

                else {
                    d = d + 2 * (x - y) + 5;
                    x++;
                    y--;
                }
                desenharPontosSimetricos (g, (int)x, (int)y, ponto);
            }
        }       
    }

    /**
     * Desenha os pontos simetricos do circulo. Um em cada octante
     * @param g - componente para acessar modo gráfico
     * @param x - coordenada x de um ponto do primeiro octante do circulo
     * @param y - coordenada y de um ponto do primeiro octante do circulo
     * @param ponto - objeto utilizado para "acender" (desenhar) um ponto
     */
    private void desenharPontosSimetricos(Graphics g, int x, int y, PontoGr ponto){
        // pega o centro do circulo
        int cx = (int)getP1().getX();
        int cy = (int)getP1().getY();

        // seta cor e espessura
        ponto.setCorPto(getCorCirculo());
        ponto.setDiametro(getEspCirculo());

        // desenha nome do circulo
        g.setColor(getCorNomeCirculo());
        g.drawString(getNomeCirculo(), cx, cy);

        // desenha os 8 pontos simetricos. Inclui o centro do circulo
        // (1) (cx+x, cy+y)
        desenharPontoSimetrico(g, cx + x, cy + y, ponto);
        // (2) (cx+y, cy+x)
        desenharPontoSimetrico(g, cx + y, cy + x, ponto);
        // (3) (cx-y, cy+x)
        desenharPontoSimetrico(g, cx - y, cy + x, ponto);
        // (4) (cx-x, cy+y)
        desenharPontoSimetrico(g, cx - x, cy + y, ponto);
        // (5) (cx-x, cy-y)
        desenharPontoSimetrico(g, cx - x, cy - y, ponto);
        // (6) (cx-y, cy-x)
        desenharPontoSimetrico(g, cx - y, cy - x, ponto);
        // (7) (cx+y, cy-x)
        desenharPontoSimetrico(g, cx + y, cy - x, ponto);
        // (8) (cx+x, cy-y)
        desenharPontoSimetrico(g, cx + x, cy - y, ponto);
    }


    /**
     * Método desenharPontoSimetrico
     *
     * @param x coordenada x
     * @param y coordenda y
     * @param ponto Ponto utilizado para desenhar o ponto
     * @param g Biblioteca grafica
     */
    private void desenharPontoSimetrico(Graphics g, int x, int y, PontoGr ponto){
        ponto.setX(x);
        ponto.setY(y);
        ponto.desenharPonto(g);
    }
    
    
    
    /*public void desenharCirculoA(Graphics g){
        double x1 = getP1().getX();
        double x2 = getP2().getX();
        double y1 = getP1().getY();
        double y2 = getP2().getY();
        PontoGr ponto; 
        double raio = calcularRaio(x1, x2, y1, y2);
        double x,y;
        int max = 450;
        
        Ponto salvos[] = new Ponto[max];
        int i = 0;
        
        for(double a = 0; a < 45; a = a + 0.1){ //360 graus ou 2pi radianos
            x = calcularX(x1, raio, a);
            y = calcularY(y1, raio, a);
            
            //guardar posicoes
            ponto = new PontoGr((int)x, (int)y, getCorCirculo(), "", getEspCirculo());
            salvos[i] = ponto;
            i++;
        }
        
        //primeira regra x y normal
        for(i = 0; i < max; i++){
            //x,y
            x = salvos[i].getX();
            y = salvos[i].getY();
            ponto = new PontoGr((int)x, (int)y, getCorCirculo(), "", getEspCirculo());
            ponto.desenharPonto(g);
            ponto = new PontoGr((int)y, (int)x, getCorCirculo(), "", getEspCirculo());
            ponto.desenharPonto(g);
            /*
            //-x,y
            x = Math.pow(salvos[i].getX(),-1);
            y = salvos[i].getY();
            ponto = new PontoGr((int)x, (int)y, getCorCirculo(), "", getEspCirculo());
            ponto.desenharPonto(g);
            // ponto = new PontoGr((int)y, (int)x, getCorCirculo(), "", getEspCirculo());
            // ponto.desenharPonto(g);
            
            //x,-y
            x = salvos[i].getX();
            y = Math.pow(salvos[i].getY(),-1);
            ponto = new PontoGr((int)x, (int)y, getCorCirculo(), "", getEspCirculo());
            ponto.desenharPonto(g);
            // ponto = new PontoGr((int)y, (int)x, getCorCirculo(), "", getEspCirculo());
            // ponto.desenharPonto(g);
            
            //-x,-y
            x = Math.pow(salvos[i].getX(),-1);
            y = Math.pow(salvos[i].getY(),-1);
            ponto = new PontoGr((int)x, (int)y, getCorCirculo(), "", getEspCirculo());
            ponto.desenharPonto(g);
            // ponto = new PontoGr((int)y, (int)x, getCorCirculo(), "", getEspCirculo());
            // ponto.desenharPonto(g);          
            
        }
        
    }*/
}


/* desenhar circulo preenchido
        //desenha um ponto colorido usando o raio como espessura
        ponto = new PontoGr((int)x1, (int)y2, getCorCirculo(), "", (int)raio);
        ponto.desenharPonto(g);
        
        //desenha ponto interno subtraindo a espessura escolhida e colocando a cor de fundo
        ponto = new PontoGr((int)x1, (int)y2, corFundo, "", ((int)raio) - getEspCirculo());
        ponto.desenharPonto(g);*/