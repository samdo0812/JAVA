package com.example.s1541.aidrone;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.JsonElement;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Map;

import ai.api.AIListener;
import ai.api.android.AIConfiguration;
import ai.api.android.AIService;
import ai.api.model.AIError;
import ai.api.model.AIResponse;
import ai.api.model.Result;


public class TabFragment1 extends Fragment implements AIListener {

    ImageButton listenButton;   //마이크 모양 Image Button
    Button button;

    //AI
    static AIService aiService;    //A.I.를 사용하기 위해
    //udp Server
    //private final String sIP = "192.168.10.1";  //서버주소
    //public static final int sPORT = 8889;   //사용할 통신 포트
    public  SendData mSendData = null;  //데이터 보낼 클래스

    String parameterString = "";    //Entity를 받는 문자열
    String command;     //드론에게 내릴 명령어 문자열

    Result result;  //전역 필드화 함으로써 모든 곳에 Query와 Intent, Entity를 사용할 수 있다.

    ListView m_ListView;    //list view 사용 객체
    CustomAdapter m_CustomAdapter;

    final AIConfiguration config = new AIConfiguration("7f043221c84a4f1eba0c7dcd8ae332b9", AIConfiguration.SupportedLanguages.Korean, AIConfiguration.RecognitionEngine.System);
    //A.I.설정(A.I.의 주소, 언어설정, 분석 시스템)

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View RootView = inflater.inflate(R.layout.tab_fragment1, container, false);

        listenButton  = (ImageButton) RootView.findViewById(R.id.listenButton);  //Image Button을 사용하기 위해 연결

        aiService = AIService.getService(getActivity(), config); //A.I. 설정을 A.I. Service에 넣음
        aiService.setListener(this);

        m_CustomAdapter = new CustomAdapter();  //커스텀 어댑터 생성
        m_ListView = (ListView) RootView.findViewById(R.id.listView1);   //xml에서 추가한 List View 연결
        m_ListView.setAdapter(m_CustomAdapter); //리스트뷰에 어댑터 연결

        return RootView;
    }

    @Override
    public void onResult(AIResponse response) {

        result = response.getResult();      //result에 결과값 받기

        listenButton.setImageResource(R.drawable.mic);

        switch (result.getResolvedQuery()) {
            case "1번 주행" :
                FreeDrive1 freeDrive1 = new FreeDrive1();   //1번 자율 주행 class를 사용하기 위하여 객체 생성
                freeDrive1.start();
                break;
            case "이번 주 행":
                FreeDrive2 freeDrive2 = new FreeDrive2();   //2번 자율 주행 class를 사용하기 위하여 객체 생성
                freeDrive2.start();
                break;
            case "3번 주행":
                FreeDrive3 freeDrive3 = new FreeDrive3();   //1번 자율 주행 class를 사용하기 위하여 객체 생성
                freeDrive3.start();
                break;
            default:
                mSendData = new SendData();
                mSendData.start();
                break;
        }

        //Get prameters
        if(result.getParameters() != null && !result.getParameters().isEmpty()) {   //만약 parameter값이 nul이 아니고 parameter값이 비어 있지 않다면
            for (final Map.Entry<String, JsonElement> entry: result.getParameters().entrySet()) {   //Map에 저장되어 있는 parameter만큼 돌려라
                parameterString = "(" + entry.getKey() + ", " + entry.getValue() + ") ";   //parameter 값 = 키, 값
            }
        }

        m_CustomAdapter.add(result.getResolvedQuery(), 1);

        switch (command) {
            case "" :
                m_CustomAdapter.add("잘 모르겠습니다.\n 다시 한번 말씀해 주시겠습니까?", 0);
                break;

            default :
                m_CustomAdapter.add("네, 알겠습니다.\n 드론에 " + result.getResolvedQuery() + "을(를) 명령하겠습니다.", 0);
                command = "";
                break;
        }

        result = null;  //result를 초기화하여 명령이 중복될 가능성 방지

    }

    @Override
    public void onError(AIError error) {

    }

    @Override
    public void onAudioLevel(float level) {

    }

    @Override
    public void onListeningStarted() {

    }

    @Override
    public void onListeningCanceled() {

    }

    @Override
    public void onListeningFinished() {

    }

    //데이터 보내는 쓰레드 클래스 I.o.t를 위한 UDP 통신
    class SendData extends Thread {     //Thread Class 상속
        public void run() {     //Class가 호출 되면 자동으로 실행
            try {
                command = "";            //드론에게 내릴 명령어를 넣을 문자열 초기화

                switch (result.getResolvedQuery()) {    //음성인식 후의 Query내용에 따른 명령어 저장
                    case "띄워" :
                        command = "takeoff";
                        break;
                    case "날아" :
                        command = "takeoff";
                        break;
                    case "이륙" :
                        command = "takeoff";
                        break;
                    case "착륙" :
                        command = "land";
                        break;
                    case "전방 플립" :
                        command = "flip f";
                        break;
                    case "전방 필립" :
                        command = "flip f";
                        break;
                    case "앞으로 필립" :
                        command = "flip f";
                        break;
                    case "앞으로 플립" :
                        command = "flip f";
                        break;
                    case "후방 플립" :
                        command = "flip b";
                        break;
                    case "후방 필립" :
                        command = "flip b";
                        break;
                    case "뒤로 필립" :
                        command = "flip b";
                        break;
                    case "뒤로 플립" :
                        command = "flip b";
                        break;
                    case "좌측 플립" :
                        command = "flip l";
                        break;
                    case "좌측 필립" :
                        command ="flip l";
                        break;
                    case "왼쪽 플립" :
                        command = "flip l";
                        break;
                    case "왼쪽 필립" :
                        command ="flip l";
                        break;
                    case "우측 플립" :
                        command = "flip r";
                        break;
                    case "우측 필립" :
                        command = "flip r";
                        break;
                    case "오른쪽 플립" :
                        command = "flip r";
                        break;
                    case "오른쪽 필립" :
                        command = "flip r";
                        break;
                    case "전진" :
                        command = "forward 100";
                        break;
                    case "앞으로" :
                        command = "forward 100";
                        break;
                    case "뒤로" :
                        command = "back 100";
                        break;
                    case "후진" :
                        command = "back 100";
                        break;
                    case "위로" :
                        command = "up 50";
                        break;
                    case "상승" :
                        command = "up 50";
                        break;
                    case "아래로" :
                        command = "down 50";
                        break;
                    case "하강" :
                        command = "down 50";
                        break;
                    case "왼쪽으로" :
                        command = "left 100";
                        break;
                    case "오른쪽으로" :
                        command = "right 100";
                        break;
                    case "좌회전" :
                        command = "ccw 90";
                        break;
                    case "우회전" :
                        command = "cw 90";
                        break;
                    case "베터리" :
                        command = "Battery?";
                        break;
                    case "배터리" :
                        command = "Battery?";
                        break;
                    case "시간" :
                        command = "Time?";
                        break;
                    default :
                        break;
                }

                InetAddress serverAddr = InetAddress.getByName("192.168.10.1"); //서버 주소

                DatagramSocket socket = new DatagramSocket();   //UDP 통신용 소켓 생성

                byte[] buf = ("command").getBytes();    //보낼 데이터 생성
                DatagramPacket packet = new DatagramPacket(buf, buf.length, serverAddr, 8889);  //패킷으로 변경

                socket.send(packet);    //패킷 전송

                buf = (command).getBytes();
                packet = new DatagramPacket(buf, buf.length, serverAddr, 8889);

                socket.send(packet);

                socket.receive(packet);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //1번 자율주행 데이터 보내는 쓰레드 클래스
    class FreeDrive1 extends Thread {
        public void run() {
            try {
                command = "1번 주행";

                InetAddress serverAddr = InetAddress.getByName("192.168.10.1"); //서버 주소

                DatagramSocket socket = new DatagramSocket();   //UDP 통신용 소켓 생성

                byte[] buf = ("command").getBytes();    //보낼 데이터 생성
                DatagramPacket packet = new DatagramPacket(buf, buf.length, serverAddr, 8889);  //패킷으로 변경

                socket.send(packet);    //패킷 전송

                buf = ("takeoff").getBytes();
                packet = new DatagramPacket(buf, buf.length, serverAddr, 8889);

                socket.send(packet);

                //드론 Action 후 시간이 흐름
                sleep(8000);    //상승 시간 때문에 8초가 적당

                //자율 주행 코딩할 부분-------------------------------------------------------------

                buf = ("flip f").getBytes();
                packet = new DatagramPacket(buf, buf.length, serverAddr, 8889);

                socket.send(packet);

                sleep(3000);    //앞에서 내린 명령어 수행 중인 드론의 delay time 때문에 중간 중간 해당 숫자만큼의 millisecnds만큼 멈춰 준다

                buf = ("forward 100").getBytes();
                packet = new DatagramPacket(buf, buf.length, serverAddr, 8889);

                socket.send(packet);

                sleep(3000);    //단순 이동은 금방 진행되기에 짧게 시간

                buf = ("flip b").getBytes();
                packet = new DatagramPacket(buf, buf.length, serverAddr, 8889);

                socket.send(packet);

                sleep(3000);

                buf = ("back 100").getBytes();
                packet = new DatagramPacket(buf, buf.length, serverAddr, 8889);

                socket.send(packet);

                sleep(3000);

                //----------------------------------------------------------------------------------

                buf = ("land").getBytes();
                packet = new DatagramPacket(buf, buf.length, serverAddr, 8889);

                socket.send(packet);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //2번 자율주행 데이터 보내는 쓰레드 클래스
    class FreeDrive2 extends Thread {
        public void run() {
            try {
                command = "2번 주행";

                InetAddress serverAddr = InetAddress.getByName("192.168.10.1"); //서버 주소

                DatagramSocket socket = new DatagramSocket();   //UDP 통신용 소켓 생성

                byte[] buf = ("command").getBytes();    //보낼 데이터 생성
                DatagramPacket packet = new DatagramPacket(buf, buf.length, serverAddr, 8889);  //패킷으로 변경

                socket.send(packet);    //패킷 전송

                buf = ("takeoff").getBytes();
                packet = new DatagramPacket(buf, buf.length, serverAddr, 8889);

                socket.send(packet);

                sleep(8000);    //상승 시간 때문에 8초가 적당

                //자율주행 코딩할 부분--------------------------------------------------------------

                buf = ("forward 150").getBytes();
                packet = new DatagramPacket(buf, buf.length, serverAddr, 8889);

                socket.send(packet);

                sleep(3000);

                buf = ("cw 90").getBytes();
                packet = new DatagramPacket(buf, buf.length, serverAddr, 8889);

                socket.send(packet);

                sleep(3000);

                buf = ("forward 150").getBytes();
                packet = new DatagramPacket(buf, buf.length, serverAddr, 8889);

                socket.send(packet);

                sleep(3000);

                buf = ("cw 90").getBytes();
                packet = new DatagramPacket(buf, buf.length, serverAddr, 8889);

                socket.send(packet);

                sleep(3000);

                buf = ("forward 150").getBytes();
                packet = new DatagramPacket(buf, buf.length, serverAddr, 8889);

                socket.send(packet);

                sleep(3000);

                buf = ("cw 90").getBytes();
                packet = new DatagramPacket(buf, buf.length, serverAddr, 8889);

                socket.send(packet);

                sleep(3000);

                buf = ("forward 150").getBytes();
                packet = new DatagramPacket(buf, buf.length, serverAddr, 8889);

                socket.send(packet);

                sleep(3000);

                //----------------------------------------------------------------------------------

                buf = ("land").getBytes();
                packet = new DatagramPacket(buf, buf.length, serverAddr, 8889);

                socket.send(packet);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //3번 자율주행 데이터 보내는 쓰레드 클래스
    class FreeDrive3 extends Thread {
        public void run() {
            try {
                command = "3번 주행";

                InetAddress serverAddr = InetAddress.getByName("192.168.10.1"); //서버 주소

                DatagramSocket socket = new DatagramSocket();   //UDP 통신용 소켓 생성

                byte[] buf = ("command").getBytes();    //보낼 데이터 생성
                DatagramPacket packet = new DatagramPacket(buf, buf.length, serverAddr, 8889);  //패킷으로 변경

                socket.send(packet);    //패킷 전송

                buf = ("takeoff").getBytes();
                packet = new DatagramPacket(buf, buf.length, serverAddr, 8889);

                socket.send(packet);

                sleep(8000);    //상승 시간 때문에 8초가 적당

                //자율주행 코딩할 부분 -------------------------------------------------------------

                buf = ("flip f").getBytes();
                packet = new DatagramPacket(buf, buf.length, serverAddr, 8889);

                socket.send(packet);

                sleep(3000);

                buf = ("flip l").getBytes();
                packet = new DatagramPacket(buf, buf.length, serverAddr, 8889);

                socket.send(packet);

                sleep(3000);

                buf = ("flip r").getBytes();
                packet = new DatagramPacket(buf, buf.length, serverAddr, 8889);

                socket.send(packet);

                sleep(3000);

                buf = ("flip b").getBytes();
                packet = new DatagramPacket(buf, buf.length, serverAddr, 8889);

                socket.send(packet);

                sleep(3000);

                buf = ("cw 400").getBytes();
                packet = new DatagramPacket(buf, buf.length, serverAddr, 8889);

                socket.send(packet);

                sleep(4000);

                buf = ("ccw 400").getBytes();
                packet = new DatagramPacket(buf, buf.length, serverAddr, 8889);

                socket.send(packet);

                sleep(4000);
                //----------------------------------------------------------------------------------

                buf = ("land").getBytes();
                packet = new DatagramPacket(buf, buf.length, serverAddr, 8889);

                socket.send(packet);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}