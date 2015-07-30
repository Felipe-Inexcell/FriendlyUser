package cl.felipes.friendlyuser;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.SeekBar;


public class Calidad1 extends Activity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    ImageButton b_back, b_send;
    CheckBox ch1a, ch1b, ch2a, ch2b;
    public static Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calidad1);
        activity = this;

        b_back = (ImageButton)this.findViewById(R.id.ib_back);
        b_send = (ImageButton)this.findViewById(R.id.ib_send);

        b_back.setOnClickListener(this);
        b_send.setOnClickListener(this);

        ch1a = (CheckBox)this.findViewById(R.id.checkBox1a);
        ch1b = (CheckBox)this.findViewById(R.id.checkBox1b);
        ch2a = (CheckBox)this.findViewById(R.id.checkBox2a);
        ch2b = (CheckBox)this.findViewById(R.id.checkBox2b);


        ch1a.setOnCheckedChangeListener(this);
        ch2a.setOnCheckedChangeListener(this);
        ch1b.setOnCheckedChangeListener(this);
        ch2b.setOnCheckedChangeListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ib_back:
                finish();
                break;
            case R.id.ib_send:
                startActivity(new Intent(this, Calidad2.class));
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
            case R.id.checkBox2a:
                if(isChecked){
                    ch2b.setChecked(false);
                }
                break;
            case R.id.checkBox2b:
                if(isChecked){
                    ch2a.setChecked(false);
                }
                break;
            default:
                break;
        }
    }
}
