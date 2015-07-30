package cl.felipes.friendlyuser;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


public class Servicios extends Activity implements View.OnClickListener {
    Button b_averia, b_calidad, b_traabilidad, b_pruebas;
    ImageButton b_off;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicios);

        b_averia = (Button)this.findViewById(R.id.b_averia);
        b_calidad = (Button)this.findViewById(R.id.b_calidad);
        b_traabilidad = (Button)this.findViewById(R.id.b_trazabilidad);
        b_pruebas = (Button)this.findViewById(R.id.b_pruebas);
        b_off = (ImageButton)this.findViewById(R.id.ib_back);

        b_averia.setOnClickListener(this);
        b_calidad.setOnClickListener(this);
        b_traabilidad.setOnClickListener(this);
        b_pruebas.setOnClickListener(this);
        b_off.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ib_back:
                finish();
                break;
            case R.id.b_averia:
                startActivity(new Intent(this, NotificarAveria.class));
                break;
            case R.id.b_trazabilidad:
                startActivity(new Intent(this, TrazabilidadMovil.class));
                break;
            case R.id.b_pruebas:
                startActivity(new Intent(this, Pruebas.class));
                break;
            case R.id.b_calidad:
                startActivity(new Intent(this, Calidad1.class));
                break;
            default:
                break;
        }
    }
}
