package com.rpifinal.hitema.controller;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.rpifinal.hitema.R;
import com.rpifinal.hitema.model.User;
import java.util.ArrayList;

public class ArrayAdapterUser extends ArrayAdapter<User> {

    // =============================================================================================
    // ATTRIBUTS MEMBRES
    private Context context;
    private ArrayList<User> liste_user;
    private User user;
    // =============================================================================================

    // =============================================================================================
    public ArrayAdapterUser(Context context,int resource, ArrayList<User> objects) {

        super(context,0,objects);
        this.context = context;
    }

    public ArrayAdapterUser(Context context, ArrayList<User> objects) {

        super(context,0,objects);
        this.context = context;
        this.liste_user = objects;
    }

    @NonNull
    public View getView (int position, @Nullable View convertView, @Nullable ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        user = liste_user.get(position);
        @SuppressLint("ViewHolder") View vueCustom_User = inflater.inflate(R.layout.activity_array_adapter_user, parent,false);

        ImageView imageListViewProfile = vueCustom_User.findViewById(R.id.list_user_view_picture);
        TextView textView_username = vueCustom_User.findViewById(R.id.tv_username);
        TextView textView_lvl = vueCustom_User.findViewById(R.id.tv_lvl);
        TextView textView_exp = vueCustom_User.findViewById(R.id.tv_exp);

        Integer exp = (user.getXp());
        String s_exp = exp.toString();

        Integer lvl = (user.getLvl());
        String s_lvl = lvl.toString();

        textView_username.setText(user.getUsername());
        textView_lvl.setText(s_lvl);
        textView_exp.setText(s_exp);
        //imageListViewProfile.setImageURI(user.getUrlPicture());
        return vueCustom_User;
    }
    // =============================================================================================
}
