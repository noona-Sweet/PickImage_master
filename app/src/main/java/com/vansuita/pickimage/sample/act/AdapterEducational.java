package com.vansuita.pickimage.sample.act;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.vansuita.pickimage.sample.R;

import java.util.ArrayList;

public class AdapterEducational extends BaseAdapter {


    private Context context;
    private int layout;
    private ArrayList<Advertis> advertislist;


    public AdapterEducational(Context context, int layout, ArrayList<Advertis> advertislist) {
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

        AdapterEducational.ViewHolder holder = new AdapterEducational.ViewHolder();

        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.row_edu, parent, false);
            holder.titles = row.findViewById(R.id.titledu2);
            holder.descriptions = row.findViewById(R.id.desceu2);
            holder.phones = row.findViewById(R.id.phoneu2);
            holder.image1 = row.findViewById(R.id.imgeedu);
            holder.shows = row.findViewById(R.id.viewedu);
            row.setTag(holder);
        } else {
            holder = (AdapterEducational.ViewHolder) row.getTag();
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

