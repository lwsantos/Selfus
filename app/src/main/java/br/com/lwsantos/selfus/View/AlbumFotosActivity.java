package br.com.lwsantos.selfus.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

import br.com.lwsantos.selfus.Adapter.FotoAdapter;
import br.com.lwsantos.selfus.Control.FotoControl;
import br.com.lwsantos.selfus.Model.Album;
import br.com.lwsantos.selfus.Model.Foto;
import br.com.lwsantos.selfus.R;

public class AlbumFotosActivity extends AppCompatActivity {

    private Album mAlbum = new Album();
    private FotoAdapter mAdapter;
    private GridView mGrdFotos;
    private ArrayList<Foto> mListaFoto = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_fotos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_home_white_24dp);

        Intent it = this.getIntent();
        mAlbum = (Album) it.getSerializableExtra("album");
        mListaFoto = new FotoControl().getListaFotos(this, mAlbum.getAlbId(), 0);

        //Definir o titulo da Activity
        getSupportActionBar().setTitle(mAlbum.getAlbNome());


        mGrdFotos = (GridView) findViewById(R.id.grdFotos);

        loadFotos();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case android.R.id.home:
                this.onBackPressed();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Bundle params = data != null ? data.getExtras() : null;

        if(params != null)
        {
            Foto foto = (Foto) params.getSerializable("foto");

            if(requestCode == 1 && resultCode == RESULT_OK)
            {
                mListaFoto.add(foto);
                mAdapter.notifyDataSetChanged();
            }
            else if(requestCode == 2 && resultCode == RESULT_OK)
            {
                for(int i=0; i < mListaFoto.size(); i++)
                {
                    if(mListaFoto.get(i).getFotId() == foto.getFotId())
                    {
                        mListaFoto.remove(i);
                        break;
                    }
                }
                mAdapter.notifyDataSetChanged();
            }
        }
    }

    public void fabAdicionar_onClick(View view) {
        Intent it = new Intent(this, AlbumAddPhotoActivity.class);
        it.putExtra("album", mAlbum);
        startActivityForResult(it,1);
    }

    private void loadFotos()
    {
        //Define o adaptador com as fotos e monta o list view layout
        mAdapter = new FotoAdapter(this, mListaFoto);

        mGrdFotos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(getApplicationContext(), FotoDetalheActivity.class);
                Foto foto = mListaFoto.get(position);
                foto.setBitmap(null);
                it.putExtra("foto", foto);
                startActivityForResult(it,2);
            }
        });

        mGrdFotos.setAdapter(mAdapter);
    }

}
