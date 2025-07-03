import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JToolBar;
import javax.swing.JOptionPane;

import ponto.*;
import reta.FiguraRetas;
import retangulo.FiguraRetangulos;
import triangulo.FiguraTriangulos;
import circulo.FiguraCirculos;
import formas.FiguraForma;

@SuppressWarnings("serial")
/**
 * Cria a interface com o usuario (GUI)
 * 
 * @author João Pedro Figols, Julia Schmidt, Leonardo Grupioni 
 * @co-author Julio Arakaki 
 * @version 20231030
 */
class Gui extends JFrame {

    int aux = 0;
    int aux1 = 0;

    // Tipo Atual de primitivo
    private TipoPrimitivo tipoAtual = TipoPrimitivo.NENHUM;

    // Cor atual
    private Color corAtual = Color.BLACK;
    private Color corAnterior = Color.BLACK;

    // Espessura atual do primitivo
    private int espAtual = 1;

    // Componentes de GUI
    // barra de menu (inserir componente)
    private JToolBar barraComandos = new JToolBar();

    // mensagens
    private JLabel msg = new JLabel("Msg: ");

    // Painel de desenho
    private PainelDesenho areaDesenho = new PainelDesenho(msg, tipoAtual, corAtual, 10);

    // Botoes
    private JButton jbPonto = new JButton("Ponto");
    private JButton jbReta = new JButton("Reta");
    private JButton jbRetangulo = new JButton("Retangulo");
    private JButton jbTriangulo = new JButton("Triangulo");
    private JButton jbCirculo = new JButton("Circulo");
    private JButton jbForma = new JButton("Mandala");
    private JButton jbRedesenhar = new JButton("Redesenhar");
    private JButton jbCtrlz = new JButton("Ctrlz");
    private JButton jbApagarE = new JButton("ApagarE");
    private JButton jbTriangTrans = new JButton("TriangTrans");
    private JButton jbSalva = new JButton("Salvar");
    private JButton jbRecupera = new JButton("Recuperar");
    private JButton jbLimpar = new JButton("Limpar");
    private JButton jbCor = new JButton("Cor");
    private JButton jbSair = new JButton("Sair");

    // Entrada (slider) para definir espessura dos primitivos
    private JLabel jlEsp = new JLabel("   Espessura: " + String.format("%-5s", 1));
    private JSlider jsEsp = new JSlider(1, 50, 1);

    private AlterandoFiguras alterar = new AlterandoFiguras();

    /**
     * Constroi a GUI
     *
     * @param larg largura da janela
     * @param alt altura da janela
     */
    public Gui(int larg, int alt) {
        /**
         * Definicoes de janela
         */
        super("Testa Primitivos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(larg, alt);
        setVisible(true);
        setResizable(false);

        // Adicionando os componentes
        barraComandos.add(jbPonto);
        barraComandos.add(jbReta);
        barraComandos.add(jbRetangulo);
        barraComandos.add(jbTriangulo);
        barraComandos.add(jbCirculo);
        barraComandos.add(jbForma);
        barraComandos.add(jbRedesenhar);
        barraComandos.add(jbCtrlz);
        barraComandos.add(jbApagarE);
        barraComandos.add(jbTriangTrans);
        barraComandos.add(jbSalva);
        barraComandos.add(jbRecupera);
        barraComandos.add(jbLimpar); // Botao de Limpar
        barraComandos.add(jbCor); // Botao de Cores

        barraComandos.add(jlEsp); // Label para espessura
        barraComandos.add(jsEsp);    // Slider para espacamento
        areaDesenho.setEsp(espAtual); // define a espessura inicial
        barraComandos.add(jbSair); // Botao de Cores

        // adiciona os componentes com os respectivos layouts
        add(barraComandos, BorderLayout.NORTH);                
        add(areaDesenho, BorderLayout.CENTER);                
        add(msg, BorderLayout.SOUTH);

        // Adiciona "tratador" ("ouvidor") de eventos para 
        // cada componente
        jbPonto.addActionListener(e -> {
                    tipoAtual = TipoPrimitivo.PONTO;
                    areaDesenho.setTipo(tipoAtual);
            });        
        jbReta.addActionListener(e -> {
                    tipoAtual = TipoPrimitivo.RETA;
                    areaDesenho.setTipo(tipoAtual);
            }); 
        jbRetangulo.addActionListener(e -> {
                    tipoAtual = TipoPrimitivo.RETANGULO;
                    areaDesenho.setTipo(tipoAtual);
            });
        jbTriangulo.addActionListener(e -> {
                    tipoAtual = TipoPrimitivo.TRIANGULO;
                    areaDesenho.setTipo(tipoAtual);
            });
        jbCirculo.addActionListener(e -> {
                    tipoAtual = TipoPrimitivo.CIRCULO;
                    areaDesenho.setTipo(tipoAtual);
            });
        jbForma.addActionListener(e -> {
                    tipoAtual = TipoPrimitivo.FORMA;
                    areaDesenho.setTipo(tipoAtual);
            }); 

        jbRedesenhar.addActionListener(e -> {
                    alterar.limpar();
                    alterar.redesenhar();
            }); 

        jbApagarE.addActionListener(e -> {
                    tipoAtual = TipoPrimitivo.NENHUM;
                    areaDesenho.sent = 0;
                    areaDesenho.setTipo(tipoAtual);
            }); 

        jbTriangTrans.addActionListener(e -> {
                    JOptionPane.showMessageDialog(null, "Selecione primeiro o triangulo e depois o ponto qualquer");
                    tipoAtual = TipoPrimitivo.NENHUM;
                    areaDesenho.sent = 1;
                    areaDesenho.setTipo(tipoAtual);
            }); 
            
            jbSalva.addActionListener(e -> {
                  areaDesenho.salvarPainel();  
            }); 
            
            jbRecupera.addActionListener(e -> {
                  areaDesenho.recuperarPainel();  
            }); 

        jbCtrlz.addActionListener(e -> {
                    areaDesenho.ctrlz();
            }); 

        jbLimpar.addActionListener(e -> {
                    areaDesenho.removeAll();
                    jsEsp.setValue(1); // inicia slider (necessario para limpar ultimo primitivo da tela) 
                    repaint();  

                    areaDesenho.limparTudo();
            });

        jbCor.addActionListener(e -> {
                    Color c = JColorChooser.showDialog(null, "Escolha uma cor", msg.getForeground()); 
                    if (c != null){ 
                        if(aux == 0 && tipoAtual == TipoPrimitivo.FORMA){
                            corAnterior = c;
                            areaDesenho.setCorAnterior(corAnterior);
                            aux = 1;
                        }else{
                            corAtual = c; // pega do chooserColor 
                            areaDesenho.setCorAtual(corAtual); // cor atual
                            aux = 0;
                        }
                    }
            }); 

        jsEsp.addChangeListener(e -> {
                    espAtual = jsEsp.getValue();
                    jlEsp.setText("   Espessura: " + String.format("%-5s", espAtual));
                    areaDesenho.setEsp(espAtual);        
            });        

        jbSair.addActionListener(e -> {
                    System.exit(0);
            });  

    }
}
