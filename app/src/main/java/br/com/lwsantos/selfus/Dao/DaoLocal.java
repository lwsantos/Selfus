package br.com.lwsantos.selfus.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import br.com.lwsantos.selfus.Helper.SQLiteHelper;

/**
 * Created by lwsantos on 08/10/16.
 */
public class DaoLocal {

    private static final String[] SCRIPT_DATABASE_CREATE = new String[]{
            "CREATE TABLE album (albId INTEGER PRIMARY KEY AUTOINCREMENT, albNome VARCHAR(255), albData DATE);"
            , "CREATE TABLE foto (fotId INTEGER PRIMARY KEY AUTOINCREMENT, fotMensagem VARCHAR(255), fotArquivo VARCHAR(255), fotAlbum INTEGER);"
    };

    private static final String[] SCRIPT_DATABASE_UPDATE = new String[0];

    private static final String NOME_BANCO = "dbSelfus";
    private static final int VERSAO_BANCO = 1;
    private SQLiteHelper dbHelper;
    protected SQLiteDatabase db;

    public DaoLocal(Context ctx)
    {
        dbHelper = new SQLiteHelper(ctx, NOME_BANCO, VERSAO_BANCO, SCRIPT_DATABASE_CREATE, SCRIPT_DATABASE_UPDATE);
        db = dbHelper.getWritableDatabase();
    }

    protected long inserir(String tabela, ContentValues valores)
    {
        long id = db.insert(tabela, "", valores);
        return id;
    }

    protected int atualizar(String tabela, ContentValues valores, String whereClause, String[] whereArgs)
    {
        int count = db.update(tabela, valores, whereClause, whereArgs);
        return count;
    }

    protected int deletar(String tabela, String whereClause, String[] whereArgs)
    {
        int count = db.delete(tabela, whereClause, whereArgs);
        return count;
    }

    protected Cursor selecionar(boolean distinct, String tabela, String[] colunas, String whereClause, String[] whereArgs, String groupBy, String having, String orderBy, String limit)
    {
        Cursor cursor = db.query(distinct, tabela, colunas, whereClause, whereArgs, groupBy, having, orderBy, limit);
        return cursor;
    }

    protected Cursor execQuery(String query, String[] selectionArgs)
    {
        Cursor cursor = db.rawQuery(query, selectionArgs);
        return  cursor;
    }

    public void fechar()
    {
        if(db != null)
        {
            db.close();
        }
    }

}
