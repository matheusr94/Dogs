package matheusrodrigues.androidapps.dogs.view

import android.content.DialogInterface
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import kotlinx.android.synthetic.main.activity_main.*
import matheusrodrigues.androidapps.dogs.R
import matheusrodrigues.androidapps.dogs.util.PERMISSION_SEND_SMS
import java.security.AccessController
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = Navigation.findNavController(this, R.id.fragment)
        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null)
    }

    fun checkSmsPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.SEND_SMS,
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    android.Manifest.permission.SEND_SMS
                )
            ) {
                AlertDialog.Builder(this)
                    .setTitle("Send SMS permission")
                    .setMessage("This app require permission to send SMS")
                    .setPositiveButton("Ask me") { dialog, which ->
                        requestSmsPermission()
                    }
                    .setNegativeButton("No") { dialog, which ->
                        notifyDetailFragment(false)
                    }
                    .show()
            } else {
                requestSmsPermission()
            }
        } else {
            notifyDetailFragment(true)
        }
    }

    private fun requestSmsPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.SEND_SMS),
            PERMISSION_SEND_SMS
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            PERMISSION_SEND_SMS -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    notifyDetailFragment(true)
                } else {
                    notifyDetailFragment(false)
                }
            }
        }
    }

    private fun notifyDetailFragment(permissionGranted: Boolean) {
        val activityFragment = fragment.childFragmentManager.primaryNavigationFragment
        if (activityFragment is DetailFragment) {
            (activityFragment as DetailFragment).onPermissionResult(permissionGranted)
        }

    }
}