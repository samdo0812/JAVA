package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
 
public class ClientBackground {
 
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private ClientGui gui;
    private String msg;
    private String nickName;
    private boolean isFreeze;
 
    public ClientBackground()
    {
        isFreeze = false;
    }
    
    public final void setGui(ClientGui gui) {
        this.gui = gui;
    }
 
    public void connet() {
        try {
            socket = new Socket("127.0.0.1", 7777);
            System.out.println("���� �����.");
             
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());
             
            //�������ڸ��� �г��� �����ϸ�. ������ �̰� �г������� �ν��� �ϰ� �ʿ� ����ְ�����?
            out.writeUTF(nickName); 
            System.out.println("Ŭ���̾�Ʈ : �޽��� ���ۿϷ�");
            while(in!=null){
                msg=in.readUTF();
                if(msg.equals("freeze"))
                {
                    isFreeze = !isFreeze;
                    gui.setGuiEnable(isFreeze);
                    if(isFreeze)
                        gui.appendMsg("System : �����ڰ� ä��â�� ��Ƚ��ϴ�.\n");
                    else
                        gui.appendMsg("System : �����ڰ� ä��â�� �쿴���ϴ�.\n");
                    continue;
                }
                gui.appendMsg(msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
    public static void main(String[] args) {
        ClientBackground clientBackground = new ClientBackground();
        clientBackground.connet();
    }
 
    public void sendMessage(String msg2) {
        try {
            out.writeUTF(msg2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
    public void setNickname(String nickName) {
        this.nickName = nickName;
    }
 
}