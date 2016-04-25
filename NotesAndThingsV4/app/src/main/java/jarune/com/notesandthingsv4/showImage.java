package jarune.com.notesandthingsv4;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class showImage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ImageView view = (ImageView) findViewById(R.id.imageView);
        String imageId = getIntent().getStringExtra("imageId");
        getNote note = new getNote();
        note.execute(imageId);
        try {
            String result = note.get();
            System.out.println("HERE2");
            System.out.println(result);
            System.out.println("HERE");
            byte[] image_array = Base64.decode(result, Base64.DEFAULT);
            ByteArrayInputStream stream1 = new ByteArrayInputStream(image_array);
            Bitmap bitmap1 = BitmapFactory.decodeStream(stream1);
            view.setImageBitmap(bitmap1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    class getNote extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //pDialog = new ProgressDialog(testzone.this);
            //pDialog.setMessage("Creating product ...");
            //pDialog.setIndeterminate(false);
            //pDialog.setCancelable(true);
            //pDialog.show();
        }

        protected String doInBackground(String... args) {


            String result = "";
            String pictureId = args[0];
            try {
                result = SqlHelper.getnote(pictureId);
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println(result);

            return result;
        }
        protected void onPostExecute(String result) {
            // dismiss the dialog once product updated
            //pDialog.dismiss();

        }
    }

}
