package jarune.com.notesandthingsv4;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class NotesList extends AppCompatActivity {

    private ArrayList<ImageInfo> images;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ArrayList<String> pictureList = new ArrayList<>();
        TextView text = (TextView) findViewById(R.id.txtView);
        text.setText(getIntent().getStringExtra("courseName"));

        getNotesSql getNotesSql = new getNotesSql();
        getNotesSql.execute(getIntent().getStringExtra("courseId"));
        GridView view = (GridView) findViewById(R.id.grid);
        images = new ArrayList<>();
        try {
            String result = getNotesSql.get();
            String[] splitString = result.split("\\|");
            if (splitString.length > 1) {
                ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, pictureList);

                    for (int i = 0; i < splitString.length; i = i + 4) {
                        ImageInfo info = new ImageInfo(splitString[i], splitString[i + 1], splitString[i + 2], splitString[i + 3]);
                        images.add(info);
                        pictureList.add(info.P_userid + " - " + info.Summary);
                }
                view.setAdapter(adapter);

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ImageInfo image = images.get(position);
                Intent intent = new Intent(NotesList.this, showImage.class);
                intent.putExtra("imageId", image.Id);
                startActivity(intent);
            }
        });
    }

    public void createNote(View v) {
        Intent intent = new Intent(NotesList.this, AddNewNote.class);
        intent.putExtra("id", getIntent().getStringExtra("id"));
        intent.putExtra("courseCount", getIntent().getIntExtra("courseCount", 0));
        if(getIntent().getIntExtra("courseCount", 0) > 0) {
            intent.putExtra("courses", (ArrayList<Course>) getIntent().getSerializableExtra("courses"));
            intent.putExtra("courseId", getIntent().getStringExtra("courseId"));
            intent.putExtra("courseName", getIntent().getStringExtra("courseName"));
        }
        startActivity(intent);
    }

    class getNotesSql extends AsyncTask<String, String, String> {

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
            String courseId = args[0];
            try {
                result = SqlHelper.listnotes(courseId);
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
