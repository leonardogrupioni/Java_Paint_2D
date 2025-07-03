package ponto;
 
/**
 * Representacao de ponto matematico
 * 
 * @author Julio Arakaki
 * @version 20220815
 */
public class Ponto {
    private double x;
    private double y;
    /**
     * Constroi um ponto em 0,0
     */
    public Ponto() {
        setX(0);
        setY(0);
    }

    /**
     * Constroi um ponto igual ao ponto p (parametro)
     *
     * @param p ponto externo
     */
    public Ponto(Ponto p) {
        setX(p.getX());
        setY(p.getY());
    }
    /**
     * Constroi um ponto com as coordenadas x e y (parametros)
     * @param x coordenada x do ponto 
     * @param y coordenada y do ponto
     */
    public Ponto(double x, double y) {
        setX(x);
        setY(y);
    }
    
    
    /**
     * Retorna a coordenada x
     * @return coordenada x
     */
    public double getX() {
        return x;
    }
    /**
     * Altera a coordenada x de acordo com o valor do parametro x
     * @param x coordenada x externo
     */
    public void setX(double x) {
        this.x = x;
    }
    /**
     * Retorna a coordenada y
     * 
     * @return coordenada y
     */
    public double getY() {
        return y;
    }
    /**
     * Altera a coordenada y de acordo com o valor do parametro y
     * @param y coordenada y externo 
     */
    public void setY(double y) {
        this.y = y;
    }
    
    /**
     * Calcula a distancia entre o ponto que vem como parametro
     * 
     * @param p ponto externo
     * 
     * @return d double valor da distancia
     * 
     */
    public double calcularDistancia(Ponto p) {
        
        double d = Math.sqrt(Math.pow(p.getY()-getY(), 2) + Math.pow(p.getX()-getX(), 2));
        
        return(d);
    
    }

    
    /**
     * Método sobrecarregado que imprime um ponto no formato [x, y]
     *
     * @return string representando o ponto
     */
    @Override
    public String toString() {
        return "Ponto [" + getX() + ", " + getY() +  "]";
    }
}
