package com.gago.parcial2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleObserver;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.gago.parcial2.BasedeDatos.DBControlador;
import com.gago.parcial2.Modelos.ListaAdapter;
import com.gago.parcial2.Modelos.Servicio;

import java.util.ArrayList;

public class ListadoActivity extends AppCompatActivity implements LifecycleObserver {

    ListView lista;

    ArrayList<Servicio> listaServicios;

    DBControlador controlador;

    ListaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);

        lista = findViewById(R.id.idListaServicios);

        controlador = new DBControlador(getApplicationContext());

        listaServicios = controlador.optenerRegistros();

        adapter = new ListaAdapter(getApplicationContext(), R.layout.lista_item_layout, listaServicios);
        lista.setAdapter(adapter);

        registerForContextMenu(lista);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2) {
            if (resultCode == Activity.RESULT_OK) {
                ArrayList<Servicio> listaServis = controlador.optenerRegistros();
                ListaAdapter adap = new ListaAdapter(getApplicationContext(), R.layout.lista_item_layout, listaServis);
                lista.setAdapter(adap);
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(getApplicationContext(), "modificacion cancelada", Toast.LENGTH_SHORT).show();
            }
        }
    }

    //menu contextual para los items de la lista
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.listado_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()) {
            case R.id.idModificarRegistroMenu:
                modificarRegistro(menuInfo.position);
                return true;
            case R.id.idBorrarRegistroMenu:
                borrarRegistro(menuInfo.position);
                return true;
        }
        return super.onContextItemSelected(item);
    }

    private void modificarRegistro(int posicion) {
        Intent intent = new Intent(this, ModificarActivity.class);
        intent.putExtra("indice", posicion);
        startActivityForResult(intent, 2);
    }

    private void borrarRegistro(int posicion) {
        int retorno = controlador.borrarRegistro(listaServicios.get(posicion));
        if (retorno == 1) {
            Toast.makeText(getApplicationContext(), "registro eliminado", Toast.LENGTH_SHORT).show();
            listaServicios = controlador.optenerRegistros();
            adapter = new ListaAdapter(getApplicationContext(), R.layout.lista_item_layout, listaServicios);
            lista.setAdapter(adapter);
        } else {
            Toast.makeText(getApplicationContext(), "error al borrar", Toast.LENGTH_SHORT).show();
        }
    }
}
