package com.vansuita.pickimage.sample.act;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.vansuita.pickimage.sample.R;

import java.util.ArrayList;

public class PartAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<Contact> contaclist;

    public PartAdapter(Context context, int layout, ArrayList<Contact> contaclist) {
        this.context = context;
        this.layout = layout;
        this.contaclist = contaclist;
    }
    @Override
    public int getCount() {
        return contaclist.size();
    }

    @Override
    public Object getItem(int i) {
        return contaclist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHolder{
        ImageView imageView,viiew,participate,accept,recommend;
        TextView usersname;
    }


    @Override
    public View getView(int i, View view, ViewGroup parent) {

        View rows = view;
        ViewHolder holdeer = new ViewHolder();

        if (rows == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
            rows = inflater.inflate( R.layout.row_part,parent,false );
            holdeer.usersname = rows.findViewById( R.id.name1 );
          /* holder.descriptionn = row.findViewById( R.id.desc2 );
            holder.phonee = row.findViewById( R.id.phone2 );
            holder.imageView = row.findViewById( R.id.imgiicon );
            holder.viiew = row.findViewById( R.id.iconview );*/
            rows.setTag( holdeer );
        } else {
            holdeer  = (PartAdapter.ViewHolder)rows.getTag();
        }
       Contact model = contaclist.get(i);

        holdeer.usersname.setText( model.getUname());
        return rows;
    }
}
