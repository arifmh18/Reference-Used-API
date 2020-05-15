package com.ardat.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        callApi()
    }

    private fun callApi() {
        val httpClient = httpClient()
        val apiRequest = apiRequest<NetworkService>(httpClient)
        val call = apiRequest.getListFilm()
        call.enqueue(object : Callback<List<RespondsAPIItem>> {
            override fun onFailure(call: Call<List<RespondsAPIItem>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Koneksi Gagal", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<List<RespondsAPIItem>>,
                response: Response<List<RespondsAPIItem>>
            ) {
                when {
                    response.isSuccessful ->
                        when {
                            response.body()?.size != 0 ->
                                tampilList(response.body()!!)
                            else -> {
                                Toast.makeText(this@MainActivity, "Berhasil", Toast.LENGTH_SHORT).show()
                            }
                        }
                    else -> {
                        Toast.makeText(this@MainActivity, "Gagal Ambil Data", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    private fun tampilList(githubUsers: List<RespondsAPIItem>) {
        listFilm.layoutManager = LinearLayoutManager(this)
        listFilm.adapter = FilmAdapter(this, githubUsers) {
            val filmList = it
            val httpClient = httpClient()
            val apiRequest = apiRequest<NetworkService>(httpClient)
            val call = apiRequest.getDetailFilm(filmList.id)
            call.enqueue(object : Callback<RespondsAPIItem> {
                override fun onFailure(call: Call<RespondsAPIItem>, t: Throwable) {
                    TODO("Not yet implemented")
                }

                override fun onResponse(
                    call: Call<RespondsAPIItem>,
                    response: Response<RespondsAPIItem>
                ) {
                    if (response.isSuccessful){
                        val data = response.body() as RespondsAPIItem
                        println(data.rtScore)
                    } else {
                        Toast.makeText(this@MainActivity, "Tidak Berhasil Parsing Data", Toast.LENGTH_SHORT).show()
                    }
                }
            })

        }
    }
}
