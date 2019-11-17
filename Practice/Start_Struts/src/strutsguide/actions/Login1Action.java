package strutsguide.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import strutsguide.beans.LogInProcess;
import strutsguide.beans.UserInfoBean;

public class Login1Action extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
        LogInProcess logInProcess = LogInProcess.getInstance();
        // 사용자가 전달한 파라미터를 저장한다.
        String userName = request.getParameter("username");        
        String password = request.getParameter("password"); 
        
        UserInfoBean userInfo = logInProcess.logIn(userName, password);
        ActionForward forward = null;                
        if (userInfo == null) {            
        	// userInfo가 null 이면 로그인이 실패한 것이다. 실패 페이지로 이동한다. 
        	// 이동할 페이지를 찾는다.
        	forward = mapping.findForward("fail");
        } else {            
        	// null이 아니면 성공 페이지로 이동한다. 이동하기 전에 사용자 정보를 session에 저장한다. 
        	HttpSession session = request.getSession();
        	session.setAttribute("userInfo", userInfo);
        	// 이동할 페이지를 찾는다.            
        	forward = mapping.findForward("success");
        	}                
        // 비지니스 로직을 마치고 View 페이지로 이동하도록 지시한다.
        return forward;
        } 
	}