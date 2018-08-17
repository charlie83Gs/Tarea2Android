package cr.ac.tec.tarea2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import cr.ac.tec.tarea2.Dominio.Usuario;

public class CrearUsuario extends AppCompatActivity {

    EditText nombre;
    EditText alias;
    EditText correo;
    EditText password;
    EditText verification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_usuario);
        nombre = (EditText)findViewById(R.id.edt_CrearUsuarioNombre);
        alias = (EditText)findViewById(R.id.edt_CrearUsuarioAlias);
        correo = (EditText)findViewById(R.id.edt_CrearUsuarioCorreo);
        password = (EditText)findViewById(R.id.edt_CrearUsuarioPassword);
        verification = (EditText)findViewById(R.id.edt_CrearUsuarioPasswordVerify);
    }

    public void crearUsuario(View view){


        String pass = password.getText().toString();
        String passVerification = verification.getText().toString();
        String nomb = nombre.getText().toString();
        String alia = alias.getText().toString();
        String corr = correo.getText().toString();
        if(pass.equals(passVerification)){
            Usuario nuevoUsuario = new Usuario(nomb,alia,corr,pass,0);
            //guardar usuario en almacenamiento
            finish();
        }else{
            Toast.makeText(getApplicationContext(), "Ambas contraseñas no coinciden, por favor intentalo de nuevo",Toast.LENGTH_LONG).show();

        }

    }
}
