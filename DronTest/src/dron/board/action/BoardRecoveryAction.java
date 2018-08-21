package dron.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dron.board.db.BoardDAO;
import dron.commons.action.Action;
import dron.commons.action.ActionForward;

public class BoardRecoveryAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("euc-kr");
		
		boolean result = false;
		int num = Integer.parseInt(request.getParameter("num"));
		
		BoardDAO boarddao = new BoardDAO();
		result = boarddao.boardRecovery(num);
		
		if(result == false) { // 글 복원이 실패할 경우 null
			System.out.println("글 복원 실패");
			return null;
		}
		// 글 복원 성공할 때 게시판 메인 페이지로 이동
		System.out.println("글 복원 성공");
		forward.setRedirect(true);
		forward.setPath("./BoardList.bo");
		
		return forward;
	}

}
