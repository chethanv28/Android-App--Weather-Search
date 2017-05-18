package com.webtech.chethan.cvwebtechhw9;

import android.app.Activity;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.graphics.Color;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.view.View;
import android.content.Intent;
import android.os.AsyncTask;
import java.net.URL;
import java.net.URLConnection;
import java.net.HttpURLConnection;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;
import java.io.*;

import android.view.View.OnClickListener;


public class MainActivity extends Activity implements OnClickListener{

    Spinner spinnerobj;
    ArrayAdapter<CharSequence> adapter;
    EditText street;
    EditText city;
    TextView errormessage;
    Button btnsearch;
    Button btnclear;
    RadioButton fahrenheit;
    Button btnabout;
    ImageView forecastio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        spinnerobj = (Spinner)findViewById(R.id.statevalue);
        adapter = ArrayAdapter.createFromResource(this, R.array.state_names, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerobj.setAdapter(adapter);
        street = (EditText)findViewById(R.id.streetaddress);
        city  = (EditText)findViewById(R.id.cityname);
        spinnerobj = (Spinner)findViewById(R.id.statevalue);
        btnsearch = (Button)findViewById(R.id.search);
        errormessage = (TextView)findViewById(R.id.errormessage);
        fahrenheit = (RadioButton)findViewById(R.id.fahrenheit);
        btnsearch.setOnClickListener(this);
        btnclear = (Button)findViewById(R.id.clear);
        btnclear.setOnClickListener(this);
        btnabout = (Button)findViewById(R.id.about);
        btnabout.setOnClickListener(this);
        forecastio = (ImageView)findViewById(R.id.imageView);
        forecastio.setOnClickListener(this);
    }

    public void onClick(View v) {

        EditText streetaddress = (EditText) findViewById(R.id.streetaddress);
        EditText cityname = (EditText) findViewById(R.id.cityname);
        Spinner state = (Spinner)findViewById(R.id.statevalue);
        switch(v.getId())
        {
            case R.id.search:
                if (streetaddress.getText().toString().trim().equals("")) {


                    errormessage.setText("Please enter a Street Address");
                    //streetaddress.setError( "Please enter a Street Address" );

                    errormessage.setTextColor(Color.rgb(255, 0, 0));
                }
                else if (cityname.getText().toString().trim().equals("")) {

                    errormessage.setText("Please enter a City");
                    //streetaddress.setError( "Please enter a Street Address" );

                    errormessage.setTextColor(Color.rgb(255, 0, 0));
                }
                else if (state.getSelectedItem().toString().equals("Select")) {

                    errormessage.setText("Please enter a State");
                    //streetaddress.setError( "Please enter a Street Address" );

                    errormessage.setTextColor(Color.rgb(255, 0, 0));
                }
                else
                {

                    String streetaddressdata = streetaddress.getText().toString();
                    String citynamedata = cityname.getText().toString();
                    String statedata = state.getSelectedItem().toString();

                    String statevalue = "";
                    /*if(statedata.equals("Alabama"))
                    {
                        statevalue = "AL";
                    }
                    else if(statedata.equals("Alaska"))
                    {
                        statevalue = "AK";
                    }
                    else if(statedata.equals("Arizona"))
                    {
                        statevalue = "AZ";
                    }
                    else if(statedata.equals("Arkansas"))
                    {
                        statevalue = "AR";
                    }
                    else if(statedata.equals("California"))
                    {
                        statevalue = "CA";
                    }
                    else if(statedata.equals("Colorado"))
                    {
                        statevalue = "CO";
                    }
                    else if(statedata.equals("Connecticut"))
                    {
                        statevalue = "CT";
                    }
                    else if(statedata.equals("Delaware"))
                    {
                        statevalue = "DE";
                    }
                    else if(statedata.equals("District Of Columbia"))
                    {
                        statevalue = "DC";
                    }
                    else if(statedata.equals("Florida"))
                    {
                        statevalue = "FL";
                    }
                    else if(statedata.equals("Georgia"))
                    {
                        statevalue = "GA";
                    }
                    else if(statedata.equals("Hawaii"))
                    {
                        statevalue = "HI";
                    }
                    else if(statedata.equals("Idaho"))
                    {
                        statevalue = "ID";
                    }
                    else if(statedata.equals("Illinois"))
                    {
                        statevalue = "IL";
                    }
                    else if(statedata.equals("Indiana"))
                    {
                        statevalue = "IN";
                    }
                    else if(statedata.equals("Iowa"))
                    {
                        statevalue = "IA";
                    }
                    else if(statedata.equals("Kansas"))
                    {
                        statevalue = "KS";
                    }
                    else if(statedata.equals("Kentucky"))
                    {
                        statevalue = "KY";
                    }
                    else if(statedata.equals("Louisiana"))
                    {
                        statevalue = "LA";
                    }
                    else if(statedata.equals("Maine"))
                    {
                        statevalue = "ME";
                    }
                    else if(statedata.equals("Maryland"))
                    {
                        statevalue = "MD";
                    }
                    else if(statedata.equals("Massachusetts"))
                    {
                        statevalue = "MA";
                    }
                    else if(statedata.equals("Michigan"))
                    {
                        statevalue = "MI";
                    }
                    else if(statedata.equals("Minnesota"))
                    {
                        statevalue = "MN";
                    }
                    else if(statedata.equals("Mississippi"))
                    {
                        statevalue = "MS";
                    }
                    else if(statedata.equals("Missouri"))
                    {
                        statevalue = "MO";
                    }
                    else if(statedata.equals("Montana"))
                    {
                        statevalue = "MT";
                    }
                    else if(statedata.equals("Nebraska"))
                    {
                        statevalue = "NE";
                    }
                    else if(statedata.equals("Nevada"))
                    {
                        statevalue = "NV";
                    }
                    else if(statedata.equals("New Hampshire"))
                    {
                        statevalue = "NH";
                    }
                    else if(statedata.equals("New Jersey"))
                    {
                        statevalue = "NJ";
                    }
                    else if(statedata.equals("New Mexico"))
                    {
                        statevalue = "NM";
                    }
                    else if(statedata.equals("New York"))
                    {
                        statevalue = "NY";
                    }
                    else if(statedata.equals("North Carolina"))
                    {
                        statevalue = "NC";
                    }
                    else if(statedata.equals("North Dakota"))
                    {
                        statevalue = "ND";
                    }
                    else if(statedata.equals("Ohio"))
                    {
                        statevalue = "OH";
                    }
                    else if(statedata.equals("Oklahoma"))
                    {
                        statevalue = "OK";
                    }
                    else if(statedata.equals("Oregon"))
                    {
                        statevalue = "OR";
                    }
                    else if(statedata.equals("Pennsylvania"))
                    {
                        statevalue = "PA";
                    }
                    else if(statedata.equals("Rhode Island"))
                    {
                        statevalue = "RI";
                    }
                    else if(statedata.equals("South Carolina"))
                    {
                        statevalue = "SC";
                    }
                    else if(statedata.equals("South Dakota"))
                    {
                        statevalue = "SD";
                    }
                    else if(statedata.equals("Tennessee"))
                    {
                        statevalue = "TN";
                    }
                    else if(statedata.equals("Texas"))
                    {
                        statevalue = "TX";
                    }
                    else if(statedata.equals("Utah"))
                    {
                        statevalue = "UT";
                    }
                    else if(statedata.equals("Vermont"))
                    {
                        statevalue = "VT";
                    }
                    else if(statedata.equals("Virginia"))
                    {
                        statevalue = "VA";
                    }
                    else if(statedata.equals("Washington"))
                    {
                        statevalue = "WA";
                    }
                    else if(statedata.equals("West Virginia"))
                    {
                        statevalue = "WV";
                    }
                    else if(statedata.equals("Wisconsin"))
                    {
                        statevalue = "WI";
                    }
                    else if(statedata.equals("Wyoming"))
                    {
                        statevalue = "WY";
                    }*/
                    statevalue = statedata;

                    RadioButton fahr= (RadioButton)findViewById(R.id.fahrenheit);
                    String unit = "Celcius";
                    RadioGroup radiogrp = (RadioGroup)findViewById(R.id.radiogroup);
                    if(fahr.isChecked())
                    {
                        unit = "Fahrenheit";
                    }
                    new Weatherdata().execute(streetaddressdata, citynamedata, statevalue, unit);

                 }
                break;
            case R.id.clear:
                errormessage.setText("");
                streetaddress.setText("");
                cityname.setText("");
                state.setSelection(0);
                fahrenheit.setChecked(true);
                break;
            case R.id.about:
                startActivity(new Intent("com.webtech.chethan.cvwebtechhw9.About"));
                break;
            case R.id.imageView:
                OpenForeCastIO();
            default:
                break;

        }
    }

    public void OpenForeCastIO()
    {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse("http://forecast.io"));
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }





    /**
     * Background Async Task to Load all product by making HTTP Request
     * */

    class Weatherdata extends AsyncTask<String, String, String>
    {
        /**
         * Before starting background thread Show Progress Dialog
         * */



        InputStream inputStream = null;
        String result = "";

        String streetaddressvalue = "";

        String citynamevalue = "";

        String unit = "Celcius";

        String statevalue = "";

        @Override
        protected String doInBackground(String... args)
        {

            HttpURLConnection httpUrlConnection;
            URLConnection conn;
            URL url;
            StringBuilder builder = null;
            try {
                String params1 = args[0];
                String params2 = args[1];
                String params3 = args[2];
                String params4 = args[3];

                String street = params1;
                String city = params2;
                streetaddressvalue = params1;
                citynamevalue = params2;
                statevalue = params3;
                unit = params4;
                String streetaddressvalueurl = streetaddressvalue;
                String citynamevalueurl = citynamevalue;
                streetaddressvalueurl = street.replaceAll("\\s","%20");
                citynamevalueurl = city.replaceAll("\\s","%20");

                String URLPHP = "http://cs-server.usc.edu:38917/index.php/?StreetAddress="+streetaddressvalueurl+"&Cityname="+citynamevalueurl+"&State="+statevalue+"&Temperature="+unit;
               // String URLPHP = "http://cs-server.usc.edu:38917/index.php/?StreetAddress=USC&Cityname=LosAngeles&State=CA&Temperature=Celcius";
                url = new URL(URLPHP);
                conn = url.openConnection();
                httpUrlConnection = (HttpURLConnection) conn;
                httpUrlConnection.setRequestMethod("GET");
                httpUrlConnection.connect();
                int responsecode = httpUrlConnection.getResponseCode();
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        conn.getInputStream()));
                BufferedReader reader;
                reader = new BufferedReader(new InputStreamReader( conn.getInputStream()));

                builder = new StringBuilder();

                String line = null;

                //Error in here
                while ((line = reader.readLine()) != null)
                {
                    builder.append(line) ;
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }



            return builder.toString();
        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String result)
        {



                Intent i = new Intent(MainActivity.this, Results.class);
                i.putExtra("JSONDATA", result);
                i.putExtra("Street", streetaddressvalue);
                i.putExtra("City", citynamevalue);
                i.putExtra("State", statevalue);
                i.putExtra("Unit", unit);
                startActivity(i);


            //Intent intent = new Intent(LoginActivity.this,GenereMusicale.class);
            //startActivity(intent);

        }

    }






}
