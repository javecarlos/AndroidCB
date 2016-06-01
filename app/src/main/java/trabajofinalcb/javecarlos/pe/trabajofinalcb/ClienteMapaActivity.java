package trabajofinalcb.javecarlos.pe.trabajofinalcb;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import entities.Cliente;
import utils.Constantes;

/**
 * Created by carlosarmando on 31/05/2016.
 */
public class ClienteMapaActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mGoogleMap;
    private Cliente cliente;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_mapa);

        //Obtenego el fragmento del tipo mama declarado en mi Layout
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.fragMap);

        //Le digo al fragmento mapa que cuando ya esté listo me avise
        mapFragment.getMapAsync(ClienteMapaActivity.this);

        cliente = ObtenerCliente();
    }

    private Cliente ObtenerCliente() {
        Cliente clienteInfo;
        if (getIntent().getExtras() != null && getIntent().getExtras().containsKey(Constantes.ARG_CLIENTE)) {
            clienteInfo = getIntent().getParcelableExtra(Constantes.ARG_CLIENTE);
        } else {
            clienteInfo = null;
        }

        return clienteInfo;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.mGoogleMap = googleMap;
        //googleMap.getUiSettings().setAllGesturesEnabled(false);
        setMarker();
    }

    private void setMarker(){
        if (cliente != null){
            mGoogleMap.clear();

            LatLng marker = new LatLng(Double.parseDouble(cliente.getEmpresaLatitud()),Double.parseDouble(cliente.getEmpresaLongitud()));

            mGoogleMap.addMarker(new MarkerOptions().position(marker));
            mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(marker, 17));
        }
    }
}
