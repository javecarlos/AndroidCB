package trabajofinalcb.javecarlos.pe.trabajofinalcb;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import entities.Cliente;
import utils.Constantes;

/**
 * Created by carlosarmando on 09/05/2016.
 */
public class ClienteAddEditActivity extends AppCompatActivity {
    TextView tvClienteNombre;

    Cliente cliente;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_add_edit);

        CargarTextView();

        cliente = ObtenerCliente();
        boolean esNuevo = VerificarEsNuevo();

        if (!esNuevo && cliente != null) {
            SetDataCliente(cliente);
        }

    }

    private void SetDataCliente(Cliente cliente) {
        tvClienteNombre.setText(cliente.getEmpresaNombre());
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

    private void CargarTextView() {
        tvClienteNombre = (TextView) findViewById(R.id.tvClienteNombre);
    }
}
