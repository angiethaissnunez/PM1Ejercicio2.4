package com.example.pm1ejercicio24;

public class DatosFirm {
Integer id;
    String Descripcion;
    String imagen;

    public DatosFirm(){

    }

    public DatosFirm(Integer id, String descripcion, String imagen) {
        this.id = id;
        Descripcion = descripcion;
        this.imagen = imagen;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
