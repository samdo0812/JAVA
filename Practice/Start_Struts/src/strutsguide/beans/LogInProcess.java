package strutsguide.beans;

import java.util.HashMap;

/** * �α����� ������ �����ϴ� �����Ͻ� ���� Ŭ���� */ 
public class LogInProcess {
	/** ����� ������ ��� �ִ� �ؽø� */    
	private HashMap userInfos = null;        
	/** ������� ��й�ȣ�� ��� �ִ� �ؽø� */    
	private HashMap userPasswords = null;        
	/** ������ LogInProcess ��ü */    
	private static LogInProcess instance = new LogInProcess();


	/** �̱������� ������ ��ü �� ���� �����Ѵ�. ������ */    
	private LogInProcess() {        
		userPasswords = new HashMap();        
		userInfos = new HashMap();                
		// ������ ����ڸ� �߰��Ѵ�.        
		// ���ϴ� ��� �߰��� �� �ִ�.        
		userPasswords.put("kwon37xi", "1234");        
		userInfos.put("kwon37xi", new UserInfoBean("kwon37xi", "011-222-4444", "kwon37xi@yahoo.co.kr"));
		userPasswords.put("narae", "n111");        
		userInfos.put("narae", new UserInfoBean("narae", "010-333-5555", "narae@mymail.xxx"));                
		userPasswords.put("woori", "w222");        
		userInfos.put("woori", new UserInfoBean("woori", "010-777-9999", "woori@yourmail.ooo"));    
	} 
	
	/**     
	 * LogInProcess�� ������ ��ü�� ��´�.
	*/    
	public static LogInProcess getInstance() {        
		return instance; 
	}

	  /**     * �α����� �����ϰ�, �����ϸ� �ش��ϴ� ������� UserInfoBean ��ü�� �����Ѵ�.
	   *      * @param userName �α����� ����ڸ�     
	   *      * @param password ��й�ȣ    
	   *      * @return UserInfoBean �α����� ������� ����. �α��� �����ϸ� null ����     
	   * */ 
	public UserInfoBean logIn(String userName, String password) {
    String userPassword = (String)userPasswords.get(userName);
    // ����ڰ� �������� ������ null ����        
    if (userPassword == null) {            
    	return null;        
    }                
    // ��й�ȣ�� ��ġ���� �ʾƵ� null ����       
    if (!userPassword.equals(password)) {            
    	return null;        
    }                
    UserInfoBean userInfo = (UserInfoBean)userInfos.get(userName);        
    	return userInfo;    
    } 
}
