package ge.gogichaishvili.themovielist.presentation.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.facebook.drawee.backends.pipeline.Fresco
import ge.gogichaishvili.themovielist.R
import ge.gogichaishvili.themovielist.databinding.ActivityMainBinding
import ge.gogichaishvili.themovielist.presentation.fragments.ListFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Fresco.initialize(this)

        if (supportFragmentManager.findFragmentById(R.id.flContent) == null) {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.flContent, ListFragment())
                addToBackStack(ListFragment::javaClass.name)
                commit()
            }
        }
    }
}