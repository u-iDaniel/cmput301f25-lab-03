package com.example.listycitylab3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class CityArrayAdapter extends ArrayAdapter<City> {
    interface EditCityAdapterListener {
        void openEditCity(City city, int position);
    }

    private EditCityAdapterListener listener;

    public CityArrayAdapter(Context context, ArrayList<City> cities) {
        super(context, 0, cities);
        if (context instanceof EditCityAdapterListener) {
            listener = (EditCityAdapterListener) context;
        } else {
            throw new RuntimeException(context + " must implement EditCityDialogListener");
        }
    }

    @NonNull
    @Override
    // Returns a custom view
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view;
        // convertView recycles old views within the ListView, if it holds nothing then we inflate
        // content.xml to assign to view
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.content, parent, false);
        } else {
            view = convertView;
        }

        City city = getItem(position);
        TextView cityName = view.findViewById(R.id.city_text);
        TextView provinceName = view.findViewById(R.id.province_text);

        cityName.setText(city.getName());
        provinceName.setText(city.getProvince());

        FloatingActionButton edit = view.findViewById(R.id.edit_button);
        edit.setOnClickListener(v -> {
            listener.openEditCity(city, position);
        });
        return view;
    }
}
