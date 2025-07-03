import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.awt.Graphics;
import java.awt.Color;

import ponto.*;
import reta.*;
import retangulo.*;
import triangulo.*;
import circulo.*;
import formas.*;

/**
 * Escreva uma descrição da classe AlterandoFiguras aqui.
 * 
 * @author João Pedro Figols, Julia Schmidt, Leonardo Grupioni 
 * @co-author Julio Arakaki 
 * @version 20231030
 */
public class AlterandoFiguras
{
    Graphics g;

    Color corFundoJava = new Color(238, 238, 238);
    
    EntradaDeDados ent = new EntradaDeDados();
    
    //setters
    public void setG(Graphics g){
        this.g = g;
    }
    
    /**
     * Redesenha os primitivos necessarios
     */
    public void redesenhar(){
        for(int i = 0; i < FiguraPontos.Pontos.size(); i++){
            FiguraPontos.redesenharPonto(g, (PontoGr)FiguraPontos.Pontos.get(i));
        }

        for(int i = 0; i < FiguraRetas.Retas.size(); i++){
            FiguraRetas.redesenharReta(g, (RetaGr)FiguraRetas.Retas.get(i));
        }

        for(int i = 0; i < FiguraCirculos.Circulo.size(); i++){
            FiguraCirculos.redesenharCirculo(g, (CirculoGr)FiguraCirculos.Circulo.get(i));
        }

        for(int i = 0; i < FiguraTriangulos.Triangulos.size(); i++){
            FiguraTriangulos.redesenharTriangulo(g, (TrianguloGr)FiguraTriangulos.Triangulos.get(i));
        }

        for(int i = 0; i < FiguraRetangulos.Retangulos.size(); i++){
            FiguraRetangulos.redesenharRetangulo(g, (RetanguloGr)FiguraRetangulos.Retangulos.get(i));
        }

        for(int i = 0; i < FiguraForma.Forma.size(); i++){
            FiguraForma.redesenharForma(g, (FormaGr)FiguraForma.Forma.get(i));
        }
    }

    /**
     * Apaga elemento selecionado pelo usuario
     *
     */
    public Object apagarElemento(int x, int y, Object ctrlz){
        ArrayList elementos = new ArrayList();
        int op = 1;
        int sent = 0;  //sentinela para verificar apenas um tipo primitivo da forma e armazenar o mesmo elemento, para assim nao verificar novamente
        boolean cond = true;  //variavel para o loop

        while(cond){
            switch(op){
                case 1:
                    for(int i = 0; i < FiguraPontos.Pontos.size(); i++){
                        PontoGr pg = (PontoGr)FiguraPontos.Pontos.get(i);

                        if((pg.getX()-pg.getDiametro()/2 <= x && x <= pg.getX()+pg.getDiametro()/2) && (pg.getY()-pg.getDiametro()/2 <= y && y <= pg.getY()+pg.getDiametro()/2)){
                            elementos.add(pg);
                        }
                    }          
                    if(!condicao(elementos.size()))op = 7;
                    else op = 2;
                    break;

                case 2:
                    for(int i = 0; i < FiguraRetas.Retas.size(); i++){
                        RetaGr rg = (RetaGr)FiguraRetas.Retas.get(i);

                        if(verificarRetas(rg, x, y)){
                            elementos.add(rg);
                        }
                    }

                    if(!condicao(elementos.size()))op = 7; 
                    else op = 3;
                    break;

                case 3:
                    for(int i = 0; i < FiguraCirculos.Circulo.size(); i++){
                        CirculoGr c = (CirculoGr)FiguraCirculos.Circulo.get(i);
                        double xC = c.getP1().getX();
                        double yC = c.getP1().getY();
                        double xF = c.getP2().getX();
                        double yF = c.getP2().getY();
                        double raio = c.calcularRaio(xC,xF,yC,yF);
                        double raio2 = c.calcularRaio(xC,x,yC,y);

                        if(raio-c.getEspCirculo()/2 <= raio2 && raio2 <= raio+c.getEspCirculo()/2) {
                            elementos.add(c);
                        }
                    }
                    if(!condicao(elementos.size())) op = 7;                
                    else op = 4;
                    break;
                case 4:
                    for(int i = 0; i < FiguraRetangulos.Retangulos.size(); i++){
                        RetanguloGr r = (RetanguloGr)FiguraRetangulos.Retangulos.get(i);
                        double x1 = r.getP1().getX();
                        double x2 = r.getP2().getX();
                        double y1 = r.getP1().getY();
                        double y2 = r.getP2().getY();

                        RetaGr vet[] = new RetaGr[4];
                        vet[0] = new RetaGr((int)x1, (int)y1, (int)x1, (int)y2, r.getCorRetangulo(), "", r.getEspRetangulo()); //encontrar y
                        vet[1] = new RetaGr((int)x1, (int)y2, (int)x2, (int)y2, r.getCorRetangulo(), "", r.getEspRetangulo()); //encontrar x
                        vet[2] = new RetaGr((int)x2, (int)y2, (int)x2, (int)y1, r.getCorRetangulo(), "", r.getEspRetangulo()); //encontrar y
                        vet[3] = new RetaGr((int)x2, (int)y1, (int)x1, (int)y1, r.getCorRetangulo(), "", r.getEspRetangulo()); //encontrar x

                        for(int j = 0; j < 4; j++){
                            RetaGr rg = vet[j];

                            if(verificarRetas(rg, x, y)){
                                elementos.add(r);
                                break;
                            }
                        }      
                    }

                    if(!condicao(elementos.size())) op = 7;                
                    else op = 5;
                    break;
                case 5:
                    for(int i = 0; i < FiguraTriangulos.Triangulos.size(); i++){
                        TrianguloGr t = (TrianguloGr)FiguraTriangulos.Triangulos.get(i);
                        double x1 = t.getP1().getX();
                        double x2 = t.getP2().getX();
                        double x3 = t.getP3().getX();
                        double y1 = t.getP1().getY();
                        double y2 = t.getP2().getY();
                        double y3 = t.getP3().getY();

                        RetaGr vet[] = new RetaGr[3];
                        vet[0] = new RetaGr((int)x1, (int)y1, (int)x2, (int)y2, t.getCorTriangulo(), "", t.getEspTriangulo());
                        vet[1] = new RetaGr((int)x2, (int)y2, (int)x3, (int)y3, t.getCorTriangulo(), "", t.getEspTriangulo());
                        vet[2] = new RetaGr((int)x1, (int)y1, (int)x3, (int)y3, t.getCorTriangulo(), "", t.getEspTriangulo());

                        for(int j = 0; j < 3; j++){
                            RetaGr rg = vet[j];

                            if(verificarRetas(rg, x, y)){
                                elementos.add(t);
                                break;
                            }
                        }      
                    }

                    if(!condicao(elementos.size())) op = 7;                
                    else op = 6;

                    break;
                case 6:
                    for(int i = 0; i < FiguraForma.Forma.size(); i++){
                        FormaGr forma = (FormaGr)FiguraForma.Forma.get(i);

                        for(int j = 0; j < forma.retaForma.size(); j++){
                            RetaGr rg = (RetaGr)forma.retaForma.get(j);

                            if(verificarRetas(rg, x, y)){
                                sent = 1;
                                break;
                            }
                        }

                        if(sent != 1){
                            for(int j = 0; j < forma.circuloForma.size(); j++){
                                CirculoGr c = (CirculoGr)forma.circuloForma.get(j);
                                double xC = c.getP1().getX();
                                double yC = c.getP1().getY();
                                double xF = c.getP2().getX();
                                double yF = c.getP2().getY();
                                double raio = c.calcularRaio(xC,xF,yC,yF);
                                double raio2 = c.calcularRaio(xC,x,yC,y);

                                if(raio-c.getEspCirculo()/2 <= raio2 && raio2 <= raio+c.getEspCirculo()/2) {
                                    sent = 1;
                                    break;
                                }
                            }
                        }

                        if(sent == 1) elementos.add(forma);
                    }

                    if(!condicao(elementos.size())) op = 7;                
                    else op = 8;
                    break;
                case 7:
                    JOptionPane.showMessageDialog(null, "Foi selecionado mais de um elemento!!! Selecione apenas um!!!");
                    cond = false;
                    break;
                case 8:
                    ctrlz = removerFigura(elementos, ctrlz);
                    cond = false;
                    break;
            }
        }

        return ctrlz;
    }

    /**
     * verifica se o ponto selecionado pelo usuario esta na espessura do primitivo
     *
     * @param rg reta a ser verificada
     * @return true ou false, significando que achou ou nao o elemento
     */
    protected boolean verificarRetas(RetaGr rg, int x, int y){
        boolean achou = false;

        double x1 = rg.getP1().getX();
        double x2 = rg.getP2().getX();
        double y1 = rg.getP1().getY();
        double y2 = rg.getP2().getY();
        int esp = rg.getEspReta();
        double auxY = 0;
        double auxX = 0;

        //pre-testes
        if(y1 > y2){
            auxY = y1;
            y1 = y2;
            y2 = auxY;
        }

        if(x1 > x2){
            auxX = x1;
            x1 = x2;
            x2 = auxX;
        }

        if(x1 == x2 && x1-esp/2 <= x && x <= x2+esp/2){
            if(y1-esp/2 <= y && y <= y2+esp/2){
                achou = true;
            }
        }else if(y1 == y2 && y1-esp/2 <= y && y <= y2+esp/2){
            if(x1-esp/2 <= x && x <= x2+esp/2){
                achou = true;
            }
        }else if(Math.abs(x2-x1) > Math.abs(y2-y1)){
            double a = rg.calcularM();
            double b = rg.calcularB();
            double yaux = a*x + b;
            if((int)yaux-esp/2 <= y && y <=(int)yaux+esp/2) {
                achou = true;
            }
        }else{
            double a = rg.calcularM();
            double b = rg.calcularB();
            double xaux = (y - b)/a;
            if((int)xaux-esp/2 <= x && x <=(int)xaux+esp/2) {
                achou = true;
            }
        }
        return achou;
    }

    /**
     * Método removerFigura
     *
     * @ArrayList elementos
     * @Object ctrlz 
     * @return ctrlz
     */
    public Object removerFigura(ArrayList elementos, Object ctrlz){
        if(elementos.size() == 1){
            if(elementos.get(0).getClass() == PontoGr.class){
                FiguraPontos.Pontos.remove(elementos.get(0));
                limpar();
                redesenhar();
            }else if(elementos.get(0).getClass() == RetaGr.class){
                FiguraRetas.Retas.remove(elementos.get(0));
                limpar();
                redesenhar();
            }else if(elementos.get(0).getClass() == TrianguloGr.class){
                FiguraTriangulos.Triangulos.remove(elementos.get(0));
                limpar();
                redesenhar();
            }else if(elementos.get(0).getClass() == CirculoGr.class){
                FiguraCirculos.Circulo.remove(elementos.get(0));
                limpar();
                redesenhar();
            }else if(elementos.get(0).getClass() == RetanguloGr.class){
                FiguraRetangulos.Retangulos.remove(elementos.get(0));
                limpar();
                redesenhar();
            }else if(elementos.get(0).getClass() == FormaGr.class){
                FiguraForma.Forma.remove(elementos.get(0));
                limpar();
                redesenhar();            
            }
            ctrlz = elementos.get(0);

        }else JOptionPane.showMessageDialog(null, "Voce nao clicou em nenhum ponto!!!");

        return ctrlz;
    }

    /**
     * Método transformacaoTriangulo
     *
     * @int x
     * @int y
     * @int xq
     * @int yq
     * @return ctrlz
     */
    public Object transformacaoTriangulo(int x, int y, int xq, int yq){
        Object ctrlz = new Object();
        ctrlz = null;
        
        ArrayList elementos = new ArrayList();

        int op = 0;
        int sent = 1;
        int sentinela = 0;
        int esp = 0;
        Color cor = Color.BLACK;

        double sX = 0;
        double sY = 0;
        double ang = 0;
        double x1 = 0;
        double x2 = 0;
        double x3 = 0; 
        double y1 = 0;
        double y2 = 0; 
        double y3 = 0;
        double x1_linha = 0;
        double y1_linha = 0;
        double x2_linha = 0;
        double y2_linha = 0; 
        double x3_linha = 0; 
        double y3_linha = 0;
        
        TrianguloGr t;

        String aux = null;

        //verifica se o primeiro ponto selecionado pertence ao triangulo
        for(int i = 0; i < FiguraTriangulos.Triangulos.size(); i++){
            t = (TrianguloGr)FiguraTriangulos.Triangulos.get(i);
            x1 = t.getP1().getX();
            x2 = t.getP2().getX();
            x3 = t.getP3().getX();
            y1 = t.getP1().getY();
            y2 = t.getP2().getY();
            y3 = t.getP3().getY();
            esp = t.getEspTriangulo();
            cor = t.getCorTriangulo();   

            RetaGr vet[] = new RetaGr[3];
            vet[0] = new RetaGr((int)x1, (int)y1, (int)x2, (int)y2, cor, "", esp);
            vet[1] = new RetaGr((int)x2, (int)y2, (int)x3, (int)y3, cor, "", esp);
            vet[2] = new RetaGr((int)x1, (int)y1, (int)x3, (int)y3, cor, "", esp);

            for(int j = 0; j < 3; j++){
                RetaGr rg = vet[j];

                if(verificarRetas(rg, x, y)){
                    elementos.add(t);
                    break;
                }
            }    
            
        }
        
        
        if(elementos.size() > 1) JOptionPane.showMessageDialog(null, "Foi selecionado mais de um elemento!!! Selecione apenas um!!!");
        else if(elementos.size() == 0) JOptionPane.showMessageDialog(null, "Esse ponto nao pertence a nenhum triangulo, selecione os dois pontos novamente!!");            
        else{
            op = ent.menuTrans();
            ctrlz = (TrianguloGr)elementos.get(0);
            if(op != 0){
                t = (TrianguloGr)elementos.get(0);
                x1 = t.getP1().getX();
                x2 = t.getP2().getX();
                x3 = t.getP3().getX();
                y1 = t.getP1().getY();
                y2 = t.getP2().getY();
                y3 = t.getP3().getY();
                esp = t.getEspTriangulo();
                cor = t.getCorTriangulo(); 
                
                switch(op){
                    case 1:
                        //Escala
                        sX = ent.escolheSx();
                        sY = ent.escolheSy();
    
                        x1_linha = sX*x1 + xq*(1 - sX);
                        y1_linha = sY*y1 + yq*(1 - sY);
    
                        x2_linha = sX*x2 + xq*(1 - sX);
                        y2_linha = sY*y2 + yq*(1 - sY);
    
                        x3_linha = sX*x3 + xq*(1 - sX);
                        y3_linha = sY*y3 + yq*(1 - sY);
                        break;
                    case 2:
                        //Rotacao
                        ang = ent.escolheAngulo();
                        ang = Math.toRadians(ang);
                        x1_linha = x1 * Math.cos(ang) - Math.sin(ang) * y1 + xq * (1 - Math.cos(ang)) + yq * Math.sin(ang);
                        y1_linha = x1 * Math.sin(ang) + y1 * Math.cos(ang) + yq * (1 - Math.cos(ang)) - xq * Math.sin(ang);
    
                        x2_linha = x2 * Math.cos(ang) - Math.sin(ang) * y2 + xq * (1 - Math.cos(ang)) + yq * Math.sin(ang);
                        y2_linha = x2 * Math.sin(ang) + y2 * Math.cos(ang) + yq * (1 - Math.cos(ang)) - xq * Math.sin(ang);
    
                        x3_linha = x3 * Math.cos(ang) - Math.sin(ang) * y3 + xq * (1 - Math.cos(ang)) + yq * Math.sin(ang);
                        y3_linha = x3 * Math.sin(ang) + y3 * Math.cos(ang) + yq * (1 - Math.cos(ang)) - xq * Math.sin(ang);
                        break;
                }
                
                FiguraTriangulos.Triangulos.remove(elementos.get(0));
                limpar();
                redesenhar();
    
                FiguraTriangulos.salvaPrimitivo((int)x1_linha, (int)y1_linha, (int)x2_linha, (int)y2_linha, (int)x3_linha, (int)y3_linha, "", esp, cor);  
                limpar();
                redesenhar();
            }
        }
        
        return ctrlz;
    }

    /**
     * Verifica o tamanho do array de elementos
     *
     * @int tam tamanho do array elementos
     * @return true ou false, significando se apresenta mais de um elemento naquele ponto selecionando
     */
    public boolean condicao(int tam){
        boolean cond =  true;
        if(tam > 1) cond = false;

        return cond;
    }

    /**
     * Limpa a tela, desenhando um ponto com as mesmas dimensoes da tela
     */
    public void limpar(){
        if(g != null){
            FiguraPontos.desenharPonto(g, 0, 0, "", 10000, corFundoJava);
        }else{
            JOptionPane.showMessageDialog(null, "Nao eh possivel redesenhar!!!");
        }   
    }

}
