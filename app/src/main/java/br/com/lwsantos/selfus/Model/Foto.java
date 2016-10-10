package br.com.lwsantos.selfus.Model;

import android.graphics.Bitmap;
import android.net.Uri;

import java.io.File;
import java.io.Serializable;

import br.com.lwsantos.selfus.Util.ImagemUtil;

/**
 * Created by lwsantos on 07/10/16.
 */
public class Foto implements Serializable {
    private long fotId;
    private long fotAlbum;
    private String fotMensagem;
    private BitmapSerializable mBitmap;
    private String fotArquivo;

    public long getFotId() {
        return fotId;
    }

    public void setFotId(long mId) {
        this.fotId = mId;
    }

    public long getFotAlbum(){ return fotAlbum; }

    public void setFotAlbum(long mAlbId) { this.fotAlbum = mAlbId; }

    public String getFotMensagem() {
        return fotMensagem;
    }

    public void setFotMensagem(String mMensagem) {
        this.fotMensagem = mMensagem;
    }

    public String getFotArquivo() {
        return fotArquivo;
    }

    public void setFotArquivo(String mArquivo) {
        this.fotArquivo = mArquivo;
    }

    public BitmapSerializable getBitmap() {

        if(mBitmap == null)
        {
            Bitmap bitmap = null;
            String pathFile = fotArquivo;
            File imgFile = new  File(pathFile);

            if(imgFile.exists()){
                bitmap = ImagemUtil.scaleToFitWidth(Uri.fromFile(imgFile), 500);
                mBitmap = new BitmapSerializable(bitmap);
            }
        }

        return mBitmap;
    }

    public void setBitmap(BitmapSerializable mBitmap) {
        this.mBitmap = mBitmap;
    }
}
