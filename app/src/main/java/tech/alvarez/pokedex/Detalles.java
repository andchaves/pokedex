package tech.alvarez.pokedex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import tech.alvarez.pokedex.models.Pokemon;
import tech.alvarez.pokedex.pokeapi.PokeapiService;

public class Detalles extends AppCompatActivity {

    private static final String TAG="POKEDEX";
    private Retrofit retrofit;
    private TextView pesoPoke;
    private TextView nomPoke;
    private TextView tipoPoke;
    private TextView habiPoke;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);
        pesoPoke=(TextView)findViewById(R.id.peso_txt);
        nomPoke=(TextView)findViewById(R.id.nombre_txt);
        tipoPoke=(TextView)findViewById(R.id.tipo_txt);
        habiPoke=(TextView)findViewById(R.id.habilidad_txt);



        retrofit = new Retrofit.Builder()
                .baseUrl("http://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Intent i=getIntent();
        Bundle b=i.getExtras();
        if (b!=null)
        {
            int in=(int)b.get("id");
            String id= in+"";

            obtenerDatos(in);
        }


    }
    public void salir(View v)
    {
        finish();
    }

    private void obtenerDatos(int in) {

        PokeapiService service = retrofit.create(PokeapiService.class);
        Call<Pokemon> pokemonRespuestaCall = service.obtenerDatosPokemon(in);

        pokemonRespuestaCall.enqueue(new Callback<Pokemon>() {
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                if(response.isSuccessful()){
                    Pokemon pokemon = response.body();
                    //Log.e(TAG," AQUI--->Pokemon: "+response.body().toString());
                    //nomPoke=(TextView)findViewById(R.id.id_txt);

                    nomPoke.setText("NOMBRE: "+ pokemon.getName());
                    tipoPoke.setText("TIPO: "+pokemon.getTypes().get(0).getType().getName());
                    habiPoke.setText("HABILIDAD: "+pokemon.getAbilities().get(0).getAbility().getName());
                    pesoPoke.setText("PESO: "+ pokemon.getWeight());


                    Log.i(TAG," Pokemon: "+pokemon.getName()+" peso: "+pokemon.getWeight()+" tipo: "+pokemon.getTypes().get(0).getType().getName());

                }else
                {
                    Log.e(TAG, "onResponse: "+response.errorBody());
                }

            }

            @Override
            public void onFailure(Call<Pokemon> call, Throwable t) {
                Log.e(TAG," onFailure: "+t.getMessage());
            }
        });

    }
}
