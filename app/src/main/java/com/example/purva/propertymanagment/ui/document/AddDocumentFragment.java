package com.example.purva.propertymanagment.ui.document;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.*;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.*;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.example.purva.propertymanagment.R;
import com.example.purva.propertymanagment.data.adapters.DocumentTypeAdapter;
import com.example.purva.propertymanagment.data.adapters.PropertySelectionAdapter;
import com.example.purva.propertymanagment.data.database.DbHelper;
import com.example.purva.propertymanagment.data.model.Property;
import com.example.purva.propertymanagment.ui.tenant.AddTenantFragment;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class AddDocumentFragment extends Fragment {
    private static final int REQUEST_CODE = 1;
    @BindView(R.id.docImg)
    ImageView imgView;
    @BindView(R.id.docName)
    EditText docnameEt;
    @BindView(R.id.docType)
    Spinner docType;
    @BindView(R.id.doComment)
    EditText commentEt;
    @BindView(R.id.propertyType)
    Spinner propertyChoice;
    @BindView(R.id.imgButton)
    ImageButton imgBtn;
    @BindView(R.id.saveDoc)
    Button saveBtn;

    static Uri imageUri;
    private Toolbar toolbar;
    private boolean imageTaken = false;
    private static DbHelper dbHelper;
    private final int REQUEST_CAMERA_USAGE = 200;
    Bitmap bitmap;
    private String documentype;
    private String documentComment;
    private String documentname;
    private String propertyId;

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CAMERA_USAGE:
                if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {

                } else {
                    Toast.makeText(getActivity(), "This Permission is needed for the app to work perfectly!", Toast.LENGTH_SHORT).show();
                }
            default:
                Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        dbHelper = new DbHelper(getActivity());
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
           getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.documentContainer, new DocumentListFragment()).commit();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("ActivityResult", resultCode + "");
        switch (requestCode) {
            case 1899:
                if (resultCode == RESULT_OK) {
                    Bitmap bmp = (Bitmap) data.getExtras().get("data");
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bmp.compress(Bitmap.CompressFormat.PNG, 40, stream);
                    byte[] byteArray = stream.toByteArray();
                    bitmap = BitmapFactory.decodeByteArray(byteArray, 0,
                            byteArray.length);
                    imgView.setImageBitmap(bitmap);
                }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_document, container, false);
        ButterKnife.bind(this, view);
        toolbar = view.findViewById(R.id.toolbaraddDoc);

        if (Build.VERSION.SDK_INT >= 21) {
            int permissionCheck = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA);
            if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_USAGE);
            }
        }
        List<Property.PropertyBean> propertyBeans = dbHelper.getAllProperties();
        PropertySelectionAdapter propertySelectionAdapter = new PropertySelectionAdapter(getActivity(), R.layout.property_selection_layout, propertyBeans);
        propertySelectionAdapter.setDropDownViewResource(R.layout.property_selection_layout);
        propertyChoice.setAdapter(propertySelectionAdapter);
        propertyChoice.setPrompt("Please Select a Property From the list");
        propertyChoice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                propertyId = propertyBeans.get(position).getId();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        String[] docTypes = getResources().getStringArray(R.array.document_types);
        DocumentTypeAdapter docAdapter = new DocumentTypeAdapter(getActivity(), R.layout.doctype_selection_layout, Arrays.asList(docTypes));
        docType.setAdapter(docAdapter);
        docType.setPrompt("Please choose a document type.");
        docType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                documentype = ((TextView)view.findViewById(R.id.document_type)).getText().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return view;
    }


    @OnClick(R.id.imgButton)
    public void takeImage(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 1899);
        imageTaken = true;
    }

    @OnClick(R.id.saveDoc)
    public void saveImage(){
        if(imageTaken) {
            documentComment = commentEt.getText().toString();
            documentname = docnameEt.getText().toString();
            byte[] img = imageViewToByte(bitmap);
            if (documentComment.isEmpty() || documentname.isEmpty() || documentype.isEmpty() || propertyId.isEmpty()) {
                Toast.makeText(getActivity(), "No Field Can be blank.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), "Document saved", Toast.LENGTH_SHORT).show();
                String landlordId = getActivity().getSharedPreferences("mydata", Context.MODE_PRIVATE).getString("userid", null);
                dbHelper.insertDocument(propertyId, landlordId, documentype, documentname, documentComment, img);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.documentContainer, new DocumentListFragment()).commit();
            }
        }else{
            Toast.makeText(getActivity(), "Please take an image for the document", Toast.LENGTH_SHORT).show();
        }
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
        String mImageName="MI_"+ timeStamp +".png";
        mediaFile = new File(mediaStorageDir.getPath() + File.separator + mImageName);
        Log.d("MediaFile", mediaFile.getPath());
        return mediaFile;
    }

    private String storeImage(Bitmap image) {
        Log.d("StoreFile", "Store Image to storage");
        File pictureFile = getOutputMediaFile();
        if (pictureFile == null) {
            Log.d("IMAGE",
                    "Error creating media file, check storage permissions: ");// e.getMessage());
            return null;
        }
        try {
            FileOutputStream fos = new FileOutputStream(pictureFile);
            image.compress(Bitmap.CompressFormat.PNG, 90, fos);
            fos.close();
            return pictureFile.getPath();
        } catch (FileNotFoundException e) {
            Log.d("IMAGE", "File not found: " + e.getMessage());
        } catch (IOException e) {
            Log.d("IMAGE", "Error accessing file: " + e.getMessage());
        }
        return null;
    }

    public static byte[] imageViewToByte(Bitmap bitmap){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

    public static Bitmap byteToBitmap(byte[] byteArray){
        return BitmapFactory.decodeByteArray(byteArray, 0,
                byteArray.length);
    }

}




