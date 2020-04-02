package com.rpifinal.hitema.partyGo.oldFiles

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rpifinal.hitema.R
import com.rpifinal.hitema.partyGo.oldFiles.model.User
import java.util.*

class ArrayAdapterUser : ArrayAdapter<User?> {
    @get:JvmName("getContext_")var context: Context
    var liste_user: ArrayList<User>? = null
    var user: User? = null

    constructor(context: Context, resource: Int, objects: ArrayList<User?>?) : super(context, 0, objects!!) {
        this.context = context
    }

    constructor(context: Context, objects: ArrayList<User>?) : super(context, 0, objects!! as List<User?>) {
        this.context = context
        liste_user = objects
    }

    fun getCustomView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        user = liste_user!![position]
        val vueCustom_User = inflater.inflate(R.layout.activity_array_adapter_user, parent, false)
        val urlUser = user!!.urlPicture
        var uri: Uri? = null
        if (urlUser != null) uri = Uri.parse(urlUser)
        val imgProfilListe = vueCustom_User.findViewById<ImageView>(R.id.list_user_view_picture)
        // Si une l'utilisateur possède une photo
        if (uri != null) { // Utilisation de Glide pour intégrer la photo dans la vue
            Glide.with(context)
                    .load(uri)
                    .apply(RequestOptions.circleCropTransform())
                    .into(imgProfilListe)
        }
        val textView_userName = vueCustom_User.findViewById<TextView>(R.id.tv_username)
        val textView_lvl = vueCustom_User.findViewById<TextView>(R.id.tv_lvl)
        val textView_exp = vueCustom_User.findViewById<TextView>(R.id.tv_exp)
        val exp = user!!.xp
        val s_exp = Integer.toString(exp)
        val lvl = user!!.lvl
        val s_lvl = Integer.toString(lvl)
        textView_userName.text = user!!.username
        textView_lvl.text = s_lvl
        textView_exp.text = s_exp
        return vueCustom_User
    }
}