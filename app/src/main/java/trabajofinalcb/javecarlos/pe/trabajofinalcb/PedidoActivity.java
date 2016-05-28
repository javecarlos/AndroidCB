package trabajofinalcb.javecarlos.pe.trabajofinalcb;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import adapters.recyclerview.RVPedidoAdapter;
import adapters.recyclerview.interfaces.IRVPedidoAdapter;
import dao.PedidoDAO;
import entities.PedidoRV;
import utils.Constantes;

/**
 * Created by gosoriot on 10/05/2016.
 */
public class PedidoActivity extends AppCompatActivity implements IRVPedidoAdapter {

    private RVPedidoAdapter mRVPedidoAdapter;
    private RecyclerView rvPedido;

    private Toolbar toolbar;

    //private GoogleApiClient client;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pedidos);
        rvPedido = (RecyclerView) findViewById(R.id.rvPedidos);

        rvPedido.setLayoutManager(new LinearLayoutManager(PedidoActivity.this));
        mRVPedidoAdapter = new RVPedidoAdapter(PedidoActivity.this);
        rvPedido.setAdapter(mRVPedidoAdapter);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Pedidos");

//        mActionBarDrawerToggle = new ActionBarDrawerToggle(PedidoActivity.this, dlMenu, toolbar, R.string.app_name, R.string.app_name);

        //      dlMenu.addDrawerListener(mActionBarDrawerToggle);
    }

    View.OnClickListener imgPedidoNuevoOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            PedidoRV pedido = null;
            Intent intent = new Intent(PedidoActivity.this, ClienteAddEditActivity.class);
            intent.putExtra(Constantes.ARG_NUEVO_PEDIDO, true);
            intent.putExtra(Constantes.ARG_PEDIDO, pedido);
            startActivity(intent);
        }
    };

    @Override
    public void onItemClick(PedidoRV pedido) {
        Intent intent = new Intent(PedidoActivity.this, ClienteInfoActivity.class);
        intent.putExtra(Constantes.ARG_PEDIDO, pedido);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mRVPedidoAdapter.clearAndAddAll(new PedidoDAO().listPedido());
    }

    //para el toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflamos el menú que va a aparecer en el Toolbar
        getMenuInflater().inflate(R.menu.menu_lista_cliente, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Capturo el click de la flecha hacia atrás
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        } else  //Capturo el click del agregar
            if (item.getItemId() == R.id.btnAgregar) {
                Intent intent = new Intent(PedidoActivity.this, ClienteAddEditActivity.class);
                //intent.putExtra(Constantes.ARG_NUEVO_CLIENTE, Pedido);
                //intent.putExtra(Constantes.ARG_CLIENTE, cliente);
                startActivity(intent);
                return true;
            }

        return super.onOptionsItemSelected(item);
    }

}