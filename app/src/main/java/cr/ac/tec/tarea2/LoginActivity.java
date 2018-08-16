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
        Toast.makeText(getApplicationContext(), "Entrando",Toast.LENGTH_SHORT).show();
        Sesion.getSesion(getApplicationContext()).validateUser();
        if(name.equals("admin") && pass.equals("admin")){
            Usuario admin = new Usuario("admin","admin","admin","admin",-1);
            Sesion.getSesion(getApplicationContext()).setUsuario(admin);
            Intent newActivityListaEventos = new Intent(view.getContext(), ListaEventos.class);
            startActivity(newActivityListaEventos);
        }else{
            //Sesion.getSesion().validateUser();
            Toast.makeText(getApplicationContext(), "Usuario y contraseña incorrecta",Toast.LENGTH_SHORT).show();
        }

    }


}
