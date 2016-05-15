package trabajofinalcb.javecarlos.pe.trabajofinalcb;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import entities.Cliente;
import entities.Productos;
import utils.Constantes;

/**
 * Created by gosoriot on 12/05/2016.
 */
public class ProductoInfoActivity extends AppCompatActivity {

    private ImageView imgProductoRegresarLista,imgProductoEditar;
    private TextView tvNomProducInfo,tvStockInfo,tvPrecioInfo,tvProductoNombreInfo;
    private Productos productos;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos_info);
       CargarTextView();
       CargarImageView();

    /*Cargar Eventos Iconos*/
        imgProductoRegresarLista.setOnClickListener(imgProductoRegresarListaOnClickListener);
        imgProductoEditar.setOnClickListener(imgProductoEditarOnClickListener);

       productos = ObtenerProducto();

     if (productos != null)
            SetDataProducto(productos);
    }

    private Productos ObtenerProducto(){
        Productos productosInfo;
        if (getIntent().getExtras() != null && getIntent().getExtras().containsKey(Constantes.ARG_PRODUCTO)) {
            productosInfo = getIntent().getParcelableExtra(Constantes.ARG_PRODUCTO);
        } else {
            productosInfo = null;
        }
        return productosInfo;
    }

    private void SetDataProducto(Productos producto) {
        tvNomProducInfo.setText(producto.getNombreP());
        tvProductoNombreInfo.setText(producto.getNombreP());
        tvStockInfo.setText(String.valueOf(producto.getStockP()));
       tvPrecioInfo.setText(String.valueOf(producto.getPrecioP()));
        ;
    }

    View.OnClickListener imgProductoRegresarListaOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(ProductoInfoActivity.this, ProductoActivity.class);
            startActivity(intent);
            finish();
        }
    };

    View.OnClickListener imgProductoEditarOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            boolean esNuevo;
            Intent intent = new Intent(ProductoInfoActivity.this, ProductoAddEditActivity.class);
            esNuevo = productos == null;
            intent.putExtra(Constantes.ARG_NUEVO_PRODUCTO,esNuevo);
            intent.putExtra(Constantes.ARG_PRODUCTO, productos);
            startActivity(intent);
        }
    };
    private void CargarTextView(){
        tvNomProducInfo = (TextView) findViewById(R.id.tvNomProducInfo);
        tvStockInfo = (TextView) findViewById(R.id.tvStockInfo);
        tvPrecioInfo = (TextView) findViewById(R.id.tvPrecioInfo);
        tvProductoNombreInfo = (TextView) findViewById(R.id.tvProductoNombreInfo);
    }

    private void CargarImageView(){
        imgProductoRegresarLista = (ImageView) findViewById(R.id.imgProductoRegresarLista);
        imgProductoEditar = (ImageView) findViewById(R.id.imgProductoEditar);
    }
}
