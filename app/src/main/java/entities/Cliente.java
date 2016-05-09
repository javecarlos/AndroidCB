package entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by carlosarmando on 05/05/2016.
 */
public class Cliente implements Parcelable {
    private String ContactoNombre;
    private String ContactoApellido;
    private String ContactoTelefono;
    private String ContactoCorreo;
    private String EmpresaNombre;
    private String EmpresaDireccion;
    private String EmpresaDistrito;
    private String EmpresaReferencia;
    private String EmpresaMap;

    public Cliente() {

    }

    public Cliente(String contactoNombre, String contactoApellido, String contactoTelefono, String contactoCorreo,
                   String empresaNombre, String empresaDireccion, String empresaDistrito, String empresaReferencia,
                   String empresaMap) {
        this.ContactoNombre = contactoNombre;
        this.ContactoApellido = contactoApellido;
        this.ContactoTelefono = contactoTelefono;
        this.ContactoCorreo = contactoCorreo;
        this.EmpresaNombre = empresaNombre;
        this.EmpresaDireccion = empresaDireccion;
        this.EmpresaDistrito = empresaDistrito;
        this.EmpresaReferencia = empresaReferencia;
        this.EmpresaMap = empresaMap;
    }

    public String getContactoNombre() {
        return ContactoNombre;
    }

    public void setContactoNombre(String contactoNombre) {
        ContactoNombre = contactoNombre;
    }

    public String getContactoApellido() {
        return ContactoApellido;
    }

    public void setContactoApellido(String contactoApellido) {
        ContactoApellido = contactoApellido;
    }

    public String getContactoTelefono() {
        return ContactoTelefono;
    }

    public void setContactoTelefono(String contactoTelefono) {
        ContactoTelefono = contactoTelefono;
    }

    public String getContactoCorreo() {
        return ContactoCorreo;
    }

    public void setContactoCorreo(String contactoCorreo) {
        ContactoCorreo = contactoCorreo;
    }

    public String getEmpresaNombre() {
        return EmpresaNombre;
    }

    public void setEmpresaNombre(String empresaNombre) {
        EmpresaNombre = empresaNombre;
    }

    public String getEmpresaDireccion() {
        return EmpresaDireccion;
    }

    public void setEmpresaDireccion(String empresaDireccion) {
        EmpresaDireccion = empresaDireccion;
    }

    public String getEmpresaDistrito() {
        return EmpresaDistrito;
    }

    public void setEmpresaDistrito(String empresaDistrito) {
        EmpresaDistrito = empresaDistrito;
    }

    public String getEmpresaReferencia() {
        return EmpresaReferencia;
    }

    public void setEmpresaReferencia(String empresaReferencia) {
        EmpresaReferencia = empresaReferencia;
    }

    public String getEmpresaMap() {
        return EmpresaMap;
    }

    public void setEmpresaMap(String empresaMap) {
        EmpresaMap = empresaMap;
    }

    protected Cliente(Parcel in) {
        ContactoNombre = in.readString();
        ContactoApellido = in.readString();
        ContactoTelefono = in.readString();
        ContactoCorreo = in.readString();
        EmpresaNombre = in.readString();
        EmpresaDireccion = in.readString();
        EmpresaDistrito = in.readString();
        EmpresaReferencia = in.readString();
        EmpresaMap = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ContactoNombre);
        dest.writeString(ContactoApellido);
        dest.writeString(ContactoTelefono);
        dest.writeString(ContactoCorreo);
        dest.writeString(EmpresaNombre);
        dest.writeString(EmpresaDireccion);
        dest.writeString(EmpresaDistrito);
        dest.writeString(EmpresaReferencia);
        dest.writeString(EmpresaMap);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Cliente> CREATOR = new Parcelable.Creator<Cliente>() {
        @Override
        public Cliente createFromParcel(Parcel in) {
            return new Cliente(in);
        }

        @Override
        public Cliente[] newArray(int size) {
            return new Cliente[size];
        }
    };
}
