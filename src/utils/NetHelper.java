package utils;

import java.text.SimpleDateFormat;

public class NetHelper {

  public static final Integer PORT = 4445;
  public static final String HOST = "localhost";
  public static final String TIME_PATTER = "dd-MM-yyyy hh:mm:ss";

  public static final void logger(LoggerStatus status, String message) {
    if (status != null) {

      SimpleDateFormat dateFormat = new SimpleDateFormat(TIME_PATTER);
      switch (status) {
        case WARNING:
          System.err.println(
              "INFO:  " + dateFormat.format(System.currentTimeMillis()) + " -> " + message);
          break;
        case NORMAL:
        case INFO:
          System.out.println(
              "INFO:  " + dateFormat.format(System.currentTimeMillis()) + " -> " + message);

      }
    }

  }
}
