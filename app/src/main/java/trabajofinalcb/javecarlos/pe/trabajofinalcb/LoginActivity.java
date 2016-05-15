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
    public String JG = "GUSTAVO OSORIO TELLO";
    Usuarios usu;
    EditText edt_usuario, edt_password;
    Button btn_ingresar;
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
        btn_ingresar=(Button)findViewById(R.id.btn_ingresar);
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

            if (isOK) {

                if (usu == null)
                    usu = new Usuarios();

                usu.setUSusu(usuario.trim());
                usu.setUSclave(password.trim());

                // boolean isConsul = new UsuariosDAO().listUsuarios(usu);
                // boolean isInserted = new ProductosDAO().insertProducto(productos);
                //  if (isConsul) {
                //  Toast.makeText(.this, productos.getNombreP() + " ha sido registrado", Toast.LENGTH_LONG).show();
                //    finish();
                // }
                //else
                //   new AlertDialog.Builder(ProductoAddEditActivity.this).setTitle(R.string.app_name).setMessage("No se pudo regristrar en la base de datos").setNegativeButton("Aceptar", null).show();



                if (usuario.equals("jg") && password.equals("123")){
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    //   intent.putExtra(MainActivity.ARG_NOMBRE,JG.toString().trim());
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
