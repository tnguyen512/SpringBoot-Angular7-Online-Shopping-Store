package me.zhulin.shopapi.api;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.Collection;
import me.zhulin.shopapi.entity.OrderMain;
import me.zhulin.shopapi.entity.ProductInOrder;
import me.zhulin.shopapi.service.ChatMessage;
import me.zhulin.shopapi.service.OrderService;
import me.zhulin.shopapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created By Zhu Lin on 3/14/2018.
 */
@RestController("/chat")
@CrossOrigin
public class ChatController {
    @Autowired
    OrderService orderService;
    @Autowired
    UserService userService;

    @GetMapping("/chat")
    public void orderList(@RequestParam String message) {
        // Reference to the specific chat in the Realtime Database
        DatabaseReference chatRef = FirebaseDatabase.getInstance().getReference("chats/chat1/messages");

        // Create a new chat message
        ChatMessage newMessage = new ChatMessage("User1", "message");

        // Push the new message to the chat and set a completion listener
        chatRef.push().setValue(newMessage, (error, ref) -> {
            if (error == null) {
                System.out.println("Message added successfully!");
            } else {
                System.err.println("Error adding message: " + error.getMessage());
            }
        });
    }
}
