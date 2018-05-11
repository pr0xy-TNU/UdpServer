package utils;

public enum LoggerStatus {
  WARNING("WARNING"),
  NORMAL("NORMAL"),
  ERROR("ERROR"),
  INFO("INFO");

  private LoggerStatus(String status){
    this.status = status;
  }
  private String status;



}
