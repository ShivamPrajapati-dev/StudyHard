package com.shivamprajapati.studyhard;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;


public class StudentProfileTab3 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    CircleImageView circleImageView;
    FirebaseStorage firebaseStorage;
    Uri selectedImage;
    StorageReference storageReference;
    StorageReference profilePicture;
    public StudentProfileTab3() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StudentProfileTab3.
     */
    // TODO: Rename and change types and number of parameters
    public static StudentProfileTab3 newInstance(String param1, String param2) {
        StudentProfileTab3 fragment = new StudentProfileTab3();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }



    }


    private static int RESULT_LOAD_IMAGE = 1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


      final FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();


        View v= inflater.inflate(R.layout.fragment_student_profile_tab3, container, false);
        Button button=(Button)v.findViewById(R.id.signout);
        circleImageView=(CircleImageView)v.findViewById(R.id.civInStudentProfile);


        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i,RESULT_LOAD_IMAGE);
            }
        });

       StorageReference storageReference=FirebaseStorage.getInstance().getReference();


       storageReference.child(FirebaseAuth.getInstance().getUid()).child("profilePicture").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
           @Override
           public void onSuccess(Uri uri) {

               Picasso.with(getContext()).load(R.drawable.profilepic).placeholder(R.drawable.profilepic).into(circleImageView);
               Picasso.with(getContext()).load(uri).into(circleImageView);
              // Toast.makeText(getContext(),"picture Loaded",Toast.LENGTH_LONG).show();
           }
       }).addOnFailureListener(new OnFailureListener() {
           @Override
           public void onFailure(@NonNull Exception e) {
          Picasso.with(getContext()).load(R.drawable.profilepic).into(circleImageView);
           }
       });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseMessaging.getInstance().unsubscribeFromTopic((SharedPref.readSharedSettingsWhichCollege(getContext(),"nameOfCollege","").replaceAll("\\s+",""))+(SharedPref.readSharedSettingsWhichYear(getContext(),"year","").replaceAll("\\s+","")));
                firebaseAuth.signOut();
                SharedPref.saveSharedSettingsIsLogin(getActivity(),"isLoggedIn",false);
                getActivity().finish();
                startActivity(new Intent(getActivity(),LoginActivity.class));
            }
        });

        return v;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        storageReference=FirebaseStorage.getInstance().getReference();
        firebaseStorage=storageReference.getStorage();

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            selectedImage = data.getData();

            profilePicture=storageReference.child(FirebaseAuth.getInstance().getUid()).child("profilePicture");

            UploadTask uploadTask= profilePicture.putFile(selectedImage);

            uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    loadImage(selectedImage);


                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                }
            });



        }
    }

    private void loadImage(Uri selectedImage) {


        StorageReference storageReference=FirebaseStorage.getInstance().getReference();

        storageReference.child(FirebaseAuth.getInstance().getUid()).child("profilePicture").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {


                Picasso.with(getContext()).load(uri).placeholder(R.drawable.profilepic).into(circleImageView);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Picasso.with(getContext()).load(R.drawable.profilepic).into(circleImageView);
            }
        });

    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

