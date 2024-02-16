package com.ostilo.kotlinfundamentals

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.gson.Gson
import com.ostilo.kotlinfundamentals.ui.theme.KotlinFundamentalsTheme

class MainActivity : ComponentActivity() {


    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        val bundleData = intent?.extras?.getBundle("name")
        val intentData = bundleData?.getString("personModel")
        val personModel = Gson().fromJson(intentData,PersonModel::class.java)
        if(personModel != null){
            when(personModel.name){
                "mo" -> {

                }
            }
        }
    }


    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KotlinFundamentalsTheme {
                val permission = rememberPermissionState(permission = android.Manifest.permission.POST_NOTIFICATIONS)
                if(!permission.status.isGranted){
                    //permission.launchPermissionRequest()
                     PermissionDialog{
                        permission.launchPermissionRequest()
                    }
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                   // checkNotificationPermission()
                }
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.size(width = 200.dp, height = 500.dp),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Button(onClick = {},
                        //modifier = Modifier.size(width = 50.dp, height = 50.dp)
                    ) {
                        Text(text = "Show Permission")
                    }
                }
            }
        }
    }

    private val requestNotificationPermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted->
            if (isGranted) { var rr = ""}// make your action here

        }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun checkNotificationPermission() {
        val permission = Manifest.permission.POST_NOTIFICATIONS
        when {
            ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED -> {
                // make your action here
                var rr = ""
            }
            shouldShowRequestPermissionRationale(permission) -> {
                // showPermissionRationaleDialog() // permission denied permanently
                var rr = ""
            }
            else -> {
                requestNotificationPermission.launch(permission)
            }
        }
    }
    @Composable
    fun PermissionDialog(function: () -> Unit) {
    }

    @Composable
    fun RationalDialog() {

    }

}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
   Button(onClick = {
       if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {

       }
   }) {

   }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    KotlinFundamentalsTheme {
        Greeting("Android")
    }
}
