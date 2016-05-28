package dao;

import android.content.ContentValues;
import android.database.Cursor;

import java.sql.Date;
import java.util.ArrayList;

import entities.Pedido;
import entities.PedidoRV;

public class PedidoDAO {
    public ArrayList<PedidoRV> listPedido() {
        Cursor cursor = DataBaseSingleton.getInstance().rawQuery(
                        "Select ID_PEDIDO, NO_E_EMPRESA As Cliente, " +
                        "       Count(ID_PRODUCTO) As Producto,Sum(NU_PRECIO) As Total " +
                        "From EC_PEDIDO ped " +
                        "  Inner Join EC_CLIENTES c ON ped.ID_CLIENTE= c.ID_CLIENTE " +
                        "Where ped.ES_ESTADO=1 " +
                        "Group By ID_PEDIDO, c.ID_CLIENTE || ' - ' || NO_NOMBRES || ' ' || NO_APELLIDOS",null);

        ArrayList<PedidoRV> lstPedido = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                lstPedido.add(transformCursorToPedidoRV(cursor));
            } while (cursor.moveToNext());
        }

        if (cursor != null && !cursor.isClosed())
            cursor.close();

        return lstPedido;
    }

    public Pedido getPedido(Pedido pedido) {
        Cursor cursor = null;
        try {
            cursor = DataBaseSingleton.getInstance().query("EC_PEDIDOS", null, "ID_PEDIDO = ?", new String[]{String.valueOf(pedido.getIdPedido())}, null, null, null, "1");

            if (cursor.moveToFirst())
                pedido = transformCursorToPedido(cursor);
            else
                pedido = null;

        } catch (Exception ex) {
            ex.printStackTrace();
            pedido = null;
        } finally {
            if (cursor != null && !cursor.isClosed())
                cursor.close();
            return pedido;
        }
    }

    public boolean insertPedido(Pedido pedido) {
        ContentValues cv = new ContentValues();
        cv.put("id_pedido", pedido.getIdPedido());
        cv.put("FE_CREACION", String.valueOf(pedido.getFeCreacion()));
        cv.put("PR_PRECIO", pedido.getPrPrecio());
        cv.put("CA_CANTIDAD", pedido.getCaCantidad());
        cv.put("ES_ESTADO", pedido.getEsEstado());
        cv.put("ID_PRODUCTO", pedido.getIdProducto());
        cv.put("ID_CLIENTE", pedido.getIdCliente());

        long inserto = DataBaseSingleton.getInstance().insert("EC_PEDIDOS", null, cv);

        return inserto != -1;
    }

    public boolean updateCliente(Pedido pedido) {
        ContentValues cv = new ContentValues();
        cv.put("ID_PEDIDOS", pedido.getIdPedido());
        cv.put("FE_CREACION", String.valueOf(pedido.getFeCreacion()));
        cv.put("PR_PRECIO", pedido.getPrPrecio());
        cv.put("CA_CANTIDAD", pedido.getCaCantidad());
        cv.put("ES_ESTADO", pedido.getEsEstado());
        cv.put("ID_PRODUCTO", pedido.getIdProducto());
        cv.put("ID_CLIENTE", pedido.getIdCliente());

        int update = DataBaseSingleton.getInstance().update("EC_PEDIDOS", cv, "ID_PEDIDO = ?", new String[]{String.valueOf(pedido.getIdPedido())});

        return update > 0;
    }

    public boolean deletePedido(Pedido pedido) {
        int delete = DataBaseSingleton.getInstance().delete("EC_PEDIDOS", "ID_PEDIDO = ?", new String[]{String.valueOf(pedido.getIdPedido())});

        return delete > 0;
    }

    private PedidoRV transformCursorToPedidoRV(Cursor cursor) {
        PedidoRV pedido = new PedidoRV();
        pedido.setIdPedido(cursor.getInt(cursor.getColumnIndex("ID_PEDIDO")));
        pedido.setCliente(cursor.getString(cursor.getColumnIndex("Cliente")));
        pedido.setProducto(cursor.getInt(cursor.getColumnIndex("Producto")));
        pedido.setTotal(cursor.getDouble(cursor.getColumnIndex("Total")));

        return pedido;
    }
    private Pedido transformCursorToPedido(Cursor cursor) {
        Pedido pedido = new Pedido();
        pedido.setIdPedido(cursor.getInt(cursor.getColumnIndex("ID_PEDIDO")));
        pedido.setFeCreacion(new Date(cursor.getLong(cursor.getColumnIndex("FE_CREACION"))));
        pedido.setPrPrecio(cursor.getInt(cursor.getColumnIndex("PR_PRECIO")));
        pedido.setCaCantidad(cursor.getInt(cursor.getColumnIndex("CA_CANTIDAD")));
        pedido.setEsEstado(cursor.getInt(cursor.getColumnIndex("ES_ESTADO")));
        pedido.setIdProducto(cursor.getInt(cursor.getColumnIndex("ID_PRODUCTO")));
        pedido.setIdCliente(cursor.getInt(cursor.getColumnIndex("ID_CLIENTE")));

        return pedido;
    }

}
