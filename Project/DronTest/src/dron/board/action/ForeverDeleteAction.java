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
        int num=Integer.parseInt(request.getParameter("num")); //삭제할 글 번호를 num 변수에 저장한다. 
         
        BoardDAO boarddao = new BoardDAO(); 
        
        result=boarddao.foreverDelete(num);
     
        if(result==false){ //영구 삭제 실패할 경우 null 
            System.out.println("영구  삭제 실패"); 
            return null; 
        } 
        //글 삭제 성공할때 게시판 메인 페이지로 이동 
        System.out.println("영구 삭제 성공"); 
        forward.setRedirect(true); 
        forward.setPath("./BoardList.bo"); 
        return forward; 
	}

}
