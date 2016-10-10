package br.com.lwsantos.selfus.Control;

import android.content.Context;

import java.util.ArrayList;

import br.com.lwsantos.selfus.Dao.AlbumDaoLocal;
import br.com.lwsantos.selfus.Model.Album;

/**
 * Created by lwsantos on 08/10/16.
 */
public class AlbumControl {

    public ArrayList<Album> getListaAlbum(Context ctx)
    {
        ArrayList<Album> lista = new ArrayList<>();
        AlbumDaoLocal dao = new AlbumDaoLocal(ctx);
        lista = dao.getListaAlbuns();
        dao.fechar();

        return lista;
    }

    public long salvar(Context ctx, Album album)
    {
        long id = 0;

        AlbumDaoLocal dao = new AlbumDaoLocal(ctx);

        if(album.getAlbId() > 0)
        {
            int count = dao.uptAlbum(album);
            if(count > 0)
                id = album.getAlbId();
        }
        else
        {
            id = dao.insAlbum(album);
        }

        dao.fechar();

        return id;
    }
}
