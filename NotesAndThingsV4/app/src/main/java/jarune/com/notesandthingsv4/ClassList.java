package jarune.com.notesandthingsv4;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;


//List view which displays all the classes
//a person is in. It will have a button to
//go to search for a class to add to your
//subscriptions, and a button to create a
//new class. Also, when they click on a list
//view item, it will move them to the notes
//page for that class
public class ClassList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void searchButton(View view) {
        Intent intent = new Intent(ClassList.this, SearchClasses.class);
        startActivity(intent);
    }

    public void createButton(View view) {
        Intent intent = new Intent(ClassList.this, CreateClass.class);
        startActivity(intent);
    }


}
