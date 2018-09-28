package com.recipebook.recipebook.ui;


import android.os.Bundle;
import android.app.Fragment;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.recipebook.recipebook.MainActivity;
import com.recipebook.recipebook.R;
import com.recipebook.recipebook.db.Recipe;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends android.support.v4.app.Fragment implements View.OnClickListener {
    private ConstraintLayout addLayout;
    private FloatingActionButton addButton;
    private ConstraintLayout entreeLayout;
    private ConstraintLayout mainCourseLayout;
    private ConstraintLayout dessertLayout;
    private ConstraintLayout condimentLayout;
    private ConstraintLayout drinkLayout;



    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        addLayout = view.findViewById(R.id.layout1);
        addButton = view.findViewById(R.id.floatingAddButton);
        entreeLayout = view.findViewById(R.id.layout2);
        mainCourseLayout = view.findViewById(R.id.layout3);
        dessertLayout = view.findViewById(R.id.layout4);
        condimentLayout = view.findViewById(R.id.layout5);
        drinkLayout = view.findViewById(R.id.linearLayout6);

        addLayout.setOnClickListener(this);
        addButton.setOnClickListener(this);
        entreeLayout.setOnClickListener(this);
        mainCourseLayout.setOnClickListener(this);
        dessertLayout.setOnClickListener(this);
        condimentLayout.setOnClickListener(this);
        drinkLayout.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.floatingAddButton:
                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container,
                        new AddRecipeFragment()).addToBackStack(null).commit();
                break;
            case R.id.layout1:
                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container,
                        new AddRecipeFragment()).addToBackStack(null).commit();
                break;
            case R.id.layout2:
                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container,
                        new EntreeListFragment()).addToBackStack(null).commit();
                break;

        }
    }


}
