package br.com.lwsantos.selfus.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

import br.com.lwsantos.selfus.Model.Album;

/**
 * Created by lwsantos on 08/10/16.
 */
public class AlbumDaoLocal extends  DaoLocal{

    public AlbumDaoLocal(Context ctx) {
        super(ctx);
    }

    public ArrayList<Album> getListaAlbuns()
    {
        ArrayList<Album> lista = new ArrayList<>();
        String[] colunas = new String[]{
                "albId", "albNome"
        };

        Cursor cursor = this.selecionar(false, "album", colunas, null, null, null, null, "albId DESC", null);

        if(cursor.moveToFirst())
        {
            int idxId = cursor.getColumnIndex("albId");
            int idxNome = cursor.getColumnIndex("albNome");

            do {
                Album album = new Album();

                album.setAlbId(cursor.getLong(idxId));
                album.setAlbNome(cursor.getString(idxNome));

                lista.add(album);

            }while (cursor.moveToNext());
        }

        return lista;
    }


    public long insAlbum(Album album)
    {
        ContentValues valores = new ContentValues();

        valores.put("albNome", album.getAlbNome());


        return this.inserir("album", valores);
    }

    public int uptAlbum(Album album)
    {
        ContentValues valores = new ContentValues();
        String[] args = new String[]{
                String.valueOf(album.getAlbId())
        };

        valores.put("albNome", album.getAlbNome());

        return this.atualizar("album", valores, "albId=?", args);
    }
}
