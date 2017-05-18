package com.webtech.chethan.cvwebtechhw9;

import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.graphics.Color;
import android.view.WindowManager;
import android.widget.Button;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Gravity;

import android.widget.LinearLayout;

import android.content.Intent;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.view.View.OnClickListener;

import org.json.JSONException;
import org.json.JSONObject;

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnplus;
    Button btnday;
    Button btnweek;
    View inflatedlayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        LinearLayout codeLearnLessons = (LinearLayout)findViewById(R.id.shareLILayout);
        //LayoutInflater inflater = (LayoutInflater) DetailsActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        codeLearnLessons.setOrientation(LinearLayout.VERTICAL);


        Intent intent = getIntent();
        String jsonArray = intent.getStringExtra("JSONDATA");

        JSONObject Obj = null;
        String city = "";
        try {

            Obj = new JSONObject(jsonArray);
            city = Obj.getString("city");
            city = city+", "+Obj.getString("state");
        }catch (JSONException e) {
            e.printStackTrace();
        }

        TextView Summary = (TextView)findViewById(R.id.moredetailssummary);
        Summary.setText("More Details for "+city);

        TableRow tRow = (TableRow)findViewById(R.id.tablerow);
        tRow.setVisibility(TableRow.VISIBLE);

        Nextday(jsonArray, codeLearnLessons);

        Button day = (Button)findViewById(R.id.day);
        Button week = (Button)findViewById(R.id.week);
        day.setBackgroundResource(R.drawable.btnclick);
        week.setBackgroundResource(R.drawable.btnnormal);


        btnday = (Button)findViewById(R.id.day);
        btnday.setOnClickListener(this);


        btnweek = (Button)findViewById(R.id.week);
        btnweek.setOnClickListener(this);

    }
    public void Nextday(String jsonArray, LinearLayout codeLearnLessons)
    {
        TextView temprow = (TextView)findViewById(R.id.temprow);
        JSONObject Obj = null;
        String tempunit;
        try {

            Obj = new JSONObject(jsonArray);
            tempunit = Obj.getString("tempUnits");
            if(tempunit.equals("&#8451"))
                tempunit = "C";
            else tempunit = "F";
            temprow.setText("Temp("+"\u00b0"+tempunit+")");
        }catch (JSONException e) {
            e.printStackTrace();
        }


        LayoutInflater inflater = LayoutInflater.from(getBaseContext());
        for(int i = 0;  i< 24; i++)
        {

            String timephp = "t";
            String tempphp = "tmp";
            String iconvalue = "cloudy";
            try {
                Obj = new JSONObject(jsonArray);

                timephp = Obj.get("t"+i).toString();

                tempphp = Obj.get("tmp"+i).toString();
                iconvalue =  Obj.get("icon"+i).toString();

            } catch (JSONException e) {
                e.printStackTrace();
            }

            inflater = LayoutInflater.from(getBaseContext());
            inflatedlayout = inflater.inflate(R.layout.listview, codeLearnLessons, false);
            if(i % 2 != 0)
            {
                inflatedlayout.setBackgroundColor(Color.WHITE);
            }
            else
            {
                inflatedlayout.setBackgroundColor(Color.LTGRAY);
            }

            ImageView icon = (ImageView) inflatedlayout.findViewById(R.id.icon);


            if(iconvalue.equals("clear-day"))
                icon.setImageResource(R.drawable.clear);

            else if(iconvalue.equals("clear-night"))
                icon.setImageResource(R.drawable.clear_night);

            else if(iconvalue.equals("rain"))
                icon.setImageResource(R.drawable.rain);

            else if(iconvalue.equals("snow"))
                icon.setImageResource(R.drawable.snow);

            else if(iconvalue.equals("sleet"))
                icon.setImageResource(R.drawable.sleet);

            else  if(iconvalue.equals("wind"))
                icon.setImageResource(R.drawable.wind);

            else if(iconvalue.equals("fog"))
                icon.setImageResource(R.drawable.fog);

            else if(iconvalue.equals("cloudy"))
                icon.setImageResource(R.drawable.cloudy);

            else if(iconvalue.equals("partly-cloudy-day"))
                icon.setImageResource(R.drawable.cloud_day);

            else if(iconvalue.equals("partly-cloudy-night"))
                icon.setImageResource(R.drawable.cloud_night);





            codeLearnLessons.addView(inflatedlayout);
            TextView time = (TextView) inflatedlayout.findViewById(R.id.time);
            time.setText(timephp);

            TextView temp = (TextView) inflatedlayout.findViewById(R.id.temp);
            temp.setText(tempphp);

        }
        inflatedlayout = inflater.inflate(R.layout.activity_plus_button_row, codeLearnLessons, false);
        inflatedlayout.setBackgroundColor(Color.LTGRAY);
        codeLearnLessons.addView(inflatedlayout);
        btnplus = (Button)findViewById(R.id.plus);
        btnplus.setOnClickListener(this);

    }
    public void onClick(View v) {


        Intent intent = getIntent();
        String jsonArray = intent.getStringExtra("JSONDATA");


        if(R.id.plus == v.getId())
        {


            LinearLayout codeLearnLessons = (LinearLayout)findViewById(R.id.shareLILayout);
            codeLearnLessons.removeViewAt(24);

            LayoutInflater inflater = (LayoutInflater) DetailsActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            codeLearnLessons.setOrientation(LinearLayout.VERTICAL);

            TextView time;
            TextView temp;

            String iconvalue = "cloudy";
            for(int i = 24;  i< 48; i++)
            {

                String timephp = "t";
                String tempphp = "tmp";
                try {
                    JSONObject Obj = null;
                    Obj = new JSONObject(jsonArray);

                    timephp = Obj.get("t"+i).toString();

                    tempphp = Obj.get("tmp"+i).toString();
                    iconvalue =  Obj.get("icon"+i).toString();

                } catch (JSONException e) {
                    e.printStackTrace();
                }


                inflatedlayout = inflater.inflate(R.layout.listview, codeLearnLessons, false);
                if(i % 2 != 0)
                {
                    inflatedlayout.setBackgroundColor(Color.WHITE);
                }
                else
                {
                    inflatedlayout.setBackgroundColor(Color.LTGRAY);
                }

                codeLearnLessons.addView(inflatedlayout);
                time = (TextView) inflatedlayout.findViewById(R.id.time);
                time.setText(timephp);


                ImageView icon = (ImageView) inflatedlayout.findViewById(R.id.icon);

                if(iconvalue.equals("clear-day"))
                    icon.setImageResource(R.drawable.clear);

                else if(iconvalue.equals("clear-night"))
                    icon.setImageResource(R.drawable.clear_night);

                else if(iconvalue.equals("rain"))
                    icon.setImageResource(R.drawable.rain);

                else if(iconvalue.equals("snow"))
                    icon.setImageResource(R.drawable.snow);

                else if(iconvalue.equals("sleet"))
                    icon.setImageResource(R.drawable.sleet);

                else  if(iconvalue.equals("wind"))
                    icon.setImageResource(R.drawable.wind);

                else if(iconvalue.equals("fog"))
                    icon.setImageResource(R.drawable.fog);

                else if(iconvalue.equals("cloudy"))
                    icon.setImageResource(R.drawable.cloudy);

                else if(iconvalue.equals("partly-cloudy-day"))
                    icon.setImageResource(R.drawable.cloud_day);

                else if(iconvalue.equals("partly-cloudy-night"))
                    icon.setImageResource(R.drawable.cloud_night);




                temp = (TextView) inflatedlayout.findViewById(R.id.temp);
                temp.setText(tempphp);

            }
        }

        Button daybtn = (Button)findViewById(R.id.day);
        Button weekbtn = (Button)findViewById(R.id.week);
        if(R.id.day == v.getId())
        {
            daybtn.setBackgroundResource(R.drawable.btnclick);
            weekbtn.setBackgroundResource(R.drawable.btnnormal);
            LinearLayout codeLearnLessons = (LinearLayout)findViewById(R.id.shareLILayout);
            codeLearnLessons.removeAllViews();
            Nextday(jsonArray, codeLearnLessons);
            TableRow tRow = (TableRow)findViewById(R.id.tablerow);
            tRow.setVisibility(TableRow.VISIBLE);

        }
        if(R.id.week == v.getId())
        {
            daybtn.setBackgroundResource(R.drawable.btnnormal);
            weekbtn.setBackgroundResource(R.drawable.btnclick);
            LinearLayout codeLearnLessons = (LinearLayout)findViewById(R.id.shareLILayout);
            codeLearnLessons.removeAllViews();

            TableRow tRow = (TableRow)findViewById(R.id.tablerow);
            tRow.setVisibility(TableRow.GONE);
            LayoutInflater inflater = (LayoutInflater) DetailsActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            codeLearnLessons.setOrientation(LinearLayout.VERTICAL);
            codeLearnLessons.setBackgroundColor(Color.WHITE);
            inflatedlayout.setBackgroundColor(Color.WHITE);

            for(int i = 1;  i<= 7; i++)
            {

                String day = "nextday";
                String nextdaydatemonth = "nextdaydatemonth";
                String MinTempValue = "mintmpvalue";
                String MaxTempValue = "maxtmpvalue";
                String tempunit = "";
                String iconvalue = "cloudy";
                try {
                    JSONObject Obj = null;
                    Obj = new JSONObject(jsonArray);

                    day = Obj.get("nextday"+i).toString();

                    nextdaydatemonth = Obj.get("nextdaydatemonth"+i).toString();

                    MinTempValue = Obj.get("mintmpvalue"+i).toString();

                    MaxTempValue = Obj.get("maxtmpvalue"+i).toString();

                    tempunit = Obj.getString("tempUnits");
                    iconvalue =  Obj.get("nextdayicon"+i).toString();

                } catch (JSONException e) {
                    e.printStackTrace();
                }



                inflatedlayout = inflater.inflate(R.layout.activity_listitemweek, codeLearnLessons, false);
                TextView summary = (TextView) inflatedlayout.findViewById(R.id.day);
                summary.setText(day + ", " + nextdaydatemonth);

                if(tempunit.equals("&#8451"))
                    tempunit = "C";
                else tempunit = "F";

                TextView minmax = (TextView) inflatedlayout.findViewById(R.id.minmax);
                minmax.setText("Min: "+MinTempValue+ "\u00b0" + tempunit + " | " +"Max: "+MaxTempValue+"\u00b0"+tempunit);
                summary.setText(day + ", " + nextdaydatemonth);


                ImageView icon = (ImageView) inflatedlayout.findViewById(R.id.weekicon);


                if(iconvalue.equals("clear-day"))
                    icon.setImageResource(R.drawable.clear);

                else if(iconvalue.equals("clear-night"))
                    icon.setImageResource(R.drawable.clear_night);

                else if(iconvalue.equals("rain"))
                    icon.setImageResource(R.drawable.rain);

                else if(iconvalue.equals("snow"))
                    icon.setImageResource(R.drawable.snow);

                else if(iconvalue.equals("sleet"))
                    icon.setImageResource(R.drawable.sleet);

                else  if(iconvalue.equals("wind"))
                    icon.setImageResource(R.drawable.wind);

                else if(iconvalue.equals("fog"))
                    icon.setImageResource(R.drawable.fog);

                else if(iconvalue.equals("cloudy"))
                    icon.setImageResource(R.drawable.cloudy);

                else if(iconvalue.equals("partly-cloudy-day"))
                    icon.setImageResource(R.drawable.cloud_day);

                else if(iconvalue.equals("partly-cloudy-night"))
                    icon.setImageResource(R.drawable.cloud_night);



                codeLearnLessons.addView(inflatedlayout);


                if(i == 1)
                {
                    inflatedlayout.setBackgroundColor(Color.argb(255, 255, 219, 106));
                }
                else if(i == 2)
                {
                    inflatedlayout.setBackgroundColor(Color.argb(255, 160, 231, 255));
                }
                else if(i == 3)
                {
                    inflatedlayout.setBackgroundColor(Color.argb(255, 255, 196, 234));
                }
                else if(i == 4)
                {
                    inflatedlayout.setBackgroundColor(Color.argb(255, 196, 255, 165));
                }
                else if(i == 5)
                {
                    inflatedlayout.setBackgroundColor(Color.argb(255, 255, 189, 183));
                }
                else if(i == 6)
                {
                    inflatedlayout.setBackgroundColor(Color.argb(255, 239, 255, 181));
                }
                else if(i == 7)
                {
                    inflatedlayout.setBackgroundColor(Color.argb(255, 187, 189, 254));
                }



            }


        }


    }



   /* public class CodeLearnAdapter extends BaseAdapter {


        List<codeLeanChapter> codeLeanChapterList = getDataForListView();
        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return codeLeanChapterList.size();
        }

        @Override
        public codeLeanChapter getItem(int arg0) {
            // TODO Auto-generated method stub
            return codeLeanChapterList.get(arg0);
        }

        @Override
        public long getItemId(int arg0) {
            // TODO Auto-generated method stub
            return arg0;
        }

        @Override
        public View getView(int arg0, View arg1, ViewGroup arg2) {





            if(arg1==null)
            {
                LayoutInflater inflater = (LayoutInflater) DetailsActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                arg1 = inflater.inflate(R.layout.listview, arg2,false);
                if(arg0 %2==0)
                {
                    arg1.setBackgroundColor(Color.LTGRAY);
                }
                else
                {
                    arg1.setBackgroundColor(Color.WHITE);
                }
            }

            TextView chapterName = (TextView)arg1.findViewById(R.id.time);
            TextView chapterDesc = (TextView)arg1.findViewById(R.id.temp);

            codeLeanChapter chapter = codeLeanChapterList.get(arg0);

            chapterName.setText(chapter.time);
            chapterDesc.setText(chapter.temperature);

            return arg1;
        }

        public codeLeanChapter getCodeLearnChapter(int position)
        {
            return codeLeanChapterList.get(position);
        }

    }
*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_details, menu);
        return true;
    }

    /*public List<codeLeanChapter> getDataForListView()
    {
        List<codeLeanChapter> codeLeanChaptersList = new ArrayList<codeLeanChapter>();

        for(int i=0;i<20;i++)
        {

            codeLeanChapter chapter = new codeLeanChapter();
            chapter.time = "Chapter "+i;
            chapter.temperature = "This is description for chapter "+i;
            codeLeanChaptersList.add(chapter);
        }

        return codeLeanChaptersList;

    }*/

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
