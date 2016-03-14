package excepciones;

/**
 * Created by andrea on 14/03/16.
 */
public class ValidarExistenciaDetalle extends Exception {
    private static final long serialVersionUID = 6298583340800472359L;

    public ValidarExistenciaDetalle(){}

    public ValidarExistenciaDetalle(String message) {
        super(message);
    }
}
