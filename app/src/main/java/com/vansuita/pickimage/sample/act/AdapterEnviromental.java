package com.vansuita.pickimage.sample.act;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.vansuita.pickimage.sample.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class AdapterEnviromental extends BaseAdapter {


    private Context context;
    private int layout;
    private ArrayList<Advertis> advertislist;


    public AdapterEnviromental(Context context, int layout, ArrayList<Advertis> advertislist) {
        this.context = context;
        this.layout = layout;
        this.advertislist = advertislist;
    }

    @Override
    public int getCount() {
        return advertislist.size();
    }

    @Override
    public Object getItem(int i) {
        return advertislist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHolder {
        ImageView image1, shows, participate, accept, recommend;
        TextView titles, descriptions, phones;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {

        View row = view;

        AdapterEnviromental.ViewHolder holder = new AdapterEnviromental.ViewHolder();

        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.row_envi, parent, false);
            holder.titles = row.findViewById(R.id.titleen2);
            holder.descriptions = row.findViewById(R.id.descen2);
            holder.phones = row.findViewById(R.id.phonen2);
            holder.image1 = row.findViewById(R.id.imgenvi);
            holder.shows = row.findViewById(R.id.viewenvi);
            row.setTag(holder);
        } else {
            holder = (AdapterEnviromental.ViewHolder) row.getTag();
        }
        Advertis model = advertislist.get(i);

        holder.titles.setText(model.getTitle());
        holder.descriptions.setText(model.getDecription());
        holder.phones.setText(model.getPhones());

        byte[] recordimage = model.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(recordimage, 0, recordimage.length);
        holder.image1.setImageBitmap(bitmap);
        return row;
    }
}
