package com.shivamprajapati.studyhard;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link StudentChatTab1.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link StudentChatTab1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StudentChatTab1 extends Fragment implements CreateAbroadcastStudent.BottomSheetListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    RecyclerView recyclerView;

    DatabaseReference databaseReference;
    CheckBox checkBox1stYear;
    CheckBox checkBox2ndYear;
    CheckBox checkBox3rdYear;
    CheckBox checkBox4thYear;
    EditText broadcast;
    Button send;
    SwipeRefreshLayout swipeRefreshLayout;
    String year1="no",year2="no",year3="no",year4="no",year="no",bcMsg;

    CreateAbroadcastStudent createAbroadcast;

    private OnFragmentInteractionListener mListener;

    public StudentChatTab1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StudentChatTab1.
     */
    // TODO: Rename and change types and number of parameters
    public static StudentChatTab1 newInstance(String param1, String param2) {
        StudentChatTab1 fragment = new StudentChatTab1();
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
    public void OnBottomSheetListener(View view1) {

        checkBox1stYear = (CheckBox) view1.findViewById(R.id.studentCB1stYear);
        checkBox2ndYear=(CheckBox)view1.findViewById(R.id.studentCB2ndYear);
        checkBox3rdYear=(CheckBox)view1.findViewById(R.id.studentCB3rdYear);
        checkBox4thYear=(CheckBox)view1.findViewById(R.id.studentCB4thYear);
        broadcast=(EditText)view1.findViewById(R.id.studentBroadcast);

         send=(Button)view1.findViewById(R.id.sendBroadcastStudent);


        checkBox1stYear.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {  year1="1st Year";
                    year=year1;

                }

                else
                { year1="no";
                    year=year1;}
            }
        });
        checkBox2ndYear.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                { year2="2nd Year";year=year2;}
                else
                {year2="no";year=year2;}
            }
        });
        checkBox3rdYear.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {year3="3rd Year";year=year3;}
                else
                {year3="no";year=year3;}
            }
        });
        checkBox4thYear.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {year4="4th Year";year=year4;}
                else
                {year4="no";year=year4;}
            }
        });



        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bcMsg = broadcast.getText().toString();
                if((checkBox1stYear.isChecked()||checkBox2ndYear.isChecked()||checkBox3rdYear.isChecked()||checkBox4thYear.isChecked())&& !(TextUtils.isEmpty(bcMsg))) {

                    String mId = FirebaseDatabase.getInstance().getReference().child(SharedPref.readSharedSettingsWhichCollege(getContext(), "nameOfCollege", "").replaceAll("\\s+","")+year.replaceAll("\\s+","")).child("mId").push().getKey();
                    databaseReference.child("broadcast").child(SharedPref.readSharedSettingsWhichCollege(getContext(), "nameOfCollege", "").replaceAll("\\s+","")+year.replaceAll("\\s+","")).push();

                    Map broadcastMap = new HashMap<>();
                    broadcastMap.put("creatorName", SharedPref.readSharedSettingsUserName(getContext(), "name", ""));
                    broadcastMap.put("year1", year1);
                    broadcastMap.put("year2", year2);
                    broadcastMap.put("year3", year3);
                    broadcastMap.put("year4", year4);
                    broadcastMap.put("bcMsg", bcMsg);
                    broadcastMap.put("from", FirebaseAuth.getInstance().getCurrentUser().getUid());
                    databaseReference.child("broadcast").child(SharedPref.readSharedSettingsWhichCollege(getContext(), "nameOfCollege", "").replaceAll("\\s+","")+year.replaceAll("\\s+","")).child(mId).updateChildren(broadcastMap);
                    broadcast.setText(null);
                    checkBox1stYear.setChecked(false);
                    checkBox2ndYear.setChecked(false);
                    checkBox3rdYear.setChecked(false);
                    checkBox4thYear.setChecked(false);
                    createAbroadcast.dismiss();


                }
                else{
                    Toast.makeText(getContext(),"Please fill all fields",Toast.LENGTH_SHORT).show();
                }

            }
        });


    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view=inflater.inflate(R.layout.fragment_student_chat_tab1, container, false);


        swipeRefreshLayout=(SwipeRefreshLayout)view.findViewById(R.id.studentBroadcastRefresh);


        databaseReference= FirebaseDatabase.getInstance().getReference();

        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(android.R.color.holo_red_dark),getResources().getColor(android.R.color.holo_green_dark),getResources().getColor(android.R.color.holo_orange_dark));





       //  view1=LayoutInflater.from(getContext()).inflate(R.layout.broadcast_by_student,null);




        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {

                swipeRefreshLayout.setRefreshing(true);
                final RecyclerView recyclerView=(RecyclerView)view.findViewById(R.id.studentBroadcastListRecyclerView);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                final List<StudentBroadcastList> studentBroadcastLists=new ArrayList<>();
                databaseReference.child("broadcast").child(SharedPref.readSharedSettingsWhichCollege(getContext(),"nameOfCollege","").replaceAll("\\s+","")+SharedPref.readSharedSettingsWhichYear(getContext(),"year","").replaceAll("\\s+","")).addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        if(dataSnapshot.child("year1").getValue().toString().equals(SharedPref.readSharedSettingsWhichYear(getContext(),"year",""))){
                            String msg=dataSnapshot.child("bcMsg").getValue().toString();
                            String cn=dataSnapshot.child("creatorName").getValue().toString();
                            String cid=dataSnapshot.child("from").getValue().toString();
                            StudentBroadcastList studentBroadcastList=new StudentBroadcastList(cn,msg,cid);
                            studentBroadcastLists.add(studentBroadcastList);
                            StudentBroadcastListAdapter broadcastListAdapter=new StudentBroadcastListAdapter(studentBroadcastLists);
                            recyclerView.setAdapter(broadcastListAdapter);
                            swipeRefreshLayout.setRefreshing(false);

                        }
                        else if(dataSnapshot.child("year2").getValue().toString().equals(SharedPref.readSharedSettingsWhichYear(getContext(),"year",""))){
                            String msg=dataSnapshot.child("bcMsg").getValue().toString();
                            String cn=dataSnapshot.child("creatorName").getValue().toString();
                            String cid=dataSnapshot.child("from").getValue().toString();
                            StudentBroadcastList studentBroadcastList=new StudentBroadcastList(cn,msg,cid);
                            studentBroadcastLists.add(studentBroadcastList);
                            StudentBroadcastListAdapter broadcastListAdapter=new StudentBroadcastListAdapter(studentBroadcastLists);
                            recyclerView.setAdapter(broadcastListAdapter);
                            swipeRefreshLayout.setRefreshing(false);

                        }
                        else if(dataSnapshot.child("year3").getValue().toString().equals(SharedPref.readSharedSettingsWhichYear(getContext(),"year",""))){
                            String msg=dataSnapshot.child("bcMsg").getValue().toString();
                            String cn=dataSnapshot.child("creatorName").getValue().toString();
                            String cid=dataSnapshot.child("from").getValue().toString();
                            StudentBroadcastList studentBroadcastList=new StudentBroadcastList(cn,msg,cid);
                            studentBroadcastLists.add(studentBroadcastList);
                            StudentBroadcastListAdapter broadcastListAdapter=new StudentBroadcastListAdapter(studentBroadcastLists);
                            recyclerView.setAdapter(broadcastListAdapter);
                            swipeRefreshLayout.setRefreshing(false);

                        }
                        else if(dataSnapshot.child("year4").getValue().toString().equals(SharedPref.readSharedSettingsWhichYear(getContext(),"year",""))){
                            String msg=dataSnapshot.child("bcMsg").getValue().toString();
                            String cn=dataSnapshot.child("creatorName").getValue().toString();
                            String cid=dataSnapshot.child("from").getValue().toString();
                            StudentBroadcastList studentBroadcastList=new StudentBroadcastList(cn,msg,cid);
                            studentBroadcastLists.add(studentBroadcastList);
                            StudentBroadcastListAdapter broadcastListAdapter=new StudentBroadcastListAdapter(studentBroadcastLists);
                            recyclerView.setAdapter(broadcastListAdapter);
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


                 final RecyclerView recyclerView=(RecyclerView)view.findViewById(R.id.studentBroadcastListRecyclerView);
                 recyclerView.setHasFixedSize(true);
                 recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                 final List<StudentBroadcastList> studentBroadcastLists=new ArrayList<>();
                 databaseReference.child("broadcast").child(SharedPref.readSharedSettingsWhichCollege(getContext(),"nameOfCollege","").replaceAll("\\s+","")+SharedPref.readSharedSettingsWhichYear(getContext(),"year","").replaceAll("\\s+","")).addChildEventListener(new ChildEventListener() {
                     @Override
                     public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                         if(dataSnapshot.child("year1").getValue().toString().equals(SharedPref.readSharedSettingsWhichYear(getContext(),"year",""))){
                             String msg=dataSnapshot.child("bcMsg").getValue().toString();
                             String cn=dataSnapshot.child("creatorName").getValue().toString();
                             String cid=dataSnapshot.child("from").getValue().toString();
                             StudentBroadcastList studentBroadcastList=new StudentBroadcastList(cn,msg,cid);
                             studentBroadcastLists.add(studentBroadcastList);
                             StudentBroadcastListAdapter broadcastListAdapter=new StudentBroadcastListAdapter(studentBroadcastLists);
                             recyclerView.setAdapter(broadcastListAdapter);
                             swipeRefreshLayout.setRefreshing(false);

                         }
                         else if(dataSnapshot.child("year2").getValue().toString().equals(SharedPref.readSharedSettingsWhichYear(getContext(),"year",""))){
                             String msg=dataSnapshot.child("bcMsg").getValue().toString();
                             String cn=dataSnapshot.child("creatorName").getValue().toString();
                             String cid=dataSnapshot.child("from").getValue().toString();
                             StudentBroadcastList studentBroadcastList=new StudentBroadcastList(cn,msg,cid);
                             studentBroadcastLists.add(studentBroadcastList);
                             StudentBroadcastListAdapter broadcastListAdapter=new StudentBroadcastListAdapter(studentBroadcastLists);
                             recyclerView.setAdapter(broadcastListAdapter);
                             swipeRefreshLayout.setRefreshing(false);

                         }
                         else if(dataSnapshot.child("year3").getValue().toString().equals(SharedPref.readSharedSettingsWhichYear(getContext(),"year",""))){
                             String msg=dataSnapshot.child("bcMsg").getValue().toString();
                             String cn=dataSnapshot.child("creatorName").getValue().toString();
                             String cid=dataSnapshot.child("from").getValue().toString();
                             StudentBroadcastList studentBroadcastList=new StudentBroadcastList(cn,msg,cid);
                             studentBroadcastLists.add(studentBroadcastList);
                             StudentBroadcastListAdapter broadcastListAdapter=new StudentBroadcastListAdapter(studentBroadcastLists);
                             recyclerView.setAdapter(broadcastListAdapter);
                             swipeRefreshLayout.setRefreshing(false);

                         }
                         else if(dataSnapshot.child("year4").getValue().toString().equals(SharedPref.readSharedSettingsWhichYear(getContext(),"year",""))){
                             String msg=dataSnapshot.child("bcMsg").getValue().toString();
                             String cn=dataSnapshot.child("creatorName").getValue().toString();
                             String cid=dataSnapshot.child("from").getValue().toString();
                             StudentBroadcastList studentBroadcastList=new StudentBroadcastList(cn,msg,cid);
                             studentBroadcastLists.add(studentBroadcastList);
                             StudentBroadcastListAdapter broadcastListAdapter=new StudentBroadcastListAdapter(studentBroadcastLists);
                             recyclerView.setAdapter(broadcastListAdapter);
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










        FloatingActionButton floatingActionButton=(FloatingActionButton)view.findViewById(R.id.fabStudent);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createAbroadcast=new CreateAbroadcastStudent();

                createAbroadcast.show(getChildFragmentManager(),"Shivam");




            }
        });


        return view;
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
