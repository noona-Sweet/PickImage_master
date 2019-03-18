package com.vansuita.pickimage.sample.act;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.vansuita.pickimage.sample.R;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class AdapterEnviromental extends RecyclerView.Adapter<AdapterEnviromental.myViewHolder> {



        private LayoutInflater inflater;
        private List<Dataenvi> info = Collections.emptyList();
        private Context context;

        public AdapterEnviromental (Context context, List<Dataenvi> info){
            this.context=context;
            inflater = LayoutInflater.from(context);
            this.info = info;

        }


        @Override
        public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = inflater.inflate( R.layout.dataenvi, parent, false);
            myViewHolder holder = new myViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(myViewHolder holder, int position) {

           Dataenvi current = info.get(position);
            holder.title.setText(current.title);;
            holder.subtitle.setText(current.subtitle);
            holder.image.setImageResource(current.imagenId);

        }

        @Override
        public int getItemCount() {
            return info.size();
        }

        class myViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            ImageView image;
            TextView title;
            TextView subtitle;

            public myViewHolder(View itemView) {
                super(itemView);

                image = (ImageView) itemView.findViewById(R.id.imageView2);
                title = (TextView) itemView.findViewById(R.id.textView);
                subtitle = (TextView) itemView.findViewById(R.id.textView3);
                itemView.setOnClickListener(this);

            }

            public void setTitle ( String titll
            ){
               image = (ImageView)itemView.findViewById( R.id.imageView2 );


            }

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,Environmental_Volunteering.class);
                context.startActivity(intent);

            }
        }
    }
