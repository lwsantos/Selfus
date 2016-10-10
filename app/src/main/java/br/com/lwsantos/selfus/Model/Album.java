package br.com.lwsantos.selfus.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by lwsantos on 08/10/16.
 */
public class Album implements Serializable {

    private long albId;
    private String albNome;
    private Date albData;
    private ArrayList<Foto> mListaFoto;

    public Album(){
        mListaFoto = new ArrayList<Foto>();
    }

    public String getAlbNome() {
        return albNome;
    }

    public void setAlbNome(String nome) {
        albNome = nome;
    }

    public void setAlbId(long id) {
        albId = id;
    }

    public long getAlbId() {
        return albId;
    }

    public Date getAlbData() {
        return albData;
    }

    public void setAlbData(Date data) {
        albData = data;
    }

    public ArrayList<Foto> getListaFoto() {
        return mListaFoto;
    }

    public void setListaFoto(ArrayList<Foto> mListaFoto) {
        this.mListaFoto = mListaFoto;
    }

    public void addListaFoto(Foto foto)
    {
        this.mListaFoto.add(foto);
    }

    public void addListaFoto(ArrayList<Foto> lista)
    {
        this.mListaFoto.addAll(lista);
    }
}
