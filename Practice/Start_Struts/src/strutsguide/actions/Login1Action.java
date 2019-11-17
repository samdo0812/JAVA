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
        // ����ڰ� ������ �Ķ���͸� �����Ѵ�.
        String userName = request.getParameter("username");        
        String password = request.getParameter("password"); 
        
        UserInfoBean userInfo = logInProcess.logIn(userName, password);
        ActionForward forward = null;                
        if (userInfo == null) {            
        	// userInfo�� null �̸� �α����� ������ ���̴�. ���� �������� �̵��Ѵ�. 
        	// �̵��� �������� ã�´�.
        	forward = mapping.findForward("fail");
        } else {            
        	// null�� �ƴϸ� ���� �������� �̵��Ѵ�. �̵��ϱ� ���� ����� ������ session�� �����Ѵ�. 
        	HttpSession session = request.getSession();
        	session.setAttribute("userInfo", userInfo);
        	// �̵��� �������� ã�´�.            
        	forward = mapping.findForward("success");
        	}                
        // �����Ͻ� ������ ��ġ�� View �������� �̵��ϵ��� �����Ѵ�.
        return forward;
        } 
	}