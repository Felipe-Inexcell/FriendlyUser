package cl.felipes.friendlyuser;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import cl.felipes.friendlyuser.extras.Constantes;
import cl.felipes.friendlyuser.extras.CustomStateDialog;
import cl.felipes.friendlyuser.extras.DialogTimeoutOperation;
import cl.felipes.friendlyuser.extras.Utilities;


public class NotificarAveria extends Activity implements View.OnClickListener {
    ImageButton b_camera, b_back, b_send;
    Spinner spinner1, spinner2;

    private String name;
    private Bitmap b = null, bmini = null;
    private static int TAKE_PICTURE = 1;
    private static int SELECT_PICTURE = 2;
    final CharSequence[] opcionCaptura = {
            "Tomar Fotografía"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_averia);

        name = Environment.getExternalStorageDirectory() + "/FRIENDLY USER/averia.jpg";

        spinner1 = (Spinner)this.findViewById(R.id.spinner1);
        spinner2 = (Spinner)this.findViewById(R.id.spinner2);
        b_camera = (ImageButton)this.findViewById(R.id.ib_camera);
        b_back = (ImageButton)this.findViewById(R.id.ib_back);
        b_send = (ImageButton)this.findViewById(R.id.ib_send);

        b_camera.setOnClickListener(this);
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
                Log.d("AVERIA", "SEND");
                //Obtenemos Datos del formulario
                final String sin = spinner1.getSelectedItem().toString();
                if(sin == null || sin.length() == 0){
                    return;
                }
                final String elem = spinner2.getSelectedItem().toString();
                if(elem == null || elem.length() == 0){
                    return;
                }
                String comen = ((TextView)findViewById(R.id.t_comentario)).getText().toString();
                if(comen.length() == 0){
                    Toast.makeText(this, "Ingrese Comentario", Toast.LENGTH_LONG).show();
                    return;
                }

                if(b == null) {
                    Toast.makeText(this, "Tome una fotografia.", Toast.LENGTH_LONG).show();
                    return;
                }

                //Debemos Checkear que este lo "no" opcional

                final Dialog dialog = new Dialog(this);
                dialog.setContentView(R.layout.dialog_averia);
                dialog.setTitle("¿Todo Correcto?");

                // set the custom dialog components - text, image and button
                TextView elemento = (TextView) dialog.findViewById(R.id.dialogaveria_elemento);
                TextView siniestro = (TextView) dialog.findViewById(R.id.dialogaveria_siniestro);
                TextView comentario = (TextView) dialog.findViewById(R.id.dialogaveria_comentario);
                elemento.setText(elem);
                siniestro.setText(sin);
                comentario.setText(comen);
                ImageView image = (ImageView) dialog.findViewById(R.id.dialogaveria_captura);
                image.setImageBitmap(b);

                ImageButton dialogOk = (ImageButton) dialog.findViewById(R.id.dialogaveria_ok);
                ImageButton dialogNok = (ImageButton) dialog.findViewById(R.id.dialogaveria_nok);
                // if button is clicked, close the custom dialog
                dialogOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        CustomStateDialog d = new CustomStateDialog(NotificarAveria.this,R.drawable.ic_state_ok,"NOTIFICACIÓN ENVIADA", Constantes.STATE_OK);
                        DialogTimeoutOperation t = new DialogTimeoutOperation(NotificarAveria.this,d,Constantes.TIME_LONG);
                        t.execute();
                    }
                });

                dialogNok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
                break;
            case R.id.ib_camera:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Escoja una Opcion:");
                builder.setIcon(android.R.drawable.ic_menu_camera);
                builder.setItems(opcionCaptura, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        Intent intent =  new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        int code = TAKE_PICTURE;
                        if (item==TAKE_PICTURE) {
                            Uri output = Uri.fromFile(new File(name));
                            intent.putExtra(MediaStore.EXTRA_OUTPUT, output);
                        } else if (item==SELECT_PICTURE){
                            intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                            code = SELECT_PICTURE;
                        }
                        startActivityForResult(intent, code);
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
                break;
            default:
                Log.d("AVERIA", "NADA CLICKEADO");
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == TAKE_PICTURE) {
            if (data != null) {
                if (data.hasExtra("data")) {
                    b = data.getParcelableExtra("data");
                }
            } else {
                b = BitmapFactory.decodeFile(name);

            }
        } else if (requestCode == SELECT_PICTURE){
            Uri selectedImage = data.getData();
            InputStream is;
            try {
                is = getContentResolver().openInputStream(selectedImage);
                BufferedInputStream bis = new BufferedInputStream(is);
                b = BitmapFactory.decodeStream(bis);

            } catch (FileNotFoundException e) {e.printStackTrace();}
        }
        try{
            //b = Bitmap.createScaledBitmap(b, 640, 480, true);
            bmini = Bitmap.createScaledBitmap(b, 64, 64, true);
            Toast.makeText(this, "Imagen Almacenada", Toast.LENGTH_SHORT).show();
        }catch(Exception ex){ex.printStackTrace();}


    }
}
