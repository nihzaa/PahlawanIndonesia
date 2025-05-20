package edu.unikom.pahlawanindonesia

import android.os.Build
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import edu.unikom.pahlawanindonesia.model.Hero

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_detail)

        val ivHeroImage = findViewById<ImageView>(R.id.img_heroImage)
        val tvHeroName = findViewById<TextView>(R.id.txt_heroName)
        val tvHeroDescription = findViewById<TextView>(R.id.txt_heroDescription)

        val hero = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Hero>("HERO_OBJECT", Hero::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Hero>("HERO_OBJECT")
        }

        if (hero != null) {
            ivHeroImage.setImageResource(hero.photo)
            tvHeroName.text = hero.name
            tvHeroDescription.text = hero.deskripsi


        }
    }
}
