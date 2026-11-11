package ro.pub.cs.systems.eim.practicaltest01var06

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import android.app.Activity
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult

class PracticalTest01Var06MainActivity : AppCompatActivity() {
    private val values = listOf("1", "2", "3", "*")
    private lateinit var startForResult: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_practical_test01_var06_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val numere1: TextView = findViewById(R.id.numere1)
        val numere2: TextView = findViewById(R.id.numere2)
        val numere3: TextView = findViewById(R.id.numere3)
        val checkBox1: CheckBox = findViewById(R.id.checkBox1)
        val checkBox2: CheckBox = findViewById(R.id.checkBox2)
        val checkBox3: CheckBox = findViewById(R.id.checkBox3)
        val playButton: Button = findViewById(R.id.button)

        playButton.setOnClickListener {
            if (!checkBox1.isChecked) {
                numere1.setText(values.random())
            }
            if (!checkBox2.isChecked) {
                numere2.setText(values.random())
            }
            if (!checkBox3.isChecked) {
                numere3.setText(values.random())
            }
            Toast.makeText(this, numere1.text.toString() + " " + numere2.text.toString() + " " + numere3.text.toString(), Toast.LENGTH_LONG).show()
        }

        startForResult = registerForActivityResult(StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                val gain = data?.getIntExtra("gain", 0)
                Toast.makeText(this, "Gain: " + gain, Toast.LENGTH_LONG).show()
            }
        }

        val computeButton: Button = findViewById(R.id.compute_button)
        computeButton.setOnClickListener {
            var checked = 0
            if (checkBox1.isChecked) checked++
            if (checkBox2.isChecked) checked++
            if (checkBox3.isChecked) checked++

            val intent = Intent(this, PracticalTest01VarSecondaryActivity::class.java).apply {
                putExtra("numar1", numere1.text.toString())
                putExtra("numar2", numere2.text.toString())
                putExtra("numar3", numere3.text.toString())
                putExtra("checked", checked)
            }
            startForResult.launch(intent)
        }
    }
}