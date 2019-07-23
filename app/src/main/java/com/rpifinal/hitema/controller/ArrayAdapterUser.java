package com.rpifinal.hitema.controller;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.rpifinal.hitema.R;
import com.rpifinal.hitema.model.User;
import java.util.ArrayList;


public class ArrayAdapterUser extends ArrayAdapter<User> {

    private Context context;
    private ArrayList<User> liste_user;
    private User user;

    public ArrayAdapterUser(Context context, int resource, ArrayList<User> objects) {

        super(context,0,objects);
        this.context = context;
    }

    public ArrayAdapterUser(Context context, ArrayList<User> objects) {

        super(context,0,objects);
        this.context = context;
        this.liste_user = objects;
    }

    public View getView (int position, @Nullable View convertView, @Nullable ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        user = liste_user.get(position);
        View vueCustom_User = inflater.inflate(R.layout.activity_array_adapter_user, parent,false);

        String urlUser = user.getUrlPicture();
        Uri uri =  Uri.parse(urlUser);
        ImageView imgProfilListe = vueCustom_User.findViewById(R.id.list_user_view_picture);

        // Si une l'utilisateur possède une photo
        if (uri != null)
        {
            // Utilisation de Glide pour intégrer la photo dans la vue
            Glide.with(context)
                    .load(uri)
                    .apply(RequestOptions.circleCropTransform())
                    .into(imgProfilListe);
        }

        TextView textView_userName = vueCustom_User.findViewById(R.id.tv_username);
        TextView textView_lvl = vueCustom_User.findViewById(R.id.tv_lvl);
        TextView textView_exp = vueCustom_User.findViewById(R.id.tv_exp);

        Integer exp = (user.getXp());
        String s_exp = exp.toString();

        Integer lvl = (user.getLvl());
        String s_lvl= lvl.toString();

        textView_userName.setText(user.getUsername());
        textView_lvl.setText(s_lvl);
        textView_exp.setText(s_exp);

        return vueCustom_User;
    }
}
