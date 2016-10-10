package br.com.lwsantos.selfus.Util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.ExifInterface;
import android.net.Uri;

/**
 * Created by lwsantos on 08/10/16.
 */
public class ImagemUtil {
    public static Bitmap getResizedImage(Bitmap bitmap, int width, int height)
    {
        try
        {
            int w = bitmap.getWidth();
            int h = bitmap.getHeight();
            int newWidth = 0;
            int newHeight = 0;
            if(width > 0)
            {
                float ratioW = (float)width/w;
                newWidth = width;
                newHeight = (int)(ratioW * h);
            }
            else if(height > 0)
            {
                float ratioH = (float)height/h;
                newWidth = (int)(ratioH * w);
                newHeight = height;
            }


            Bitmap resizedBitmap = Bitmap.createScaledBitmap(bitmap, newWidth, newHeight, true);

            //Limpa a memoria do arquivo original
            bitmap.recycle();
            bitmap = null;

            return resizedBitmap;

        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    // Scale and maintain aspect ratio given a desired width
    // BitmapScaler.scaleToFitWidth(bitmap, 100);
    public static Bitmap scaleToFitWidth(Uri uriFile, int newWidth)
    {
        try {
            Bitmap bitmap = BitmapFactory.decodeFile(uriFile.getPath());

            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            float ratio = (float) newWidth / width;
            int newheight = (int) (ratio * height);

            //Verificar orietacao da foto
            ExifInterface exif = new ExifInterface(uriFile.getPath());
            int rotation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            int rotationInDegrees = exifToDegrees(rotation);

            Matrix matrix = new Matrix();
            //Rotacionar foto
            if (rotation != 0f) {
                matrix.preRotate(rotationInDegrees);
            }

            bitmap = Bitmap.createScaledBitmap(bitmap, newWidth, newheight, true);

            Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, newWidth, newheight, matrix, true);

            //Limpa a memoria do arquivo original
            bitmap.recycle();
            bitmap = null;

            return resizedBitmap;

        }catch (Exception e)
        {

        }

        return null;
    }

    public static int exifToDegrees(int exifOrientation) {
        if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_90) { return 90; }
        else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_180) {  return 180; }
        else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_270) {  return 270; }
        return 0;
    }

    public static Bitmap cropCenter(Bitmap image){

        if (image.getWidth() >= image.getHeight()){

            image = Bitmap.createBitmap(
                    image,
                    image.getWidth()/2 - image.getHeight()/2,
                    0,
                    image.getHeight(),
                    image.getHeight()
            );

        }else{

            image = Bitmap.createBitmap(
                    image,
                    0,
                    image.getHeight()/2 - image.getWidth()/2,
                    image.getWidth(),
                    image.getWidth()
            );
        }

        return image;
    }

    public Bitmap getRoundedShape(Bitmap scaleBitmapImage, int targetWidth, int targetHeight) {

        Bitmap targetBitmap = Bitmap.createBitmap(targetWidth, targetHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(targetBitmap);
        Path path = new Path();
        path.addCircle(((float) targetWidth) / 2,
                ((float) targetHeight) / 2,
                (Math.min(((float) targetWidth), ((float) targetHeight)) / 2),
                Path.Direction.CW);
        Paint paint = new Paint();
        paint.setColor(Color.GRAY);
//paint.setStyle(Paint.Style.STROKE);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setFilterBitmap(true);
        canvas.drawOval(new RectF(0, 0, targetWidth, targetHeight), paint) ;
//paint.setColor(Color.TRANSPARENT);
        canvas.clipPath(path);

        /*** Crop imagem a partir do centro, com corte quadrado (altura e largura) **/

        // a variavel target eh utilizada para saber a quantidade de pixels que a imagem tera (formato quadrado)
        int target = 0;

        //pega a menor medida da imagem original (largura ou altura)
        if(scaleBitmapImage.getWidth() < scaleBitmapImage.getHeight())
            target = scaleBitmapImage.getWidth();
        else
            target = scaleBitmapImage.getHeight();

        // x e y sao as posicoes (coordenadas) que a imagem sera cortada
        //pega a largura e divide por 2 para achar o meio. Subtrai a metade do tamanho do target para deslocar a posicao, pois o crop começa da posiçao para a direita
        int x = (scaleBitmapImage.getWidth()/2) - (target/2);
        int y = (scaleBitmapImage.getHeight()/2) - (target/2);

        Bitmap sourceBitmap = Bitmap.createBitmap(scaleBitmapImage, x, y, target, target);

        /***************************************************************************/

        canvas.drawBitmap(sourceBitmap, new Rect(0, 0, sourceBitmap.getWidth(),
                sourceBitmap.getHeight()), new RectF(0, 0, targetWidth,
                targetHeight), paint);
        return targetBitmap;
    }

    public static Bitmap getCroppedBitmap(Bitmap bmp, int radius) {
        Bitmap sbmp;
        if(bmp.getWidth() != radius || bmp.getHeight() != radius)
            sbmp = Bitmap.createScaledBitmap(bmp, radius, radius, false);
        else
            sbmp = bmp;
        Bitmap output = Bitmap.createBitmap(sbmp.getWidth(),
                sbmp.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xffa19774;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, sbmp.getWidth(), sbmp.getHeight());

        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(Color.parseColor("#BAB399"));
        canvas.drawCircle(sbmp.getWidth() / 2+0.7f, sbmp.getHeight() / 2+0.7f,
                sbmp.getWidth() / 2+0.1f, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(sbmp, rect, rect, paint);


        return output;
    }
}
