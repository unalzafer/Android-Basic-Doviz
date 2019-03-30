package unalzafer.com.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.SearchView;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

import unalzafer.com.Adapter.DovizAdapter;
import unalzafer.com.Models.DovizModel;
import unalzafer.com.R;

public class MainActivity extends AppCompatActivity {

    //Url olarak siz belirlemeniz lazım. Json data olarak bir data veren bir url alabilirsiniz.
    /*

    { "Doviz": [ { "Code": "USD", "Unit": "1", "Name": "Amerikan Dolar\u0131", "Arrow": "up", "Buying": "5,5785", "Selling": "5,5807", "Percentage": "%0,58", "UpdateDate": "2019-03-30 11:52:13", "DetailLink": "Tıklanınca detay linki girilecek", "ImageLink": "resim linki girilecek" }]}
     */

    private static final String url="Json data get url giriniz";
    private RecyclerView recyclerView;
    private DovizAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    ArrayList<DovizModel> list;
    SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        list=new ArrayList<>();

        searchView=(SearchView)findViewById(R.id.swSearch);
        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchView.setIconified(false);
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                Log.e("queryTextinfo",s);
                if(s!=null&&!s.equals("")){
                    mAdapter.getFilter().filter(s);
                }
                return false;
            }
        });


        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response) {
                        JSONObject obj = null;
                        JSONArray jsonArray=null;
                        try {
                            obj = new JSONObject(String.valueOf(response));
                            jsonArray = obj.getJSONArray("Doviz");


                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                DovizModel hero = new DovizModel(jsonObject.getString("Code"), jsonObject.getString("Unit"),
                                        jsonObject.getString("Name"), jsonObject.getString("Arrow"), jsonObject.getString("Buying"),
                                        jsonObject.getString("Selling"), jsonObject.getString("Percentage"), jsonObject.getString("UpdateDate"),
                                        jsonObject.getString("DetailLink"), jsonObject.getString("ImageLink"));

                                list.add(hero);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                        recyclerView.setHasFixedSize(true);
                        layoutManager = new LinearLayoutManager(MainActivity.this);
                        recyclerView.setLayoutManager(layoutManager);

                        mAdapter = new DovizAdapter(list);
                        recyclerView.setAdapter(mAdapter);

                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Eğer herhangi hata ile karşılaşıldı ise bu bölüm çalıştırılır.
                        Log.d("Error.Response", error.toString());
                    }
                }
        );

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(getRequest);



    }

}
