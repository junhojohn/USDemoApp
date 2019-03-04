package demo.onstar.car.suresofttech.com.carremotecontroldemo.utils;
import demo.onstar.car.suresofttech.com.carremotecontroldemo.R;

import android.graphics.Color;
import android.widget.BaseAdapter;

import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class BinderData extends BaseAdapter {

    // XML node keys
    static final String KEY_TAG = "vehicledata"; // parent node
    static final String KEY_ID = "id";
    static final String KEY_FUNC = "func";
    static final String KEY_CONDN = "condition";
    static final String KEY_ICON = "icon";

    LayoutInflater inflater;
    ImageView thumb_image;
    List<HashMap<String,String>> vehicleDataCollection;
    ViewHolder holder;
    ImageView arrowImageView;
    public BinderData() {
        // TODO Auto-generated constructor stub
    }

    public BinderData(Activity act, List<HashMap<String,String>> map) {

        this.vehicleDataCollection = map;

        inflater = (LayoutInflater) act
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    public int getCount() {
        // TODO Auto-generated method stub
//		return idlist.size();
        return vehicleDataCollection.size();
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

            holder.tvFunc = (TextView)vi.findViewById(R.id.tvFunc); // function name
            holder.tvCondn = (TextView)vi.findViewById(R.id.tvCondition); // value
            holder.tvImage =(ImageView)vi.findViewById(R.id.list_image); // thumb image
            arrowImageView = (ImageView)vi.findViewById(R.id.imageView1);

            vi.setTag(holder);
        }
        else{

            holder = (ViewHolder)vi.getTag();
        }

        // Setting all values in listview

        holder.tvFunc.setText(vehicleDataCollection.get(position).get(KEY_FUNC));
        holder.tvCondn.setText(vehicleDataCollection.get(position).get(KEY_CONDN));

        if(vehicleDataCollection.get(position).get(KEY_FUNC).equals("Next service") ||
                vehicleDataCollection.get(position).get(KEY_FUNC).equals("Brakepads") ){
            arrowImageView.setVisibility(View.GONE);
            arrowImageView.setSelected(false);
        }
        if(vehicleDataCollection.get(position).get(KEY_FUNC).equals("Next service") ||
                vehicleDataCollection.get(position).get(KEY_FUNC).equals("Trips") ||
                vehicleDataCollection.get(position).get(KEY_FUNC).equals("Brakepads")){
            holder.tvCondn.setTextColor(Color.rgb(219,151,0));
        }else if(vehicleDataCollection.get(position).get(KEY_FUNC).equals("Fuel level")){
            holder.tvCondn.setTextColor(Color.RED);
        }else if(vehicleDataCollection.get(position).get(KEY_FUNC).equals("Vehicle")){
            holder.tvCondn.setTextColor(Color.rgb(36,120,255));
        }else{
            holder.tvCondn.setTextColor(Color.BLACK);
        }

        //Setting an image
        String uri = "drawable/"+ vehicleDataCollection.get(position).get(KEY_ICON);
        int imageResource = vi.getContext().getApplicationContext().getResources().getIdentifier(uri, null, vi.getContext().getApplicationContext().getPackageName());
        Drawable image = vi.getContext().getResources().getDrawable(imageResource);
        holder.tvImage.setImageDrawable(image);

        return vi;
    }

    /*
     *
     * */
    static class ViewHolder{

        TextView tvFunc;
        TextView tvCondn;
        ImageView tvImage;
    }

}