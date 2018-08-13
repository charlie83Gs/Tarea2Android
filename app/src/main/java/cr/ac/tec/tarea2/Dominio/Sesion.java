package cr.ac.tec.tarea2.Dominio;

public class Sesion {
    Usuario usuario = null;
    private static Sesion sesion = null;

    public static Sesion getSesion() {
        if(sesion == null){
            sesion = new Sesion();
        }

        return sesion;
    }

    private Sesion() {

    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }


}
