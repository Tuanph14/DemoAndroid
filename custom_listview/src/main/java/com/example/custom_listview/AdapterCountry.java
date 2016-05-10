package com.example.custom_listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by tuan on 05/07/2016.
 */
public class AdapterCountry extends BaseAdapter{

    Context context;
    int layoutRes;
    List<Country> countries;


    public AdapterCountry(Context context, int layoutRes, List<Country> countries) {
        this.context = context;
        this.layoutRes = layoutRes;
        this.countries = countries;
    }

    @Override
    public int getCount() {
        return countries.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(layoutRes, parent, false);

            viewHolder = new ViewHolder();

            viewHolder.tvNameEn = (TextView) convertView.findViewById(R.id.tvNameEn);
            viewHolder.tvNameVi = (TextView) convertView.findViewById(R.id.tvNameVi);
            viewHolder.image    = (ImageView) convertView.findViewById(R.id.flag);
            convertView.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvNameEn.setText(countries.get(position).getNameEn());
        viewHolder.tvNameVi.setText(countries.get(position).getNameVi());
        viewHolder.image.setImageResource(countries.get(position).getImage());

        return convertView;
    }

    class ViewHolder{
        TextView tvNameEn;
        TextView tvNameVi;
        ImageView image;

    }
}
