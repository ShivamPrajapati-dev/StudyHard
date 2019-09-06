package com.shivamprajapati.studyhard;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link StudentTeacherTab2.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link StudentTeacherTab2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StudentTeacherTab2 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    String notificationKey,name,branch,uid;

   // RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    DatabaseReference databaseReference;

    private OnFragmentInteractionListener mListener;

    public StudentTeacherTab2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StudentTeacherTab2.
     */
    // TODO: Rename and change types and number of parameters
    public static StudentTeacherTab2 newInstance(String param1, String param2) {
        StudentTeacherTab2 fragment = new StudentTeacherTab2();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
     final View v=inflater.inflate(R.layout.fragment_student_teacher_tab2, container, false);
        FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
         databaseReference=FirebaseDatabase.getInstance().getReference();
        FirebaseUser firebaseUser=FirebaseAuth.getInstance().getCurrentUser();


         swipeRefreshLayout=(SwipeRefreshLayout)v.findViewById(R.id.studentTeacherRefresh);




         swipeRefreshLayout.setColorSchemeColors(getResources().getColor(android.R.color.holo_red_dark),getResources().getColor(android.R.color.holo_green_dark),getResources().getColor(android.R.color.holo_orange_dark));

         swipeRefreshLayout.post(new Runnable() {
             @Override
             public void run() {
               swipeRefreshLayout.setRefreshing(true);
              final  RecyclerView recyclerView=(RecyclerView) v.findViewById(R.id.studentTeacherRecyclerView);
                 recyclerView.setHasFixedSize(true);
                 recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                 final List<TeacherList> list=new ArrayList<>();
                databaseReference.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        if (!(dataSnapshot.getKey().equals("broadcast"))&&!(dataSnapshot.getKey().equals("chat"))&&dataSnapshot.child("tag").getValue().toString().equals("teacher")&&
                                dataSnapshot.child("nameOfCollege").getValue().toString().equals(SharedPref.readSharedSettingsWhichCollege(getContext(),"nameOfCollege",""))) {


                            name = dataSnapshot.child("name").getValue().toString();
                            branch = dataSnapshot.child("branch").getValue().toString();
                            uid = dataSnapshot.getKey();
                            notificationKey=dataSnapshot.child("notificationKey").getValue().toString();
                            TeacherList teacherList = new TeacherList(name, branch, uid,notificationKey);

                            list.add(teacherList);
                            TeacherListAdapter teacherListAdapter = new TeacherListAdapter(list);
                            recyclerView.setAdapter(teacherListAdapter);
                            swipeRefreshLayout.setRefreshing(false);

                        }
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
             }
         });


         swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
             @Override
             public void onRefresh() {
                final RecyclerView recyclerView=(RecyclerView) v.findViewById(R.id.studentTeacherRecyclerView);
                 recyclerView.setHasFixedSize(true);
                 recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
              final List<TeacherList> list=new ArrayList<>();
                 databaseReference.addChildEventListener(new ChildEventListener() {
                     @Override
                     public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                         if (!(dataSnapshot.getKey().equals("broadcast"))&&!(dataSnapshot.getKey().equals("chat"))&&dataSnapshot.child("tag").getValue().toString().equals("teacher")&&
                                 dataSnapshot.child("nameOfCollege").getValue().toString().equals(SharedPref.readSharedSettingsWhichCollege(getContext(),"nameOfCollege",""))) {


                             name = dataSnapshot.child("name").getValue().toString();
                             branch = dataSnapshot.child("branch").getValue().toString();
                             uid = dataSnapshot.getKey();
                             notificationKey=dataSnapshot.child("notificationKey").getValue().toString();
                             TeacherList teacherList = new TeacherList(name, branch, uid,notificationKey);

                             list.add(teacherList);
                             TeacherListAdapter teacherListAdapter = new TeacherListAdapter(list);
                             recyclerView.setAdapter(teacherListAdapter);
                             swipeRefreshLayout.setRefreshing(false);

                         }
                     }

                     @Override
                     public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                     }

                     @Override
                     public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                     }

                     @Override
                     public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                     }

                     @Override
                     public void onCancelled(@NonNull DatabaseError databaseError) {

                     }
                 });
             }
         });







        return v;
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
