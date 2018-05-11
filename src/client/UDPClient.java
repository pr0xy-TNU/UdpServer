package client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import utils.NetHelper;

import static utils.LoggerStatus.ERROR;
import static utils.LoggerStatus.INFO;
import static utils.NetHelper.HOST;
import static utils.NetHelper.logger;


public class UDPClient {

  private DatagramSocket socket;
  private InetAddress address;
  private byte buffer[];

  public UDPClient() {
    try {
      this.socket = new DatagramSocket();
      this.address = InetAddress.getByName(NetHelper.HOST);
      address = InetAddress.getByName(HOST);
    } catch (SocketException e) {
      logger(ERROR, e.getMessage());
    } catch (UnknownHostException e) {
      logger(ERROR, "Can`t connect to localhost");
    }
    logger(INFO, "Start up udp client");
  }

  public String sendMessage(String message) {
    buffer = message.getBytes();
    DatagramPacket packet = new DatagramPacket(buffer, buffer.length, this.address, NetHelper.PORT);
    String received = null;
    try {
      socket.send(packet);
      packet = new DatagramPacket(buffer, buffer.length);
      socket.receive(packet);
      received = new String(packet.getData(), 0, packet.getLength());
      logger(INFO, "Client get message: " + received);

    } catch (IOException e) {
      logger(ERROR, "Error send message...");
    }
    return received;
  }

  public void close() {
    this.socket.close();
  }
}
