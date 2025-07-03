package PPersistencia;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.json.JSONException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

import ponto.*;
import reta.*;
import retangulo.*;
import triangulo.*;
import circulo.*;
import formas.*;

import java.awt.Color;

/**
 * Classe Json que le e escreve arquivos json para persistencia.
 * 
 * @author João Pedro Figols, Julia Schmidt, Leonardo Grupioni 
 * @co-author Julio Arakaki 
 * @version 20231030
 */
public class Json extends Mapeamento implements Persistencia 
{
    /**
     * Método JsonWriter escreve em um arquivo json
     */
    public void JsonWriter(String nomeArquivo){
        int sent = 0;
        String aux = null;
        int op = 0;
        
        try{
            // Criar a estrutura JSON conforme o formato desejado
            nomeArquivo = nomeArquivo + ".json";
  
            FileReader reader = new FileReader(nomeArquivo);
            
            do{
                sent = 0;

                //a variavel aux recebe uma entrada de dados
                aux = JOptionPane.showInputDialog(
                    "\nO Arquivo ja existe!! Deseja Salvar por cima?\n" +
                    "   1. Sim\n"+
                    "   2. Nao\n");

                if(aux != null){
                    if(aux.equals("1") || aux.equals("2")){
                        op = Integer.parseInt(aux); //op recebe aux transformado de String para int
                    }else{
                        JOptionPane.showMessageDialog(null, "Selecione uma dessas duas opcoes!!");
                        sent = 1;
                    }    
                } else{
                    JOptionPane.showMessageDialog(null, "Cancelando...");
                }
            }while(sent != 0);
        }catch(IOException e){}
        
        if(op == 0 || op == 1){
            JSONObject desenho = new JSONObject();
            JSONObject root = new JSONObject();
            JSONArray pontoArray = new JSONArray();
            JSONArray retaArray = new JSONArray();
            JSONArray trianguloArray = new JSONArray();
            JSONArray retanguloArray = new JSONArray();
            JSONArray circuloArray = new JSONArray();
            JSONArray mandalaArray = new JSONArray();
    
            JSONObject cor, cor2;
            JSONObject p1,p2,p3;
            
            double coordX, coordY;
            
    
            for(int i = 0; i < FiguraPontos.Pontos.size(); i++){
                PontoGr pg = (PontoGr)FiguraPontos.Pontos.get(i);
    
                JSONObject ponto = new JSONObject();
                ponto.put("id", "ponto_" + (i+1));
                ponto.put("esp", pg.getDiametro());
                
                ponto.put("x", coordNormX(pg.getX()));
                ponto.put("y", coordNormY(pg.getY()));
    
                cor = new JSONObject();
                cor.put("r", pg.getCorPto().getRed());
                cor.put("g", pg.getCorPto().getGreen());
                cor.put("b", pg.getCorPto().getBlue());
    
                ponto.put("cor", cor);
    
                pontoArray.put(ponto);
                desenho.put("ponto", pontoArray);
            }      
    
            for(int i = 0; i < FiguraRetas.Retas.size(); i++){
                RetaGr rg = (RetaGr)FiguraRetas.Retas.get(i);
    
                JSONObject reta = new JSONObject();
                reta.put("id", "reta_" + (i+1));
                reta.put("esp", rg.getEspReta());
    
                p1 = new JSONObject();
                p2 = new JSONObject();
                
                p1.put("x", coordNormX(rg.getP1().getX()));
                p1.put("y", coordNormY(rg.getP1().getY()));
                
                p2.put("x", coordNormX(rg.getP2().getX()));
                p2.put("y", coordNormY(rg.getP2().getY()));
    
                reta.put("p1", p1);
                reta.put("p2", p2);
    
                // Acessar a chave "cor" para obter os valores de R, G e B
                cor = new JSONObject();
                cor.put("r", rg.getCorReta().getRed());
                cor.put("g", rg.getCorReta().getGreen());
                cor.put("b", rg.getCorReta().getBlue());
                reta.put("cor", cor);
    
                // Adicionar a reta no array de retas
                retaArray.put(reta);// Adicione os arrays ao objeto desenho
                desenho.put("reta", retaArray);
            }
    
            for(int i = 0; i < FiguraCirculos.Circulo.size(); i++){
                CirculoGr c = (CirculoGr)FiguraCirculos.Circulo.get(i);
                JSONObject circ = new JSONObject();
                circ.put("id", "circulo_" + (i+1));
                circ.put("esp", c.getEspCirculo());
    
                p1 = new JSONObject();
    
                p1.put("x", coordNormX(c.getP1().getX()));
                p1.put("y", coordNormY(c.getP1().getY()));
                circ.put("centro", p1);
                circ.put("raio", coordNormX((int)c.getRaio()));
    
                // Acessar a chave "cor" para obter os valores de R, G e B
                cor = new JSONObject();
                cor.put("r", c.getCorCirculo().getRed());
                cor.put("g", c.getCorCirculo().getGreen());
                cor.put("b", c.getCorCirculo().getBlue());
                circ.put("cor", cor);
    
                circuloArray.put(circ);// Adicione os arrays ao objeto desenho
                desenho.put("circulo", circuloArray);
            }
    
            for(int i = 0; i < FiguraTriangulos.Triangulos.size(); i++){
                TrianguloGr t = (TrianguloGr)FiguraTriangulos.Triangulos.get(i);
                JSONObject triang = new JSONObject();
                triang.put("id", "triangulo_" + i+1);
                triang.put("esp", t.getEspTriangulo());
    
                // Criar pontos p1, p2 e p3
                p1 = new JSONObject();
                p2 = new JSONObject();
                p3 = new JSONObject();
                
                p1.put("x", coordNormX(t.getP1().getX()));
                p1.put("y", coordNormY(t.getP1().getY()));
                
                p2.put("x", coordNormX(t.getP2().getX()));
                p2.put("y", coordNormY(t.getP2().getY()));
                
                p3.put("x", coordNormX(t.getP3().getX()));
                p3.put("y", coordNormY(t.getP3().getY()));
    
                triang.put("p1", p1);
                triang.put("p2", p2);
                triang.put("p3", p3);
    
                // Acessar a chave "cor" para obter os valores de R, G e B
                cor = new JSONObject();
                cor.put("r", t.getCorTriangulo().getRed());
                cor.put("g", t.getCorTriangulo().getGreen());
                cor.put("b", t.getCorTriangulo().getBlue());
                triang.put("cor", cor);
    
                // Adicionar a triang no array de triangulos
                trianguloArray.put(triang);// Adicione os arrays ao objeto desenho
                desenho.put("triangulo", trianguloArray);
            }
    
            for(int i = 0; i < FiguraRetangulos.Retangulos.size(); i++){
                RetanguloGr rt = (RetanguloGr)FiguraRetangulos.Retangulos.get(i);
                JSONObject retang = new JSONObject();
                retang.put("id", "retangulo_" + (i+1));
                retang.put("esp", rt.getEspRetangulo());
    
                // Criar pontos p1 e p2 das diagonais do retangulo
                p1 = new JSONObject();
                p2 = new JSONObject();
                
                p1.put("x", coordNormX(rt.getP1().getX()));
                p1.put("y", coordNormY(rt.getP1().getY()));
                
                p2.put("x", coordNormX(rt.getP2().getX()));
                p2.put("y", coordNormY(rt.getP2().getY()));
    
                retang.put("p1", p1);
                retang.put("p2", p2);
    
                // Acessar a chave "cor" para obter os valores de R, G e B
                cor = new JSONObject();
                cor.put("r", rt.getCorRetangulo().getRed());
                cor.put("g", rt.getCorRetangulo().getGreen());
                cor.put("b", rt.getCorRetangulo().getBlue());
                retang.put("cor", cor);
    
                // Adicionar a retang no array de retas
                retanguloArray.put(retang);// Adicione os arrays ao objeto desenho
                desenho.put("retangulo", retanguloArray);
            }
    
            for(int i = 0; i < FiguraForma.Forma.size(); i++){
                FormaGr f = (FormaGr)FiguraForma.Forma.get(i);
                JSONObject mand = new JSONObject();
                mand.put("id", "mandala_" + (i+1));
                mand.put("esp", f.getEspForma());
    
                // Criar pontos p1 e p2 das diagonais do retangulo
                p1 = new JSONObject();
                p2 = new JSONObject();
                
                p1.put("x", coordNormX(f.getP1().getX()));
                p1.put("y", coordNormY(f.getP1().getY()));
                
                p2.put("x", coordNormX(f.getP2().getX()));
                p2.put("y", coordNormY(f.getP2().getY()));
            
                mand.put("p1", p1);
                mand.put("p2", p2);
    
                // Criar cor e cor2 com os valores de R, G e B
                cor = new JSONObject();
                cor.put("r", f.getCor1Forma().getRed());
                cor.put("g", f.getCor1Forma().getGreen());
                cor.put("b", f.getCor1Forma().getBlue());
                mand.put("cor1", cor);
    
                cor2 = new JSONObject();
                cor2.put("r", f.getCor2Forma().getRed());
                cor2.put("g", f.getCor2Forma().getGreen());
                cor2.put("b", f.getCor2Forma().getBlue());
                mand.put("cor2", cor2);
    
                // Adicionar a mand no array de retas
                mandalaArray.put(mand);// Adicione os arrays ao objeto desenho
                desenho.put("mandala", mandalaArray);
            }
    
            root.put("desenho", desenho);
    
            try (FileWriter file = new FileWriter(nomeArquivo)) {
                file.write(root.toString(4)); // O argumento 4 indica a quantidade de espaços de recuo para a formatação
                JOptionPane.showMessageDialog(null, "Arquivo JSON criado com sucesso.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Método JsonReader le um arquivo json
     */
    public void JsonReader(String nomeArquivo) {
        try {
            // Ler o arquivo JSON

            nomeArquivo = nomeArquivo + ".json";

            FileReader reader = new FileReader(nomeArquivo);

            // Criar um objeto JSON a partir do arquivo
            JSONObject jsonObject = new JSONObject(new JSONTokener(reader));

            // Acessar a chave "desenho"
            JSONObject desenho = jsonObject.getJSONObject("desenho");

            // Para acessar as chaves dentro de "desenho" 
            if(!desenho.isNull("ponto")){
                JSONArray pontoArray = desenho.getJSONArray("ponto");

                // Processar os PONTOS
                for (int i = 0; i < pontoArray.length(); i++) {
                    JSONObject ponto = pontoArray.getJSONObject(i);
                    String id = ponto.getString("id");
                    double esp = ponto.getDouble("esp");
                    double x = coordDispX(ponto.getDouble("x"));
                    double y = coordDispY(ponto.getDouble("y"));
                    // Acessar a chave "cor" para obter os valores de R, G e B
                    JSONObject cor = ponto.getJSONObject("cor");
                    int r = cor.getInt("r");
                    int g = cor.getInt("g");
                    int b = cor.getInt("b");

                    FiguraPontos.salvaPrimitivo((int)x, (int)y, "", (int)esp, new Color(r, g, b));
                }
            }

            if(!desenho.isNull("reta")){
                JSONArray retaArray = desenho.getJSONArray("reta");

                // Processar as RETAS
                for (int i = 0; i < retaArray.length(); i++) {
                    JSONObject reta = retaArray.getJSONObject(i);
                    String id = reta.getString("id");
                    double esp = reta.getDouble("esp");

                    // Acessar os pontos p1 e p2
                    JSONObject p1 = reta.getJSONObject("p1");
                    JSONObject p2 = reta.getJSONObject("p2");

                    double p1x = coordDispX(p1.getDouble("x"));
                    double p1y = coordDispY(p1.getDouble("y"));
                    double p2x = coordDispX(p2.getDouble("x"));
                    double p2y = coordDispY(p2.getDouble("y"));

                    // Acessar a chave "cor" para obter os valores de R, G e B
                    JSONObject cor = reta.getJSONObject("cor");
                    int r = cor.getInt("r");
                    int g = cor.getInt("g");
                    int b = cor.getInt("b");

                    FiguraRetas.salvaPrimitivo((int)p1x, (int)p1y, (int)p2x, (int)p2y, "", (int)esp, new Color(r, g, b));
                }
            }

            if(!desenho.isNull("circulo")){
                JSONArray circuloArray = desenho.getJSONArray("circulo");

                // Processar os CIRCULOS
                for (int i = 0; i < circuloArray.length(); i++) {
                    JSONObject circulo = circuloArray.getJSONObject(i);
                    String id = circulo.getString("id");
                    double esp = circulo.getDouble("esp");

                    // Acessar informacoes do circulo
                    JSONObject centro = circulo.getJSONObject("centro");
                    double centroX = coordDispX(centro.getDouble("x"));
                    double centroY = coordDispY(centro.getDouble("y"));
                    double raio = coordDispX(circulo.getDouble("raio"));
                    double p2x = centroX + raio;
                    double p2y = centroY;
                    
                    // Acessar a chave "cor" para obter os valores de R, G e B
                    JSONObject cor = circulo.getJSONObject("cor");
                    int r = cor.getInt("r");
                    int g = cor.getInt("g");
                    int b = cor.getInt("b");

                    FiguraCirculos.salvaPrimitivo((int)centroX, (int)centroY, (int)p2x, (int)p2y, "", (int)esp, new Color(r, g, b));  
                }
            }

            if(!desenho.isNull("triangulo")){
                JSONArray trianguloArray = desenho.getJSONArray("triangulo");

                // Processar os TRIANGULOS
                for (int i = 0; i < trianguloArray.length(); i++) {
                    JSONObject triangulo = trianguloArray.getJSONObject(i);
                    String id = triangulo.getString("id");
                    double esp = triangulo.getDouble("esp");

                    // Acessar informacoes dos tres pontos do triangulo
                    JSONObject p1 = triangulo.getJSONObject("p1");
                    JSONObject p2 = triangulo.getJSONObject("p2");
                    JSONObject p3 = triangulo.getJSONObject("p3");

                    double p1x = coordDispX(p1.getDouble("x"));
                    double p1y = coordDispY(p1.getDouble("y"));
                    double p2x = coordDispX(p2.getDouble("x"));
                    double p2y = coordDispY(p2.getDouble("y"));
                    double p3x = coordDispX(p3.getDouble("x"));
                    double p3y = coordDispY(p3.getDouble("y"));

                    // Acessar a chave "cor" para obter os valores de R, G e B
                    JSONObject cor = triangulo.getJSONObject("cor");
                    int r = cor.getInt("r");
                    int g = cor.getInt("g");
                    int b = cor.getInt("b");

                    FiguraTriangulos.salvaPrimitivo((int)p1x, (int)p1y, (int)p2x, (int)p2y, (int)p3x, (int)p3y, "", (int)esp, new Color(r, g, b));    

                }
            }

            if(!desenho.isNull("retangulo")){
                JSONArray retanguloArray = desenho.getJSONArray("retangulo");

                // Processar RETANGULOS
                for (int i = 0; i < retanguloArray.length(); i++) {
                    JSONObject retangulo = retanguloArray.getJSONObject(i);
                    String id = retangulo.getString("id");
                    double esp = retangulo.getDouble("esp");

                    // Acessar informacoes dos dois pontos do retangulo
                    JSONObject p1 = retangulo.getJSONObject("p1");
                    JSONObject p2 = retangulo.getJSONObject("p2");

                    double p1x = coordDispX(p1.getDouble("x"));
                    double p1y = coordDispY(p1.getDouble("y"));
                    double p2x = coordDispX(p2.getDouble("x"));
                    double p2y = coordDispY(p2.getDouble("y"));
                   
                    // Acessar a chave "cor" para obter os valores de R, G e B
                    JSONObject cor = retangulo.getJSONObject("cor");
                    int r = cor.getInt("r");
                    int g = cor.getInt("g");
                    int b = cor.getInt("b");

                    FiguraRetangulos.salvaPrimitivo((int)p1x, (int)p1y, (int)p2x, (int)p2y, "", (int)esp, new Color(r, g, b));
                }
            }

            if(!desenho.isNull("mandala")){
                JSONArray mandalaArray = desenho.getJSONArray("mandala");

                // Processar MANDALAS
                for (int i = 0; i < mandalaArray.length(); i++) {
                    JSONObject mandala = mandalaArray.getJSONObject(i);
                    String id = mandala.getString("id");
                    double esp = mandala.getDouble("esp");

                    // Acessar informacoes dos pontos da mandala
                    JSONObject p1 = mandala.getJSONObject("p1");
                    JSONObject p2 = mandala.getJSONObject("p2");

                    double p1x = coordDispX(p1.getDouble("x"));
                    double p1y = coordDispY(p1.getDouble("y"));
                    double p2x = coordDispX(p2.getDouble("x"));
                    double p2y = coordDispY(p2.getDouble("y"));

                    // Acessar informacoes das cores da mandala
                    JSONObject cor1 = mandala.getJSONObject("cor1");
                    JSONObject cor2 = mandala.getJSONObject("cor2");

                    int r1 = cor1.getInt("r");
                    int g1 = cor1.getInt("g");
                    int b1 = cor1.getInt("b");

                    int r2 = cor2.getInt("r");
                    int g2 = cor2.getInt("g");
                    int b2 = cor2.getInt("b");

                    FiguraForma.salvaPrimitivo((int)p1x, (int)p1y, (int)p2x, (int)p2y, "", (int)esp, new Color(r1, g1, b1), new Color(r2, g2, b2));

                }
            }

            // Feche o leitor
            reader.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Este arquivo nao existe!!");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

