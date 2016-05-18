package trabajofinalcb.javecarlos.pe.trabajofinalcb;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import adapters.recyclerview.RVClienteAdapter;
import adapters.recyclerview.interfaces.IRVClienteAdapter;
import dao.ClienteDAO;
import dao.ProductosDAO;
import entities.Cliente;
import utils.Constantes;

/**
 * Created by carlosarmando on 05/05/2016.
 */
public class ClienteActivity extends AppCompatActivity implements IRVClienteAdapter {
    RecyclerView rvCliente;
    RVClienteAdapter mRVClienteAdapter;
    ImageView imgClienteNuevo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_cliente);

        rvCliente = (RecyclerView) findViewById(R.id.rvCliente);

        mRVClienteAdapter = new RVClienteAdapter(ClienteActivity.this);

//        mRVClienteAdapter.add(new Cliente("Luciana", "Ramos", "923123213", "luciana.ramos@yahoo.es", "Embotelladora Ramos",
//                "Avenida Benavides 234", "Miraflores", "Al costado del Atlantic City", ""));
//        mRVClienteAdapter.add(new Cliente("Juan", "Perez", "987654321", "jperez@hotmail.com", "Galletera del Norte",
//                "Avenida Industrial 123", "Trujillo", "Al costado de la plaza de armas", ""));

        rvCliente.setLayoutManager(new LinearLayoutManager(ClienteActivity.this));
        rvCliente.setAdapter(mRVClienteAdapter);

        imgClienteNuevo = (ImageView) findViewById(R.id.imgClienteNuevo);
        imgClienteNuevo.setOnClickListener(imgClienteNuevoOnClickListener);
    }

    View.OnClickListener imgClienteNuevoOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Cliente cliente = null;
            Intent intent = new Intent(ClienteActivity.this, ClienteAddEditActivity.class);
            intent.putExtra(Constantes.ARG_NUEVO_CLIENTE, true);
            intent.putExtra(Constantes.ARG_CLIENTE, cliente);
            startActivity(intent);
        }
    };

    @Override
    public void onItemClick(Cliente cliente) {
        Intent intent = new Intent(ClienteActivity.this, ClienteInfoActivity.class);
        intent.putExtra(Constantes.ARG_CLIENTE, cliente);
        startActivity(intent);
    }

    @Override
    protected void onStart(){
        super.onStart();
        mRVClienteAdapter.clearAndAddAll(new ClienteDAO().listCliente());
    }
}
