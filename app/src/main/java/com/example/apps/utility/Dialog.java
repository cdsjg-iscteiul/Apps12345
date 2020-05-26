package com.example.apps.utility;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import java.util.ArrayList;

public class Dialog extends AppCompatDialogFragment {


    private ArrayList<String> listsNames;
    private InterfaceListener listener;

    @NonNull
    @Override
    public android.app.Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        Bundle b = getArguments();
        listsNames = b.getStringArrayList("sendlist");
        final CharSequence[] s =  listsNames.toArray(new CharSequence[listsNames.size()+1]);
        s[listsNames.size()]="Remove From Shopping List";
        builder.setTitle("Send To List").setItems(s, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which==s.length-1){
                    listener.removeAndUpdate();
                }else{

                }

            }
        });

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        listener = (InterfaceListener) context;
    }

    public interface InterfaceListener{
        void sendToList(int i);

        void removeAndUpdate();
    }


}
