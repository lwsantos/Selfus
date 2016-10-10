package br.com.lwsantos.selfus.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

import br.com.lwsantos.selfus.Model.Foto;

/**
 * Created by lwsantos on 08/10/16.
 */
public class FotoDaoLocal extends DaoLocal {

    public FotoDaoLocal(Context ctx) {
        super(ctx);
    }

    public ArrayList<Foto> getListaFotos(long albId, int limite)
    {
        ArrayList<Foto> lista = new ArrayList<>();

        String query = "SELECT * FROM foto WHERE fotAlbum = ? ORDER BY fotId";
        if(limite > 0)
            query += " LIMIT " + limite ;
        String[] args = new String[]{
                String.valueOf(albId)
        };

        Cursor cursor = this.execQuery(query, args);

        lista = mapearDados(cursor);

        return lista;
    }

    public long insFoto(Foto foto, long albId)
    {
        ContentValues valores = new ContentValues();

        valores.put("fotMensagem", foto.getFotMensagem());
        valores.put("fotArquivo", foto.getFotArquivo());
        valores.put("fotAlbum", albId);

        return this.inserir("foto", valores);
    }

    public int delFoto(long id)
    {
        String[] args = new String[]{
                String.valueOf(id)
        };

        return this.deletar("foto", "fotId=?", args);
    }

    private ArrayList<Foto> mapearDados(Cursor cursor)
    {
        ArrayList<Foto> lista = new ArrayList<>();
        if(cursor.moveToFirst())
        {

            int idxId = cursor.getColumnIndex("fotId");
            int idxMensagem = cursor.getColumnIndex("fotMensagem");
            int idxArquivo = cursor.getColumnIndex("fotArquivo");
            int idxFotoAlbum = cursor.getColumnIndex("fotAlbum");

            do {
                Foto foto = new Foto();

                foto.setFotId(cursor.getLong(idxId));
                foto.setFotMensagem(cursor.getString(idxMensagem));
                foto.setFotArquivo(cursor.getString(idxArquivo));
                foto.setFotAlbum(cursor.getLong(idxFotoAlbum));

                lista.add(foto);

            }while (cursor.moveToNext());
        }

        return lista;
    }
}
