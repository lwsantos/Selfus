package br.com.lwsantos.selfus.Control;

import android.content.Context;

import java.util.ArrayList;

import br.com.lwsantos.selfus.Dao.FotoDaoLocal;
import br.com.lwsantos.selfus.Model.Foto;

/**
 * Created by lwsantos on 08/10/16.
 */
public class FotoControl {

    public ArrayList<Foto> getListaFotos(Context ctx, long albId, int limite)
    {
        ArrayList<Foto> lista = new ArrayList<>();
        FotoDaoLocal dao = new FotoDaoLocal(ctx);
        lista = dao.getListaFotos(albId, limite);
        dao.fechar();

        return lista;
    }

    public long insFoto(Context ctx, Foto foto, long albId)
    {
        long id = 0;

        FotoDaoLocal dao = new FotoDaoLocal(ctx);

        id = dao.insFoto(foto, albId);

        dao.fechar();

        return id;
    }

    public void delFoto(Context ctx, long fotId)
    {
        FotoDaoLocal dao = new FotoDaoLocal(ctx);
        dao.delFoto(fotId);
        dao.fechar();
    }
}
