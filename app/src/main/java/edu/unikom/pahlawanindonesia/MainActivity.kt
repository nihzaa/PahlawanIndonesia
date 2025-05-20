package edu.unikom.pahlawanindonesia

//NIM : 10122908
//Nama : Nizar Ihza Zulkarnain
//Kelas : IF13
//Last Modified : 19/05/2025

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.unikom.pahlawanindonesia.adapter.ListHeroAdapter
import edu.unikom.pahlawanindonesia.model.Hero

class MainActivity : AppCompatActivity() {

    private lateinit var rvHeroes: RecyclerView
    private val list = ArrayList<Hero>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        rvHeroes = findViewById(R.id.rv_heroes)
        rvHeroes.setHasFixedSize(true)

        list.addAll(getListHero())
        showRecyclerList()
    }

//    private fun getListHero(): ArrayList<Hero> {
//
//        val dataName = resources.getStringArray(R.array.data_name)
//        val dataDescription = resources.getStringArray(R.array.data_description)
//        val dataPhoto = resources.getIntArray(R.array.data_photo)
//        val listHero = ArrayList<Hero>()
//        for (i in dataName.indices) {
//            val hero = Hero(dataName[i], dataDescription[i], dataPhoto[i])
//            listHero.add(hero)
//        }
//
//        return listHero
//    }

    // Di MainActivity.kt
    private fun getListHero(): ArrayList<Hero> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhotoTypedArray = resources.obtainTypedArray(R.array.data_photo) // PENTING
        val listHero = ArrayList<Hero>()

        // Pastikan semua array memiliki panjang yang sama, atau gunakan panjang array terpendek untuk loop
        // Contoh: val itemCount = minOf(dataName.size, dataDescription.size, dataPhotoTypedArray.length())
        // for (i in 0 until itemCount) { ... }

        for (i in dataName.indices) { // Asumsi semua array punya panjang yang sama
            val photoResourceId = dataPhotoTypedArray.getResourceId(i, -1) // Default -1 jika tidak ada

            // Debugging: Cetak apa yang didapatkan
            Log.d("MainActivity", "Pahlawan: ${dataName[i]}, Deskripsi: ${dataDescription[i]}, PhotoID: $photoResourceId")

            if (photoResourceId != -1) { // Hanya tambahkan jika ID foto valid
                val hero = Hero(dataName[i], dataDescription[i], photoResourceId)
                listHero.add(hero)
            } else {
                // Jika ID foto -1, berarti ada masalah dengan entri di data_photo
                // atau gambar tidak ditemukan. Anda bisa menambahkan placeholder atau log.
                Log.e("MainActivity", "Photo resource ID not found for ${dataName[i]} at index $i. Check your data_photo array and drawable folder.")
                // Contoh: menambahkan hero dengan gambar placeholder
                // val hero = Hero(dataName[i], dataDescription[i], R.drawable.placeholder_image) // Ganti R.drawable.placeholder_image dengan gambar placeholder Anda
                // listHero.add(hero)
            }
        }
        dataPhotoTypedArray.recycle() // SANGAT PENTING
        return listHero
    }

    private fun showRecyclerList(){

        val listHeroAdapter = ListHeroAdapter(list){data ->
            showSelectedHero(data)
        }

        listHeroAdapter.setOnItemClickCallback(object : ListHeroAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Hero) {
                showSelectedHero(data)
            }
        })

        rvHeroes.layoutManager = LinearLayoutManager(this)
        rvHeroes.adapter = listHeroAdapter
        rvHeroes.setHasFixedSize(true)
    }

    private fun showSelectedHero(hero:Hero){
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("HERO_OBJECT", hero)
        startActivity(intent)

    }

}