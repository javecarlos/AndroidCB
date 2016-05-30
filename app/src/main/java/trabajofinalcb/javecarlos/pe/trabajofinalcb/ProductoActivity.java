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
import android.widget.Button;
import android.widget.ImageView;

import adapters.recyclerview.RVProductoAdapter;
import adapters.recyclerview.interfaces.IRVProductoAdapter;
import dao.ProductosDAO;
import entities.Productos;
import utils.Constantes;

/**
 * Created by gosoriot on 10/05/2016.
 */
public class ProductoActivity extends AppCompatActivity implements IRVProductoAdapter {

    private RVProductoAdapter mRVProductosAdapter;
    private RecyclerView rvProductos;
    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_productos);
        rvProductos = (RecyclerView) findViewById(R.id.rvProductos);

        rvProductos.setLayoutManager(new LinearLayoutManager(ProductoActivity.this));
        mRVProductosAdapter = new RVProductoAdapter(ProductoActivity.this);
        rvProductos.setAdapter(mRVProductosAdapter);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getResources().getString(R.string.texto_menu_productos));
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
        } else  //Capturo el click del guardar
            if (item.getItemId() == R.id.btnAgregar) {
                Productos productos = null;
                Intent intent = new Intent(ProductoActivity.this, ProductoAddEditActivity.class);
                intent.putExtra(Constantes.ARG_NUEVO_PRODUCTO, true);
                intent.putExtra(Constantes.ARG_PRODUCTO, productos);
                startActivity(intent);

                return true;
            }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSelectItem(Productos productos) {
        Intent intent = new Intent(ProductoActivity.this, ProductoInfoActivity.class);
        intent.putExtra(Constantes.ARG_PRODUCTO, productos);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mRVProductosAdapter.clearAndAddAll(new ProductosDAO().listProducto());
    }
}
