package trabajofinalcb.javecarlos.pe.trabajofinalcb;

import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import adapters.recyclerview.RVMenuAdapter;
import adapters.recyclerview.interfaces.IRVMenuAdapter;
import entities.Menu;

public class MainActivity extends AppCompatActivity implements IRVMenuAdapter {

    private RecyclerView rvMenu;
    private RVMenuAdapter mRVMenuAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvMenu = (RecyclerView) findViewById(R.id.rvMenu);

        mRVMenuAdapter = new RVMenuAdapter(MainActivity.this);
        mRVMenuAdapter.add(new Menu(1, "icon_persona", "Clientes"));
        mRVMenuAdapter.add(new Menu(2, "icon_producto", "Productos"));
        mRVMenuAdapter.add(new Menu(3, "icon_pedido", "Pedidos"));
        mRVMenuAdapter.add(new Menu(4, "icon_cerrar_sesion", "Cerrar Sesión"));
        rvMenu.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        rvMenu.setAdapter(mRVMenuAdapter);
    }

    @Override
    public void onItemClick(Menu menu) {
//        Toast toast = Toast.makeText(MainActivity.this,menu.getDescripcion(),Toast.LENGTH_SHORT);
//        toast.show();
        Intent intentOpcion;

        switch (menu.getId()) {
            case 1:
                intentOpcion = new Intent(MainActivity.this, ClienteActivity.class);
                startActivity(intentOpcion);
                break;
            case 2:
                //productos
                intentOpcion = new Intent(MainActivity.this, ProductoActivity.class);
                startActivity(intentOpcion);
                break;
            case 3:
                intentOpcion = new Intent(MainActivity.this, PedidoActivity.class);
                startActivity(intentOpcion);
                break;
            case 4:
                //CERRAR SESSION
                PreferenceManager.getDefaultSharedPreferences(MainActivity.this).edit().clear().commit();
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
            default:
                break;
        }
    }
}
