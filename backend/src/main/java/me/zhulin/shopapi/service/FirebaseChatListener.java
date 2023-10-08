package me.zhulin.shopapi.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.DatabaseReference.CompletionListener;
import com.google.firebase.database.FirebaseDatabase;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class FirebaseChatListener {

  @PostConstruct
  public void initializeFirebaseApp() throws IOException {
    FileInputStream serviceAccount =
        new FileInputStream("/Users/toinguyen/WorkSpace/SpringBoot-Angular7-Online-Shopping-Store/backend/src/main/resources/fir-chat-9cad0-firebase-adminsdk-ufpau-8deb48a7bd.json");

    FirebaseOptions options = new FirebaseOptions.Builder()
        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
        .setDatabaseUrl("https://fir-chat-9cad0-default-rtdb.firebaseio.com")
        .build();

    FirebaseApp.initializeApp(options);
  }

  public void startChatListener() {
    DatabaseReference chatRef = FirebaseDatabase.getInstance().getReference("chat_history");

    chatRef.addChildEventListener(new ChildEventListener() {
      @Override
      public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
        // Handle new chat messages
        // This method is called whenever a new chat message is added to the database
        ChatMessage chatMessage = dataSnapshot.getValue(ChatMessage.class);
        System.out.println("New message: " + chatMessage.getMessage());
      }

      @Override
      public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {
        // Handle updated chat messages
      }

      @Override
      public void onChildRemoved(DataSnapshot dataSnapshot) {
        // Handle deleted chat messages
      }

      @Override
      public void onChildMoved(DataSnapshot dataSnapshot, String prevChildKey) {
        // Handle moved chat messages
      }

      @Override
      public void onCancelled(DatabaseError databaseError) {
        System.err.println("Error: " + databaseError.getMessage());
      }
    });
  }
}
