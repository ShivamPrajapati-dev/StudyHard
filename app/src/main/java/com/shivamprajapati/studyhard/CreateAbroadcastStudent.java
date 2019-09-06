package com.shivamprajapati.studyhard;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CreateAbroadcastStudent extends BottomSheetDialogFragment {

    public BottomSheetListener bottomSheetListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.broadcast_by_student, container, false);

        bottomSheetListener.OnBottomSheetListener(v);




        return v;
    }


    public interface BottomSheetListener{
        void OnBottomSheetListener(View v);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            bottomSheetListener= (BottomSheetListener) getParentFragment();
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString()+"must implement BottomSheetListener");
        }

    }
}