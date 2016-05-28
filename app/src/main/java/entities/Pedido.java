package entities;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Pedido implements Parcelable {
    public int IdPedido;
    public Date feCreacion;
    public int prPrecio;
    public int caCantidad;
    public int esEstado;
    public int idProducto;
    public int idCliente;

    public Pedido() {
    }

    public int getIdPedido() {
        return IdPedido;
    }

    public void setIdPedido(int idPedido) {
        IdPedido = idPedido;
    }

    public Date getFeCreacion() {
        return feCreacion;
    }

    public void setFeCreacion(Date feCreacion) {
        this.feCreacion = feCreacion;
    }

    public int getPrPrecio() {
        return prPrecio;
    }

    public void setPrPrecio(int prPrecio) {
        this.prPrecio = prPrecio;
    }

    public int getCaCantidad() {
        return caCantidad;
    }

    public void setCaCantidad(int caCantidad) {
        this.caCantidad = caCantidad;
    }

    public int getEsEstado() {
        return esEstado;
    }

    public void setEsEstado(int esEstado) {
        this.esEstado = esEstado;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    protected Pedido(Parcel in) {
        IdPedido = in.readInt();
        long tmpFeCreacion = in.readLong();
        feCreacion = tmpFeCreacion != -1 ? new Date(tmpFeCreacion) : null;
        prPrecio = in.readInt();
        caCantidad = in.readInt();
        esEstado = in.readInt();
        idProducto = in.readInt();
        idCliente = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(IdPedido);
        dest.writeLong(feCreacion != null ? feCreacion.getTime() : -1L);
        dest.writeInt(prPrecio);
        dest.writeInt(caCantidad);
        dest.writeInt(esEstado);
        dest.writeInt(idProducto);
        dest.writeInt(idCliente);
    }

    @SuppressWarnings("unused")
    public static final Creator<Pedido> CREATOR = new Creator<Pedido>() {
        @Override
        public Pedido createFromParcel(Parcel in) {
            return new Pedido(in);
        }

        @Override
        public Pedido[] newArray(int size) {
            return new Pedido[size];
        }
    };
}
