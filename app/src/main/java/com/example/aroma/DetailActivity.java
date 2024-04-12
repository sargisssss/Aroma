package com.example.aroma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.clans.fab.FloatingActionButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class DetailActivity extends AppCompatActivity {

    TextView detailDesc, detailTitle, detailPrice;
    RelativeLayout delete_edit;
    ImageView detailImage;
    FloatingActionButton deleteButton, editButton;
    ImageButton likeButton;
    String key = "";
    String imageUrl = "";
    DatabaseReference likesRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        detailDesc = findViewById(R.id.detailDesc);
        detailImage = findViewById(R.id.detailImage);
        detailTitle = findViewById(R.id.detailTitle);
        detailPrice = findViewById(R.id.detailPrice);
        deleteButton = findViewById(R.id.deleteButton);
        editButton = findViewById(R.id.editButton);
        likeButton = findViewById(R.id.likeButton);

        delete_edit = findViewById(R.id.delete_edit);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            detailDesc.setText(bundle.getString("Description"));
            detailTitle.setText(bundle.getString("Title"));
            detailPrice.setText(bundle.getString("Price"));
            key = bundle.getString("Key");
            imageUrl = bundle.getString("Image");
            Glide.with(this).load(bundle.getString("Image")).into(detailImage);
        }

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            String currentUserEmail = currentUser.getEmail();
            if (currentUserEmail != null && currentUserEmail.equals("sargis.arakelyan33@gmail.com")) {
                delete_edit.setVisibility(View.VISIBLE);
            } else {
                delete_edit.setVisibility(View.GONE);
            }
        }

        likeButton.setOnClickListener(new View.OnClickListener() {
            boolean isLiked = false; // Initially not liked

            @Override
            public void onClick(View v) {
                isLiked = !isLiked; // Toggle like status
                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                if (currentUser != null) {
                    String userId = currentUser.getUid();
                    DatabaseReference userLikesRef = FirebaseDatabase.getInstance().getReference().child("user_likes").child(userId).child(key);
                    if (isLiked) {
                        // Save entire product details to Firebase
                        userLikesRef.setValue(getProductDetails()).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    // Update UI
                                    likeButton.setImageResource(R.drawable.baseline_favorite_24_red);
                                    Toast.makeText(DetailActivity.this, "Liked", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(DetailActivity.this, "Failed to like", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    } else {
                        // Remove like status from Firebase
                        userLikesRef.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    // Update UI
                                    likeButton.setImageResource(R.drawable.baseline_favorite_border_24);
                                    Toast.makeText(DetailActivity.this, "Unliked", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(DetailActivity.this, "Failed to unlike", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            String userId = currentUser.getUid();
            DatabaseReference userLikesRef = FirebaseDatabase.getInstance().getReference().child("user_likes").child(userId).child(key);
            userLikesRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        // The product is liked
                        // You can retrieve the entire product details from the dataSnapshot
                        Map<String, Object> productDetails = (Map<String, Object>) dataSnapshot.getValue();
                        // Now you can use productDetails to update UI or perform any other operations
                        likeButton.setImageResource(R.drawable.baseline_favorite_24_red);
                    } else {
                        // The product is not liked
                        likeButton.setImageResource(R.drawable.baseline_favorite_border_24);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // Handle error
                }
            });
        }
    }

    // Helper method to get product details
    private Map<String, Object> getProductDetails() {
        Map<String, Object> productDetails = new HashMap<>();
        productDetails.put("dataDesc", detailDesc.getText().toString());
        productDetails.put("dataImage", imageUrl);
        productDetails.put("dataPrice", detailPrice.getText().toString());
        productDetails.put("dataTitle", detailTitle.getText().toString());
        return productDetails;
    }
}
