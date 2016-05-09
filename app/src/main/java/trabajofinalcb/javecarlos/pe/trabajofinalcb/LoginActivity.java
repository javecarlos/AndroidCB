package trabajofinalcb.javecarlos.pe.trabajofinalcb;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by carlosarmando on 03/05/2016.
 */
public class LoginActivity extends AppCompatActivity {
    private static final String nombreUsuario = "admin";
    private static final String claveUsuario = "1234";

//    EditText etUsuario;
//    EditText etClave;

    EditText etUsuario, etClave;
    TextView tvErrorUsuario, tvErrorClave;
    Button btnEntrar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsuario = (EditText) findViewById(R.id.etUsuario);
        etClave = (EditText) findViewById(R.id.etClave);
        tvErrorUsuario = (TextView)findViewById(R.id.tvErrorUsuario);
        tvErrorClave = (TextView)findViewById(R.id.tvErrorClave);
        btnEntrar = (Button) findViewById(R.id.btnEntrar);

        btnEntrar.setOnClickListener(btnEntrarOnClickListener);
    }

    View.OnClickListener btnEntrarOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            tvErrorUsuario.setVisibility(View.GONE);
            tvErrorClave.setVisibility(View.GONE);
            boolean esCorrecto = true;

            String nombreUsuarioIngreso = etUsuario.getText().toString().trim();
            String claveUsuarioIngreso = etClave.getText().toString().trim();

            /*Logica para validar datos*/

            /*Fin Logica para validar datos*/

            if (nombreUsuarioIngreso.isEmpty()) {
                tvErrorUsuario.setVisibility(View.VISIBLE);
                tvErrorUsuario.setText("El Nombre de Usuario no puede ser vacio");
                esCorrecto = false;
            } else {
                if (!nombreUsuario.equals(nombreUsuarioIngreso)) {
                    tvErrorUsuario.setVisibility(View.VISIBLE);
                    tvErrorUsuario.setText("Nombre de Usuario incorrecto");
                    esCorrecto = false;
                }
                else{
                    tvErrorUsuario.setVisibility(View.INVISIBLE);
                    tvErrorUsuario.setText("");
                    //esCorrecto = true;
                }
            }

            if (claveUsuarioIngreso.isEmpty()) {
                tvErrorClave.setVisibility(View.VISIBLE);
                tvErrorClave.setText("La clave de Usuario no puede ser vacio");
                esCorrecto = false;
            } else {
                if (!claveUsuario.equals(claveUsuarioIngreso)) {
                    tvErrorClave.setVisibility(View.VISIBLE);
                    tvErrorClave.setText("Clave de Usuario incorrecta");
                    esCorrecto = false;
                }else{
                    tvErrorClave.setVisibility(View.INVISIBLE);
                    tvErrorClave.setText("");
                    //esCorrecto = true;
                }
            }

            if (esCorrecto) {
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        }
    };
}
