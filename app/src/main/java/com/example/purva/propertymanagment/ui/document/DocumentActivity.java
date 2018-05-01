package com.example.purva.propertymanagment.ui.document;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import butterknife.BindView;
import butterknife.OnClick;
import com.example.purva.propertymanagment.R;

public class DocumentActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.documentContainer, new AddDocumentFragment())
                .commit();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

}
