package com.example.pm1ejercicio24.Configuraciones;

public class BdFirmas {
    public static final String NAME_DATABASE = "signaturess";
    public static final String TABLE_FIRMA = "firmas";
    public static final String id = "id";
    public static final String descripcion = "descripcion";
    public static final String imagen = "imagen";

    public static final String CREATE_TABLE_FIRMA = "CREATE TABLE "+ TABLE_FIRMA + "(id INTEGER PRIMARY KEY AUTOINCREMENT,"+ "descripcion TEXT, imagen BLOB)";
    public static final String DROP_TABLE_FIRMA = "DROP TABLE IF EXISTS "+ TABLE_FIRMA;
}
