package br.com.lwsantos.selfus.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.lwsantos.selfus.Control.FotoControl;
import br.com.lwsantos.selfus.Model.Foto;
import br.com.lwsantos.selfus.R;

public class FotoDetalheActivity extends AppCompatActivity {

    private Foto mFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foto_detalhe);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //Definir o titulo da Activity
        getSupportActionBar().setTitle("Olha a foto!! ;-)");

        Intent it = this.getIntent();
        mFoto = (Foto) it.getSerializableExtra("foto");

        ImageView imgFoto = (ImageView) findViewById(R.id.imgFoto);
        imgFoto.setAdjustViewBounds(true);
        imgFoto.setImageBitmap(mFoto.getBitmap().bitmap);

        TextView lblMensagem = (TextView) findViewById(R.id.lblMensagem);
        lblMensagem.setText(mFoto.getFotMensagem());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_foto_detalhe, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case android.R.id.home:
                this.onBackPressed();
                break;
            case R.id.action_delete:
                new FotoControl().delFoto(this, mFoto.getFotId());
                mFoto.setBitmap(null);
                Intent it = new Intent();
                it.putExtra("foto", mFoto);
                setResult(RESULT_OK, it);
                finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
