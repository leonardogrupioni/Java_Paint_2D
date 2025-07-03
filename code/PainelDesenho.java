import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import java.util.Collection;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import PPersistencia.*;

import ponto.*;
import reta.*;
import retangulo.*;
import triangulo.*;
import circulo.*;
import formas.*;

/**
 * Cria desenhos de acordo com o tipo e eventos do mouse
 * 
 * @author João Pedro Figols, Julia Schmidt, Leonardo Grupioni 
 * @co-author Julio Arakaki 
 * @version 20231030
 */
public class  PainelDesenho extends JPanel implements MouseListener, MouseMotionListener {
    JLabel msg;           // Label para mensagens
    TipoPrimitivo tipo; // Tipo do primitivo
    Color corAtual = Color.BLACK;;       // Cor atual do primitivo
    Color corAnterior = Color.BLACK;
    int esp = 1;              // Diametro do ponto
    boolean desenhar;
    
    // Para ponto
    int x, y, xq, yq;

    // Para reta
    int x1, y1, x2, y2, x3, y3;
    int aux = 0;
    int sent = 0;
    
    // selecionar primeiro click do mouse
    boolean primeiraVez = true; 
    boolean transfCtrlz = false;
    boolean SinalCtrlz = false;

    Graphics g;

    public static Object ctrlz = new Object();
    
    AlterandoFiguras alterar = new AlterandoFiguras();
    EntradaDeDados ent = new EntradaDeDados();
    Persistencia persistencia = new Json();

    /**
     * Constroi o painel de desenho
     *
     * @param msg mensagem a ser escrita no rodape do painel
     * @param tipo tipo atual do primitivo
     * @param corAtual cor atual do primitivo
     * @param esp espessura atual do primitivo
     */
    public PainelDesenho(JLabel msg, TipoPrimitivo tipo, Color corAtual, int esp){
        setTipo(tipo);
        setMsg(msg);
        setCorAtual(corAtual);
        setEsp(esp);

        // Adiciona "ouvidor" de eventos de mouse
        this.addMouseListener(this); 
        this.addMouseMotionListener(this);

    }

    /**
     * Altera o tipo atual do primitivo
     *
     * @param tipo tipo do primitivo
     */
    public void setTipo(TipoPrimitivo tipo){
        this.tipo = tipo;
    }

    /**
     * Retorna o tipo do primitivo
     *
     * @return tipo do primitivo
     */
    public TipoPrimitivo getTipo(){
        return this.tipo;
    }

    /**
     * Altera a espessura do primitivo
     *
     * @param esp espessura do primitivo
     */
    public void setEsp(int esp){
        this.esp = esp;
    }

    /**
     * Retorna a espessura do primitivo
     *
     * @return espessura do primitivo
     */
    public int getEsp(){
        return this.esp;
    }

    /**
     * Altera a cor atual do primitivo
     *
     * @param corAtual cor atual do primitivo
     */
    public void setCorAtual(Color corAtual){
        this.corAtual = corAtual;
    }

    /**
     * retorna a cor atual do primitivo
     *
     * @return cor atual do primitivo
     */
    public Color getCorAtual(){
        return this.corAtual;
    }

    /**
     * Altera a cor atual do primitivo
     *
     * @param corAtual cor atual do primitivo
     */
    public void setCorAnterior(Color corAnterior){
        this.corAnterior = corAnterior;
    }

    /**
     * retorna a cor atual do primitivo
     *
     * @return cor atual do primitivo
     */
    public Color getCorAnterior(){
        return this.corAnterior;
    }

    /**
     * Altera a msg a ser apresentada no rodape
     *
     * @param msg mensagem a ser apresentada
     */
    public void setMsg(JLabel msg){
        this.msg = msg;
    }

    /**
     * Retorna a mensagem
     *
     * @return mensagem as ser apresentada no rodape
     */
    public JLabel getMsg(){
        return this.msg;
    }

    /**
     * Metodo chamado quando o paint eh acionado
     *
     * @param g biblioteca para desenhar em modo grafico
     */
    public void paintComponent(Graphics g) {   
        desenharPrimitivos(g);
    }

    /**
     * Evento: pressionar do mouse
     *
     * @param e dados do evento
     */
    public void mousePressed(MouseEvent e) { 
        g = getGraphics();
        alterar.setG(g);
        
        SinalCtrlz = false;
        
        if (tipo == TipoPrimitivo.PONTO){
            x = e.getX();
            y = e.getY();
            paint(g);

            FiguraPontos.salvaPrimitivo(x, y, "", getEsp(), getCorAtual());
            ctrlz = new PontoGr(x, y, getCorAtual(), "", getEsp());
        } else if (tipo == TipoPrimitivo.RETA || tipo == TipoPrimitivo.RETANGULO || tipo == TipoPrimitivo.CIRCULO || tipo == TipoPrimitivo.FORMA){
            if (primeiraVez == true) {
                x1 = (int)e.getX();
                y1 = (int)e.getY();
                primeiraVez = false;
            }else {
                x2 = (int)e.getX();
                y2 = (int)e.getY();
                primeiraVez = true;
                paint(g);

                if(tipo == TipoPrimitivo.RETA) {
                    FiguraRetas.salvaPrimitivo(x1, y1, x2, y2, "", getEsp(), getCorAtual());
                    ctrlz = new RetaGr(x1, y1, x2, y2, getCorAtual(), "", getEsp());
                }else if(tipo == TipoPrimitivo.RETANGULO){
                    FiguraRetangulos.salvaPrimitivo(x1, y1, x2, y2, "", getEsp(), getCorAtual());
                    ctrlz = new RetanguloGr(x1, y1, x2, y2, "", getEsp(), getCorAtual());
                } else if(tipo == TipoPrimitivo.CIRCULO) {
                    FiguraCirculos.salvaPrimitivo(x1, y1, x2, y2, "", getEsp(), getCorAtual());
                    ctrlz = new CirculoGr(x1, y1, x2, y2, "", getEsp(), getCorAtual());
                }else if(tipo == TipoPrimitivo.FORMA){
                    FiguraForma.salvaPrimitivo(x1, y1, x2, y2, "", getEsp(), getCorAtual(), getCorAnterior());
                    ctrlz = new FormaGr(x1, y1, x2, y2, "", getEsp(), getCorAtual(), getCorAnterior());
                }
            }
        } else if (tipo == TipoPrimitivo.TRIANGULO){
            if (primeiraVez == true) {
                x1 = (int)e.getX();
                y1 = (int)e.getY();
                primeiraVez = false;
            } else {
                if(aux == 0){
                    x2 = (int)e.getX();
                    y2 = (int)e.getY();
                    aux++;
                } else{
                    x3 = (int)e.getX();
                    y3 = (int)e.getY();
                    primeiraVez = true;
                    aux = 0;
                    paint(g);

                    FiguraTriangulos.salvaPrimitivo(x1, y1, x2, y2, x3, y3, "", getEsp(), getCorAtual());    
                    ctrlz = new TrianguloGr(x1, y1, x2, y2, x3, y3, "", getEsp(), getCorAtual());
                }
            }
        }else{
            if(sent == 0){
                x = e.getX();
                y = e.getY();
                ctrlz = alterar.apagarElemento(x, y, ctrlz);
                SinalCtrlz = true;
            }else if(sent == 1){
                if(primeiraVez == true){
                    //ponto do triangulo
                    x = e.getX();
                    y = e.getY();
                    primeiraVez = false;
                } else{
                    //ponto qualquer
                    xq = e.getX();
                    yq = e.getY();
                    primeiraVez = true;
                    
                    SinalCtrlz = true;
                    transfCtrlz = true;
                    ctrlz = alterar.transformacaoTriangulo(x, y, xq, yq);
                }
            }
        }
    }     

    public void mouseReleased(MouseEvent e) { 
    }           

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e) {
    }

    /**
     * Evento mouseMoved: escreve mensagem no rodape (x, y) do mouse
     *
     * @param e dados do evento do mouse
     */
    public void mouseMoved(MouseEvent e) {
        this.msg.setText("("+e.getX() + ", " + e.getY() + ") - " + getTipo());
    }

    /**
     * Desenha os primitivos
     *
     * @param g biblioteca para desenhar em modo grafico
     */
    public void desenharPrimitivos(Graphics g){
        if (tipo == TipoPrimitivo.PONTO){
            FiguraPontos.desenharPonto(g, x, y, "", getEsp(), getCorAtual());
        }

        if (tipo == TipoPrimitivo.RETA){
            FiguraRetas.desenharReta(g, x1, y1, x2, y2, "", getEsp(), getCorAtual());
        }

        if (tipo==TipoPrimitivo.RETANGULO){
            FiguraRetangulos.desenharRetangulo(g, x1, y1, x2, y2, "", getEsp(), getCorAtual());
        }

        if (tipo==TipoPrimitivo.TRIANGULO){
            FiguraTriangulos.desenharTriangulo(g, x1, y1, x2, y2, x3, y3, "", getEsp(), getCorAtual());
        }

        if (tipo==TipoPrimitivo.CIRCULO){
            FiguraCirculos.desenharCirculo(g, x1, y1, x2, y2, "", getEsp(), getCorAtual());
        }

        if (tipo==TipoPrimitivo.FORMA){
            FiguraForma.desenharForma(g, x1, y1, x2, y2, "", getEsp(), getCorAtual(), getCorAnterior());
        }
    }

    /**
     * Refaz a ultima coisa, apenas uma vez, que o usuario realizou
     * 
     */
    public void ctrlz(){
        //ctrlz para apenas um comando
        if(ctrlz != null && SinalCtrlz == false){
            //verifica qual o tipo de cada objeto realizado pela ultima vez, removendo da estrutura de dados e redesenhando a tela
            if(ctrlz.getClass() == PontoGr.class){
                FiguraPontos.Pontos.remove(FiguraPontos.Pontos.size()-1);
                alterar.limpar();
                alterar.redesenhar();
            }else if(ctrlz.getClass() == RetaGr.class){
                FiguraRetas.Retas.remove(FiguraRetas.Retas.size()-1);
                alterar.limpar();
                alterar.redesenhar();
            }else if(ctrlz.getClass() == TrianguloGr.class){
                FiguraTriangulos.Triangulos.remove(FiguraTriangulos.Triangulos.size()-1);
                alterar.limpar();
                alterar.redesenhar();
            }else if(ctrlz.getClass() == CirculoGr.class){
                FiguraCirculos.Circulo.remove(FiguraCirculos.Circulo.size()-1);
                alterar.limpar();
                alterar.redesenhar();
            }else if(ctrlz.getClass() == RetanguloGr.class){
                FiguraRetangulos.Retangulos.remove(FiguraRetangulos.Retangulos.size()-1);
                alterar.limpar();
                alterar.redesenhar();
            }else if(ctrlz.getClass() == FormaGr.class){
                FiguraForma.Forma.remove(FiguraForma.Forma.size()-1);
                alterar.limpar();
                alterar.redesenhar();
            }
        } else if(SinalCtrlz == true && ctrlz != null) {
            //verifica qual foi o ultimo elemento apagado, para assim desenhar novamente o primitivo, ja o adicionando na estrutura de dados
            if(ctrlz.getClass() == PontoGr.class){
                PontoGr pg = (PontoGr) ctrlz;
                double x = pg.getX();
                double y = pg.getY();
                int esp = pg.getDiametro();
                Color cor = pg.getCorPto();

                FiguraPontos.salvaPrimitivo((int)x,(int)y,"", esp, cor);  
                alterar.limpar();
                alterar.redesenhar();
            }else if(ctrlz.getClass() == RetaGr.class){
                RetaGr rg = (RetaGr) ctrlz;
                double x1 = rg.getP1().getX();
                double y1 = rg.getP1().getY();
                double x2 = rg.getP2().getX();
                double y2 = rg.getP2().getY();
                int esp = rg.getEspReta();
                Color cor = rg.getCorReta();

                FiguraRetas.salvaPrimitivo((int)x1, (int)y1, (int)x2, (int)y2, "", esp, cor);  
                alterar.limpar();
                alterar.redesenhar();
            }else if(ctrlz.getClass() == TrianguloGr.class){
                TrianguloGr t = (TrianguloGr) ctrlz;
                double x1 = t.getP1().getX();
                double y1 = t.getP1().getY();
                double x2 = t.getP2().getX();
                double y2 = t.getP2().getY();
                double x3 = t.getP3().getX();
                double y3 = t.getP3().getY();
                int esp = t.getEspTriangulo();
                Color cor = t.getCorTriangulo();
                
                if(transfCtrlz == true){
                    FiguraTriangulos.Triangulos.remove(FiguraTriangulos.Triangulos.size()-1);
                    alterar.limpar();
                    alterar.redesenhar();
                }

                FiguraTriangulos.salvaPrimitivo((int)x1, (int)y1, (int)x2, (int)y2, (int)x3, (int)y3, "", esp, cor);  
                alterar.limpar();
                alterar.redesenhar();
            }else if(ctrlz.getClass() == CirculoGr.class){
                CirculoGr c = (CirculoGr) ctrlz;
                double x1 = c.getP1().getX();
                double y1 = c.getP1().getY();
                double x2 = c.getP2().getX();
                double y2 = c.getP2().getY();
                int esp = c.getEspCirculo();
                Color cor = c.getCorCirculo();

                FiguraCirculos.salvaPrimitivo((int)x1, (int)y1, (int)x2, (int)y2, "", esp, cor);  
                alterar.limpar();
                alterar.redesenhar();
            }else if(ctrlz.getClass() == RetanguloGr.class){
                RetanguloGr rg = (RetanguloGr) ctrlz;
                double x1 = rg.getP1().getX();
                double y1 = rg.getP1().getY();
                double x2 = rg.getP2().getX();
                double y2 = rg.getP2().getY();
                int esp = rg.getEspRetangulo();
                Color cor = rg.getCorRetangulo();

                FiguraRetangulos.salvaPrimitivo((int)x1, (int)y1, (int)x2, (int)y2, "", esp, cor);  
                alterar.limpar();
                alterar.redesenhar();
            }else if(ctrlz.getClass() == FormaGr.class){
                FormaGr f = (FormaGr) ctrlz;
                double x1 = f.getP1().getX();
                double y1 = f.getP1().getY();
                double x2 = f.getP2().getX();
                double y2 = f.getP2().getY();
                int esp = f.getEspForma();
                Color cor1 = f.getCor1Forma();
                Color cor2 = f.getCor2Forma();

                FiguraForma.salvaPrimitivo((int)x1, (int)y1, (int)x2, (int)y2, "", esp, cor1, cor2);  
                alterar.limpar();
                alterar.redesenhar();
            }
        }else{
            JOptionPane.showMessageDialog(null, "Nao eh possivel dar ctrlZ!!!");
        }

        ctrlz = null;
    }
    
    /**
     * Método salvarPainel
     *
     */
    public void salvarPainel(){
        String nomeArquivo = ent.nomeArquivo();
        if(nomeArquivo != null && !nomeArquivo.equals("")) {
            persistencia.JsonWriter(nomeArquivo);
        }
    }
    
    /**
     * Método recuperarPainel
     *
     */
    public void recuperarPainel(){
        g = getGraphics();
        alterar.setG(g);
        
        String nomeArquivo = ent.nomeArquivo();
        if(nomeArquivo != null && !nomeArquivo.equals("")){
            persistencia.JsonReader(nomeArquivo);
            alterar.redesenhar();
        }
    }
    
    /**
     * Limpa tudo da estrutura de dados de cada primitivo
     */
    public void limparTudo(){
        FiguraPontos.deletarTudo();
        FiguraRetas.deletarTudo();
        FiguraCirculos.deletarTudo();
        FiguraRetangulos.deletarTudo();
        FiguraTriangulos.deletarTudo();
        FiguraForma.deletarTudo();
    }
}
