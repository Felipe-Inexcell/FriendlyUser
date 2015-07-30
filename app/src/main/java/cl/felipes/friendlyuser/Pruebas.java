package cl.felipes.friendlyuser;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;


public class Pruebas extends Activity implements View.OnClickListener {
    ImageButton b_back, b_send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pruebas);

        b_back = (ImageButton)this.findViewById(R.id.ib_back);

        b_back.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ib_back:
                finish();
                break;
            default:
                break;
        }
    }
}
