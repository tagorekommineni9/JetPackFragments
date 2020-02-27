package com.example.jetpackfragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class FirstFragment extends Fragment {
    Button btn_go;
    private NavController navController;



    ArrayList<Pokemon_> parray;
    RecycleAdapater adapter;

    public FirstFragment() {

    }


    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Dataservice service = RetrofitClientInstance.getRetrofitInstance().create(Dataservice.class);

        navController = Navigation.findNavController(getActivity(),R.id.nav_host_fragment);

        Call<Pokemon> call = service.getAllPokemons();

        call.enqueue(new Callback<Pokemon>() {
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                Pokemon pokemon = response.body();
                try{
                    parray = new ArrayList<>(pokemon.getPokemon());
                    generateView(parray,view);
                }
                catch (NullPointerException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Pokemon> call, Throwable t) {
                Toast.makeText(getActivity().getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    public View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) v.getTag();
            int position = viewHolder.getAdapterPosition();
            Toast.makeText(getActivity().getApplicationContext(),parray.get(position).getName(),Toast.LENGTH_LONG).show();


            Bundle b = new Bundle();
            b.putParcelable("data",parray.get(position));


            navController.navigate(R.id.secondFragment,b);
        }
    };

    public  void generateView(ArrayList<Pokemon_> pary, View view)
    {
        adapter = new RecycleAdapater(pary,getActivity().getApplicationContext());
        LinearLayoutManager manager = new LinearLayoutManager(getActivity().getApplicationContext(),LinearLayoutManager.VERTICAL,false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        adapter.setClickListener(onClickListener);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_first, container, false);
    }




    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

}
