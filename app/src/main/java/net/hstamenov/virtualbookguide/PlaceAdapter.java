package net.hstamenov.virtualbookguide;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;

import java.util.ArrayList;
import java.util.List;

import net.hstamenov.virtualbookguide.models.Place;

/**
 * Created by hstamenov on 24.6.2017 г..
 */

public class PlaceAdapter extends ArrayAdapter<Place> implements View.OnClickListener {

    private List<Place> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView name;
        TextView address;
        ImageView icon;
    }

    public PlaceAdapter(List<Place> data, Context context) {
        super(context, R.layout.listview_item, data);
        this.dataSet = data;
        this.mContext = context;

    }

    @Override
    public void onClick(View v) {

        int position=(Integer) v.getTag();
        Object object= getItem(position);
        Place dataModel = (Place)object;
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Place dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.listview_item, parent, false);

            viewHolder.name = (TextView) convertView.findViewById(R.id.name);
            viewHolder.address = (TextView) convertView.findViewById(R.id.address);
            viewHolder.icon = (ImageView) convertView.findViewById(R.id.icon);

            result = convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        lastPosition = position;

        viewHolder.name.setText(dataModel.number + " : " + dataModel.name);
        viewHolder.address.setText(dataModel.address);

        if(dataModel.visited) {
            viewHolder.icon.setImageDrawable(ContextCompat.getDrawable(this.getContext(), R.drawable.ic_stamp));
        }

        // Return the completed view to render on screen
        return convertView;
    }
}
