package com.example.purva.propertymanagment.ui.document;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.purva.propertymanagment.R;
import com.example.purva.propertymanagment.data.adapters.DocumentListAdapter;
import com.example.purva.propertymanagment.data.database.DbHelper;
import com.example.purva.propertymanagment.data.model.Document;

import java.util.List;

public class DocumentListFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private Toolbar mToolbar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_document_list, container, false);
        mRecyclerView = view.findViewById(R.id.documentList);
        mToolbar = view.findViewById(R.id.toolbarDocList);
        DbHelper dbHelper = new DbHelper(getActivity());
        int num = dbHelper.getDocumentCount();
        List<Document.DocumentBean> list = dbHelper.getAllDocuments();
        Log.d("DATASIZE", list.size()+"");
        DocumentListAdapter documentListAdapter = new DocumentListAdapter(getActivity(), list);
        mRecyclerView.setAdapter(documentListAdapter);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }
}
