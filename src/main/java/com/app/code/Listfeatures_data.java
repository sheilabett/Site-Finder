package com.app.code;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.SearchView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class Listfeatures_data extends AppCompatActivity {
    private Context mContext;
    EditText editText;
    RecyclerView recyclerView;
    private FeaturesAdapter adapter;
    TrialAdapter mAdapter;
    private StorageReference storageReference;
    private List<Featureslist_method> Listdata;
    SearchView searchView;
    DatabaseReference featuredbreff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listfeatures_data);
        mContext = Listfeatures_data.this;
        recyclerView = findViewById(R.id.my_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        searchView = findViewById(R.id.action_search); // inititate a search view
        Listdata = new ArrayList<>();
        storageReference = FirebaseStorage.getInstance().getReference("sites");
        featuredbreff = FirebaseDatabase.getInstance().getReference("features").child("com/app/code/sites");
        featuredbreff.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (snapshot.exists()) {
                    Featureslist_method Featuredetails = snapshot.getValue(Featureslist_method.class);
                    Listdata.add(Featuredetails);
                    //adapter = new FeaturesAdapter(mContext, Listdata);
                    mAdapter = new TrialAdapter(mContext,Listdata);
                    recyclerView.setAdapter(mAdapter);
                    mAdapter.notifyDataSetChanged();

                }

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }


            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setQueryHint("site name");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                mAdapter.getFilter().filter(s);
                return false;
            }
        });
    }
}