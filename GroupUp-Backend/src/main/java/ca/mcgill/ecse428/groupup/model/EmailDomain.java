package ca.mcgill.ecse428.groupup.model;

public enum EmailDomain {
  MCGILL("mail.mcgill.ca"), CARLETON("carleton.ca");

  String domain;

  EmailDomain(String domain) {
    this.domain = domain;
  }

  public String getDomain() {
    return domain;
  }
}
