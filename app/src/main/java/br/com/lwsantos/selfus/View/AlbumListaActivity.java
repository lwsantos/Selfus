package br.com.lwsantos.selfus.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;

import br.com.lwsantos.selfus.Adapter.AlbumAdapter;
import br.com.lwsantos.selfus.Control.AlbumControl;
import br.com.lwsantos.selfus.Model.Album;
import br.com.lwsantos.selfus.R;

public class AlbumListaActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManeger;

    private ArrayList<Album> mListaAlbum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.icon);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Selfus - Meus álbuns :-)");

        mRecyclerView = (RecyclerView) findViewById(R.id.rvwAlbum);
        // Se é conhecido que o tamanho do layout do RecyclerView não altera (height = match_parent)
        // mesmo que a quantidade de itens for desconhecido
        // defina o SetHasFixedSize como true para melhorar o desempenho do componente
        mRecyclerView.setHasFixedSize(true);

        // Os possíveis gerenciadores de layout são:
        // - LinearLayoutManager: exibe os itens em lista de forma linear na horizontal ou vertical
        // - GridLayoutManager: exibe os itens em grade
        // - StaggeredGridLayoutManager: exibe os itens em uma grade escalonada
        mLayoutManeger = new LinearLayoutManager(this);
        ((LinearLayoutManager)mLayoutManeger).setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManeger);
    }

    @Override
    protected void onResume() {
        super.onResume();

        mListaAlbum = new AlbumControl().getListaAlbum(this);

        //O Adapter é responsável por pegar os dados e montar os itens da lista
        mAdapter = new AlbumAdapter(this, mListaAlbum);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void fabAdicionar_onClick(View view) {
        Intent it = new Intent(this, AlbumCreateActivity.class);
        startActivityForResult(it, 1);
    }
}
