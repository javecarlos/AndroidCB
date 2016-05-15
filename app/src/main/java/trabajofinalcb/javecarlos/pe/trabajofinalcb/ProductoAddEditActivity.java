package trabajofinalcb.javecarlos.pe.trabajofinalcb;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompatSideChannelService;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import dao.ProductosDAO;
import entities.Cliente;
import entities.Productos;
import utils.Constantes;

/**
 * Created by gosoriot on 11/05/2016.
 */
public class ProductoAddEditActivity extends AppCompatActivity {

    private ImageView imgProductRegresar,imgProducGuardar;
    private TextView tvProductoNombre;
    private EditText etProductNombre,etCantStock,etPrecioUnid;
    Productos productos;
    boolean esNuevo;
    private boolean isUpdate;
    String Productox,stockx,preciox;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto_add_edit);
        etProductNombre = (EditText) findViewById(R.id.etProductNombre);
        etCantStock = (EditText) findViewById(R.id.etCantStock);
        etPrecioUnid = (EditText) findViewById(R.id.etPrecioUnid);
        tvProductoNombre = (TextView) findViewById(R.id.tvProductoNombre);
        imgProducGuardar = (ImageView) findViewById(R.id.imgProducGuardar);
        imgProductRegresar = (ImageView) findViewById(R.id.imgProductRegresar);
        CargarTextView();
        productos = ObtenerProducto();
        esNuevo = VerificarEsNuevo();

        if (!esNuevo && productos != null) {
            SetDataCliente(productos);
        } else {
            tvProductoNombre.setText("Nuevo Producto");
        }

        imgProducGuardar.setOnClickListener(imgProducGuardarOnClickListener);
        imgProductRegresar.setOnClickListener(imgProductRegresarOnClickListener);
    }

    View.OnClickListener imgProducGuardarOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            boolean isOK = true;
            Productox=etProductNombre.getText().toString();
            stockx=etCantStock.getText().toString();
            preciox=etPrecioUnid.getText().toString();
            if (Productox.trim().isEmpty()) {
                etProductNombre.setError("Ingrese Nombre del Producto");
                isOK = false;
            }
            else
            {  if (Productox.trim().length() > 20)
                {
                    etProductNombre.setError("El tamaño de Nombre de Productos debe de ser menor de 20");
                    isOK = false;
                }
            }

            if (stockx.trim().isEmpty()) {
                etCantStock.setError("Ingrese Stock");
                isOK = false;
            }
            if (preciox.isEmpty()) {
                etPrecioUnid.setError("Ingrese Precio");
                isOK = false;
            }
            if (isOK) {
                if (productos == null)
                    productos = new Productos();
                productos.setNombreP(Productox.trim());
                productos.setStockP(Integer.parseInt(stockx.trim()));
                productos.setPrecioP(Integer.parseInt(preciox.trim()));

                if(esNuevo) {
                    boolean isInserted = new ProductosDAO().insertProducto(productos);
                    if (isInserted) {
                        Toast.makeText(ProductoAddEditActivity.this, productos.getNombreP() + " ha sido registrado", Toast.LENGTH_LONG).show();
                        finish();
                    } else
                        new AlertDialog.Builder(ProductoAddEditActivity.this).setTitle(R.string.app_name).setMessage("No se pudo regristrar en la base de datos").setNegativeButton("Aceptar", null).show();
                }
                else
                {
                    boolean isInserted = new ProductosDAO().updateProducto(productos);
                    if (isInserted) {
                        Toast.makeText(ProductoAddEditActivity.this, productos.getNombreP() + " ha sido Actualizada", Toast.LENGTH_LONG).show();
                        finish();
                    } else
                        new AlertDialog.Builder(ProductoAddEditActivity.this).setTitle(R.string.app_name).setMessage("No se pudo Actualizar en la base de datos").setNegativeButton("Aceptar", null).show();


                }


            }
        }
    };

    private void SetDataCliente(Productos productos) {
        tvProductoNombre.setText(ObtenerProducto().getNombreP());
        etProductNombre.setText(ObtenerProducto().getNombreP());
        etCantStock.setText(String.valueOf(ObtenerProducto().getStockP()));
        etPrecioUnid.setText(String.valueOf(ObtenerProducto().getPrecioP()));
    }

    View.OnClickListener imgProductRegresarOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent;
            if (esNuevo) {
                intent = new Intent(ProductoAddEditActivity.this, ProductoActivity.class);
            } else {
                intent = new Intent(ProductoAddEditActivity.this, ProductoInfoActivity.class);
                intent.putExtra(Constantes.ARG_NUEVO_PRODUCTO, false);
                intent.putExtra(Constantes.ARG_PRODUCTO, productos);
            }
            startActivity(intent);
            finish();
        }
    };

    private boolean VerificarEsNuevo() {
        boolean resultado;

        if (getIntent().getExtras() != null && getIntent().getExtras().containsKey(Constantes.ARG_NUEVO_PRODUCTO)) {
            resultado = getIntent().getBooleanExtra(Constantes.ARG_NUEVO_PRODUCTO, true);
        } else {
            resultado = true;
        }

        return resultado;
    }


    private Productos ObtenerProducto() {
        Productos ProductoInfo;
        if (getIntent().getExtras() != null && getIntent().getExtras().containsKey(Constantes.ARG_PRODUCTO)) {
            ProductoInfo = getIntent().getParcelableExtra(Constantes.ARG_PRODUCTO);
        } else {
            ProductoInfo = null;
        }
        return ProductoInfo;
    }

    private void CargarTextView() {
        tvProductoNombre = (TextView) findViewById(R.id.tvProductoNombre);
    }
}
