package com.webtech.chethan.cvwebtechhw9;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.hamweather.aeris.communication.AerisEngine;

import org.json.JSONException;
import org.json.JSONObject;

public class MapActivity extends Activity implements MapFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_map);

        AerisEngine.initWithKeys(this.getString(R.string.aeris_client_id), this.getString(R.string.aeris_client_secret), this);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        //add a fragment
        MapFragment myFragment = new MapFragment();
        Intent intent = getIntent();
        String JSONARRAY = "";;
        String jsonArray = intent.getStringExtra("JSONDATA");
        JSONARRAY = jsonArray;

        try {
            JSONObject Obj = new JSONObject(getIntent().getStringExtra("JSONDATA"));
            String latvalue =  Obj.getString("lat");
            String longvalue =  Obj.getString("long");


            Bundle bundle = new Bundle();
            bundle.putString("lat", latvalue);
            bundle.putString("long", longvalue);
            // set Fragmentclass Arguments
            myFragment.setArguments(bundle);



        }
        catch(JSONException e)
        {
            e.printStackTrace();
        }



        fragmentTransaction.add(R.id.MapFrame, myFragment);
        fragmentTransaction.commit();
    }
    public void onFragmentInteraction(Uri uri)
    {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_map, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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
}
