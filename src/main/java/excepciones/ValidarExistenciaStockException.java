package excepciones;

/**
 * Created by andrea on 14/03/16.
 */
public class ValidarExistenciaStockException extends Exception {


    private static final long serialVersionUID = -7170270736339728855L;

    public ValidarExistenciaStockException(){}

    public ValidarExistenciaStockException(String message) {
        super(message);
    }
}
