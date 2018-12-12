package com.example.indalamar.scp_1488;

import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;

public class RecyclerViewItem  {
    public String text;
    public String imageUrl;
    public int id ;

    public RecyclerViewItem(Item item) {
        this.text = item.getText();
        if (text.isEmpty())
            text = "This picture has no any description";
        this.imageUrl = item.getSizes().get(item.getSizes().size() - 1).getUrl();
        this.id = item.getId();
    }

    public RecyclerViewItem (String text , String imageUrl , int id) {
        this.text = text;
        this.imageUrl = imageUrl;
        this.id = id ;
    }
}
