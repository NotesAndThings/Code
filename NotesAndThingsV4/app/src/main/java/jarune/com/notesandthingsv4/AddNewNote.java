package jarune.com.notesandthingsv4;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class AddNewNote extends AppCompatActivity {

    public String mCurrentPhotoPath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_note);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    //does this appear as a change?
    static final int REQUEST_TAKE_PHOTO = 1;

    private void dispatchTakePictureIntent() {
//        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        // Ensure that there's a camera activity to handle the intent
//        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
//            // Create the File where the photo should go
//            File photoFile = null;
//            try {
//                photoFile = createImageFile();
//            } catch (IOException ex) {
//                // Error occurred while creating the File
//            }
//            // Continue only if the File was successfully created
//            if (photoFile != null) {
//                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
//                        Uri.fromFile(photoFile));
//                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
//
//
//            }
//        }
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, 1);
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap bitmap = (Bitmap) extras.get("data");


        /*
         * Convert the image to a string
         * */
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream); //compress to which format you want.
            byte[] byte_arr = stream.toByteArray();
            String image_str = Base64.encodeToString(byte_arr, Base64.DEFAULT);

            String p_courseid = getIntent().getStringExtra("courseId");
            String p_userid = Globals.UserId;
            String uploaddate = getDateTime(new Date(System.currentTimeMillis()));
            String classdate = getDateTime(new Date(((DatePicker) findViewById(R.id.classDate)).getCalendarView().getDate()));
            String summary = ((EditText) findViewById(R.id.summary)).getText().toString();
            String category = ((EditText) findViewById(R.id.category)).getText().toString();
            String subject = ((EditText) findViewById(R.id.subject)).getText().toString();
            String pictureid = p_userid + (new Random().nextInt());
            createNote note = new createNote();
            note.execute(pictureid, p_courseid, p_userid, uploaddate, classdate, subject, summary, category, image_str);
            Intent intent = new Intent(AddNewNote.this, NotesList.class);
            intent.putExtra("courseId",getIntent().getStringExtra("courseId"));
            startActivity(intent);
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String imageFileName = "imageName";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    public void takePic(View v) {
        dispatchTakePictureIntent();
    }

    private String getDateTime(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "MM/dd/yy; hh:mm:ss", Locale.getDefault());
        return dateFormat.format(date);
    }

    class createNote extends AsyncTask<String, String, String> {

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
            String p_courseId = args[1];
            String p_userId = args[2];
            String pictureuploaddate = args[3];
            String pictureclassdate = args[4];
            String picturesubject = args[5];
            String picturesummary = args[6];
            String picturecategory = args[7];
            String image_str = args[8];
            try {
                result = SqlHelper.addnewnote(pictureId, p_courseId, p_userId, pictureuploaddate, pictureclassdate, picturesubject, picturesummary, picturecategory, image_str);
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
