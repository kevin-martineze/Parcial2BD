package com.gago.parcial2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.gago.parcial2.BasedeDatos.DBControlador;
import com.gago.parcial2.Modelos.Servicio;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    DBControlador controlador;

    Spinner spEstrato;
    Spinner spNivel_educativo;
    EditText edCedula, edNombre, edSalario;
    TextView tvUnidad;

    Button btRegistrar, btBuscar, btActualizar, btEliminar, btListadop;
    int Estrato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        spEstrato = findViewById(R.id.idSpinnerEstrato);
        spNivel_educativo = findViewById(R.id.idSpinnerNivel_Educativo);
        edCedula = findViewById(R.id.idCedula);
        edNombre = findViewById(R.id.idNombre);
        edSalario = findViewById(R.id.idSalario);
        btRegistrar = findViewById(R.id.idBtRegistrar);
        btBuscar = findViewById(R.id.idBtBuscar);
        btActualizar = findViewById(R.id.idBtActualizar);
        btEliminar = findViewById(R.id.idBtEliminar);


        controlador = new DBControlador(getApplicationContext());

        ArrayAdapter<CharSequence> otroAdacter = ArrayAdapter.createFromResource(this
                , R.array.spinner_tipo_servicio, R.layout.support_simple_spinner_dropdown_item);
        spEstrato.setAdapter(otroAdacter);
        spNivel_educativo.setAdapter(otroAdacter);

        spEstrato.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

        btRegistrar.setOnClickListener(this);
        btActualizar.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.idBtRegistrar:
                Calendar calendar = Calendar.getInstance();
                try {
                    int cedula = edCedula.getText().toString().isEmpty() ? 0 : Integer.parseInt(edCedula.getText().toString());
                    Servicio servicio = new Servicio(edNombre.getText().toString(), calendar, cedula, Estrato);
                    long retorno = controlador.agregarRegistro(servicio);
                    if (retorno != -1) {
                        Toast.makeText(v.getContext(), "registro guardado", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(v.getContext(), "registro fallido", Toast.LENGTH_SHORT).show();
                    }
                    Eliminar();
                } catch (NumberFormatException numEx) {
                    Toast.makeText(getApplicationContext(), "numero muy grande", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.idBtEliminar:
                Eliminar();
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_listado) {
            Intent i = new Intent(this, ListadoActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void Eliminar() {
        edCedula.setText("");
        edNombre.setText("");
        edSalario.setText("");
    }

}
