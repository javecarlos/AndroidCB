package adapters.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import adapters.recyclerview.interfaces.IRVMenuAdapter;
import entities.Menu;
import trabajofinalcb.javecarlos.pe.trabajofinalcb.R;

/**
 * Created by carlosarmando on 04/05/2016.
 */
public class RVMenuAdapter extends RecyclerView.Adapter<RVMenuAdapter.RVMenuViewHolder> {
    private ArrayList<Menu> listaMenu;
    private IRVMenuAdapter _mIRVMenuAdapter;

    public RVMenuAdapter(IRVMenuAdapter mIRVMenuAdapter) {
        listaMenu = new ArrayList<>();
        _mIRVMenuAdapter = mIRVMenuAdapter;
    }

    public void add(Menu menu) {
        listaMenu.add(menu);
        notifyDataSetChanged();
    }

    @Override
    public RVMenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RVMenuViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_menu, parent, false));
    }

    @Override
    public void onBindViewHolder(RVMenuViewHolder holder, int position) {
        Menu menu = listaMenu.get(position);

        holder.tvDescripcion.setText(menu.getDescripcion());

        switch (menu.getId()) {
            case 1:
                holder.imgIcono.setImageResource(R.drawable.ic_accessibility_black_24dp);
                break;
            case 2:
                holder.imgIcono.setImageResource(R.drawable.ic_view_stream_black_24dp);
                break;
            case 3:
                holder.imgIcono.setImageResource(R.drawable.ic_shopping_cart_black_24dp);
                break;
            case 4:
                holder.imgIcono.setImageResource(R.drawable.ic_power_settings_new_black_24dp);
                break;
            default:
                holder.imgIcono.setImageResource(R.drawable.ic_accessibility_black_24dp);
                break;
        }

        /*Evento Click al seleccionar el menu*/
        holder.itemView.setOnClickListener(itemViewOnClickListener);
        holder.itemView.setTag(position);
    }

    /*Evento Click al seleccionar el menu*/
    View.OnClickListener itemViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            _mIRVMenuAdapter.onItemClick(listaMenu.get((Integer) v.getTag()));
        }
    };

    @Override
    public int getItemCount() {
        return listaMenu.size();
    }

    static class RVMenuViewHolder extends RecyclerView.ViewHolder {
        ImageView imgIcono;
        TextView tvDescripcion;

        public RVMenuViewHolder(View itemView) {
            super(itemView);

            imgIcono = (ImageView) itemView.findViewById(R.id.imgIcono);
            tvDescripcion = (TextView) itemView.findViewById(R.id.tvDescripcion);
        }
    }
}
