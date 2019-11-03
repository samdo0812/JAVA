package com.example.s1541.aidrone;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import com.romainpiel.shimmer.Shimmer;
import com.romainpiel.shimmer.ShimmerTextView;

public class TabFragment2 extends Fragment {

    TextView textView;
    ShimmerTextView shimmerTextView;
    Shimmer shimmer;

    ListView m_ListView;
    CustomAdapter m_CustomAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View RootView = inflater.inflate(R.layout.tab_fragment2, container, false);

        shimmerTextView = (ShimmerTextView) RootView.findViewById(R.id.shimmer_tv);
        shimmer = new Shimmer();
        shimmer.start(shimmerTextView);

        m_CustomAdapter = new CustomAdapter();  //커스텀 어댑터 생성
        m_ListView = (ListView) RootView.findViewById(R.id.listView2);   //xml에서 추가한 List View 연결
        m_ListView.setAdapter(m_CustomAdapter); //리스트뷰에 어댑터 연결

        m_CustomAdapter.add("띄워, 날아 이륙", 1);
        m_CustomAdapter.add("네, 알겠습니다. 드론을 이륙 하겠습니다.", 0);
        m_CustomAdapter.add("착륙", 1);
        m_CustomAdapter.add("네, 알겠습니다. 드론을 착륙 하겠습니다.", 0);
        m_CustomAdapter.add("전진, 앞으로", 1);
        m_CustomAdapter.add("네, 알겠습니다. 드론을 전진 하겠습니다.", 0);
        m_CustomAdapter.add("후진, 뒤로", 1);
        m_CustomAdapter.add("네, 알겠습니다. 드론을 후진 하겠습니다.", 0);
        m_CustomAdapter.add("위로, 상승", 1);
        m_CustomAdapter.add("네, 알겠습니다. 드론을 상승 하겠습니다.", 0);
        m_CustomAdapter.add("아래로, 하강", 1);
        m_CustomAdapter.add("네, 알겠습니다. 드론을 하강 하겠습니다.", 0);
        m_CustomAdapter.add("왼쪽으로", 1);
        m_CustomAdapter.add("네, 알겠습니다. 드론을 왼쪽으로 이동 하겠습니다.", 0);
        m_CustomAdapter.add("오른쪽으로", 1);
        m_CustomAdapter.add("네, 알겠습니다. 드론을 오른쪽으로 이동 하겠습니다.", 0);
        m_CustomAdapter.add("좌회전, 우회전", 1);
        m_CustomAdapter.add("네, 알겠습니다. 드론을 회전 하겠습니다.", 0);
        m_CustomAdapter.add("전방플립, 앞으로 플립, 후방플립, 뒤로 플립" + "\n" + "좌측플립, 왼쪽 플립, 우측플립, 오른쪽 플립", 1);
        m_CustomAdapter.add("네, 알겠습니다. 드론을 플립 하겠습니다.", 0);

        return RootView;
    }
}
