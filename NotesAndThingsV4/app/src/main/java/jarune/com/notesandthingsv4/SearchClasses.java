package jarune.com.notesandthingsv4;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class SearchClasses extends AppCompatActivity {

    private searchSql sql;
    private String results;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_classes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void searchClassesButton(View v) throws IOException {
        String university = "U of A";
        EditText searchText = (EditText) findViewById(R.id.search_class_textbox);

        if(searchText.getText().toString().isEmpty())
        {
            Toast.makeText(this, "Please Enter Class To Search", Toast.LENGTH_SHORT);
        }

        sql = new searchSql();
        sql.execute(searchText.getText().toString(), university, "");
        //String stuff = SqlHelper.searchclasses(searchText.getText().toString(), university);
        //parsing stuff and what not

        try {
            results = sql.get();

            String[] splitString = results.split("\\|");
            if (splitString.length > 0) {
                id = splitString[0];
                ArrayList<Course> courses = new ArrayList<>();
                if (splitString.length > 2) {
                    for (int i = 1; i < splitString.length; i = i + 7) {
                        Course course = new Course(splitString[i], splitString[i + 1], splitString[i + 2], splitString[i + 3], splitString[i + 4], splitString[i + 5], splitString[i + 6]);
                        courses.add(course);
                    }
                }
                searchSuccessful(courses);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    class searchSql extends AsyncTask<String, String, String> {

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


            String coursename = args[0];
            String university = args[1];
            String result = "";
            try {
                result = SqlHelper.searchclasses(coursename, university);
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

    public void searchSuccessful(ArrayList<Course> courses) {
        Intent intent = new Intent(SearchClasses.this, SearchClasses.class);
        intent.putExtra("id", id);
        intent.putExtra("courseCount", courses.size());
        if(courses.size() > 0) {
            intent.putExtra("courses", courses);
        }
        startActivity(intent);
    }

}
