package br.com.lwsantos.selfus.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import java.util.ArrayList;

import br.com.lwsantos.selfus.Control.FotoControl;
import br.com.lwsantos.selfus.Holder.AlbumHolder;
import br.com.lwsantos.selfus.Model.Album;
import br.com.lwsantos.selfus.R;
import br.com.lwsantos.selfus.View.AlbumFotosActivity;

/**
 * Created by lwsantos on 08/10/16.
 */
public class AlbumAdapter  extends RecyclerView.Adapter<AlbumHolder> {

    ArrayList<Album> mListaAlbum;
    Context mContext;

    public AlbumAdapter (Context context, ArrayList<Album> lista)
    {
        mListaAlbum = lista;
        mContext = context;
    }

    @Override
    public AlbumHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layout = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = layout.inflate(R.layout.layout_album_item, parent, false);
        AlbumHolder holder = new AlbumHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(AlbumHolder holder, int position) {
        final Album album = mListaAlbum.get(position);

        if(album.getListaFoto().size() == 0)
        {
            album.setListaFoto(new FotoControl().getListaFotos(mContext, album.getAlbId(), 3));
        }

        holder.mTitulo.setText(album.getAlbNome());

        //Define o adaptador com as fotos e monta o list view layout
        FotoAdapter adapter = new FotoAdapter(mContext, album.getListaFoto());
        holder.mGridFotos.setAdapter(adapter);

        /**
         * Exibe as fotos do album ao clicar em um item do RecyclerView
         * Com excessao se clicar em uma das fotos
         */
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(mContext, AlbumFotosActivity.class);
                album.setListaFoto(null);
                it.putExtra("album", album);
                ((Activity)mContext).startActivity(it);
            }
        });

        /**
         * Exibe as fotos do album ao clicar em uma das fotos
         */
        holder.mGridFotos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(mContext, AlbumFotosActivity.class);
                album.setListaFoto(null);
                it.putExtra("album", album);
                ((Activity)mContext).startActivity(it);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mListaAlbum.size();
    }

}
