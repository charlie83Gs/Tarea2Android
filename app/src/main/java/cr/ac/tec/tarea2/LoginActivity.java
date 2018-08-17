package cr.ac.tec.tarea2;

import android.content.Intent;
import android.media.MediaCas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import cr.ac.tec.tarea2.Dominio.Almacenamiento;
import cr.ac.tec.tarea2.Dominio.Sesion;
import cr.ac.tec.tarea2.Dominio.Usuario;

public class LoginActivity extends AppCompatActivity {
    private EditText username;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        username = (EditText)findViewById(R.id.edt_LoginName);
        password = (EditText)findViewById(R.id.edt_LoginPassword);

        //Sesion.getSesion();

    }

    public void login(View view){
        String name = username.getText().toString();
        String pass = password.getText().toString();
        Sesion.getSesion(getApplicationContext()).validateUser();
        Sesion sesion = Sesion.getSesion(getApplicationContext());
        if(name.equals("admin") && pass.equals("admin")){
            Usuario admin = new Usuario("admin","admin","admin","admin",-1);
            sesion.setUsuario(admin);
            Intent newActivityListaEventos = new Intent(view.getContext(), ListaEventos.class);
            startActivity(newActivityListaEventos);
        }else{
            Usuario usuarioActual = sesion.getUsuarioByAlias(name);
            if(usuarioActual != null && usuarioActual.validatePassword(pass)){
                sesion.setUsuario(usuarioActual);
                Intent newActivityListaEventos = new Intent(view.getContext(), ListaEventos.class);
                startActivity(newActivityListaEventos);
            }else{
            //Sesion.getSesion().validateUser();
            Toast.makeText(getApplicationContext(), "Usuario y contraseña incorrecta",Toast.LENGTH_SHORT).show();
        }
        }

    }

    public void nuevoUsuario(View view){
        Intent newActivityListaEventos = new Intent(view.getContext(), CrearUsuario.class);
        startActivity(newActivityListaEventos);
    }


}
