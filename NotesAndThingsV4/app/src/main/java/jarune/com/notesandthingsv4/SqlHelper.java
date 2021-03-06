package jarune.com.notesandthingsv4;

import android.net.Uri;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by spencernelson on 4/17/16.
 */
public class SqlHelper {

    static String link = "http://jarunenotesandthings.com/Notesandthings.php";

    //Adding new picture function. Still in progress
    public static String addnewnote(String pictureId,String p_courseId,String p_userId,String pictureuploaddate,String pictureclassdate,String picturesubject,String picturesummary,String picturecategory,String image_str) throws IOException {
        CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
        URL url = new URL(link);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoInput(true);
        conn.setDoOutput(true);
        Uri.Builder builder = new Uri.Builder()
                .appendQueryParameter("pictureid", pictureId)
                .appendQueryParameter("p_courseid", p_courseId)
                .appendQueryParameter("p_userid", p_userId)
                .appendQueryParameter("pictureuploaddate", pictureuploaddate)
                .appendQueryParameter("pictureclassdate", pictureclassdate)
                .appendQueryParameter("picturesubject", picturesubject)
                .appendQueryParameter("picturesummary", picturesummary)
                .appendQueryParameter("picturecategory", picturecategory)
                .appendQueryParameter("pictureimage", image_str)
                .appendQueryParameter("command", "addnewnote");
        String query = builder.build().getEncodedQuery();
        OutputStream os = conn.getOutputStream();
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(os, "UTF-8"));
        writer.write(query);
        writer.flush();
        writer.close();
        os.close();

        conn.connect();
        int status = conn.getResponseCode();
        System.out.println(status);
        BufferedReader reader = new BufferedReader(new
                InputStreamReader(conn.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line = "";

        // Read Server Response
        //Will look like:
        //University|CourseNum|Instructor|Semester|Year|<br>
        while ((line = reader.readLine()) != null) {
            sb.append(line);
            //break;
        }
        return sb.toString();
    }

    //function to get the class list for a particular user

    public static String classlist(String userid) throws IOException {
        CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
        URL url = new URL(link);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        Uri.Builder builder = new Uri.Builder()
                .appendQueryParameter("userid", userid)
                .appendQueryParameter("command", "classlist");
        String query = builder.build().getEncodedQuery();
        OutputStream os = conn.getOutputStream();
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(os, "UTF-8"));
        writer.write(query);
        writer.flush();
        writer.close();
        os.close();

        conn.connect();
        int status = conn.getResponseCode();
        System.out.println(status);
        BufferedReader reader = new BufferedReader(new
                InputStreamReader(conn.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line = "";

        // Read Server Response
        //Will look like:
        //University|CourseNum|Instructor|Semester|Year|<br>
        while ((line = reader.readLine()) != null) {
            sb.append(line);
            //break;
        }
        return sb.toString();
    }

    public static String createclass(String courseid, String courseuniversity, String coursenum, String coursename, String courseinstructor, String coursesemester, String courseyear) throws IOException {
        CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
        URL url = new URL(link);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        Uri.Builder builder = new Uri.Builder()
                .appendQueryParameter("courseid", courseid)
                .appendQueryParameter("courseuniversity", courseuniversity)
                .appendQueryParameter("coursenum", coursenum)
                .appendQueryParameter("coursename", coursename)
                .appendQueryParameter("courseinstructor", courseinstructor)
                .appendQueryParameter("coursesemester", coursesemester)
                .appendQueryParameter("courseyear", courseyear)
                .appendQueryParameter("command", "createclass");
        String query = builder.build().getEncodedQuery();
        OutputStream os = conn.getOutputStream();
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(os, "UTF-8"));
        writer.write(query);
        writer.flush();
        writer.close();
        os.close();

        conn.connect();
        BufferedReader reader = new BufferedReader(new
                InputStreamReader(conn.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line = null;

        // Read Server Response
        while ((line = reader.readLine()) != null) {
            sb.append(line);
            //break;
        }
        return sb.toString();
    }

    public static String login(String username, String password) throws IOException {
        CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
        URL url = new URL(link);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        Uri.Builder builder = new Uri.Builder()
                .appendQueryParameter("username", username)
                .appendQueryParameter("password", password)
                .appendQueryParameter("command", "login");
        String query = builder.build().getEncodedQuery();
        OutputStream os = conn.getOutputStream();
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(os, "UTF-8"));
        writer.write(query);
        writer.flush();
        writer.close();
        os.close();

        conn.connect();
        BufferedReader reader = new BufferedReader(new
                InputStreamReader(conn.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line = null;

        // Read Server Response
        //Will Look like:
        //id
        while ((line = reader.readLine()) != null) {
            sb.append(line);
            //break;
        }
        reader.close();
        writer.close();
        conn.disconnect();
        return sb.toString();
    }

    public static String createaccount(String userid, String username, String password, String firstname, String lastname, String university) throws IOException {
        CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
        URL url = new URL(link);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        Uri.Builder builder = new Uri.Builder()
                .appendQueryParameter("userid", userid)
                .appendQueryParameter("username", username)
                .appendQueryParameter("password", password)
                .appendQueryParameter("firstname", firstname)
                .appendQueryParameter("lastname", lastname)
                .appendQueryParameter("university", university)
                .appendQueryParameter("command", "createaccount");
        String query = builder.build().getEncodedQuery();
        OutputStream os = conn.getOutputStream();
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(os, "UTF-8"));
        writer.write(query);
        writer.flush();
        writer.close();
        os.close();

        conn.connect();
        BufferedReader reader = new BufferedReader(new
                InputStreamReader(conn.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line = null;

        // Read Server Response
        while ((line = reader.readLine()) != null) {
            sb.append(line);
            //break;
        }
        return sb.toString();
    }

    public static String searchclasses(String coursename, String university) throws IOException {
        CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
        URL url = new URL(link);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        //System.out.println(coursename);
        Uri.Builder builder = new Uri.Builder()
                .appendQueryParameter("coursename", coursename)
                .appendQueryParameter("university", university)
                .appendQueryParameter("command", "searchclasses");
        String query = builder.build().getEncodedQuery();
        OutputStream os = conn.getOutputStream();
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(os, "UTF-8"));
        writer.write(query);
        writer.flush();
        writer.close();
        os.close();

        conn.connect();
        BufferedReader reader = new BufferedReader(new
                InputStreamReader(conn.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line = null;

        // Read Server Response
        //Will look like:
        //pictureId|P_userId|ClassDate|Summary|<br>
        while ((line = reader.readLine()) != null) {
            sb.append(line);
            //break;
        }
        return sb.toString();
    }

    public static String listnotes(String courseid) throws IOException {
        CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
        URL url = new URL(link);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        System.out.println(courseid);
        Uri.Builder builder = new Uri.Builder()
                .appendQueryParameter("courseid", courseid)
                .appendQueryParameter("command", "listnotes");
        String query = builder.build().getEncodedQuery();
        OutputStream os = conn.getOutputStream();
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(os, "UTF-8"));
        writer.write(query);
        writer.flush();
        writer.close();
        os.close();

        conn.connect();
        BufferedReader reader = new BufferedReader(new
                InputStreamReader(conn.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line = null;

        // Read Server Response
        //Will look like:
        //pictureId|P_userId|ClassDate|Summary|<br>
        while ((line = reader.readLine()) != null) {
            sb.append(line);
            //break;
        }
        return sb.toString();
    }

    public static String addclass(String userid, String courseid) throws IOException {
        CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
        URL url = new URL(link);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        Uri.Builder builder = new Uri.Builder()
                .appendQueryParameter("userid", userid)
                .appendQueryParameter("courseid", courseid)
                .appendQueryParameter("command", "addclass");
        String query = builder.build().getEncodedQuery();
        OutputStream os = conn.getOutputStream();
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(os, "UTF-8"));
        writer.write(query);
        writer.flush();
        writer.close();
        os.close();

        conn.connect();
        BufferedReader reader = new BufferedReader(new
                InputStreamReader(conn.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line = null;

        // Read Server Response
        while ((line = reader.readLine()) != null) {
            sb.append(line);
            //break;
        }
        return sb.toString();
    }

    //NEEDS WORK AND TESTING
    public static String getnote(String pictureid) throws IOException {
        CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
        URL url = new URL(link);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        Uri.Builder builder = new Uri.Builder()
                .appendQueryParameter("pictureid", pictureid)
                .appendQueryParameter("command", "getnote");
        String query = builder.build().getEncodedQuery();
        OutputStream os = conn.getOutputStream();
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(os, "UTF-8"));
        writer.write(query);
        writer.flush();
        writer.close();
        os.close();

        conn.connect();
        BufferedReader reader = new BufferedReader(new
                InputStreamReader(conn.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line = null;

        // Read Server Response
        //Will look like:
        //image
        while ((line = reader.readLine()) != null) {
            sb.append(line);
            //break;
        }
        return sb.toString();
    }

    public static String testphpaccess(String username, String password) throws IOException {
        CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
        URL url = new URL(link);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        Uri.Builder builder = new Uri.Builder()
                .appendQueryParameter("userid", username)
                .appendQueryParameter("password", password)
                .appendQueryParameter("command", "testphpaccess");
        String query = builder.build().getEncodedQuery();
        OutputStream os = conn.getOutputStream();
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(os, "UTF-8"));
        writer.write(query);
        writer.flush();
        writer.close();
        os.close();

        conn.connect();
        BufferedReader reader = new BufferedReader(new
                InputStreamReader(conn.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line = "";

        // Read Server Response
        while ((line = reader.readLine()) != null) {
            sb.append(line);
            break;
        }
        System.out.println(sb.toString());
        return sb.toString();
    }

    public static String removeClass(String userid, String courseid) throws IOException {
        CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
        URL url = new URL(link);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        Uri.Builder builder = new Uri.Builder()
                .appendQueryParameter("userid", userid)
                .appendQueryParameter("courseid", courseid)
                .appendQueryParameter("command", "removeclass");
        String query = builder.build().getEncodedQuery();
        OutputStream os = conn.getOutputStream();
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(os, "UTF-8"));
        writer.write(query);
        writer.flush();
        writer.close();
        os.close();

        conn.connect();
        BufferedReader reader = new BufferedReader(new
                InputStreamReader(conn.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line = "";

        // Read Server Response
        while ((line = reader.readLine()) != null) {
            sb.append(line);
            break;
        }
        System.out.println(sb.toString());
        return sb.toString();
    }
}