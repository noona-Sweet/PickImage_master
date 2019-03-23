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

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static com.vansuita.pickimage.sample.act.AppSharedPreferences.PARTICIPATES_COUNTER;

class HealthAdapter extends BaseAdapter {


   private Context context;
   private int layout;
   private ArrayList<AdvertisV>advertislist;

   AppSharedPreferences appSharedPreferences;

   public HealthAdapter(Context context, int layout, ArrayList<AdvertisV> advertislist) {
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

   public class ViewHolder{
      ImageView image1,shows,participate,accept,recommend;
      TextView titles,descriptions,phones,counts;
   }

   @Override
   public View getView(int i, View view, ViewGroup parent) {

      View row =view;

     HealthAdapter.ViewHolder holder = new ViewHolder();

      if (row == null){
         LayoutInflater inflater = (LayoutInflater)context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
         row = inflater.inflate(R.layout.row_vol,parent,false );
         holder.titles = row.findViewById( R.id.title2 );
         holder.descriptions = row.findViewById( R.id.desc2 );
         holder.phones = row.findViewById( R.id.phone2 );
         holder.image1 = row.findViewById( R.id.imgiicon );
         holder.shows = row.findViewById( R.id.viewicon );
         holder.counts = row.findViewById(R.id.counts);
         holder.participate = row.findViewById(R.id.particon);
         row.setTag( holder );
      } else {
         holder  = (HealthAdapter.ViewHolder)row.getTag();
      }
      AdvertisV model = advertislist.get(i);

      holder.titles.setText( model.getTitle());
      holder.descriptions.setText( model.getDecription() );
      holder.phones.setText( model.getPhones() );

      appSharedPreferences = new AppSharedPreferences(context);
       final ViewHolder finalHolder = holder;
       holder.participate.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              int participates_counter = appSharedPreferences.readInteger(PARTICIPATES_COUNTER) + 1;
              appSharedPreferences.writeInteger(PARTICIPATES_COUNTER, participates_counter);
              finalHolder.counts.setText(participates_counter);
          }
      });

      byte[]recordimage = model.getImage();
      Bitmap bitmap= BitmapFactory.decodeByteArray( recordimage,0,recordimage.length );
      holder.image1.setImageBitmap( bitmap );
      return row;
   }
}