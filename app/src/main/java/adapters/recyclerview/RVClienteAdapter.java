package adapters.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;

import entities.Cliente;
import trabajofinalcb.javecarlos.pe.trabajofinalcb.R;

/**
 * Created by carlosarmando on 07/05/2016.
 */
public class RVClienteAdapter extends RecyclerView.Adapter<RVClienteAdapter.RVClienteViewHolder> {
    private ArrayList<Cliente> listaCliente;

    public RVClienteAdapter() {
        listaCliente = new ArrayList<>();
    }

    public void add(Cliente cliente) {
        listaCliente.add(cliente);
        notifyDataSetChanged();
    }

    @Override
    public RVClienteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RVClienteViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_cliente_rv, parent, false));
    }

    @Override
    public void onBindViewHolder(RVClienteViewHolder holder, int position) {
        Cliente cliente = listaCliente.get(position);

        holder.tvNombreClienteRV.setText(cliente.getEmpresaNombre());
        holder.tvDireccionClienteRV.setText(cliente.getEmpresaDireccion());
        holder.tvTelefonoClienteRV.setText(cliente.getContactoTelefono());
    }

    @Override
    public int getItemCount() {
        return listaCliente.size();
    }

    static class RVClienteViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombreClienteRV, tvDireccionClienteRV, tvTelefonoClienteRV;

        public RVClienteViewHolder(View itemView) {
            super(itemView);

            tvNombreClienteRV = (TextView) itemView.findViewById(R.id.tvNombreClienteRV);
            tvDireccionClienteRV = (TextView) itemView.findViewById(R.id.tvDireccionClienteRV);
            tvTelefonoClienteRV = (TextView) itemView.findViewById(R.id.tvTelefonoClienteRV);
        }
    }
}
