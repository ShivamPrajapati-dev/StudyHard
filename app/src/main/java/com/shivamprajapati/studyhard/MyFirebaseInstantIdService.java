package com.shivamprajapati.studyhard;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class MyFirebaseInstantIdService extends FirebaseInstanceIdService {
    @Override
    public void onTokenRefresh() {
        String refreshToken=FirebaseInstanceId.getInstance().getToken();
        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();
        try{
        databaseReference.child("chat").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("token").setValue(refreshToken);
    }catch (Exception e){
            Log.i("TAG",e.getLocalizedMessage().toString());
        }
    }
}
