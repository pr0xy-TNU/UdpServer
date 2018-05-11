package test;

import client.UDPClient;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import server.UDPServer;

public class UDPTest {

  UDPClient udpClient;
  UDPServer udpServer;

  @Before
  public void setup() {
    new UDPServer().start();
    udpClient = new UDPClient();
  }

  @Test
  public void udpClientTest() {
    String test1 = udpClient.sendMessage("Hello server");
    Assert.assertEquals("Hello server", test1);

    String test2 = udpClient.sendMessage("lower case message");
    Assert.assertEquals("lower case message", test2);
  }

  @After
  public void end(){
    udpClient.sendMessage("exit");
    udpClient.close();
  }

}
