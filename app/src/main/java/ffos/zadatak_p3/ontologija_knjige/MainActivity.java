package ffos.zadatak_p3.ontologija_knjige;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    private Adapter adapter;
    private RESTTask asyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RecyclerView recyclerView = findViewById(R.id.ontologija);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter(this);
        recyclerView.setAdapter(adapter);

        SearchView searchView = (SearchView) findViewById(R.id.search);

        searchView.setQueryHint("Pretraži Sunčicinu ontologiju..");
        searchView.setSubmitButtonEnabled(true);
        searchView.setIconifiedByDefault(false);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                asyncTask = new RESTTask();
                if(query.toLowerCase().equals("prikaz")){
                    asyncTask.execute(getString(R.string.REST_URL));
                }else{
                    asyncTask.execute( "https://oziz.ffos.hr/nastava20192020/skojic_19/ontologija/search/" + query);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //adapter.getFilter().filter(newText);
                return false;
            }
        });

    }

    private class RESTTask extends AsyncTask<String, Void, List<Ontologija_knjiga>> {
        protected List<Ontologija_knjiga> doInBackground(String... adresa) {
            String stringUrl = adresa[0];
            List<Ontologija_knjiga> vrati = null;
            try {
                URL myUrl = new URL(stringUrl);
                HttpURLConnection connection = (HttpURLConnection)
                        myUrl.openConnection();
                connection.setRequestMethod("GET");
                connection.setReadTimeout(15000);
                connection.setConnectTimeout(15000);
                connection.connect();
                InputStreamReader streamReader = new
                        InputStreamReader(connection.getInputStream());
                BufferedReader reader = new BufferedReader(streamReader);
                Type listType = new TypeToken<ArrayList<Ontologija_knjiga>>() {
                }.getType();
                vrati = new Gson().fromJson(reader, listType);
                reader.close();
                streamReader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return vrati;
        }

        protected void onProgressUpdate(Integer... progress) {

        }

        protected void onPostExecute(List<Ontologija_knjiga> podaci) {
            adapter.setPodaci(podaci);
            adapter.notifyDataSetChanged();
        }

    }


}