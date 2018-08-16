package cr.ac.tec.tarea2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class CrearUsuario extends AppCompatActivity {

    EditText nombre;
    EditText alias;
    EditText correo;
    EditText nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_usuario);
    }

    public void crearUsuario(View view){

    }
}
