package com.nmp160423075.project_nmp_new

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.nmp160423075.project_nmp_new.MainPagerAdapter
import com.nmp160423075.project_nmp_new.StudentListFragment
import com.nmp160423075.project_nmp_new.my_friends
import com.nmp160423075.project_nmp_new.settings
import com.nmp160423075.project_nmp_new.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragments = arrayListOf(
            StudentListFragment(),
            my_friends(),
            settings()
        )

        binding.viewPager.adapter = MainPagerAdapter(this, fragments)

        binding.bottomNav.setOnItemSelectedListener {
            binding.viewPager.currentItem = when (it.itemId) {
                R.id.itemHome -> 0
                R.id.itemMyFriend -> 1
                R.id.itemSettings -> 2
                else -> 0
            }
            true
        }
        //endregion

//        val mahasiswaList = listOf(
//            Mahasiswa(
//                "Robby Wijaya Saputera",
//                "160423075",
//                "IMES",
//                R.drawable.pfp_160423075,
//                "Saya suka coding",
//                "Belajar Android, Machine Learning",
//                "Pernah ikut lomba AI"
//            ),
//            Mahasiswa(
//                "Ken Keanu Tanujaya",
//                "160423071",
//                "NCS",
//                R.drawable.pfp_160042071,
//                "Saya suka coding",
//                "Belajar Android, Machine Learning",
//                "Pernah ikut lomba AI"
//            ),
//            Mahasiswa(
//                "Vincent Firdaus Kurniawan",
//                "160423061",
//                "DSAI",
//                R.drawable.pfp_160423061,
//                "AMIN LULUS 7 Semester",
//                "Native Mobile Programming, Hybrid Mobile Programming",
//                "Pernah juara 3 lomba"
//            )
//        )


//        adapter = MahasiswaAdapter(mahasiswaList) { mhs ->
//            val intent = Intent(this, DetailMahasiswaActivity::class.java).apply {
//                putExtra("nama", mhs.nama)
//                putExtra("nrp", mhs.nrp)
//                putExtra("program", mhs.program)
//                putExtra("foto", mhs.foto)
//                putExtra("aboutMe", mhs.aboutMe)
//                putExtra("myCourse", mhs.myCourse)
//                putExtra("myExperience", mhs.myExperience)
//            }
//            startActivity(intent)
//        }
//
//        binding.recMahasiswa.layoutManager = LinearLayoutManager(this)
//        binding.recMahasiswa.adapter = adapter
    }
}
