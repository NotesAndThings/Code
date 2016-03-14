package jarune.com.notesandthings;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ClassList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        ArrayList<String> classes = new ArrayList<String>();
        classes.add("UA CSCE 3613");
        classes.add("UA PHIL 2013");
        classes.add("UA CSCE 4013");
        classes.add("UA CHEM 1114");

        ArrayAdapter<String> adapter;
        int layoutID = android.R.layout.simple_list_item_1;
        adapter = new ArrayAdapter<String>(this, layoutID, classes);
        ListView list = (ListView)findViewById(R.id.classList);
        list.setAdapter(adapter);

    }

}
