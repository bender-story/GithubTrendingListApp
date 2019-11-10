package self.com.githubtrendinglistapp.controller

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
// This class makes the navigation easy.
object Navigator{

    // Add/replace fragment to the activity
    fun addFragment(context: FragmentActivity?,fragmentName: String, addToBackStack: Boolean, bundle: Bundle?,tag: String,fragmentId:Int) {
        val manager = context?.supportFragmentManager
        val ft = manager?.beginTransaction()

        if (addToBackStack) {
            ft?.addToBackStack(tag)
        }
        val fragment = androidx.fragment.app.Fragment.instantiate(context, fragmentName, bundle)
        ft?.replace(fragmentId, fragment, tag)
        ft?.commitAllowingStateLoss()
    }
    //move from one activity to other.
    fun goToActivity(fromActivity: Activity, toActivity: Class<out Any>, finish: Boolean = false, bundle: Bundle? = null) {
        val intent = Intent(fromActivity, toActivity)
        intent.putExtras(bundle ?:Bundle())

        fromActivity.startActivity(intent)

        if (finish) fromActivity.finish()
    }
}