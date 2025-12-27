package com.nmp160423075.project_nmp_new

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.nmp160423075.project_nmp_new.databinding.ActivityDetailMahasiswaBinding
import com.squareup.picasso.Picasso

class DetailMahasiswaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailMahasiswaBinding

    companion object {
        var friendCount = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMahasiswaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val nama = intent.getStringExtra("nama")
        val nrp = intent.getStringExtra("nrp")
        val program = intent.getStringExtra("program")
        val photoUrl = intent.getStringExtra("photo_url")
        val aboutMe = intent.getStringExtra("aboutMe")
        val myCourse = intent.getStringExtra("myCourse")
        val myExperience = intent.getStringExtra("myExperience")

        binding.txtNamaDetail.text = nama
        binding.txtNRPDetail.text = nrp

        Picasso.get()
            .load(photoUrl)
            .placeholder(R.mipmap.ic_launcher)
            .error(R.mipmap.ic_launcher)
            .into(binding.imgFotoDetail)

        val spinnerItems = listOf("About Me", "My Course", "My Experiences")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerItems)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spnInfo.adapter = adapter

        binding.txtInfo.text = aboutMe

        binding.spnInfo.setOnItemSelectedListener { _, _, position, _ ->
            binding.txtInfo.text = when (position) {
                1 -> myCourse
                2 -> myExperience
                else -> aboutMe
            }
        }

        binding.btnRequestFriend.setOnClickListener {
            friendCount++
            AlertDialog.Builder(this)
                .setTitle("Friend Request")
                .setMessage("Sukses menambah $nama!\nJumlah friend: $friendCount")
                .setPositiveButton("OK", null)
                .show()
        }

        binding.btnExit.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        when (program) {
            "DSAI" -> binding.radioDSAI.isChecked = true
            "NCS" -> binding.radioNCS.isChecked = true
            "IMES" -> binding.radioIMES.isChecked = true
            "DMT" -> binding.radioDMT.isChecked = true
            "GD" -> binding.radioGD.isChecked = true
        }

        binding.radioDSAI.isEnabled = false
        binding.radioNCS.isEnabled = false
        binding.radioIMES.isEnabled = false
        binding.radioDMT.isEnabled = false
        binding.radioGD.isEnabled = false
    }

    private fun Spinner.setOnItemSelectedListener(
        onSelected: (parent: android.widget.AdapterView<*>, view: android.view.View?, position: Int, id: Long) -> Unit
    ) {
        this.onItemSelectedListener = object : android.widget.AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: android.widget.AdapterView<*>, view: android.view.View?, position: Int, id: Long) {
                onSelected(parent, view, position, id)
            }
            override fun onNothingSelected(parent: android.widget.AdapterView<*>) {}
        }
    }
}
