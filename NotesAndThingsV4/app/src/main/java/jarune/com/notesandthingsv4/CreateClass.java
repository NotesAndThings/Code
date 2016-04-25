package jarune.com.notesandthingsv4;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;

public class CreateClass extends AppCompatActivity {

    String university;
    String name;
    String instructor;
    String year;
    String semester;
    String number;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_class);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Spinner dropdown = (Spinner)findViewById(R.id.spinner1);
        String[] items = new String[]{"Fall", "Spring", "Summer", "Winter"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
    }

    public void ClassCreate(View v) throws IOException {

        university = "U of A";
        name = ((EditText)findViewById(R.id.name)).getText().toString();
        instructor = ((EditText)findViewById(R.id.instructor)).getText().toString();
        year = ((EditText)findViewById(R.id.year)).getText().toString();
        semester = ((Spinner)findViewById(R.id.spinner1)).getSelectedItem().toString();
        number = ((EditText)findViewById(R.id.number)).getText().toString();

        id = name + String.valueOf((new Random().nextInt()));

        Course course = new Course(id, university, number, name, instructor, semester, year);
        createclasssql sql = new createclasssql();
        sql.execute("", "", "");
        try {
            String results = sql.get();
            if(results.equals("Success")) {
                Successful(course);
            }
            else {
                Toast.makeText(CreateClass.this, "Failed", Toast.LENGTH_SHORT);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
       }

    }

    public void Successful(Course course) {
        Globals.courses.add(course);
        Intent intent = new Intent(CreateClass.this, ClassList.class);
//        intent.putExtra("id", getIntent().getStringExtra("id"));
//        intent.putExtra("courseCount", (getIntent().getIntExtra("courseCount", 0))+1);
//        if(getIntent().getIntExtra("courseCount", 0) > 0) {
//            intent.putExtra("courses", courses);
//        }
        startActivity(intent);
    }

    class createclasssql extends AsyncTask<String, String, String> {

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
            try {
                result = SqlHelper.createclass(id, university, number, name, instructor, semester, year);
                if(result.equals("Success")) {
                    result = SqlHelper.addclass(Globals.UserId, id);
                }
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
