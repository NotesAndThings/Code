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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.EventListener;
import java.util.concurrent.ExecutionException;

public class Login extends AppCompatActivity {


    private ProgressDialog pDialog;
    private String results;
    private EditText username;
    private EditText password;
    private String id;
    private loginSql sql;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
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

        Button btnLogin = (Button) findViewById(R.id.SignInbutton);
        btnLogin.setOnClickListener((new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                username = (EditText) findViewById(R.id.Username);
                password = (EditText) findViewById(R.id.Password);
                String name = username.getText().toString();
                String pass = password.getText().toString();
                // starting background task to update product

                sql = new loginSql();
                sql.execute(name, pass, "");
                try {
                    results = sql.get();
                    if(!results.equals("0 results")) {
                        loginSuccessful();
                    }
                    else {
                        Toast.makeText(Login.this, "Incorrect Login Information", 3);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }));

    }

    public void loginSuccessful() {
        Intent intent = new Intent(Login.this, RegisterAccount.class);
        intent.putExtra("id", results);
        startActivity(intent);
    }

    public void registerAccount(View v) {
        Intent intent = new Intent(Login.this, RegisterAccount.class);
        startActivity(intent);
    }

    class loginSql extends AsyncTask<String, String, String> {

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


            String username = args[0];
            String password = args[1];
            String result = "";
            try {
                result = SqlHelper.login(username, password);
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
