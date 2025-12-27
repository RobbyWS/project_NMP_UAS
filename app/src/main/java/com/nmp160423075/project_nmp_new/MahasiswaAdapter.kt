package com.nmp160423075.project_nmp_new

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nmp160423075.project_nmp_new.Mahasiswa
import com.nmp160423075.project_nmp_new.databinding.MahasiswaCardBinding
import com.squareup.picasso.Picasso
import java.net.URL

class MahasiswaAdapter(
    private val listMahasiswa: List<Mahasiswa>,
    private val onClick: (Mahasiswa) -> Unit
) : RecyclerView.Adapter<MahasiswaAdapter.ViewHolder>() {

    class ViewHolder(val binding: MahasiswaCardBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MahasiswaCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mhs = listMahasiswa[position]

        holder.binding.txtNama.text = mhs.nama
        holder.binding.txtNRP.text = mhs.nrp
        holder.binding.txtProgram.text = mhs.program

        Thread {
            try {
                val bitmap = BitmapFactory.decodeStream(
                    URL(mhs.photo_url).openStream()
                )
                holder.binding.imgMahasiswa.post {
                    holder.binding.imgMahasiswa.setImageBitmap(bitmap)
                }
            } catch (_: Exception) {}
        }.start()

        holder.binding.root.setOnClickListener {
            onClick(mhs)
        }
    }

    override fun getItemCount(): Int = listMahasiswa.size
}
