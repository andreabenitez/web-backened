package excepciones;

/**
 * Created by andrea on 15/03/16.
 */
public class NoExisteProductoException extends Exception {
    private static final long serialVersionUID = 7206937465585311561L;

    public NoExisteProductoException(){}

    public NoExisteProductoException(String message) {
        super(message);
    }
}
