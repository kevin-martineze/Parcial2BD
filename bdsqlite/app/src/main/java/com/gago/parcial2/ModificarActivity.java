package com.gago.parcial2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.gago.parcial2.BasedeDatos.DBControlador;
import com.gago.parcial2.Modelos.Servicio;

import java.util.ArrayList;
import java.util.Calendar;

public class ModificarActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvTitulo,tvUnidad;
    EditText edNombre, edCedula;
    Spinner spinnerEstrato,spinnerNivel_educativo;
    Button btRegistrar, btBuscar, btActualizar, btEliminar, btListadop;

    DBControlador controlador;

    int Estrato;
    int indice;
    int cedula;
    long id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        tvTitulo = findViewById(R.id.idTvTitulo);
        edCedula = findViewById(R.id.idCedula);
        edNombre = findViewById(R.id.idNombre);
        spinnerEstrato = findViewById(R.id.idSpinnerEstrato);
        spinnerNivel_educativo = findViewById(R.id.idSpinnerNivel_Educativo);

        btRegistrar = findViewById(R.id.idBtRegistrar);
        btBuscar = findViewById(R.id.idBtBuscar);
        btActualizar = findViewById(R.id.idBtActualizar);
        btEliminar = findViewById(R.id.idBtEliminar);
        btListadop = findViewById(R.id.idBtListado);

        tvTitulo.setText(getString(R.string.modificar_registro));

        controlador = new DBControlador(getApplicationContext());


        Intent i = getIntent();
        indice = i.getIntExtra("indice", 0);

        ArrayList<Servicio> lista = controlador.optenerRegistros();

        Servicio servicio = lista.get(indice);
        id = servicio.getId();







        ArrayAdapter<CharSequence> otroAdacter = ArrayAdapter.createFromResource(this
                , R.array.spinner_tipo_servicio,  R.layout.support_simple_spinner_dropdown_item);


            spinnerEstrato.setAdapter(otroAdacter);
        spinnerEstrato.setSelection(servicio.getTipoServicio());
        spinnerEstrato.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Estrato = position;
                switch (Estrato) {
                    case 0:
                        tvUnidad.setText("1");
                        break;
                    case 1:
                        tvUnidad.setText("2");
                        break;
                    case 2:
                        tvUnidad.setText("3");
                        break;
                    case 3:
                        tvUnidad.setText("4");
                        break;
                    case 4:
                        tvUnidad.setText("5");
                        break;
                    case 5:
                        tvUnidad.setText("6");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btActualizar.setOnClickListener(this);
        btBuscar.setOnClickListener(this);
        btEliminar.setOnClickListener(this);
        btRegistrar.setOnClickListener(this);
        btListadop.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.idBtRegistrar:

                Calendar calendar = Calendar.getInstance();
                try {
                    int cedula = edCedula.getText().toString().isEmpty() ? 0 : Integer.parseInt(edCedula.getText().toString());
                    Servicio servicio = new Servicio(edNombre.getText().toString(), calendar, cedula, cedula);
                    int retorno = controlador.actualizarRegistro(servicio);
                    if (retorno == 1) {
                        Toast.makeText(getApplicationContext(), "actualizacion exitosa", Toast.LENGTH_SHORT).show();
                        setResult(Activity.RESULT_OK);
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "fallo en la actualizacion", Toast.LENGTH_SHORT).show();
                    }
                } catch (NumberFormatException nuEx) {
                    Toast.makeText(getApplicationContext(), "numero muy grande", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.idBtEliminar:
                setResult(Activity.RESULT_CANCELED);
                finish();
                break;
        }
    }
}
