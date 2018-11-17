package dron.board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dron.board.db.BoardDAO;
import dron.commons.action.Action;
import dron.commons.action.ActionForward;

public class ForeverDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = new ActionForward(); 
        request.setCharacterEncoding("euc-kr");
         
        boolean result=false; 
        int num=Integer.parseInt(request.getParameter("num")); //������ �� ��ȣ�� num ������ �����Ѵ�. 
         
        BoardDAO boarddao = new BoardDAO(); 
        
        result=boarddao.foreverDelete(num);
     
        if(result==false){ //���� ���� ������ ��� null 
            System.out.println("����  ���� ����"); 
            return null; 
        } 
        //�� ���� �����Ҷ� �Խ��� ���� �������� �̵� 
        System.out.println("���� ���� ����"); 
        forward.setRedirect(true); 
        forward.setPath("./BoardList.bo"); 
        return forward; 
	}

}
