package trabajofinalcb.javecarlos.pe.trabajofinalcb;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import adapters.recyclerview.RVClienteAdapter;
import entities.Cliente;

/**
 * Created by carlosarmando on 05/05/2016.
 */
public class ClienteActivity extends AppCompatActivity {
    RecyclerView rvCliente;
    RVClienteAdapter mRVClienteAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_cliente);

        rvCliente = (RecyclerView) findViewById(R.id.rvCliente);

        mRVClienteAdapter = new RVClienteAdapter();
        mRVClienteAdapter.add(new Cliente("Luciana", "Ramos", "923123213", "luciana.ramos@yahoo.es", "Embotelladora Ramos",
                "Avenida Benavides 234", "Miraflores", "Al costado del Atlantic City", ""));
        mRVClienteAdapter.add(new Cliente("Juan", "Perez", "987654321", "jperez@hotmail.com", "Galletera del Norte",
                "Avenida Industrial 123", "Trujillo", "Al costado de la plaza de armas", ""));
        mRVClienteAdapter.add(new Cliente("Luciana", "Ramos", "923123213", "luciana.ramos@yahoo.es", "Embotelladora Ramos",
                "Avenida Benavides 234", "Miraflores", "Al costado del Atlantic City", ""));
        mRVClienteAdapter.add(new Cliente("Juan", "Perez", "987654321", "jperez@hotmail.com", "Galletera del Norte",
                "Avenida Industrial 123", "Trujillo", "Al costado de la plaza de armas", ""));
        mRVClienteAdapter.add(new Cliente("Luciana", "Ramos", "923123213", "luciana.ramos@yahoo.es", "Embotelladora Ramos",
                "Avenida Benavides 234", "Miraflores", "Al costado del Atlantic City", ""));
        mRVClienteAdapter.add(new Cliente("Juan", "Perez", "987654321", "jperez@hotmail.com", "Galletera del Norte",
                "Avenida Industrial 123", "Trujillo", "Al costado de la plaza de armas", ""));
        mRVClienteAdapter.add(new Cliente("Luciana", "Ramos", "923123213", "luciana.ramos@yahoo.es", "Embotelladora Ramos",
                "Avenida Benavides 234", "Miraflores", "Al costado del Atlantic City", ""));
        mRVClienteAdapter.add(new Cliente("Juan", "Perez", "987654321", "jperez@hotmail.com", "Galletera del Norte",
                "Avenida Industrial 123", "Trujillo", "Al costado de la plaza de armas", ""));
        mRVClienteAdapter.add(new Cliente("Luciana", "Ramos", "923123213", "luciana.ramos@yahoo.es", "Embotelladora Ramos",
                "Avenida Benavides 234", "Miraflores", "Al costado del Atlantic City", ""));
        mRVClienteAdapter.add(new Cliente("Juan", "Perez", "987654321", "jperez@hotmail.com", "Galletera del Norte",
                "Avenida Industrial 123", "Trujillo", "Al costado de la plaza de armas", ""));

        rvCliente.setLayoutManager(new LinearLayoutManager(ClienteActivity.this));
        rvCliente.setAdapter(mRVClienteAdapter);
    }
}
