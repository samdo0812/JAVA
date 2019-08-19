package Practice;

public class prac_4 {
	
	//try catch finally
	
	public static void main(String[] args) { 
		
		try {
			boolean isSucces = login("samdo" , "11111111");
			if (isSucces) {
				System.out.println("Login success");
			}else {
				System.out.println("Login fail");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		finally {
			System.out.println("Copyright samdo");
		}
		
	}

	static boolean login(String id, String pw) throws Exception {
		
		//Android -> "samdo" , "11111111" -> Server
		boolean isNetworkFailed = true;
		boolean isNoID = false;
		boolean isPasswordWrong = false;
		boolean isPWExpired = true;
		
		if(isNetworkFailed) throw new Exception("Network Failed");
		else if(isNoID) throw new Exception("user ID no exist");
		else if(isPasswordWrong) throw new Exception("Password Wrong");
		else if(isPWExpired) throw new Exception("Need change password");
		
		return true;
	}
}
	
	