package dron.board.action; 

import java.util.ArrayList; 
import java.util.List; 

import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 
import javax.servlet.http.HttpSession; 

import dron.board.db.BoardDAO; 
import dron.commons.action.Action; 
import dron.commons.action.ActionForward; 

public class BoardListAction implements Action{ 
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception { 
		ActionForward forward = new ActionForward(); 
		HttpSession session=request.getSession(); 
		String id=(String)session.getAttribute("id"); 

		/*������ ���̵� ������ �ٽ� �α��� ȭ������ ������*/
		/*if(id==null){ 
			forward.setRedirect(true); 
			forward.setPath("./MemberLogin.dron"); 
			return forward; 
		}*/

		BoardDAO boarddao = new BoardDAO(); 
		List boardlist = new ArrayList(); 
		List d_boardlist = new ArrayList();

		int page=1; 
		int limit=10; 

		if(request.getParameter("page")!=null){ 
			page=Integer.parseInt(request.getParameter("page")); 
		} 

		int listcount=boarddao.getListCount(); // ���� �Խ��� ���̺��� ��ü row ������ �޾ƿ�. 
		int d_listcount = boarddao.d_getListCount(); // ������ ���̺��� ��ü row ������ �����´�.
		
		
		
		boardlist = boarddao.getBoardList(page,limit); // ���� �Խ��� ���̺��� ��ü row ����Ʈ�� �޾ƿ�. 
		d_boardlist = boarddao.d_getBoardList(page, limit);
		

		//�� ������ ��. 
		int maxpage=(int)((double)listcount/limit+0.95); //0.95�� ���ؼ� �ø� ó��. 
		//���� �������� ������ ���� ������ ��(1, 11, 21 ��...) 
		int startpage = (((int) ((double)page / limit + 0.9)) - 1) * limit + 1; 
		//���� �������� ������ ������ ������ ��.(10, 20, 30 ��...) 
		int endpage = maxpage; 

		if (endpage<startpage+limit-1) endpage=maxpage; 

		request.setAttribute("page", page);          //���� ������ ��. 
		request.setAttribute("maxpage", maxpage); //�ִ� ������ ��. 
		request.setAttribute("startpage", startpage); //���� �������� ǥ���� ù ������ ��. 
		request.setAttribute("endpage", endpage);     //���� �������� ǥ���� �� ������ ��. 
		request.setAttribute("listcount",listcount); //�� ��. 
		request.setAttribute("boardlist", boardlist);
		
		
		request.setAttribute("d_listcount", d_listcount);
		request.setAttribute("d_boardlist", d_boardlist);

		forward.setRedirect(false); 
		forward.setPath("./Board/boardList.jsp"); 
		return forward; 
	} 
}


