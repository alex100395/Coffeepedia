package com.example.coffeepedia.ui.main;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.example.coffeepedia.MainNavActivity;
import com.example.coffeepedia.R;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {


    ViewPager viewPager;

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;

    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_gallery2, container, false);
        final TextView tv = root.findViewById(R.id.section_label);
        final ImageView iv = root.findViewById(R.id.picture);
//        pageViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
//        return root;
        Button b1 = root.findViewById(R.id.button5);
        Button b2 = root.findViewById(R.id.button6);
        Button b3 = root.findViewById(R.id.button9);
        viewPager = (ViewPager) getActivity().findViewById(R.id.view_pager);

        if(getArguments().getInt(ARG_SECTION_NUMBER)== 1)
        {
            tv.setText("You are viewing the first picture");
            iv.setImageDrawable(getResources().getDrawable(R.drawable.picture1));
            b1.setVisibility(View.INVISIBLE);
            b3.setVisibility(View.INVISIBLE);
            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                    public void onClick(View v) {

                }
            });
            b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewPager.setCurrentItem(1);
                }
            });
        }else if(getArguments().getInt(ARG_SECTION_NUMBER)== 2)
        {
            tv.setText("You are viewing the second picture");
            iv.setImageDrawable(getResources().getDrawable(R.drawable.picture2));
            b3.setVisibility(View.INVISIBLE);
            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewPager.setCurrentItem(0);
                }
            });
            b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewPager.setCurrentItem(2);
                }
            });
        }else if(getArguments().getInt(ARG_SECTION_NUMBER)== 3)
        {
            tv.setText("You are viewing the third picture");
            iv.setImageDrawable(getResources().getDrawable(R.drawable.picture3));
            b3.setVisibility(View.INVISIBLE);
            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewPager.setCurrentItem(1);
                }
            });
            b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewPager.setCurrentItem(3);
                }
            });
        }else if(getArguments().getInt(ARG_SECTION_NUMBER)== 4)
        {
            tv.setText("You are viewing the fouth picture");
            iv.setImageDrawable(getResources().getDrawable(R.drawable.picture4));
            b3.setVisibility(View.INVISIBLE);
            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewPager.setCurrentItem(2);
                }
            });
            b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewPager.setCurrentItem(4);
                }
            });
        }else if(getArguments().getInt(ARG_SECTION_NUMBER)== 5)
        {
            tv.setText("You are viewing the fifth picture");
            iv.setImageDrawable(getResources().getDrawable(R.drawable.picture5));
            b2.setVisibility(View.INVISIBLE);
            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewPager.setCurrentItem(3);
                }
            });
            b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            b3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(getContext(), MainNavActivity.class);
                    startActivity(in);
                }
            });
        }

        return root;
    }

}