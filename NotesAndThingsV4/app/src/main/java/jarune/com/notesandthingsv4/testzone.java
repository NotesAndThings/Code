package jarune.com.notesandthingsv4;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class testzone extends AppCompatActivity {

    //This is an example of generally how the database access is going to work. I can alter it to where we do text parsing in
    //the sqlhelper class, so that every class doesn't have to do it.
    private ProgressDialog pDialog;
    private String results;
    private TextView view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testzone);
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
        String results = "";
        new gotoPhp().execute();
        view = (TextView)findViewById(R.id.textView);
    }

    class gotoPhp extends AsyncTask<String, String, String> {

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
                result = SqlHelper.testphpaccess("testuser", "testpass");
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println(result);
            results = result;

            return null;
        }
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once product updated
            //pDialog.dismiss();
            view.setText(results);
        }
    }

}
