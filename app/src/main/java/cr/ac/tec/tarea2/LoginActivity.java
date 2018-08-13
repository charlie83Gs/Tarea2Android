package cr.ac.tec.tarea2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    private EditText username;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        username = (EditText)findViewById(R.id.edt_LoginName);
        password = (EditText)findViewById(R.id.edt_LoginPassword);
    }

    public void login(View view){
        String name = username.getText().toString();
        String pass = password.getText().toString();
    }
}
