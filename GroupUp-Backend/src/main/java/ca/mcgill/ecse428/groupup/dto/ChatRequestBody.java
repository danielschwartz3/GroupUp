package ca.mcgill.ecse428.groupup.dto;


public class ChatRequestBody {
  String name;
  String[] members;
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String[] getMembers() {
    return members;
  }
  public void setMembers(String[] members) {
    this.members = members;
  }
}
