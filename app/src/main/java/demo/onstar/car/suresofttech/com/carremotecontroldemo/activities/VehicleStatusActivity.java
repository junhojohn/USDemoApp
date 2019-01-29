package demo.onstar.car.suresofttech.com.carremotecontroldemo.activities;

import demo.onstar.car.suresofttech.com.carremotecontroldemo.R;
import demo.onstar.car.suresofttech.com.carremotecontroldemo.utils.BinderData;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class VehicleStatusActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{
    // XML node keys
    static final String KEY_TAG = "weatherdata"; // parent node
    static final String KEY_ID = "id";
    static final String KEY_CITY = "city";
    static final String KEY_TEMP_C = "tempc";
    static final String KEY_TEMP_F = "tempf";
    static final String KEY_CONDN = "condition";
    static final String KEY_SPEED = "windspeed";
    static final String KEY_ICON = "icon";

    // List items
    ListView list;
    BinderData adapter = null;
    List<HashMap<String,String>> weatherDataCollection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_status);

        getXMLParser();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        createToolbarIcon();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void getXMLParser(){
        try {
            // https://www.codeproject.com/Articles/507651/Customized-Android-ListView-with-Image-and-Text
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse (getAssets().open("weatherdata.xml"));

            weatherDataCollection = new ArrayList<HashMap<String,String>>();

            // normalize text representation
            doc.getDocumentElement ().normalize ();

            NodeList weatherList = doc.getElementsByTagName("weatherdata");

            HashMap<String,String> map = null;

            for (int i = 0; i < weatherList.getLength(); i++) {

                map = new HashMap<String,String>();

                Node firstWeatherNode = weatherList.item(i);

                if(firstWeatherNode.getNodeType() == Node.ELEMENT_NODE){

                    Element firstWeatherElement = (Element)firstWeatherNode;
                    //-------
                    NodeList idList = firstWeatherElement.getElementsByTagName(KEY_ID);
                    Element firstIdElement = (Element)idList.item(0);
                    NodeList textIdList = firstIdElement.getChildNodes();
                    //--id
                    map.put(KEY_ID, ((Node)textIdList.item(0)).getNodeValue().trim());

                    //2.-------
                    NodeList cityList = firstWeatherElement.getElementsByTagName(KEY_CITY);
                    Element firstCityElement = (Element)cityList.item(0);
                    NodeList textCityList = firstCityElement.getChildNodes();
                    //--city
                    map.put(KEY_CITY, ((Node)textCityList.item(0)).getNodeValue().trim());

                    //3.-------
                    NodeList tempList = firstWeatherElement.getElementsByTagName(KEY_TEMP_C);
                    Element firstTempElement = (Element)tempList.item(0);
                    NodeList textTempList = firstTempElement.getChildNodes();
                    //--city
                    map.put(KEY_TEMP_C, ((Node)textTempList.item(0)).getNodeValue().trim());

                    //4.-------
                    NodeList condList = firstWeatherElement.getElementsByTagName(KEY_CONDN);
                    Element firstCondElement = (Element)condList.item(0);
                    NodeList textCondList = firstCondElement.getChildNodes();
                    //--city
                    map.put(KEY_CONDN, ((Node)textCondList.item(0)).getNodeValue().trim());

                    //5.-------
                    NodeList speedList = firstWeatherElement.getElementsByTagName(KEY_SPEED);
                    Element firstSpeedElement = (Element)speedList.item(0);
                    NodeList textSpeedList = firstSpeedElement.getChildNodes();
                    //--city
                    map.put(KEY_SPEED, ((Node)textSpeedList.item(0)).getNodeValue().trim());

                    //6.-------
                    NodeList iconList = firstWeatherElement.getElementsByTagName(KEY_ICON);
                    Element firstIconElement = (Element)iconList.item(0);
                    NodeList textIconList = firstIconElement.getChildNodes();
                    //--city
                    map.put(KEY_ICON, ((Node)textIconList.item(0)).getNodeValue().trim());

                    //Add to the Arraylist
                    weatherDataCollection.add(map);
                }
            }


            BinderData bindingData = new BinderData(this,weatherDataCollection);


            list = (ListView) findViewById(R.id.list);

            Log.i("BEFORE", "<<------------- Before SetAdapter-------------->>");

            list.setAdapter(bindingData);

            Log.i("AFTER", "<<------------- After SetAdapter-------------->>");

            // Click event for single list row
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {

                    Intent i = new Intent();
                    i.setClass(VehicleStatusActivity.this, DetailPageActivity.class);

                    // parameters
                    i.putExtra("position", String.valueOf(position + 1));

                    /* selected item parameters
                     * 1.	City name
                     * 2.	Weather
                     * 3.	Wind speed
                     * 4.	Temperature
                     * 5.	Weather icon
                     */
                    i.putExtra("city", weatherDataCollection.get(position).get(KEY_CITY));
                    i.putExtra("weather", weatherDataCollection.get(position).get(KEY_CONDN));
                    i.putExtra("windspeed", weatherDataCollection.get(position).get(KEY_SPEED));
                    i.putExtra("temperature", weatherDataCollection.get(position).get(KEY_TEMP_C));
                    i.putExtra("icon", weatherDataCollection.get(position).get(KEY_ICON));

                    // start the sample activity
                    startActivity(i);
                }
            });

        }

        catch (IOException ex) {
            Log.e("Error", ex.getMessage());
        }
        catch (Exception ex) {
            Log.e("Error", "Loading exception");
        }
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_remote_control_system, menu);
//        return true;
        return false;
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_remote_control) {
            Intent intent = new Intent(VehicleStatusActivity.this, RemoteControlSystemActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_navigation_map) {
            Intent intent = new Intent(VehicleStatusActivity.this, OnStarMapActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_vehicle_status) {

        } else if (id == R.id.nav_system_configuration) {
            Intent intent = new Intent(VehicleStatusActivity.this, ConfigurationActivity.class);
            startActivity(intent);
        } else if(id == R.id.nav_logout){
            Intent intent = new Intent(VehicleStatusActivity.this, MainActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void createToolbarIcon(){
        ActionBar ab = getSupportActionBar() ;

        ab.setIcon(R.drawable.img_remote_control);
        ab.setDisplayUseLogoEnabled(true) ;
        ab.setDisplayShowHomeEnabled(true) ;
    }
}
