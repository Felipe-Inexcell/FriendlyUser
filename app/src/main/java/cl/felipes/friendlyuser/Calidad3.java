package cl.felipes.friendlyuser;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;

import java.util.ArrayList;

import cl.felipes.friendlyuser.extras.Constantes;
import cl.felipes.friendlyuser.extras.CustomStateDialog;
import cl.felipes.friendlyuser.extras.DialogTimeoutOperation;


public class Calidad3 extends Activity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    ImageButton b_back, b_send;
    CheckBox ch1a, ch1b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calidad3);
        b_back = (ImageButton)this.findViewById(R.id.ib_back);
        b_send = (ImageButton)this.findViewById(R.id.ib_send);

        b_back.setOnClickListener(this);
        b_send.setOnClickListener(this);

        ch1a = (CheckBox)this.findViewById(R.id.checkBox1a);
        ch1b = (CheckBox)this.findViewById(R.id.checkBox1b);


        ch1a.setOnCheckedChangeListener(this);
        ch1b.setOnCheckedChangeListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ib_back:
                finish();
                break;
            case R.id.ib_send:
                ArrayList<Activity> a = new ArrayList<>();
                a.add(Calidad1.activity);
                a.add(Calidad2.activity);
                CustomStateDialog d = new CustomStateDialog(this,R.drawable.ic_state_ok,"DATOS ENVIADOS", Constantes.STATE_OK);
                DialogTimeoutOperation t = new DialogTimeoutOperation(this,d,Constantes.TIME_LONG, a);
                t.execute();
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton b, boolean isChecked) {
        switch (b.getId()){
            case R.id.checkBox1a:
                if(isChecked){
                    ch1b.setChecked(false);
                }
                break;
            case R.id.checkBox1b:
                if(isChecked){
                    ch1a.setChecked(false);
                }
                break;
            default:
                break;
        }
    }
}
