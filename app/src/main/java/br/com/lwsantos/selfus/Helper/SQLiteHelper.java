package br.com.lwsantos.selfus.Helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lwsantos on 08/10/16.
 */
public class SQLiteHelper extends SQLiteOpenHelper {

    private String[] scriptSQLCreate;
    private String[] scriptSQLUpdate;

    public SQLiteHelper(Context context, String nomeBanco, int versaoBanco, String[] scriptSQLCreate, String[] scriptSQLUpdate){
        super(context, nomeBanco, null, versaoBanco);
        this.scriptSQLCreate = scriptSQLCreate;
        this.scriptSQLUpdate = scriptSQLUpdate;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        int qtdeScript = this.scriptSQLCreate.length;
        for(int i=0; i < qtdeScript; i++)
        {
            String script = this.scriptSQLCreate[i];
            db.execSQL(script);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        int qtdeScript = this.scriptSQLUpdate.length;
        for(int i=0; i < qtdeScript; i++)
        {
            String script = this.scriptSQLUpdate[i];
            db.execSQL(script);
        }
    }
}
