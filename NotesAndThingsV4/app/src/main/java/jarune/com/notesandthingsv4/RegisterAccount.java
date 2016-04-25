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

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ExecutionException;

public class RegisterAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_account);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void UserCreate(View v) {
        String username = ((EditText)findViewById(R.id.Username)).getText().toString();
        String password = ((EditText)findViewById(R.id.Password)).getText().toString();
        String firstname = ((EditText)findViewById(R.id.firstname)).getText().toString();
        String lastname = ((EditText)findViewById(R.id.lastname)).getText().toString();
        String university = ((EditText)findViewById(R.id.university)).getText().toString();
        String id = username + (new Random().nextInt());

        registerSql sql = new registerSql();
        sql.execute(id, username, password, firstname, lastname, university);
        try {
            String result = sql.get();
            if(result.equals("Success")) {
                Globals.UserId = id;
                Intent intent = new Intent(RegisterAccount.this, ClassList.class);
//                intent.putExtra("id", id);
                startActivity(intent);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    class registerSql extends AsyncTask<String, String, String> {

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


            String id = args[0];
            String username = args[1];
            String password = args[2];
            String firstname = args[3];
            String lastname = args[4];
            String university = args[5];
            String result = "";
            try {
                result = SqlHelper.createaccount(id, username, password, firstname, lastname, university);
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
