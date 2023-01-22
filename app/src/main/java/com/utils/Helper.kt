package com.utils

import android.text.TextUtils
import android.util.Patterns
import android.view.View
import java.lang.Exception
import java.util.regex.Pattern

class Helper {

    companion object{

        fun validEmail(email:String):Boolean
        {
            return  !TextUtils.isEmpty(email) &&
                    Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }

        fun  hideKeyboard(view:View){
            try {

            }catch(e:Exception){
                e.printStackTrace()

            }
        }
    }

}