package com.gago.parcial2.BasedeDatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

/**
 * clase que hereda de SQLiteOpenHelper que controla la base de datos
 */
public class DBHelper extends SQLiteOpenHelper {

    /**
     * constructor que crea la base de datos
     * @param context contexto de la actividad
     * @param name nombre de la base de datos
     * @param factory cursor
     * @param version version de la base de datos
     */
    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    /**
     * metodo para crear las tablas de la base de datos
     * @param db objeto clase SQLiteDatabase
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ModeloBD.CREAR_TABLA_REGISTROS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
