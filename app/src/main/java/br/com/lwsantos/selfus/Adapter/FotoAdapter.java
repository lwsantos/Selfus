package br.com.lwsantos.selfus.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

import br.com.lwsantos.selfus.Holder.FotoHolder;
import br.com.lwsantos.selfus.Model.Foto;
import br.com.lwsantos.selfus.Util.ImagemUtil;

/**
 * Created by lwsantos on 08/10/16.
 */
public class FotoAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<Foto> mListaFoto;

    public FotoAdapter(Context context, ArrayList<Foto> lista)
    {
        mContext = context;
        mListaFoto = lista;
    }

    @Override
    public int getCount() {
        return mListaFoto.size();
    }

    @Override
    public Object getItem(int position) {
        return mListaFoto.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        FotoHolder holder;
        ImageView imageView;

        if (convertView == null) {
            imageView = new ImageView(mContext);
            //A imagem sera ajustada de acordo com a largura da coluna, respeitando o ratio
            imageView.setAdjustViewBounds(true);

            convertView = imageView;

            holder = new FotoHolder();
            holder.image = imageView;
            holder.position = position;
            convertView.setTag(holder);

        } else {
            //imageView = (ImageView) convertView;
            holder = (FotoHolder) convertView.getTag();
            ((ImageView)convertView).setImageBitmap(null);
        }

        new AsyncTask<FotoHolder, Void, Bitmap>() {
            private FotoHolder v;

            @Override
            protected Bitmap doInBackground(FotoHolder... params) {
                v = params[0];
                Bitmap bm = mListaFoto.get(position).getBitmap().bitmap;
                bm = ImagemUtil.cropCenter(bm);
                return bm;
            }

            @Override
            protected void onPostExecute(Bitmap result) {
                super.onPostExecute(result);
                v.image.setImageBitmap(result);

            }
        }.execute(holder);

        //imageView.setImageBitmap(mListaFoto.get(position).getBitmap().bitmap);
        //return imageView;

        return convertView;
    }
}
