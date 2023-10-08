package me.zhulin.shopapi.service;

public class ChatMessage {
  private String sender;
  private String message;

  public ChatMessage(String user1, String s) {
  }

  // Getters and setters

  public String getSender() {
    return sender;
  }

  public void setSender(String sender) {
    this.sender = sender;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
