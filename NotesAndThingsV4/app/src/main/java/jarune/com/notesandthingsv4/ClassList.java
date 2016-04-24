package jarune.com.notesandthingsv4;

import android.content.Intent;
import android.database.DataSetObserver;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


//List view which displays all the classes
//a person is in. It will have a button to
//go to search for a class to add to your
//subscriptions, and a button to create a
//new class. Also, when they click on a list
//view item, it will move them to the notes
//page for that class
public class ClassList extends AppCompatActivity {

    public ArrayList<Course> courses;
    public String id;
    public boolean hasCourses;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        hasCourses = false;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        getClassListSql sql = new getClassListSql();
//        //sql.execute(getIntent().getStringExtra("id"), "", "");
//        sql.execute("1", "", "");
//        try {
//            String results = sql.get();
//            if(!results.equals("0 results")) {
//                System.out.println(results);
//            }
//            else {
//                Toast.makeText(ClassList.this, "Failed to get classes", Toast.LENGTH_SHORT);
//            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
        ListView listView = (ListView) findViewById(R.id.classList);
        id = getIntent().getStringExtra("id");
        if(getIntent().getIntExtra("courseCount", 0) > 0) {
            courses = (ArrayList<Course>) getIntent().getSerializableExtra("courses");
            System.out.println("here");
            ArrayList<String> courseNames = new ArrayList<>();
            System.out.println("here2");
            for(int i = 0; i < courses.size(); i++) {
                System.out.println("here3");
                courseNames.add(courses.get(i).getName() + " - " + courses.get(i).getInstructor());
            }
            ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, courseNames);
            listView.setAdapter(adapter);
        }
    }

    public void searchButton(View view) {
        Intent intent = new Intent(ClassList.this, SearchClasses.class);
        intent.putExtra("id", getIntent().getStringExtra("id"));
        intent.putExtra("courseCount", getIntent().getIntExtra("courseCount", 0));
        if(getIntent().getIntExtra("courseCount", 0) > 0) {
            intent.putExtra("courses", courses);
        }
        startActivity(intent);
    }

    public void createButton(View view) {
        Intent intent = new Intent(ClassList.this, CreateClass.class);
        intent.putExtra("id", getIntent().getStringExtra("id"));
        intent.putExtra("courseCount", getIntent().getIntExtra("courseCount", 0));
        if(getIntent().getIntExtra("courseCount", 0) > 0) {
            intent.putExtra("courses", courses);
        }
        startActivity(intent);
    }

    class getClassListSql extends AsyncTask<String, String, String> {

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
            String userId = args[0];
            try {
                result = SqlHelper.classlist(userId);
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
