package trabajofinalcb.javecarlos.pe.trabajofinalcb;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import adapters.recyclerview.RVProductoAdapter;
import adapters.recyclerview.interfaces.IRVProductoAdapter;
import dao.DataBaseHelper;
import dao.DataBaseSingleton;
import dao.ProductosDAO;
import entities.Cliente;
import entities.Productos;
import utils.Constantes;

/**
 * Created by gosoriot on 10/05/2016.
 */
public class ProductoActivity extends AppCompatActivity implements IRVProductoAdapter{

    private RVProductoAdapter mRVProductosAdapter;
    private RecyclerView rvProductos;
    private Button btFirstAgregar;
    private ImageView imgProductNuevo;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_lista_productos);
      rvProductos = (RecyclerView) findViewById(R.id.rvProductos);
      imgProductNuevo = (ImageView) findViewById(R.id.imgProductNuevo);

      rvProductos.setLayoutManager(new LinearLayoutManager(ProductoActivity.this));
      mRVProductosAdapter = new RVProductoAdapter(ProductoActivity.this);
      rvProductos.setAdapter(mRVProductosAdapter);

      imgProductNuevo.setOnClickListener(imgProductNuevoOnClickListener);
    }


    View.OnClickListener imgProductNuevoOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Productos productos = null;
            Intent intent = new Intent(ProductoActivity.this, ProductoAddEditActivity.class);
            intent.putExtra(Constantes.ARG_NUEVO_PRODUCTO, true);
            intent.putExtra(Constantes.ARG_PRODUCTO, productos);
            startActivity(intent);
        }
    };

   @Override
   public void onSelectItem(Productos productos) {
     Intent intent = new Intent(ProductoActivity.this, ProductoInfoActivity.class);
      intent.putExtra(Constantes.ARG_PRODUCTO, productos);
        startActivity(intent);
  }

   @Override
   protected void onStart(){
      super.onStart();
        mRVProductosAdapter.clearAndAddAll(new ProductosDAO().listProducto());
  }
}
