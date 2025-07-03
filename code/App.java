import PPersistencia.Mapeamento;

/**
 * Aplicacao para testar primitivos graficos.
 * 
 * @author João Pedro Figols, Julia Schmidt, Leonardo Grupioni 
 * @co-author Julio Arakaki 
 * @version 20231030
 */
public class App {
    public static void main(String args[]) {
        // Cria e define dimensao da janela (em pixels)
        new Gui(Mapeamento.l, Mapeamento.h); 
    }
}