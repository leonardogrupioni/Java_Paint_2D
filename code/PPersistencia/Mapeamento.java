package PPersistencia;

/**
 * Mapeamento calcula a coordenada normalizada e do dispositivo usando a largura e altura (l e h) definidas para a janela do programa.
 * 
 * @author João Pedro Figols, Julia Schmidt, Leonardo Grupioni  
 * @version 20231030
 */
public class Mapeamento 
{
    public static int l = 1150;
    public static int h = 700;
    
    /**
     * Método coordNormX calcula o valor normalizado da coordenada x
     */
    public double coordNormX(double x){
        double coord = x / l; 
        return coord;
    }
    
    /**
     * Método coordNormY calcula o valor normalizado da coordenada y
     */
    public double coordNormY(double y){
        double coord = y / h; 
        return coord;
    }
    
    /**
     * Método coordDispX calcula o valor de x para o dispositivo usando a coordenada normalizada x
     */
    public double coordDispX(double x){
        double coord = x * l; 
        return coord;
    }
    
    /**
     * Método coordDispY calcula o valor de y para o dispositivo usando a coordenada normalizada y
     */
    public double coordDispY(double y){
        double coord = y * h; 
        return coord;
    }
}
