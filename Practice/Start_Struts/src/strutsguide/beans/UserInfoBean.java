package strutsguide.beans;

public class UserInfoBean {
	/** 사용자명 */    
	private String userName = null;        
	/** 사용자의 전화번호 */    
	private String phone = null;        
	/** 사용자의 이메일 */    
	private String email = null;

	/** 기본 생성자 */    
	public UserInfoBean() {        
		// do nothing;    
	}        
	/** 사용자 정보를 받는 생성자 */    
	public UserInfoBean(String userName, String phone, String email) {        
		this.userName = userName;        
		this.phone = phone;        
		this.email = email;    
	}        
	public String getUserName() {        
		return userName;    
	}
	public void setUserName(String userName) {        
		this.userName = userName;    
	}
	public String getPhone() {        
		return phone;    
	}
	public void setPhone(String phone) {        
		this.phone = phone;    
	}
	public String getEmail() {        
		return email;    
	}
	public void setEmail(String email) {        
		this.email = email;    
	} 
}
