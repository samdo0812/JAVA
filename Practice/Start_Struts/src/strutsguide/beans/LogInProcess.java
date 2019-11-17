package strutsguide.beans;

import java.util.HashMap;

/** * 로그인을 실제로 수행하는 비지니스 로직 클래스 */ 
public class LogInProcess {
	/** 사용자 정보를 담고 있는 해시맵 */    
	private HashMap userInfos = null;        
	/** 사용자의 비밀번호를 담고 있는 해시맵 */    
	private HashMap userPasswords = null;        
	/** 유일한 LogInProcess 객체 */    
	private static LogInProcess instance = new LogInProcess();


	/** 싱글턴으로 오로지 객체 한 개만 생성한다. 생성자 */    
	private LogInProcess() {        
		userPasswords = new HashMap();        
		userInfos = new HashMap();                
		// 가상의 사용자를 추가한다.        
		// 원하는 대로 추가할 수 있다.        
		userPasswords.put("kwon37xi", "1234");        
		userInfos.put("kwon37xi", new UserInfoBean("kwon37xi", "011-222-4444", "kwon37xi@yahoo.co.kr"));
		userPasswords.put("narae", "n111");        
		userInfos.put("narae", new UserInfoBean("narae", "010-333-5555", "narae@mymail.xxx"));                
		userPasswords.put("woori", "w222");        
		userInfos.put("woori", new UserInfoBean("woori", "010-777-9999", "woori@yourmail.ooo"));    
	} 
	
	/**     
	 * LogInProcess의 유일한 객체를 얻는다.
	*/    
	public static LogInProcess getInstance() {        
		return instance; 
	}

	  /**     * 로그인을 수행하고, 성공하면 해당하는 사용자의 UserInfoBean 객체를 리턴한다.
	   *      * @param userName 로그인할 사용자명     
	   *      * @param password 비밀번호    
	   *      * @return UserInfoBean 로그인한 사용자의 정보. 로그인 실패하면 null 리턴     
	   * */ 
	public UserInfoBean logIn(String userName, String password) {
    String userPassword = (String)userPasswords.get(userName);
    // 사용자가 존재하지 않으면 null 리턴        
    if (userPassword == null) {            
    	return null;        
    }                
    // 비밀번호가 일치하지 않아도 null 리턴       
    if (!userPassword.equals(password)) {            
    	return null;        
    }                
    UserInfoBean userInfo = (UserInfoBean)userInfos.get(userName);        
    	return userInfo;    
    } 
}
