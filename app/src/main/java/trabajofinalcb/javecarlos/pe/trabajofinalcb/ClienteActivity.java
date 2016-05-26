package trabajofinalcb.javecarlos.pe.trabajofinalcb;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

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

    private Toolbar toolbar;
    //para el menu
    private DrawerLayout dlMenu;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private NavigationView nvMenu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_cliente);

        rvCliente = (RecyclerView) findViewById(R.id.rvCliente);

        mRVClienteAdapter = new RVClienteAdapter(ClienteActivity.this);

        rvCliente.setLayoutManager(new LinearLayoutManager(ClienteActivity.this));
        rvCliente.setAdapter(mRVClienteAdapter);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //para el menu
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Clientes");

        dlMenu = (DrawerLayout) findViewById(R.id.dlClientes);
        mActionBarDrawerToggle = new ActionBarDrawerToggle(ClienteActivity.this, dlMenu, toolbar, R.string.app_name, R.string.app_name);

        dlMenu.addDrawerListener(mActionBarDrawerToggle);

        nvMenu = (NavigationView) findViewById(R.id.nvMenu);
        nvMenu.setNavigationItemSelectedListener(nvMenuOnNavigationItemSelectedListener);
    }

    //para el toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflamos el menú que va a aparecer en el Toolbar
        getMenuInflater().inflate(R.menu.menu_lista_cliente, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //para el menu
    NavigationView.OnNavigationItemSelectedListener nvMenuOnNavigationItemSelectedListener = new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            nvMenu.setCheckedItem(item.getItemId());

            Intent intentOpcion;
            if (item.getItemId() == R.id.navListaCliente) {
                intentOpcion = new Intent(ClienteActivity.this, ClienteActivity.class);
                startActivity(intentOpcion);
            }
            if (item.getItemId() == R.id.navListaProducto) {
                intentOpcion = new Intent(ClienteActivity.this, ProductoActivity.class);
                startActivity(intentOpcion);
            }
            if (item.getItemId() == R.id.navListaPedido) {
                intentOpcion = new Intent(ClienteActivity.this, ClienteActivity.class);
                startActivity(intentOpcion);
            }
            if (item.getItemId() == R.id.navCerrarSesion) {
                PreferenceManager.getDefaultSharedPreferences(ClienteActivity.this).edit().clear().commit();
                Intent intent = new Intent(ClienteActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }

            dlMenu.closeDrawer(GravityCompat.START);
            return true;
        }
    };

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mActionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mActionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mActionBarDrawerToggle.onOptionsItemSelected(item))
            return true;

        if (item.getItemId() == R.id.btnAgregar) {
            Cliente cliente = null;
            Intent intent = new Intent(ClienteActivity.this, ClienteAddEditActivity.class);
            intent.putExtra(Constantes.ARG_NUEVO_CLIENTE, true);
            intent.putExtra(Constantes.ARG_CLIENTE, cliente);
            startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

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
