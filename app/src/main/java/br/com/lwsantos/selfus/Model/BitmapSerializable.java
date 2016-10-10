package br.com.lwsantos.selfus.Model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by lwsantos on 07/10/16.
 */
public class BitmapSerializable {
    public Bitmap bitmap;

    public BitmapSerializable(Bitmap bitmap)
    {
        this.bitmap = bitmap;
    }

    private void writeObject(ObjectOutputStream out)throws IOException
    {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        this.bitmap.compress(Bitmap.CompressFormat.PNG, 0, byteStream);
        byte bitmapBytes[] = byteStream.toByteArray();
        out.write(bitmapBytes, 0, bitmapBytes.length);
    }

    private void readObject(ObjectInputStream in) throws IOException
    {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        int b;
        while((b= in.read()) != -1)
            byteStream.write(b);
        byte bitmapBytes[] = byteStream.toByteArray();
        this.bitmap = BitmapFactory.decodeByteArray(bitmapBytes, 0, bitmapBytes.length);
    }
}
