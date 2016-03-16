package excepciones;

/**
 * Created by andrea on 15/03/16.
 */
public class NoExisteProveedorException extends Exception {
    private static final long serialVersionUID = -1705935014191670762L;

    public NoExisteProveedorException(){}

    public NoExisteProveedorException(String message) {
        super(message);
    }
}
