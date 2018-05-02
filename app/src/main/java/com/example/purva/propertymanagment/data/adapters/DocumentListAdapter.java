package com.example.purva.propertymanagment.data.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.purva.propertymanagment.R;
import com.example.purva.propertymanagment.data.database.DbHelper;
import com.example.purva.propertymanagment.data.model.Document;
import com.example.purva.propertymanagment.data.model.Property;
import com.example.purva.propertymanagment.ui.document.DocumentDetailFragment;

import java.util.List;

import static com.example.purva.propertymanagment.ui.document.AddDocumentFragment.byteToBitmap;

public class DocumentListAdapter extends RecyclerView.Adapter<DocumentListAdapter.MyHolder> {
    Context context;
    List<Document.DocumentBean> documents;
    DbHelper dbHelper;
    public DocumentListAdapter(Context context, List<Document.DocumentBean> documents) {
        this.context = context;
        this.documents = documents;
        dbHelper = new DbHelper(context);
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.document_item_layout, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        Document.DocumentBean documentBean = documents.get(position);
        String propertyId = documentBean.getPropertyId();
        String documentName = documentBean.getDocumentName();
        String documentType = documentBean.getDocumentType();
        byte[] img = documentBean.getImage();
        String documentComment = documentBean.getDocComment();
        String landlordId = ((AppCompatActivity)context).getSharedPreferences("mydata", Context.MODE_PRIVATE).getString("userid", null);
        holder.docImg.setImageBitmap(byteToBitmap(img));
        holder.docType.setText(documentType);
        holder.docName.setText(documentName);
        holder.docComment.setText(documentComment);
        holder.docImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ViewHOlder", "VIewholde clicked");
                Bundle b = new Bundle();
                b.putInt("documentId",documentBean.getDocumentId());
                Log.d("DOCUMENTID", documentBean.getDocumentId()+"");
                b.putString("propertyId", propertyId);
                b.putString("name", documentName);
                b.putString("type", documentType);
                b.putString("comment", documentComment);
                DocumentDetailFragment detailFragment = new DocumentDetailFragment();
                detailFragment.setArguments(b);
                ((AppCompatActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.documentContainer, detailFragment).commit();
            }
        });
        //Property.PropertyBean propertyBean = dbHelper.getPropertyBeanByKeys(propertyId, landlordId);
    }

    @Override
    public int getItemCount() {
        return documents.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        ImageView docImg;
        TextView docName, docType, docComment, tenantName, propertyInfo;

        public MyHolder(View itemView) {
            super(itemView);
            docImg = itemView.findViewById(R.id.docImgItem);
            docName = itemView.findViewById(R.id.docuName);
            docType = itemView.findViewById(R.id.docuType);
            docComment = itemView.findViewById(R.id.commentInfo);
            tenantName = itemView.findViewById(R.id.tenantInfo);
            propertyInfo = itemView.findViewById(R.id.propertyInfo);
        }

    }
}
