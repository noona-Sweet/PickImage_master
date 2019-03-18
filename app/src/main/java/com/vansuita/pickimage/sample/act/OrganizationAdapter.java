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
import android.widget.Toast;

import com.vansuita.pickimage.sample.R;

import java.util.ArrayList;

public class OrganizationAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<Advertis> advertisArrayList;


    public OrganizationAdapter(Context context, int layout, ArrayList<Advertis> advertisArrayList) {
        this.context = context;
        this.layout = layout;
        this.advertisArrayList = advertisArrayList;
    }

    public int getCount() {
        return advertisArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return advertisArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHolder{
        ImageView imageView,viiew,d1, participate,accept,recommend;
        TextView titlee,descriptionn,phonee,count;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {

        View row =view;
        ViewHolder holder = new ViewHolder();

        if (row == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
            row = inflater.inflate( R.layout.row,parent,false );
            holder.titlee = row.findViewById( R.id.title2 );
            holder.descriptionn = row.findViewById( R.id.desc2 );
            holder.phonee = row.findViewById( R.id.phone2 );
            holder.imageView = row.findViewById( R.id.imgiicon );
            holder.viiew = row.findViewById( R.id.iconview );
            holder.d1 = row.findViewById(R.id.dells);
            row.setTag( holder );
        } else {
            holder  = (ViewHolder)row.getTag();
        }
        final Advertis model = advertisArrayList.get(i);

        holder.titlee.setText( model.getTitle());
        holder.descriptionn.setText( model.getDecription() );
        holder.phonee.setText( model.getPhones() );

        byte[]recordimage = model.getImage();
        Bitmap bitmap= BitmapFactory.decodeByteArray( recordimage,0,recordimage.length );
        holder.imageView.setImageBitmap( bitmap );
        holder.d1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "removed", Toast.LENGTH_SHORT).show();

            }

        });


        return row;
    }
}
