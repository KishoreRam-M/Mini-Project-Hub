package Upd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {
    private static DatagramSocket socket;
    private static InetAddress ip;
    private static JTextArea textArea;
    private static JTextField textField;

    public static void main(String[] args) throws Exception {
        socket = new DatagramSocket();
        ip = InetAddress.getByName("localhost");

        JFrame frame = new JFrame("UDP Chat Client");
        textArea = new JTextArea(20, 50);
        textArea.setEditable(false);
        textField = new JTextField(50);

        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(new SendButtonListener());

        frame.getContentPane().add(new JScrollPane(textArea), BorderLayout.CENTER);
        frame.getContentPane().add(textField, BorderLayout.SOUTH);
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
                    textArea.append("Received from server: " + message + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private static class SendButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String msg = textField.getText();
            if (msg.equalsIgnoreCase("exit")) {
                try {
                    socket.close();
                    System.exit(0);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else {
                try {
                    DatagramPacket sendPacket = new DatagramPacket(msg.getBytes(), msg.length(), ip, 9999);
                    socket.send(sendPacket);
                    textArea.append("You: " + msg + "\n");
                    textField.setText("");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
