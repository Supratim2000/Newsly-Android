package com.example.newsly

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject

class NewsActivity : AppCompatActivity(), onItemClicked {

    private lateinit var NewsAdapter: Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        val intent: Intent = intent
        val URL: String? = intent.extras?.getString("selectedUrl")

        var recyclerView: RecyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        if (URL != null) {
            fetchData(URL)
        }
        NewsAdapter = Adapter(this)
        recyclerView.adapter = NewsAdapter
    }

    private fun fetchData(Url: String){

        Log.v("fDataCall", "Feach Data called")

        val NewsFeededArray: ArrayList<NewsDataClass> = ArrayList<NewsDataClass>()

        val queue: RequestQueue = Volley.newRequestQueue(this)

        val request: JsonObjectRequest = JsonObjectRequest(Request.Method.GET,Url,null,object: Response.Listener<JSONObject>{
            override fun onResponse(response: JSONObject?) {
                val JSONToString: String = response.toString()
                try {
                    val root: JSONObject? = response
                    val newsArray = root?.getJSONArray("articles")
                    if (newsArray != null) {
                        for(i in 0 until newsArray.length()) {
                            val currentArrayItem: JSONObject = newsArray.getJSONObject(i)
                            val tempAuthor: String? = currentArrayItem.getString("author")
                            val currentAuthor:String = if(tempAuthor == null) { "No author" } else { tempAuthor }
                            val currentTitle: String = currentArrayItem.getString("title")
                            val currentUrl: String = currentArrayItem.getString("url")
                            val currentUrlToImage: String = currentArrayItem.getString("urlToImage")
                            val currentPublishedAt: String = currentArrayItem.getString("publishedAt")

                            NewsFeededArray.add(NewsDataClass(currentAuthor, currentTitle, currentUrl, currentUrlToImage, currentPublishedAt))
                        }
                    }
                    NewsAdapter.updateNews(NewsFeededArray)
                    Log.v("success", "Json Loaded succesfully...\n $JSONToString")
                } catch(error: JSONException) {
                    Log.e("error","Json Not loaded...")
                    error.printStackTrace()
                }
            }
        },object: Response.ErrorListener{
            override fun onErrorResponse(error: VolleyError?) {
                Toast.makeText(applicationContext,"Data not loaded...\nKindly Check your Internet connection",Toast.LENGTH_LONG).show()
                Log.e("failure","Data Not loaded...\nKindly Check your Internet connection")
                error?.printStackTrace()
            }
        })

        queue.add(request)

    }

    override fun onItemClicked(data: NewsDataClass) {
        val builder: CustomTabsIntent.Builder = CustomTabsIntent.Builder();
        val customTabsIntent: CustomTabsIntent = builder.build();
        builder.setToolbarColor(resources.getColor(R.color.Red));
        customTabsIntent.launchUrl(this, Uri.parse(data.getUrl()));
    }
}