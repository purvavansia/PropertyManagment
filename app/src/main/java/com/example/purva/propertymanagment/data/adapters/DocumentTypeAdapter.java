package com.example.purva.propertymanagment.data.adapters;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.purva.propertymanagment.R;

import java.util.List;

public class DocumentTypeAdapter extends ArrayAdapter<String> {
    private LayoutInflater inflater;
    private List<String> docTypes;
    private Context mContext;
    public DocumentTypeAdapter(Context context, int resource, List objects){
        super(context, resource, objects);
        this.mContext = context;
        this.inflater = LayoutInflater.from(context);
        this.docTypes = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        return createItemView(position, convertView, parent);
    }

    private View createItemView(int position, View convertView, ViewGroup parent){
        View view = this.inflater.inflate(R.layout.doctype_selection_layout, parent, false);
        TextView type = view.findViewById(R.id.document_type);
        type.setText(this.docTypes.get(position));
        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent){
        return createItemView(position, convertView, parent);
    }

}
