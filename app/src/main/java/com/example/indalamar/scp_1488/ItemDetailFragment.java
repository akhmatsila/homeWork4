package com.example.indalamar.scp_1488;

import android.app.Activity;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;



public class ItemDetailFragment extends Fragment {

    private ImageView rootView;


    public ItemDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey("id")) {

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(getArguments().getString("id"));
            }
        }
    }

    @Override
    public ImageView onCreateView(LayoutInflater inflater, ViewGroup container,
                                  Bundle savedInstanceState) {
        rootView = (ImageView) inflater.inflate(R.layout.item_detail, container, false);
        Picasso.get().load(getArguments().getString("url")).into(rootView);
        return rootView;
    }
}
