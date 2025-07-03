package PPersistencia;
 

/**
 * Interface Persistencia.
 * 
 * @author João Pedro Figols, Julia Schmidt, Leonardo Grupioni 
 * @co-author Julio Arakaki 
 * @version 20231030
 */
public interface Persistencia
{
    public void JsonWriter(String nomeArquivo);
    public void JsonReader(String nomeArquivo);
}
