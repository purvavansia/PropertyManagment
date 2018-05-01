package com.example.purva.propertymanagment.ui.document;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.*;
import android.widget.*;
import com.example.purva.propertymanagment.R;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.app.Activity.RESULT_OK;

public class AddDocumentFragment extends Fragment {
    private static final int REQUEST_CODE = 1;
    static Uri imageUri;
    private Toolbar toolbar;
    private EditText commentEt;
    private EditText docnameEt;
    private Spinner docType;
    private Spinner propertyChoice;
    private ImageButton imgBtn;
    private ImageView imgView;
    private Button saveBtn;
    private boolean imageTaken = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.adddocument, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.documents_icon) {
            // ((AppCompatActivity)this.mContext).getSupportFragmentManager().beginTransaction().replace(R.id.tenantFragmentContainer, new AddTenantFragment()).commit();
        }
        return super.onOptionsItemSelected(item);
    }

    public void takePhoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File photo = new File(Environment.getExternalStorageDirectory(), "Pic.jpg");
        intent.putExtra(MediaStore.EXTRA_OUTPUT,
                Uri.fromFile(photo));
        imageUri = Uri.fromFile(photo);
        this.startActivityForResult(intent, 1899);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("ActivityResult", resultCode + "");
        switch (requestCode) {
            case 1899:
                if (resultCode == RESULT_OK) {
                    Bitmap bmp = (Bitmap) data.getExtras().get("data");
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    //bmp.
                    bmp.compress(Bitmap.CompressFormat.PNG, 40, stream);
                    byte[] byteArray = stream.toByteArray();

                    // convert byte array to Bitmap
                    Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0,
                            byteArray.length);
                    storeImage(bitmap);
                    imgView.setImageBitmap(bitmap);
                }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_document, container, false);
        Bundle b = getArguments();
        toolbar = view.findViewById(R.id.toolbaraddDoc);
        imgBtn = view.findViewById(R.id.imgBtn);
        imgView = view.findViewById(R.id.docImg);
        commentEt = view.findViewById(R.id.doComment);
        docnameEt = view.findViewById(R.id.docName);
        saveBtn = view.findViewById(R.id.saveDoc);
        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 1899);
                imageTaken = true;
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageTaken) {
                    Toast.makeText(getActivity(), "Document Saved", Toast.LENGTH_SHORT).show();
                }
            }
        });


        return view;
    }

    /** Create a File for saving an image or video */
    private  File getOutputMediaFile(){
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.
        File mediaStorageDir = new File(Environment.getExternalStorageDirectory()
                + "/Android/data/"
                + "/ImgFiles");
        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                Log.d("DirectoryFailed", "Failed to create directory");
                return null;
            }
        }
        // Create a media file name
        String timeStamp = new SimpleDateFormat("ddMMyyyy_HHmm").format(new Date());
        File mediaFile;
        String mImageName="MI_"+ timeStamp +".jpg";
        mediaFile = new File(mediaStorageDir.getPath() + File.separator + mImageName);
        Log.d("MediaFile", mediaFile.getPath());
        return mediaFile;
    }

    private void storeImage(Bitmap image) {
        File pictureFile = getOutputMediaFile();
        if (pictureFile == null) {
            Log.d("IMAGE",
                    "Error creating media file, check storage permissions: ");// e.getMessage());
            return;
        }
        try {
            FileOutputStream fos = new FileOutputStream(pictureFile);
            image.compress(Bitmap.CompressFormat.PNG, 90, fos);
            fos.close();
        } catch (FileNotFoundException e) {
            Log.d("IMAGE", "File not found: " + e.getMessage());
        } catch (IOException e) {
            Log.d("IMAGE", "Error accessing file: " + e.getMessage());
        }
    }

}


