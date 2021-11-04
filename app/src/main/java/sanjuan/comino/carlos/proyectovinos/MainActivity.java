package sanjuan.comino.carlos.proyectovinos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btCrearVino;
    TextView tvVinos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    private void initialize() {

        btCrearVino = findViewById(R.id.btCrearVino);
        btCrearVino.setOnClickListener(this);
        tvVinos = findViewById(R.id.tvVinos);
        escribeVinos();

    }

    @Override
    public void onClick(View view) {
        Intent i = new Intent(this, crearActivity.class);
        startActivity(i);

    }

    private void escribeVinos(){
        String[] vinos = FileIO.getFileLines(getExternalFilesDir(null), getString(R.string.fileName));
        if (vinos != null){
            for (String linea : vinos) {
                Vino vino = Csv.getVino(linea);
            }
        }
    }
}