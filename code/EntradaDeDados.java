import javax.swing.JOptionPane;

/**
 * EntradaDeDados é a classe com os metódos para entrada de dados via JOptionPane.
 * 
 * @author João Pedro Figols, Julia Schmidt, Leonardo Grupioni 
 * @version 20231030
 */
public class EntradaDeDados
{
    /**
     * Método menuTrans, menu das transformacoes geometricas do triangulo
     *
     * @return op - opcao selecionada
     */
    public int menuTrans(){
        //ver oq ele quer fazer: escala ou rotação
        int sentinela = 0;
        String aux = null;
        int op = 0;

        do{
            sentinela = 0;

            //a variavel aux recebe uma entrada de dados
            aux = JOptionPane.showInputDialog(
                "\n=======Transformacao=======\n" +
                "   1. Escala\n"+
                "   2. Rotacao\n" +
                "Escolha uma das opções anteriores: ");

            if(aux != null){
                if(aux.equals("1") || aux.equals("2")){
                    op = Integer.parseInt(aux); //op recebe aux transformado de String para int
                }else{
                    JOptionPane.showMessageDialog(null, "Selecione uma dessas duas opcoes!!");
                    sentinela = 1;
                }    
            } else{
                JOptionPane.showMessageDialog(null, "Cancelando...");
            }

        }while(sentinela != 0);

        return op;
    }

    /**
     * Método escolheSx
     *
     * @return sX
     */
    public double escolheSx(){
        int sentinela = 0;
        String aux = null;
        double sX = 0;

        do{
            sentinela = 0;

            //a variavel aux recebe uma entrada de dados
            aux = JOptionPane.showInputDialog("Sx: ");

            if(aux != null){
                sX = Double.parseDouble(aux);
            } else{
                sX = 1;
            }

        }while(sentinela != 0);

        return sX;
    }

    /**
     * Método escolheSy
     *
     * @return sY
     */
    public double escolheSy(){
        int sentinela = 0;
        String aux = null;
        double sY = 0;

        do{
            sentinela = 0;

            //a variavel aux recebe uma entrada de dados
            aux = JOptionPane.showInputDialog("Sy: ");

            if(aux != null){
                sY = Double.parseDouble(aux);
            } else{
                sY = 1;
            }

        }while(sentinela != 0);

        return sY;
    }

    /**
     * Método escolheAngulo
     *
     * @return ang
     */
    public double escolheAngulo(){
        int sentinela = 0;
        String aux = null;
        double ang = 0;

        do{
            sentinela = 0;

            //a variavel aux recebe uma entrada de dados
            aux = JOptionPane.showInputDialog("Angulo: ");

            if(aux != null){
                ang = Double.parseDouble(aux);
            } else{
                ang = 0;
            }

        }while(sentinela != 0);

        return ang;
    }

    /**
     * Método nomeArquivo
     *
     * @return nomeArquivo
     */
    public String nomeArquivo(){
        int sent = 0;
        String nomeArquivo = "";

        do{ 
            try{
                //a variavel aux recebe uma entrada de dados
                nomeArquivo = JOptionPane.showInputDialog("Nome do arquivo: ");

                if((nomeArquivo.split(" ").length) > 1){ //verifica se o nome do arquivo apresenta apenas uma palavra
                    JOptionPane.showMessageDialog(null, "\nNome de arquivo, não deve conter espaços!!!");
                    sent = 1;
                }
                else if(!(verificarNomeArquivo(nomeArquivo))){ //verifica se o nome do arquivo nao apresenta caracteres especiais
                    JOptionPane.showMessageDialog(null,"\nNome de arquivo não pode conter as seguintes caracteres:\n \\ / : ? * \" < > |");
                    sent = 1;
                }
            }catch(NullPointerException e){
                JOptionPane.showMessageDialog(null, "Cancelando...");
            }
        }while(sent != 0);

        return nomeArquivo;
    }

    /**
     * Método verificarNomeArquivo, verifica se o nome digiado apresenta caracteres especiais: " : ? / \ * > < |
     *
     * @param _nomeArquivo String, nome do arquivo digitado 
     * @return true ou false, retorna true se nao haver nenhum caractere especial, se nao retorna false
     */
    private boolean verificarNomeArquivo(String _nomeArquivo){
        boolean bool = true;
        char ch;
        //loop que percorrera todos os caracteres do nome dado ao arquivo
        for(int i = 0; i < _nomeArquivo.length(); i++){
            ch = _nomeArquivo.charAt(i);    //variavel ch recebe cada um dos caracteres para serem verificados
            if(ch == 34 || ch == 47 || ch == 92 || ch == 42 || ch == 124 || ch == 58 || ch == 60 || ch == 62 || ch == 63) { //usando tabela ascii
                bool = false;
                break;
            }
        }

        return bool;
    }
}
