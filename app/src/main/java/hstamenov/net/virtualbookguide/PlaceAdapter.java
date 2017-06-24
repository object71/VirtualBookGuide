package hstamenov.net.virtualbookguide;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import hstamenov.net.virtualbookguide.models.Place;

/**
 * Created by hstamenov on 24.6.2017 г..
 */

public class PlaceAdapter extends ArrayAdapter<Place> implements View.OnClickListener {

    private ArrayList<Place> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView name;
        TextView address;
        ImageView icon;
    }

    public PlaceAdapter(ArrayList<Place> data, Context context) {
        super(context, R.layout.listview_item, data);
        this.dataSet = data;
        this.mContext=context;

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

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        lastPosition = position;

        viewHolder.name.setText(dataModel.name);
        viewHolder.address.setText(dataModel.address);
        viewHolder.icon.setOnClickListener(this);
        viewHolder.icon.setTag(position);
        // Return the completed view to render on screen
        return convertView;
    }
}
