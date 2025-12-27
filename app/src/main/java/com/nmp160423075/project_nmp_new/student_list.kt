package com.nmp160423075.project_nmp_new

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nmp160423075.project_nmp_new.Mahasiswa
import com.nmp160423075.project_nmp_new.databinding.FragmentStudentListBinding
import org.json.JSONArray
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [student_list.newInstance] factory method to
 * create an instance of this fragment.
 */
class StudentListFragment : Fragment(R.layout.fragment_student_list) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MahasiswaAdapter
    private val list = ArrayList<Mahasiswa>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recMahasiswa)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        fetchMahasiswa()
    }

    private fun fetchMahasiswa() {
        Thread {
            try {
                val url = URL(
                    "http://10.0.2.2/Project_NMP_2025/nmp_project/get_all_student.php"
                )
                val conn = url.openConnection() as HttpURLConnection
                conn.requestMethod = "GET"

                val reader = BufferedReader(InputStreamReader(conn.inputStream))
                val json = reader.readText()
                Log.d("JSON", json)

                val jsonArray = JSONArray(json)
                list.clear()

                for (i in 0 until jsonArray.length()) {
                    val obj = jsonArray.getJSONObject(i)
                    list.add(
                        Mahasiswa(
                            obj.getString("nrp"),
                            obj.getString("nama"),
                            obj.getString("email"),
                            obj.getString("program"),
                            obj.getString("about_me"),
                            obj.getString("my_course"),
                            obj.getString("my_experiences"),
                            obj.getString("photo_url")
                        )
                    )
                }

                requireActivity().runOnUiThread {
                    adapter = MahasiswaAdapter(list) { mhs ->
                        val intent = Intent(requireContext(), DetailMahasiswaActivity::class.java)
                        intent.putExtra("nrp", mhs.nrp)
                        startActivity(intent)
                    }
                    recyclerView.adapter = adapter
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }.start()
    }



    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment student_list.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            StudentListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}