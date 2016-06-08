package trabajofinalcb.javecarlos.pe.trabajofinalcb;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;

import adapters.recyclerview.RVPedidoDetAdapter;
import adapters.recyclerview.interfaces.IRVPedidoDetAdapter;
import adapters.spinner.SPAdapter;
import dao.ClienteDAO;
import dao.PedidoDAO;
import entities.Cliente;
import entities.PedidoCab;
import entities.PedidoDet;
import utils.Constantes;

public class PedidoNuevoActivity extends AppCompatActivity implements IRVPedidoDetAdapter {
    private SPAdapter mSPAdapter;
    private Spinner spCliente;

    PedidoCab pc = new PedidoCab();
    PedidoDAO pd = new PedidoDAO();
    Cliente cliente = new Cliente();

    private RVPedidoDetAdapter mRVPedidoDetAdapter;
    private RecyclerView rvPedidoDet;

    private TextView tvTotal;

    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido_nuevo);
        rvPedidoDet = (RecyclerView) findViewById(R.id.rvPedidosDet);
        spCliente = (Spinner)findViewById(R.id.spCliente);
        tvTotal= (TextView) findViewById(R.id.tvTotalPedidoInfo);

        //RecyclerView
        rvPedidoDet.setLayoutManager(new LinearLayoutManager(PedidoNuevoActivity.this));
        mRVPedidoDetAdapter = new RVPedidoDetAdapter(PedidoNuevoActivity.this);
        rvPedidoDet.setAdapter(mRVPedidoDetAdapter);

        //Spinner
        mSPAdapter = new SPAdapter(PedidoNuevoActivity.this,new ClienteDAO().listCliente());
        spCliente.setAdapter(mSPAdapter);
        spCliente.setOnItemSelectedListener(spClienteOnItemSelectedListener);

        //Toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbarNuevo);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Nuevo Pedido");
    }

    AdapterView.OnItemSelectedListener spClienteOnItemSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            cliente = (Cliente) parent.getSelectedItem();
            Toast.makeText(PedidoNuevoActivity.this,cliente.getEmpresaId()+" - "+cliente.getEmpresaNombre(),Toast.LENGTH_LONG).show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }
    };

    @Override
    public void onItemClick(PedidoDet pedido) {

    }

    @Override
    protected void onStart() {
        super.onStart();
        pc = new PedidoCab();
        tvTotal.setText("0.0");
        pc.setIdPedido(0);
        mRVPedidoDetAdapter.clearAndAddAll(new PedidoDAO().lstPedidoDet(pc));
    }

    //para el toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflamos el menú que va a aparecer en el Toolbar
        getMenuInflater().inflate(R.menu.menu_tool_nuevo_guardar, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Capturo el click de la flecha hacia atrás
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        } else  //Capturo el click de agregar o guardar
            if (item.getItemId() == R.id.btnAgregar) {
                pd.deletePedido(pc);
                finish();
            }
            if (item.getItemId() == R.id.btnGuardar) {
                pd.deletePedido(pc);
                finish();
            }
        return super.onOptionsItemSelected(item);
    }

}