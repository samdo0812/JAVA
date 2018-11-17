package client;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

import javax.swing.*;
 
public class ClientGui  extends JFrame {
 
    private static final long serialVersionUID = 1L;
    private JButton btnSave = new JButton("��ȭ��������");
    private JTextArea jta = new JTextArea(40, 25);
    private JTextField jtf = new JTextField(25);
    private JScrollPane jsp = new JScrollPane(jta);
    private ClientBackground client = new ClientBackground();
    private static String nickName;
     
    public ClientGui() {

        add(btnSave, BorderLayout.NORTH);
        add(jsp, BorderLayout.CENTER);
        add(jtf, BorderLayout.SOUTH);
        
        jtf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String msg = nickName+ ":" + jtf.getText()+"\n";
                client.sendMessage(msg);
                jtf.setText("");
            }
        });
        jta.setEditable(false);
        jta.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setBounds(800, 100, 400, 600);
        
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String txt = jta.getText();
                txt = txt.replace("\n", "\r\n"); //\n�� \r\n���� ��ȯ
                String fileName = "��ȭ����.txt" ;
                 
                 
                try{
                    // ���� ��ü ����
                    File file = new File(fileName) ;
                     
                    // true ������ ������ ���� ���뿡 �̾ �ۼ�
                    FileWriter fw = new FileWriter(file, false) ;
                     
                    // ���Ͼȿ� ���ڿ� ����
                    fw.write(txt);
                    fw.flush();
         
                    // ��ü �ݱ�
                    fw.close();
                    
                    jta.append("System : ��ȭ������ ����Ǿ����ϴ�.\n");
                }catch(Exception eeee) {
                    eeee.printStackTrace();
                }    
            }
        });
         
        setTitle(nickName);
        client.setGui(this);
        client.setNickname(nickName);
        client.connet();
    }
     
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("����� �г��Ӻ��� �����ϼ��� : ");
        nickName = scanner.nextLine();
        scanner.close();        
         
        new ClientGui();
    }
 
 
    public void setGuiEnable(boolean isFreeze)
    {
        if(isFreeze)
        {
            jta.setEnabled(false);
            jtf.setEnabled(false);
        }
        else
        {
            jta.setEnabled(true);
            jtf.setEnabled(true);
        }
    }
    
    public void appendMsg(String msg) {
        jta.append(msg);
        jta.setCaretPosition(jta.getDocument().getLength());
    }
     
 
}