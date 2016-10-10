package br.com.lwsantos.selfus.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import br.com.lwsantos.selfus.Control.AlbumControl;
import br.com.lwsantos.selfus.Model.Album;
import br.com.lwsantos.selfus.R;

public class AlbumCreateActivity extends AppCompatActivity {

    private Album mAlbum = new Album();
    private EditText mTxtTitulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_create);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_home_white_24dp);

        //Definir o titulo da Activity
        getSupportActionBar().setTitle("Criar meu álbum :-)");

        mTxtTitulo = (EditText) findViewById(R.id.txtTitulo);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_albuns_create, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {

            this.onBackPressed();

        }
        else if (id == R.id.action_concluir)
        {
            salvar();
            Intent it = new Intent(this, AlbumFotosActivity.class);
            it.putExtra("album", mAlbum);
            startActivity(it);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    public void salvar() {

        mAlbum.setAlbNome(mTxtTitulo.getText().toString());

        // Adiciona o produto na base de dados e na propria lista
        long id = new AlbumControl().salvar(getApplicationContext(), mAlbum);
        mAlbum.setAlbId(id);
    }
}
