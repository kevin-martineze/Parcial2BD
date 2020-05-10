package com.gago.parcial2.BasedeDatos;

/**
 * clase auxiliar que representa el modelo de la base de datos
 */
public class ModeloBD {

    public static final String NOMBRE_DB = "SQLite";
    public static final String NOMBRE_TABLA = "registros";
    public static final String COL_CEDULA = "cedula";
    public static final String COL_NOMBRE = "nombre";
    public static final String COL_ESTRATO = "estrato";
    public static final String COL_SALARIO = "salario";
    public static final String COL_NIVEL_EDUCATIVO = "nivel_educativo";

    public static final String CREAR_TABLA_REGISTROS = "CREATE TABLE " +
            "" + NOMBRE_TABLA + " ( " + COL_CEDULA + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            " " + COL_NOMBRE + " TEXT, " + COL_ESTRATO + " INTEGER, " +
            " " + COL_SALARIO + " INTEGER, " + COL_NIVEL_EDUCATIVO + " TEXT)";

}
