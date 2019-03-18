package com.vansuita.pickimage.sample.act;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.vansuita.pickimage.sample.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Post_Adapter_Volunteer extends RecyclerView.Adapter<Post_Adapter_Volunteer.ViewHolder> {

        private OnItemClickListener listener;

        static class ViewHolder extends RecyclerView.ViewHolder {
            private OnItemClickListener listener;
            TextView volunteer;
            ImageView v1, aaccept,rrecomend,viiew,paart;

            ViewHolder(View itemView) {
                super(itemView);
                volunteer = itemView.findViewById( R.id.vv1);

                v1= itemView.findViewById(R.id.vieweas);
                aaccept = itemView.findViewById( R.id.accept);
                rrecomend = itemView.findViewById(R.id.recomend);
                viiew = itemView.findViewById(R.id.vieew);
                paart= itemView.findViewById(R.id.participate);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = getAdapterPosition();
                    }
                });

            }
        }

        public interface OnItemClickListener{

            void onItemClick(Advertis advertis);

        }
        public void SetOnItemClickListener(OnItemClickListener listener){
            this.listener = listener;


        }


        private Context context;

        private List<Postitems> postsv;

        public Post_Adapter_Volunteer(Context c , List<Postitems> postList){
            this.context = c;
            postsv = postList;
        }
        @Override
        public Post_Adapter_Volunteer.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            LayoutInflater inflater = LayoutInflater.from(context);
            View v = inflater.inflate(R.layout.postitems_volunteer, parent, false);
            return new ViewHolder( v );


        }

    @Override

    public int getItemCount() {

            return postsv.size();
        }
        @Override
        public void onBindViewHolder(Post_Adapter_Volunteer.ViewHolder holder, final int position) {

            final int currentposition = position;

            final Postitems pos = postsv.get(position);

            holder.volunteer.setText(pos.getVol1());


            holder.v1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "View", Toast.LENGTH_SHORT).show();
                }
            });
        }
}
