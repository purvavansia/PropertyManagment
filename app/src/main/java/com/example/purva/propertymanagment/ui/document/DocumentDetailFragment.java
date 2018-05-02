package com.example.purva.propertymanagment.ui.document;

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

public class DocumentDetailFragment extends Fragment {
    TextView nameTx;
    TextView typeTx;
    TextView commentTx;
    Button backBtn;

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
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.documentContainer, new DocumentListFragment()).commit();
            }
        });
        return view;
    }

}
