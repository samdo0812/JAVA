package server;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
 
public class ServerGui extends JFrame {
 
    private static final long serialVersionUID = 1L;
    private JTextArea jta = new JTextArea(40, 25);
    private JTextField jtf = new JTextField(25);
    private JScrollPane jsp = new JScrollPane(jta);
    private JButton btnFreeze = new JButton("대화 내용 얼리기 / 해제하기");
    private ServerBackground server = new ServerBackground();
 
    public ServerGui() throws IOException {
 
        add(jsp, BorderLayout.CENTER);
        add(jtf, BorderLayout.SOUTH);
        add(btnFreeze, BorderLayout.NORTH);
        jtf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String msg = "System : "+ jtf.getText() + "\n";
                jta.append(msg);
                server.sendMessage(msg);
                jtf.setText("");
            }
        });
        btnFreeze.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                server.sendMessage("freeze");
            }
        });
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setBounds(200, 100, 400, 600);
        setTitle("서버부분");
 
        server.setGui(this);
        server.setting();
    }
 
    public static void main(String[] args) throws IOException {
        new ServerGui();
    }
 
    public void appendMsg(String msg) {
        jta.append(msg);
    }
 
}