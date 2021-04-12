/*
Layth Mahfoodh s991523983 PROG38448
 */

package layth.mahfoodh.s991523983;



import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;

//import android.support.v7.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;



import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
// used to display the map
public class WebServiceFrag extends Fragment {

    private TextView txtDisplayWeather;
    private EditText txtLat, txtLong;
    private String latitude;
//    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        /*
        @Override
        public void onMapReady(GoogleMap googleMap) {
            LatLng sydney = new LatLng(-34, 151);
            googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        }
    };
*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

         View view =inflater.inflate(R.layout.frag_webservice, container, false);

        txtDisplayWeather = (TextView)view.findViewById(R.id.txtDisplayWeather);
        new ReadJSONFeedTask().execute(
                "http://extjs.org.cn/extjs/examples/grid/survey.html");


       final  Button submitWeather  = (Button)view.findViewById(R.id.btnWeather);
        txtLat = (EditText)view.findViewById(R.id.txtLat);
    //    latitude = txtLat.getText().toString();



        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Enter a Name")
                .setTitle("Mandatory field ex.");

        builder.setView(view);




        submitWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             //   getWeather1(view);

                //read geocoordinates from edit texts
                //
     //           txtLat = (EditText)view.findViewById(R.id.txtLat);
                //    txtLong = (EditText)findViewById(R.id.txtLong);
              latitude = txtLat.getText().toString();
                //  String longitude = txtLong.getText().toString();
if(latitude.isEmpty()&& latitude.length()<5){
    txtLat.setError("Wrong entery!!");
}
else {
    //get weather information using geo coordinates
    //this method calls OpenWeatherMap API
    //
    //create the URL to call JSON service
    //"http://api.openweathermap.org/data/2.5/weather?lat=35&lon=139&appid=13f04464b7119837cf1dc4fa8b39caa3");

    String url = "https://api.openweathermap.org/data/2.5/weather?";

    url += "zip=" + latitude;
    //   url+="lat="+latitude;
    //  url+="&lon="+longitude;


    url += "&appid=23f04464b7119837cf1dc4fa8b39caa3"; //from OpenWeatherMap website
    Log.d("URL", url);
    new ReadJSONFeedTask().execute(url);
    //new ReadJSONFeedTask().execute(
    //        "https://api.openweathermap.org/data/2.5/weather?lat=35&lon=139&appid=13f04464b7119837cf1dc4fa8b39caa3");

}
            }


        });

        return view;
    }



    public void getWeather1(View view)
    {
        //read geocoordinates from edit texts
        txtLat = (EditText)view.findViewById(R.id.txtLat);
        //    txtLong = (EditText)findViewById(R.id.txtLong);
        latitude = txtLat.getText().toString();
        //  String longitude = txtLong.getText().toString();

        //get weather information using geo coordinates
        //this method calls OpenWeatherMap API
        //
        //create the URL to call JSON service
        //"http://api.openweathermap.org/data/2.5/weather?lat=35&lon=139&appid=13f04464b7119837cf1dc4fa8b39caa3");

        String url = "https://api.openweathermap.org/data/2.5/weather?";

        url+="zip="+latitude;
        //   url+="lat="+latitude;
        //  url+="&lon="+longitude;


        url+="&appid=23f04464b7119837cf1dc4fa8b39caa3"; //from OpenWeatherMap website
        Log.d("URL",url);
        new ReadJSONFeedTask().execute(url);
        //new ReadJSONFeedTask().execute(
        //        "https://api.openweathermap.org/data/2.5/weather?lat=35&lon=139&appid=13f04464b7119837cf1dc4fa8b39caa3");








    }


    public String readJSONFeed(String address) {
        URL url = null;
        try {
            url = new URL(address);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        };
        StringBuilder stringBuilder = new StringBuilder();
        HttpURLConnection urlConnection = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            InputStream content = new BufferedInputStream(
                    urlConnection.getInputStream());
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(content));
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }
        return stringBuilder.toString();
    }
    private class ReadJSONFeedTask extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... urls) {
            return readJSONFeed(urls[0]);
        }
        protected void onPostExecute(String result) {
            try {
                //JSONArray jsonArray = new JSONArray(result);
                //Uncomment the two rows below to parse weather data from OpenWeatherMap
                JSONObject weatherJson = new JSONObject(result);
                JSONArray dataArray1= weatherJson.getJSONArray("weather");
                String strResults="Weather\n";
                for (int i = 0; i < dataArray1.length(); i++) {
                    JSONObject jsonObject = dataArray1.getJSONObject(i);

                    /*
                    strResults +="\nlon: "+jsonObject.getString("lon");
                    strResults +="\nlat: "+jsonObject.getString("lat");
                    strResults +="\nhumidity: "+jsonObject.getString("humidity");
                    strResults +="\nname: "+jsonObject.getString("name");
                    strResults +="\ntemp: "+jsonObject.getString("temp");
                    strResults +="\nlatitude: "+jsonObject.getString("latitude");
                   */

                    //      strResults +="id: "+jsonObject.getString("id");
                    //      strResults +="\nmain: "+jsonObject.getString("main");
                    //       strResults +="\ndescription: "+jsonObject.getString("description");


                }
                //
                JSONObject dataObject= weatherJson.getJSONObject("main");

                //        strResults +="\nlon: "+dataObject.getString("lon");
                //        strResults +="\nlat: "+dataObject.getString("lat");
                strResults +="\ntemp: "+dataObject.getString("temp");
                strResults +="\nhumidity: "+dataObject.getString("humidity");
                //   strResults +="\nname: "+dataObject.getString("name");
                //      strResults +="\ntemp: "+dataObject.getString("temp");
                //    strResults +="\nzip: "+dataObject.getString("zip");
                //         strResults +="\ntemp_min: "+dataObject.getString("temp_min");
                //         strResults +="\ntemp_max: "+dataObject.getString("temp_max");

                JSONObject dataObject1= weatherJson.getJSONObject("coord");
                strResults +="\nlon: "+dataObject1.getString("lon");
                strResults +="\nlat: "+dataObject1.getString("lat");
                strResults +="\nzip: "+latitude;


                //  JSONObject dataObject2= weatherJson.getJSONObject("name");
                //  strResults +="\nname: "+dataObject2.


                //
                txtDisplayWeather.setText(strResults);
                //txtDisplayWeather.setText(weatherJson.getString("weather"));
                //
                //uncomment the code below for parsing survey data
                /*
                JSONArray jsonArray = new JSONArray(result);
                Log.i("JSON", "Number of surveys in feed: " + jsonArray.length());
                //---print out the content of the json feed---
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Log.d("survey",jsonObject.getString("surveyDate"));
                    Toast.makeText(getBaseContext(),
                            jsonObject.getString("surveyTime") +
                                    " - " + jsonObject.getString("appeId"),
                            Toast.LENGTH_SHORT).show();
                }
                */
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }








    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
   //     SupportMapFragment mapFragment =
   //             (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
   //     if (mapFragment != null) {
   ////         mapFragment.getMapAsync(callback);
    //}
    }
}