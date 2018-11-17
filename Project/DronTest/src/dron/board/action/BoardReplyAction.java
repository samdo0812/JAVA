package dron.board.action; 

import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 

import dron.board.db.BoardBean; 
import dron.board.db.BoardDAO; 
import dron.commons.action.Action; 
import dron.commons.action.ActionForward; 

public class BoardReplyAction implements Action{ 

    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception { 
        request.setCharacterEncoding("euc-kr"); 
        ActionForward forward = new ActionForward(); 
         
        BoardDAO boarddao = new BoardDAO(); 
        BoardBean boarddata = new BoardBean(); 
        int result=0; 
         
        boarddata.setBOARD_NUM(Integer.parseInt(request.getParameter("BOARD_NUM"))); 
        boarddata.setBOARD_NAME(request.getParameter("BOARD_NAME")); 
        boarddata.setBOARD_SUBJECT(request.getParameter("BOARD_SUBJECT")); 
        boarddata.setBOARD_CONTENT(request.getParameter("BOARD_CONTENT")); 
        boarddata.setBOARD_RE_REF(Integer.parseInt(request.getParameter("BOARD_RE_REF"))); 
        boarddata.setBOARD_RE_LEV(Integer.parseInt(request.getParameter("BOARD_RE_LEV"))); 
        boarddata.setBOARD_RE_SEQ(Integer.parseInt(request.getParameter("BOARD_RE_SEQ"))); 
         
        result=boarddao.boardReply(boarddata); 
        if(result==0){ 
            System.out.println("���� ����"); 
            return null; 
        } 
        System.out.println("���� �Ϸ�"); 
         
        forward.setRedirect(true); 
        forward.setPath("./BoardDetailAction.bo?num="+result);         
        return forward; 
    } 
} 
