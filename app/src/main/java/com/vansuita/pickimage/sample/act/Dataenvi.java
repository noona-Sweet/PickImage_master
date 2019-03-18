package com.vansuita.pickimage.sample.act;

import java.io.Serializable;
import java.util.List;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

public class Dataenvi  implements Serializable {


    public int imagenId;
    public String title;
    public String subtitle;

    public Dataenvi(FragmentActivity activity, List<Dataenvi> info) {

    }

    public int getImagenId() {
        return imagenId;
    }

    public void setImagenId(int imagenId) {
        this.imagenId = imagenId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }
}
