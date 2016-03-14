package excepciones;

/**
 * Created by andrea on 13/03/16.
 */
public class TamanoPaginaExcepcion extends Exception {

    private static final long serialVersionUID = -9015730250302878789L;

    public TamanoPaginaExcepcion(){}

    public TamanoPaginaExcepcion(String message) {
        super(message);
    }
}
