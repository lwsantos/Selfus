package br.com.lwsantos.selfus.Holder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import br.com.lwsantos.selfus.R;

/**
 * Created by lwsantos on 08/10/16.
 */
public class AlbumHolder extends RecyclerView.ViewHolder {
    public TextView mTitulo;
    public GridView mGridFotos;
    public CardView mCardView;

    public AlbumHolder(final View itemView) {
        super(itemView);

        mTitulo = (TextView) itemView.findViewById(R.id.txtAlbumTitulo);
        mGridFotos = (GridView) itemView.findViewById(R.id.grdFotos);
        mCardView = (CardView) itemView.findViewById(R.id.cvwAlbum);
    }
}
