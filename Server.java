package Upd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

public class Server {
    private static final int PORT = 9999;
    private static DatagramSocket socket;
    private static JTextArea textArea;
    private static Map<InetAddress, Integer> users = new HashMap<>();

    public static void main(String[] args) throws Exception {
        socket = new DatagramSocket(PORT);
        JFrame frame = new JFrame("UDP Chat Server");
        textArea = new JTextArea(20, 50);
        textArea.setEditable(false);
        JTextField replyField = new JTextField(50);
        JButton sendButton = new JButton("Send Reply");

        sendButton.addActionListener(new SendButtonListener(replyField));

        frame.getContentPane().add(new JScrollPane(textArea), BorderLayout.CENTER);
        frame.getContentPane().add(replyField, BorderLayout.SOUTH);
        frame.getContentPane().add(sendButton, BorderLayout.EAST);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        receiveMessages();
    }

    private static void receiveMessages() {
        new Thread(() -> {
            try {
                while (true) {
                    byte[] receiveData = new byte[1024];
                    DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                    socket.receive(receivePacket);

                    String message = new String(receivePacket.getData(), 0, receivePacket.getLength());
                    InetAddress clientAddress = receivePacket.getAddress();
                    int clientPort = receivePacket.getPort();

                    // Store user address in HashMap
                    users.put(clientAddress, clientPort);
                    textArea.append("Received: " + message + "\n");

                    if (message.equalsIgnoreCase("exit")) {
                        textArea.append("Exiting server...\n");
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private static class SendButtonListener implements ActionListener {
        private JTextField replyField;

        public SendButtonListener(JTextField replyField) {
            this.replyField = replyField;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String reply = replyField.getText();
            if (!reply.isEmpty()) {
                try {
                    for (Map.Entry<InetAddress, Integer> user : users.entrySet()) {
                        DatagramPacket sendPacket = new DatagramPacket(reply.getBytes(), reply.length(), user.getKey(), user.getValue());
                        socket.send(sendPacket);
                    }
                    textArea.append("Sent: " + reply + "\n");
                    replyField.setText("");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
