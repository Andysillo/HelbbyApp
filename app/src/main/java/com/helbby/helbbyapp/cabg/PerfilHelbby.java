package com.helbby.helbbyapp.cabg;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by Andy on 2/09/17.
 */

public class PerfilHelbby extends Fragment {

    TextView nameTextView, emailTextView, idTextView;
    ImageView photoView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.perfilhelbby,container,false);

        nameTextView = view.findViewById(R.id.nameTextView);
        emailTextView = view.findViewById(R.id.emailTextView);
        // idTextView = view.findViewById(R.id.idTextView);
        photoView = view.findViewById(R.id.photoView);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null){
            String name = user.getDisplayName();
            String email = user.getEmail();
            Glide.with(this).load(user.getPhotoUrl()).into(photoView);
          // String uid = user.getUid();

            nameTextView.setText(name);
            emailTextView.setText(email);
          //  idTextView.setText(uid);
        }else {
            goLoginScreen();
        }
        return view;
    }

    private void goLoginScreen() {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
