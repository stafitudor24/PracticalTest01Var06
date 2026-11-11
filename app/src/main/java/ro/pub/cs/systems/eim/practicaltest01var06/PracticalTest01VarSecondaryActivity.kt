package ro.pub.cs.systems.eim.practicaltest01var06

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.widget.Button
import android.widget.TextView

class PracticalTest01VarSecondaryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_practical_test01_var_secondary)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val intentFromParent = intent
        val numar1 = intentFromParent.getStringExtra("numar1") ?: ""
        val numar2 = intentFromParent.getStringExtra("numar2") ?: ""
        val numar3 = intentFromParent.getStringExtra("numar3") ?: ""
        val checked = intentFromParent.getIntExtra("checked", 0)

        val gainedTextView: TextView = findViewById(R.id.gained_text_view)

        var gain = 0
        val faraJoker = listOf(numar1, numar2, numar3).filter { it != "*" }
        if (faraJoker.distinct().size <= 1) {
            gain = when (checked) {
                0 -> 100
                1 -> 50
                2 -> 10
                else -> 0
            }
            gainedTextView.text = "Gained " + gain
        } else {
            gainedTextView.text = "Not gained"
        }

        val okButton: Button = findViewById(R.id.ok_button)
        okButton.setOnClickListener {
            val returnIntent = Intent().apply {
                putExtra("Scor", gain)
            }
            setResult(RESULT_OK, returnIntent)
            finish()
        }
    }
}
