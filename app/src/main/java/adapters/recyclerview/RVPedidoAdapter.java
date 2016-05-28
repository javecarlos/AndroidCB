package adapters.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import adapters.recyclerview.interfaces.IRVPedidoAdapter;
import entities.PedidoRV;
import trabajofinalcb.javecarlos.pe.trabajofinalcb.R;

public class RVPedidoAdapter extends RecyclerView.Adapter<RVPedidoAdapter.RVPedidoViewHolder> {
    private ArrayList<PedidoRV> listaPedido;
    private IRVPedidoAdapter _mIRVPedidoAdapter;

    public RVPedidoAdapter(IRVPedidoAdapter mIRVPedidoAdapter) {
        listaPedido = new ArrayList<>();
        _mIRVPedidoAdapter = mIRVPedidoAdapter;
    }

    public void add(PedidoRV pedido) {
        listaPedido.add(pedido);
        notifyDataSetChanged();
    }

    public void addAll(ArrayList<PedidoRV> lstPedido) {
        int position = listaPedido.size();
        listaPedido.addAll(lstPedido);
        notifyItemRangeInserted(position, lstPedido.size());
    }

    public void clearAndAddAll(ArrayList<PedidoRV> lstPedido) {
        listaPedido.clear();
        listaPedido.addAll(lstPedido);
        notifyDataSetChanged();
    }

    @Override
    public RVPedidoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RVPedidoViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_pedido_rv, parent, false));
    }

    @Override
    public void onBindViewHolder(RVPedidoViewHolder holder, int position) {
        PedidoRV pedido = listaPedido.get(position);

        //holder.tvIdPedidoRV.setText(String.valueOf(pedido.getIdPedido()));
        holder.tvClienteRV.setText(pedido.getCliente());
        holder.tvProductoRV.setText(String.valueOf(pedido.getProducto())+" producto(s)");
        holder.tvTotalRV.setText("S/. "+String.valueOf(pedido.getTotal()));

        holder.itemView.setOnClickListener(itemViewOnClickListener);
        holder.itemView.setTag(position);
    }

    View.OnClickListener itemViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            PedidoRV pedido = listaPedido.get((Integer) v.getTag());
            _mIRVPedidoAdapter.onItemClick(pedido);
        }
    };

    @Override
    public int getItemCount() {
        return listaPedido.size();
    }

    static class RVPedidoViewHolder extends RecyclerView.ViewHolder {
        TextView tvIdPedidoRV, tvClienteRV, tvProductoRV, tvTotalRV;

        public RVPedidoViewHolder(View itemView) {
            super(itemView);

            tvClienteRV = (TextView) itemView.findViewById(R.id.tvClienteRV);
            tvProductoRV = (TextView) itemView.findViewById(R.id.tvProductoRV);
            //tvIdPedidoRV = (TextView) itemView.findViewById(R.id.tvIdPedidoRV);
            tvTotalRV = (TextView) itemView.findViewById(R.id.tvTotalRV);
        }
    }
}
