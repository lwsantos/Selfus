package br.com.lwsantos.selfus.View;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.InputStream;

import br.com.lwsantos.selfus.Control.FotoControl;
import br.com.lwsantos.selfus.Model.Album;
import br.com.lwsantos.selfus.Model.Foto;
import br.com.lwsantos.selfus.R;
import br.com.lwsantos.selfus.Util.ImagemUtil;

public class AlbumAddPhotoActivity extends AppCompatActivity {

    private Foto mFoto;
    private Album mAlbum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_add_photo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //Definir o titulo da Activity
        getSupportActionBar().setTitle("La vai a foto ;-)");

        Intent it = this.getIntent();
        mAlbum = (Album) it.getSerializableExtra("album");

        abrirGaleria(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_albuns_add_photo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case android.R.id.home:
                this.onBackPressed();
                break;
            case R.id.action_addPhoto:

                EditText txtMensagem = (EditText) findViewById(R.id.txtMensagem);
                mFoto.setFotMensagem(txtMensagem.getText().toString());
                long id= new FotoControl().insFoto(this, mFoto, mAlbum.getAlbId());
                mFoto.setFotId(id);

                Intent it = new Intent();
                it.putExtra("foto", mFoto);
                setResult(RESULT_OK, it);

                finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 1) {
            if(resultCode == Activity.RESULT_OK) {

                try {
                    final Uri imageUri = data.getData();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};

                    final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                    Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                    selectedImage = ImagemUtil.getResizedImage(selectedImage, 800, 0);

                    ImageView imgFoto = (ImageView) findViewById(R.id.imageSelected);
                    imgFoto.setImageBitmap(selectedImage);

                    Cursor cursor = getContentResolver().query(imageUri, filePathColumn, null, null, null);
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String filePath = cursor.getString(columnIndex);
                    cursor.close();

                    mFoto = new Foto();
                    mFoto.setFotArquivo(filePath);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void abrirGaleria(boolean multiplaSelecao){

        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");

        if(multiplaSelecao) {
            photoPickerIntent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        }

        startActivityForResult(photoPickerIntent, 1);
    }

}
