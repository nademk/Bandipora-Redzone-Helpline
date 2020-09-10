package com.redzone.bandipora.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.redzone.bandipora.R;
import com.redzone.bandipora.adapters.HomeRecentPostAdapter;
import com.redzone.bandipora.listeners.ListItemClickListener;
import com.redzone.bandipora.models.Posts;

import java.util.ArrayList;

public class PostActivity extends AppCompatActivity {

    private Activity mActivity;
    private Context mContext;

    private ArrayList<Posts> mContentList;
    private ArrayList<Posts> mCatWisePostList;
    private HomeRecentPostAdapter mAdapter = null;
    private RecyclerView mRecycler;
    private String mCategoryName;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initFirebase();
        initVar();
        initView();
        initFunctionality();
        initListener();

    }

    private void initVar() {
        mActivity = PostActivity.this;
        mContext = mActivity.getApplicationContext();

        Intent intent = getIntent();
        if (intent != null) {
            mCategoryName = intent.getStringExtra("title");
        }

        mContentList = new ArrayList<>();
        mCatWisePostList = new ArrayList<>();
    }

    private void initView() {
        setContentView(R.layout.activity_post);
        Bundle b = getIntent().getExtras();
        String catName = b.getString("title");
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText(catName);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRecycler = (RecyclerView) findViewById(R.id.rvItem);
        mRecycler.setLayoutManager(new GridLayoutManager(mActivity, 1, GridLayoutManager.VERTICAL, false));
        mAdapter = new HomeRecentPostAdapter(mContext, mActivity, mCatWisePostList);
        mRecycler.setAdapter(mAdapter);

    }

    private void initFunctionality() {
        loadData();

    }

    public void initListener() {
        // recycler list item click listener
        mAdapter.setItemClickListener(new ListItemClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                Posts model = mCatWisePostList.get(position);
                String phoneNumber = model.getPhone();

                switch (view.getId()) {

                    case R.id.call:
                        Intent callIntent = new Intent(Intent.ACTION_DIAL);
                        callIntent.setData(Uri.parse("tel:"+Uri.encode(phoneNumber.trim())));
                        callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(callIntent);
                        break;
                    default:
                        break;
                }
            }
        });
    }


    private void loadData() {
        loadPostsFromFirebase();
    }

    private void initFirebase() {
        FirebaseApp.initializeApp(this);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference();
    }

    private void loadPostsFromFirebase() {
        mDatabaseReference.child("sites").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot contentSnapShot : dataSnapshot.getChildren()) {
                    Posts post = contentSnapShot.getValue(Posts.class);
                    mContentList.add(post);
                }
                for (int i = 0; i < mContentList.size(); i++) {
                    if (mContentList.get(i).getCategory().equals(mCategoryName)) {
                        mCatWisePostList.add(mContentList.get(i));
                    }
                }
                updateUI();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    private void updateUI() {
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mContentList.size() != 0) {
            updateUI();
        }
    }
}
