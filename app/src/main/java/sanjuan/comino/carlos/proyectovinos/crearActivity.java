package sanjuan.comino.carlos.proyectovinos;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class crearActivity extends AppCompatActivity {

    EditText etId, etNombre, etBodega, etColor, etOrigen, etGraduacion, etFecha;
    EditText[] campos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitdad_crea_vino);
        initialize();
    }

    private void initialize() {

        campos = new EditText[7];

        etId = findViewById(R.id.etid);
        campos[0] = etId;
        etNombre = findViewById(R.id.etNombre);
        campos[1] = etNombre;
        etBodega = findViewById(R.id.etBodega);
        campos[2] = etBodega;
        etColor = findViewById(R.id.etColor);
        campos[3] = etColor;
        etOrigen = findViewById(R.id.etOrigen);
        campos[4] = etOrigen;
        etGraduacion = findViewById(R.id.etGraduacion);
        campos[5] = etGraduacion;
        etFecha = findViewById(R.id.etFecha);
        campos[6] = etFecha;


        Button btCancel = findViewById(R.id.cancelbt);
        btCancel.setOnClickListener((View view) -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
        Button btnAñadir = findViewById(R.id.añadirbt);
        btnAñadir.setOnClickListener((View view) -> {
            CrearVino();
        });

    }

    private void CrearVino(){
        AlertDialog.Builder msgError = new AlertDialog.Builder(this);
        boolean error = false;
        String csv="";

        for (EditText et : campos) {
            if (et.getText().toString().isEmpty()){
                error = true;

                msgError.setTitle("Error");
                msgError.setMessage("Debe llenar todos los campos");
                msgError.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }});


            }else{
                csv += (et.getText().toString()+";");
            }

        }

        if(error) {
            msgError.create().show();
        }else{
            FileIO.writeLine(getExternalFilesDir(null), getString(R.string.fileName), csv);
            Toast.makeText(this, "Vino añadido",Toast.LENGTH_LONG).show();
        }

        }





    private Vino sacarVino(){
        Vino v = new Vino();
        v.setId(Long.parseLong(etId.getText().toString()));
        v.setNombre(etNombre.getText().toString());
        v.setOrigen(etOrigen.getText().toString());
        v.setBodega(etBodega.getText().toString());
        v.setColor(etColor.getText().toString());
        try {
            v.setGraduacion(Double.parseDouble(etGraduacion.getText().toString()));
        }
        catch (NumberFormatException e){
            return null;
        }
        try {
            v.setFecha(Integer.parseInt(etFecha.getText().toString()));
        }
        catch (NumberFormatException e){
            return null;
        }
        return v;
    }

}
