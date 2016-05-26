package trabajofinalcb.javecarlos.pe.trabajofinalcb;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import org.w3c.dom.Text;
import java.io.IOException;

import dao.UsuariosDAO;
import entities.Usuarios;

import dao.DataBaseHelper;
import dao.DataBaseSingleton;

/**
 * Created by carlosarmando on 03/05/2016.
 */
public class LoginActivity extends AppCompatActivity {
    Usuarios usu;
    EditText edt_usuario, edt_password;
    ImageButton btn_ingresar;
    TextView edt_error;

    String usuario, password;
    Context context;
    Dialog dialog;
    Activity activity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edt_usuario=(EditText)findViewById(R.id.edt_usuario);
        edt_password=(EditText)findViewById(R.id.edt_password);
        edt_error=(TextView)findViewById(R.id.edt_error);
        btn_ingresar=(ImageButton)findViewById(R.id.btn_ingresar);
        btn_ingresar.setOnClickListener(btnEntrarOnClickListener);

        DataBaseHelper dataBaseHelper = new DataBaseHelper(LoginActivity.this);
        try {
            dataBaseHelper.createDataBase();
            new DataBaseSingleton(LoginActivity.this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    View.OnClickListener btnEntrarOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            boolean isOK = true;
            usuario=edt_usuario.getText().toString();
            password=edt_password.getText().toString();

            if (usuario.trim().isEmpty()) {
                edt_usuario.setError("Ingrese Usuario");
                isOK = false;
            }

            if (password.trim().isEmpty()) {
                edt_password.setError("Ingrese Clave");
                isOK = false;
            }

            if (isOK) {//Validacion OK
                usu=new Usuarios();
                usu.setUSusu(usuario.trim());
                usu.setUSclave(password.trim());
                Usuarios usu1=new Usuarios();
                UsuariosDAO ud=new UsuariosDAO();
                usu1=ud.ValUsuario(usu);
                if (usu1 != null){ //No encontro Usuario
                    Intent intent = new Intent(LoginActivity.this, ClienteActivity.class);
                    startActivityForResult(intent, 99);
                    finish();
                }
                else {
                    edt_error.setText("Credenciales Incorrectas");
                }
            }
        }

    };
}
