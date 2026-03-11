package dam_a51609.helloworldoptional

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.os.Build
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val display = Build.DISPLAY.toString()
        val model = Build.MODEL.toString()
        val brand = Build.BRAND.toString()
        val type = Build.TYPE.toString()
        val user = Build.USER.toString()
        val version = Build.VERSION_CODES.BAKLAVA.toString()
        val sdk = Build.VERSION.SDK_INT.toString()
        val incremental = Build.VERSION.INCREMENTAL.toString()
        val base = Build.VERSION_CODES_FULL.BASE.toString()
        val manufacturer = Build.MANUFACTURER.toString()
        val multitext = findViewById<EditText>(R.id.editTextTextMultiLine)
        multitext.text.append("\n",
            "Manufacturer: ", manufacturer, "\n",
            "Model: ", model, "\n",
            "Brand: ", brand, "\n",
            "Type: ", type, "\n",
            "User: ",user, "\n",
            "Base: ",base, "\n",
            "Incremental: ",incremental, "\n",
            "SDK: ",sdk, "\n",
            "Version Code: ", version, "\n",
            "Display: ", display, "\n")

        println(this@MainActivity.localClassName + " onCreate")

        println(getString(R.string.activity_oncreate_msg, this@MainActivity.localClassName))

    }
}