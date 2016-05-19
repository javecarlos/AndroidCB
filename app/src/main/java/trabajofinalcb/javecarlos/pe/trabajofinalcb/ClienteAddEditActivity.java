package trabajofinalcb.javecarlos.pe.trabajofinalcb;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import dao.ClienteDAO;
import entities.Cliente;
import utils.Constantes;
import utils.Utils;

/**
 * Created by carlosarmando on 09/05/2016.
 */
public class ClienteAddEditActivity extends AppCompatActivity {
    TextView tvClienteNombre;
    EditText etContactoNombre, etContactoApellido, etContactoTelefono, etContactoCorreo, etClienteNombre,
            etClienteDireccion, etClienteDistrito, etClienteReferencia;
    ImageView imgClienteRegresar, imgClienteGuardar;

    Cliente cliente;
    boolean esNuevo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_add_edit);

        CargarTextView();
        CargarEditText();
        CargarImageView();

        cliente = ObtenerCliente();
        esNuevo = VerificarEsNuevo();

        if (!esNuevo && cliente != null) {
            SetDataCliente(cliente);
        } else {
            tvClienteNombre.setText(R.string.texto_nuevo_cliente);
        }

        /*Cargar Eventos de los Iconos*/
        imgClienteRegresar.setOnClickListener(imgClienteRegresarOnClickListener);
        imgClienteGuardar.setOnClickListener(imgClienteGuardarOnClickListener);
    }

    View.OnClickListener imgClienteRegresarOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent;

            if (esNuevo) {
                intent = new Intent(ClienteAddEditActivity.this, ClienteActivity.class);
            } else {
                intent = new Intent(ClienteAddEditActivity.this, ClienteInfoActivity.class);
                intent.putExtra(Constantes.ARG_NUEVO_CLIENTE, false);
                intent.putExtra(Constantes.ARG_CLIENTE, cliente);
            }

            startActivity(intent);
            finish();
        }
    };

    View.OnClickListener imgClienteGuardarOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String mensaje = ValidarDatos();

            if (mensaje.isEmpty()) {
                if (cliente == null)
                    cliente = new Cliente();
                cliente.setContactoNombre(etContactoNombre.getText().toString().trim());
                cliente.setContactoApellido(etContactoApellido.getText().toString().trim());
                cliente.setContactoTelefono(Integer.parseInt(etContactoTelefono.getText().toString().trim()));
                cliente.setContactoCorreo(etContactoCorreo.getText().toString().trim());
                cliente.setEmpresaNombre(etClienteNombre.getText().toString().trim());
                cliente.setEmpresaDireccion(etClienteDireccion.getText().toString().trim());
                cliente.setEmpresaDistrito(etClienteDistrito.getText().toString().trim());
                cliente.setEmpresaReferencia(etClienteReferencia.getText().toString().trim());
                cliente.setEmpresaMap("");

                /*Logica para Guardar Cliente*/
                if (esNuevo) {
                    //Insert
                    boolean isInserted = new ClienteDAO().insertCliente(cliente);
                    if (isInserted) {
                        Toast.makeText(ClienteAddEditActivity.this, cliente.getEmpresaNombre() + " " +
                                getResources().getString(R.string.texto_mensaje_insertado), Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(ClienteAddEditActivity.this, ClienteActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        String errorInsercion = getResources().getString(R.string.error_insertar_cliente);
                        String btnAceptar = getResources().getString(R.string.btnAceptar);
                        new AlertDialog.Builder(ClienteAddEditActivity.this).setTitle(R.string.app_name)
                                .setMessage(errorInsercion).setNegativeButton(btnAceptar, null).show();
                    }
                } else {
                    //Update
                    boolean isUpdated = new ClienteDAO().updateCliente(cliente);
                    if (isUpdated) {
                        Toast.makeText(ClienteAddEditActivity.this, cliente.getEmpresaNombre() + " " +
                                getResources().getString(R.string.texto_mensaje_actualizado), Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(ClienteAddEditActivity.this, ClienteActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        String errorActualizar = getResources().getString(R.string.error_actualizar_cliente);
                        String btnAceptar = getResources().getString(R.string.btnAceptar);
                        new AlertDialog.Builder(ClienteAddEditActivity.this).setTitle(R.string.app_name)
                                .setMessage(errorActualizar).setNegativeButton(btnAceptar, null).show();
                    }
                }
            } else {
                String btnAceptar = getResources().getString(R.string.btnAceptar);
                new AlertDialog.Builder(ClienteAddEditActivity.this).setTitle(R.string.mensaje_error_cliente)
                        .setMessage(mensaje).setNeutralButton(btnAceptar, null).show();
            }
        }
    };

    private String ValidarDatos() {
        String resultado = "";

        if (etContactoNombre.getText().toString().trim().isEmpty()) {
            resultado = getResources().getString(R.string.error_vacio_nombre_contacto);
            return resultado;
        }

        if (etContactoNombre.getText().toString().trim().length() > 50) {
            resultado = getResources().getString(R.string.error_maxlength_nombre_contacto);
            return resultado;
        }

        if (etContactoApellido.getText().toString().trim().isEmpty()) {
            resultado = getResources().getString(R.string.error_vacio_apellido_contacto);
            return resultado;
        }

        if (etContactoApellido.getText().toString().trim().length() > 50) {
            resultado = getResources().getString(R.string.error_maxlength_apellido_contacto);
            return resultado;
        }

        if (etContactoTelefono.getText().toString().trim().isEmpty()) {
            resultado = getResources().getString(R.string.error_vacio_telefono_contacto);
            return resultado;
        }

        if (etContactoTelefono.getText().toString().trim().length() < 9) {
            resultado = getResources().getString(R.string.error_maxlength_telefono_contacto);
            return resultado;
        }

        if (etContactoCorreo.getText().toString().trim().isEmpty()) {
            resultado = getResources().getString(R.string.error_vacio_correo_contacto);
            return resultado;
        }

        if (!Utils.IsEmailValid(etContactoCorreo.getText().toString().trim())) {
            resultado = getResources().getString(R.string.error_formato_correo_contacto);
            return resultado;
        }

        if (etContactoCorreo.getText().toString().trim().length() > 50) {
            resultado = getResources().getString(R.string.error_maxlength_correo_contacto);
            return resultado;
        }

        if (etClienteNombre.getText().toString().trim().isEmpty()) {
            resultado = getResources().getString(R.string.error_vacio_nombre_empresa);
            return resultado;
        }

        if (etClienteNombre.getText().toString().trim().length() > 50) {
            resultado = getResources().getString(R.string.error_maxlength_nombre_empresa);
            return resultado;
        }

        if (etClienteDireccion.getText().toString().trim().isEmpty()) {
            resultado = getResources().getString(R.string.error_vacio_direccion_empresa);
            return resultado;
        }

        if (etClienteDireccion.getText().toString().trim().length() > 50) {
            resultado = getResources().getString(R.string.error_maxlength_direccion_empresa);
            return resultado;
        }

        if (etClienteDistrito.getText().toString().trim().isEmpty()) {
            resultado = getResources().getString(R.string.error_vacio_distrito_empresa);
            return resultado;
        }

        if (etClienteDistrito.getText().toString().trim().length() > 50) {
            resultado = getResources().getString(R.string.error_maxlength_distrito_empresa);
            return resultado;
        }

        if (etClienteReferencia.getText().toString().trim().isEmpty()) {
            resultado = getResources().getString(R.string.error_vacio_referencia_empresa);
            return resultado;
        }

        if (etClienteReferencia.getText().toString().trim().length() > 50) {
            resultado = getResources().getString(R.string.error_maxlength_referencia_empresa);
            return resultado;
        }

        return resultado;
    }

    private void SetDataCliente(Cliente cliente) {
        tvClienteNombre.setText(cliente.getEmpresaNombre());
        etContactoNombre.setText(cliente.getContactoNombre());
        etContactoApellido.setText(cliente.getContactoApellido());
        etContactoTelefono.setText(String.valueOf(cliente.getContactoTelefono()));
        etContactoCorreo.setText(cliente.getContactoCorreo());
        etClienteNombre.setText(cliente.getEmpresaNombre());
        etClienteDireccion.setText(cliente.getEmpresaDireccion());
        etClienteDistrito.setText(cliente.getEmpresaDistrito());
        etClienteReferencia.setText(cliente.getEmpresaReferencia());
    }

    private Cliente ObtenerCliente() {
        Cliente clienteInfo;
        if (getIntent().getExtras() != null && getIntent().getExtras().containsKey(Constantes.ARG_CLIENTE)) {
            clienteInfo = getIntent().getParcelableExtra(Constantes.ARG_CLIENTE);
        } else {
            clienteInfo = null;
        }

        return clienteInfo;
    }

    private boolean VerificarEsNuevo() {
        boolean resultado;

        if (getIntent().getExtras() != null && getIntent().getExtras().containsKey(Constantes.ARG_NUEVO_CLIENTE)) {
            resultado = getIntent().getBooleanExtra(Constantes.ARG_NUEVO_CLIENTE, true);
        } else {
            resultado = true;
        }

        return resultado;
    }

    private void CargarImageView() {
        imgClienteRegresar = (ImageView) findViewById(R.id.imgClienteRegresar);
        imgClienteGuardar = (ImageView) findViewById(R.id.imgClienteGuardar);
    }

    private void CargarEditText() {
        etContactoNombre = (EditText) findViewById(R.id.etContactoNombre);
        etContactoApellido = (EditText) findViewById(R.id.etContactoApellido);
        etContactoTelefono = (EditText) findViewById(R.id.etContactoTelefono);
        etContactoCorreo = (EditText) findViewById(R.id.etContactoCorreo);
        etClienteNombre = (EditText) findViewById(R.id.etClienteNombre);
        etClienteDireccion = (EditText) findViewById(R.id.etClienteDireccion);
        etClienteDistrito = (EditText) findViewById(R.id.etClienteDistrito);
        etClienteReferencia = (EditText) findViewById(R.id.etClienteReferencia);
    }

    private void CargarTextView() {
        tvClienteNombre = (TextView) findViewById(R.id.tvClienteNombre);
    }
}
