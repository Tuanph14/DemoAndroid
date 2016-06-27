package com.edu.t2f.asynctask;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by tuan on 06/27/2016.
 */
public class MyAdapter extends ArrayAdapter {

    Context mContext;
    int resource;
    List<Country> countries;
    public MyAdapter(Context context, int resource, List<Country> objects) {
        super(context, resource, objects);
        mContext = context;
        this.resource = resource;
        countries = objects;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        if(convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater  inflater = (LayoutInflater)
                    mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(resource,parent,false);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.flag);
            viewHolder.Name = (TextView) convertView.findViewById(R.id.tvNameEn);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.imageView.setImageResource(R.drawable.vietnam);
        viewHolder.Name.setText(countries.get(position).getNameEn());
        return convertView;
    }

    class ViewHolder{
        TextView Name;
        ImageView imageView;
    }

}
