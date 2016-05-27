package com.edu.t2f.databasecountry;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by tuan on 05/23/2016.
 */
public class AdapterCountry extends ArrayAdapter<Country> {

    Context mcontext;
    int mresource;
    List <Country> countries;

    public AdapterCountry(Context context, int resource, List<Country> objects) {
        super(context, resource, objects);
        mcontext = context;
        mresource = resource;
        countries = objects;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.customelist, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.tvNameEn = (TextView) convertView.findViewById(R.id.tvNameEn);
            viewHolder.tvNameVi = (TextView) convertView.findViewById(R.id.tvNameVi);
            viewHolder.ivflag = (ImageView) convertView.findViewById(R.id.ivflag);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tvNameEn.setText(countries.get(position).getNameEn());
        viewHolder.tvNameVi.setText(countries.get(position).getNameVi());

        return convertView;
    }
    class  ViewHolder{
        TextView tvNameEn;
        TextView tvNameVi;

        ImageView ivflag;
    }
}
