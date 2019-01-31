package demo.onstar.car.suresofttech.com.carremotecontroldemo.utils;
import demo.onstar.car.suresofttech.com.carremotecontroldemo.R;

import android.graphics.Color;
import android.widget.BaseAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class BinderData extends BaseAdapter {

    // XML node keys
    static final String KEY_TAG = "weatherdata"; // parent node
    static final String KEY_ID = "id";
    static final String KEY_CITY = "city";
    static final String KEY_TEMP_C = "tempc";
    static final String KEY_TEMP_F = "tempf";
    static final String KEY_CONDN = "condition";
    static final String KEY_SPEED = "windspeed";
    static final String KEY_ICON = "icon";

    LayoutInflater inflater;
    ImageView thumb_image;
    List<HashMap<String,String>> weatherDataCollection;
    ViewHolder holder;
    ImageView arrowImageView;
    public BinderData() {
        // TODO Auto-generated constructor stub
    }

    public BinderData(Activity act, List<HashMap<String,String>> map) {

        this.weatherDataCollection = map;

        inflater = (LayoutInflater) act
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    public int getCount() {
        // TODO Auto-generated method stub
//		return idlist.size();
        return weatherDataCollection.size();
    }

    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View vi=convertView;
        if(convertView==null){

            vi = inflater.inflate(R.layout.list_row, null);
            holder = new ViewHolder();

            holder.tvCity = (TextView)vi.findViewById(R.id.tvCity); // city name
            holder.tvWeather = (TextView)vi.findViewById(R.id.tvCondition); // city weather overview
            holder.tvTemperature =  (TextView)vi.findViewById(R.id.tvTemp); // city temperature
            holder.tvWeatherImage =(ImageView)vi.findViewById(R.id.list_image); // thumb image
            arrowImageView = (ImageView)vi.findViewById(R.id.imageView1);

            vi.setTag(holder);
        }
        else{

            holder = (ViewHolder)vi.getTag();
        }

        // Setting all values in listview

        holder.tvCity.setText(weatherDataCollection.get(position).get(KEY_CITY));
        holder.tvWeather.setText(weatherDataCollection.get(position).get(KEY_CONDN));
        holder.tvTemperature.setText(weatherDataCollection.get(position).get(KEY_TEMP_C));

        if(weatherDataCollection.get(position).get(KEY_CITY).equals("Next service") ||
                weatherDataCollection.get(position).get(KEY_CITY).equals("Brakepads") ){
            arrowImageView.setVisibility(View.GONE);
            arrowImageView.setSelected(false);
        }
        if(weatherDataCollection.get(position).get(KEY_CITY).equals("Next service") ||
                weatherDataCollection.get(position).get(KEY_CITY).equals("Trips") ||
                weatherDataCollection.get(position).get(KEY_CITY).equals("Brakepads")){
            holder.tvWeather.setTextColor(Color.rgb(219,151,0));
        }else if(weatherDataCollection.get(position).get(KEY_CITY).equals("Fuel level")){
            holder.tvWeather.setTextColor(Color.RED);
        }else if(weatherDataCollection.get(position).get(KEY_CITY).equals("Vehicle")){
            holder.tvWeather.setTextColor(Color.rgb(36,120,255));
        }else{
            holder.tvWeather.setTextColor(Color.BLACK);
        }

        //Setting an image
        String uri = "drawable/"+ weatherDataCollection.get(position).get(KEY_ICON);
        int imageResource = vi.getContext().getApplicationContext().getResources().getIdentifier(uri, null, vi.getContext().getApplicationContext().getPackageName());
        Drawable image = vi.getContext().getResources().getDrawable(imageResource);
        holder.tvWeatherImage.setImageDrawable(image);

        return vi;
    }

    /*
     *
     * */
    static class ViewHolder{

        TextView tvCity;
        TextView tvTemperature;
        TextView tvWeather;
        ImageView tvWeatherImage;
    }

}