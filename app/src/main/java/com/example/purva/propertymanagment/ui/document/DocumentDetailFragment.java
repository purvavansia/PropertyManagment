package com.example.purva.propertymanagment.ui.document;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.example.purva.propertymanagment.R;
import com.example.purva.propertymanagment.data.database.DbHelper;

public class DocumentDetailFragment extends Fragment {
    TextView nameTx;
    TextView typeTx;
    TextView commentTx;
    Button backBtn;
    Button deleteBtn;
    DbHelper dbHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_document_detail, container, false);
        Bundle b = getArguments();
        nameTx = view.findViewById(R.id.dnameText);
        typeTx = view.findViewById(R.id.dtypeText);
        commentTx = view.findViewById(R.id.dcommentText);
        nameTx.setText(b.getString("name"));
        typeTx.setText(b.getString("type"));
        commentTx.setText(b.getString("comment"));
        backBtn = view.findViewById(R.id.backToList);
        deleteBtn = view.findViewById(R.id.deleteDoc);
        dbHelper = new DbHelper(getActivity());
        String propertyId = b.getString("propertyId");
        int documentId = b.getInt("documentId");
        String landlordId = getActivity().getSharedPreferences("mydata", Context.MODE_PRIVATE).getString("userid", null);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.documentContainer, new DocumentListFragment()).commit();
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.deleteDocument(documentId);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.documentContainer, new DocumentListFragment()).commit();
            }
        });
        return view;
    }

}
