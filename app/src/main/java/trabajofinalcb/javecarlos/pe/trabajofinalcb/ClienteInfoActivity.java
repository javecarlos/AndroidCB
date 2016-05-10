package trabajofinalcb.javecarlos.pe.trabajofinalcb;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import entities.Cliente;
import utils.Constantes;

/**
 * Created by carlosarmando on 09/05/2016.
 */
public class ClienteInfoActivity extends AppCompatActivity {
    private TextView tvClienteNombreInfo, tvContactoNombreInfo, tvContactoApellidoInfo, tvContactoCorreoInfo, tvContactoTelefonoInfo,
            tvClienteDireccionInfo, tvClienteDistritoInfo, tvClienteReferenciaInfo;
    private ImageView imgClienteRegresarLista, imgClienteEditar, imgClienteLlamar, imgClienteMapa;

    private Cliente cliente;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_info);

        CargarTextView();
        CargarImageView();

        /*Cargar Eventos Iconos*/
        imgClienteRegresarLista.setOnClickListener(imgClienteRegresarListaOnClickListener);
        imgClienteEditar.setOnClickListener(imgClienteEditarOnClickListener);
        imgClienteLlamar.setOnClickListener(imgClienteLlamarOnClickListener);
        imgClienteMapa.setOnClickListener(imgClienteMapaOnClickListener);

        cliente = ObtenerCliente();

        if (cliente != null)
            SetDataCliente(cliente);
    }

    View.OnClickListener imgClienteRegresarListaOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(ClienteInfoActivity.this, ClienteActivity.class);
            startActivity(intent);
            finish();
        }
    };
    View.OnClickListener imgClienteEditarOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            boolean esNuevo;
            Intent intent = new Intent(ClienteInfoActivity.this, ClienteAddEditActivity.class);
            esNuevo = cliente == null;
            intent.putExtra(Constantes.ARG_NUEVO_CLIENTE,esNuevo);
            intent.putExtra(Constantes.ARG_CLIENTE, cliente);
            startActivity(intent);
        }
    };
    View.OnClickListener imgClienteLlamarOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };
    View.OnClickListener imgClienteMapaOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

    private Cliente ObtenerCliente(){
        Cliente clienteInfo;
        if (getIntent().getExtras() != null && getIntent().getExtras().containsKey(Constantes.ARG_CLIENTE)) {
            clienteInfo = getIntent().getParcelableExtra(Constantes.ARG_CLIENTE);
        } else {
            clienteInfo = null;
        }

        return clienteInfo;
    }

    private void CargarTextView(){
        tvClienteNombreInfo = (TextView) findViewById(R.id.tvClienteNombreInfo);
        tvContactoNombreInfo = (TextView) findViewById(R.id.tvContactoNombreInfo);
        tvContactoApellidoInfo = (TextView) findViewById(R.id.tvContactoApellidoInfo);
        tvContactoCorreoInfo = (TextView) findViewById(R.id.tvContactoCorreoInfo);
        tvContactoTelefonoInfo = (TextView) findViewById(R.id.tvContactoTelefonoInfo);
        tvClienteDireccionInfo = (TextView) findViewById(R.id.tvClienteDireccionInfo);
        tvClienteDistritoInfo = (TextView) findViewById(R.id.tvClienteDistritoInfo);
        tvClienteReferenciaInfo = (TextView) findViewById(R.id.tvClienteReferenciaInfo);
    }

    private void CargarImageView(){
        imgClienteRegresarLista = (ImageView)findViewById(R.id.imgClienteRegresarLista);
        imgClienteEditar = (ImageView)findViewById(R.id.imgClienteEditar);
        imgClienteLlamar = (ImageView)findViewById(R.id.imgClienteLlamar);
        imgClienteMapa = (ImageView)findViewById(R.id.imgClienteMapa);
    }

    private void SetDataCliente(Cliente cliente) {
        tvClienteNombreInfo.setText(cliente.getEmpresaNombre());
        tvContactoNombreInfo.setText(cliente.getContactoNombre());
        tvContactoApellidoInfo.setText(cliente.getContactoApellido());
        tvContactoCorreoInfo.setText(cliente.getContactoCorreo());
        tvContactoTelefonoInfo.setText(cliente.getContactoTelefono());
        tvClienteDireccionInfo.setText(cliente.getEmpresaDireccion());
        tvClienteDistritoInfo.setText(cliente.getEmpresaDistrito());
        tvClienteReferenciaInfo.setText(cliente.getEmpresaReferencia());
    }
}
