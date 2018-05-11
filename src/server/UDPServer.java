package server;

import static utils.LoggerStatus.ERROR;
import static utils.LoggerStatus.INFO;
import static utils.NetHelper.logger;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import utils.NetHelper;

public class UDPServer extends Thread {

  private DatagramSocket socket;
  private byte[] buffer = new byte[256];
  private boolean isActive;

  public UDPServer() {
    try {
      socket = new DatagramSocket(NetHelper.PORT);
    } catch (SocketException e) {
      logger(ERROR, "Can`t open socket fro server!");
    }
    logger(INFO, "Start up udp client");
  }

  @Override
  public void run() {
    boolean isActive = true;
    while (isActive) {
      DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
      try {
        socket.receive(packet);
      } catch (IOException e) {
        logger(ERROR, e.getMessage());
      }
      InetAddress address = packet.getAddress();
      logger(INFO, address.getCanonicalHostName());
      packet = new DatagramPacket(buffer, buffer.length, address, packet.getPort());
      String received = new String(packet.getData(), 0, packet.getLength());
      logger(INFO, received);
      if (received.equalsIgnoreCase("exit")) {
        this.isActive = Boolean.FALSE;
        continue;
      }
      try {
        socket.send(packet);
      } catch (IOException e) {
        logger(ERROR, e.getMessage());
      }
    }
    socket.close();
    logger(INFO, "Connection closed.");
  }
}
