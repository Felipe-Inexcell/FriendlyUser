package cl.felipes.friendlyuser;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;

import cl.felipes.friendlyuser.extras.Constantes;
import cl.felipes.friendlyuser.extras.CustomStateDialog;
import cl.felipes.friendlyuser.extras.DialogTimeoutOperation;


public class TrazabilidadMovil extends Activity implements View.OnClickListener {
    ImageButton b_back, b_send;
    View content;
    SeekBar bar1;
    public static Activity actividad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trazabilidad_movil);
        actividad = this;

        bar1 = (SeekBar)this.findViewById(R.id.seekBar);
        bar1.setEnabled(false);

        content = this.findViewById(R.id.content);
        b_back = (ImageButton)this.findViewById(R.id.ib_back);
        b_send = (ImageButton)this.findViewById(R.id.ib_send);

        b_back.setOnClickListener(this);
        b_send.setOnClickListener(this);



    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ib_back:
                finish();
                break;
            case R.id.ib_send:
                startActivity(new Intent(this, TrazabilidadWIFI.class));
                break;
        }
    }
}
