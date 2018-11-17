package dron.board.db; 

import java.sql.Connection; 
import java.sql.PreparedStatement; 
import java.sql.ResultSet; 
import java.sql.SQLException; 
import java.util.ArrayList; 
import java.util.List; 

import javax.naming.Context; 
import javax.naming.InitialContext; 
import javax.sql.DataSource;

import dron.board.db.BoardBean; 

public class BoardDAO { 
    Connection con; 
    PreparedStatement pstmt, pstmt2; 
    ResultSet rs; 
    public BoardDAO() { 
        try{ 
            Context init = new InitialContext(); 
              DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB"); 
              con = ds.getConnection(); 
        }catch(Exception ex){ 
            System.out.println("DB ���� ���� : " + ex); 
            return; 
        } 
    } 
     
    /*���� ���� ���ϱ�(��ü ���� ������ ��ȯ�Ѵ�) 
     * ���� ������ ǥ�� �� ����¡ó���� �Ҷ� ���ȴ�.*/ 
    public int getListCount() { 
        int x= 0; 
         
        try{ 
            pstmt=con.prepareStatement("select count(*) from board"); 
            rs = pstmt.executeQuery(); 
             
            if(rs.next()){ 
                x=rs.getInt(1);  //count(*)�Ѱ��� x�� �����Ѵ�. 
            } 
        }catch(Exception ex){ 
            System.out.println("getListCount ����: " + ex);             
        }finally{ 
            if(rs!=null) try{rs.close();}catch(SQLException ex){} 
            if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
        } 
        return x; 
    } 
     
    /*�� ��� ���� 
     * List��ü�� ��ȯ�Ѵ�. �μ�1) ����� ������, �μ�2) �� �������� ǥ���� �� ��*/ 
    public List getBoardList(int page,int limit){ 
        String board_list_sql="select * from "+ 
        "(select rownum rnum,BOARD_NUM,BOARD_NAME,BOARD_SUBJECT,"+ 
        "BOARD_CONTENT,BOARD_FILE,BOARD_RE_REF,BOARD_RE_LEV,"+ 
        "BOARD_RE_SEQ,BOARD_READCOUNT,BOARD_DATE from "+ 
        "(select * from board order by "+ 
        "BOARD_RE_REF ASC,BOARD_RE_SEQ ASC)) "+ 
        "where rnum>=? and rnum<=?"; 

        List list = new ArrayList();
         
        int startrow=(page-1)*10+1; //�б� ������ row ��ȣ. 
        int endrow=startrow+limit-1; //���� ������ row ��ȣ.         
        try{ 
            pstmt = con.prepareStatement(board_list_sql);
            pstmt.setInt(1, startrow); 
            pstmt.setInt(2, endrow); 
            rs = pstmt.executeQuery(); 
             
            while(rs.next()){ 
                BoardBean board = new BoardBean(); 
                board.setBOARD_NUM(rs.getInt("BOARD_NUM")); 
                board.setBOARD_NAME(rs.getString("BOARD_NAME")); 
                board.setBOARD_SUBJECT(rs.getString("BOARD_SUBJECT")); 
                board.setBOARD_CONTENT(rs.getString("BOARD_CONTENT")); 
                board.setBOARD_FILE(rs.getString("BOARD_FILE")); 
                board.setBOARD_RE_REF(rs.getInt("BOARD_RE_REF")); 
                board.setBOARD_RE_LEV(rs.getInt("BOARD_RE_LEV")); 
                board.setBOARD_RE_SEQ(rs.getInt("BOARD_RE_SEQ")); 
                board.setBOARD_READCOUNT(rs.getInt("BOARD_READCOUNT")); 
                board.setBOARD_DATE(rs.getDate("BOARD_DATE"));
                list.add(board); 
            } 
             
            return list;
        }catch(Exception ex){ 
            System.out.println("getBoardList ���� : " + ex); 
        }finally{ 
            if(rs!=null) try{rs.close();}catch(SQLException ex){} 
            if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
        } 
        return null; 
    } 
     
    /*�� ��� 
     * 1) board_num�ʵ��� �ִ밪�� ���´�. ������ �ߺ����� ���� ���� �������� ���� 
     * 2) �۵������ : �۾���� �����̹Ƿ� �亯�� ���õ� �ʵ�� ��� 0����..ref��(�۱׷��ȣ�� ���ο� ��ȣ�� ����)*/ 
    public boolean boardInsert(BoardBean board){ 
        int num =0; 
        String sql=""; 
         
        int result=0; 
         
        try{ 
            pstmt=con.prepareStatement( 
                    "select max(board_num) from board"); 
            rs = pstmt.executeQuery(); 
             
            if(rs.next()) 
                num =rs.getInt(1)+1;  //���� ��ϵǾ������� �� ��ȣ +1 
            else 
                num=1;//�� ����� �Ǿ����� ������ num=1�� 
             
            sql="insert into board (BOARD_NUM,BOARD_NAME,BOARD_SUBJECT,"; 
            sql+="BOARD_CONTENT, BOARD_FILE,BOARD_RE_REF,"+ 
                "BOARD_RE_LEV,BOARD_RE_SEQ,BOARD_READCOUNT,"+ 
                "BOARD_DATE) values(?,?,?,?,?,?,?,?,?,sysdate)"; 
             
            pstmt = con.prepareStatement(sql); 
            pstmt.setInt(1, num); 
            pstmt.setString(2, board.getBOARD_NAME()); 
            pstmt.setString(3, board.getBOARD_SUBJECT()); 
            pstmt.setString(4, board.getBOARD_CONTENT()); 
            pstmt.setString(5, board.getBOARD_FILE()); 
            pstmt.setInt(6, num); 
            pstmt.setInt(7, 0); 
            pstmt.setInt(8, 0); 
            pstmt.setInt(9, 0); 
             
            result=pstmt.executeUpdate(); 
            if(result==0)return false; 
             
            return true; 
        }catch(Exception ex){ 
            System.out.println("boardInsert ���� : "+ex); 
        }finally{ 
            if(rs!=null) try{rs.close();}catch(SQLException ex){} 
            if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
        } 
        return false; 
    } 
  
    //�� ���� ���� //�� ���ڵ� ��ȣ�� �μ��� �޾ƿ´�. 
    public BoardBean getDetail(int num) throws Exception{ 
        BoardBean board = null; 
        try{ 
            pstmt = con.prepareStatement( 
                    "select * from board where BOARD_NUM = ?"); 
            pstmt.setInt(1, num); 
             
            rs= pstmt.executeQuery(); 
             
            if(rs.next()){ 
                board = new BoardBean(); 
                board.setBOARD_NUM(rs.getInt("BOARD_NUM")); 
                board.setBOARD_NAME(rs.getString("BOARD_NAME")); 
                board.setBOARD_SUBJECT(rs.getString("BOARD_SUBJECT")); 
                board.setBOARD_CONTENT(rs.getString("BOARD_CONTENT")); 
                board.setBOARD_FILE(rs.getString("BOARD_FILE")); 
                board.setBOARD_RE_REF(rs.getInt("BOARD_RE_REF")); 
                board.setBOARD_RE_LEV(rs.getInt("BOARD_RE_LEV")); 
                board.setBOARD_RE_SEQ(rs.getInt("BOARD_RE_SEQ")); 
                board.setBOARD_READCOUNT(rs.getInt("BOARD_READCOUNT")); 
                board.setBOARD_DATE(rs.getDate("BOARD_DATE")); 
            } 
            return board; 
        }catch(Exception ex){ 
            System.out.println("getDetail ���� : " + ex); 
        }finally{ 
            if(rs!=null)try{rs.close();}catch(SQLException ex){} 
            if(pstmt !=null)try{pstmt.close();}catch(SQLException ex){} 
        } 
        return null; 
    } 
    
  //��ȸ�� ������Ʈ(�� ������ Ȯ���ϴ� ���� ȣ��ȴ�) 
    public void setReadCountUpdate(int num) throws Exception{ 
        String sql="update board set BOARD_READCOUNT = "+ 
            "BOARD_READCOUNT+1 where BOARD_NUM = "+num; 
         
        try{ 
            pstmt=con.prepareStatement(sql); 
            pstmt.executeUpdate(); 
        }catch(SQLException ex){ 
            System.out.println("setReadCountUpdate ���� : "+ex); 
        } 
    } 
    
  //�� ����. 
    public boolean boardModify(BoardBean modifyboard) throws Exception{ 
        String sql="update board set BOARD_SUBJECT=?,"; 
        sql+="BOARD_CONTENT=? where BOARD_NUM=?"; 
         
        try{ 
            pstmt = con.prepareStatement(sql); 
            pstmt.setString(1, modifyboard.getBOARD_SUBJECT()); 
            pstmt.setString(2, modifyboard.getBOARD_CONTENT()); 
            pstmt.setInt(3, modifyboard.getBOARD_NUM()); 
            pstmt.executeUpdate(); 
            return true; 
        }catch(Exception ex){ 
            System.out.println("boardModify ���� : " + ex); 
        }finally{ 
            if(rs!=null)try{rs.close();}catch(SQLException ex){} 
            if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){} 
            } 
        return false; 
    } 
    
  //�۾������� Ȯ��(�۾��̸� Ȯ���� ���� ������ ��´�.) 
    public boolean isBoardWriter(int num,String id){ 
        String board_sql="select * from board where BOARD_NUM=?"; 
         
        try{ 
            pstmt=con.prepareStatement(board_sql); 
            pstmt.setInt(1, num); 
            rs=pstmt.executeQuery(); 
            rs.next(); 
             
            if(id.equals(rs.getString("BOARD_NAME"))){ 
                return true; 
            } 
        }catch(SQLException ex){ 
            System.out.println("isBoardWriter ���� : "+ex); 
        } 
        return false; 
    }
    
 // �� ����(�׼� Ŭ�������� ��й�ȣ ��ġ ���� Ȯ���� �� �޼��带 �����Ѵ�.) 
    public boolean boardDelete(int num){ 
    	String board_copy_sql="select * from board where BOARD_num=?";
    	
    	String sql="";
    	
        String board_delete_sql="delete from board where BOARD_num=?"; 
        
        int result=0;
        
        BoardBean board = null;
        
        try {
        	
        	// ������ ���� ��ȸ�Ѵ�.
        	pstmt=con.prepareStatement(board_copy_sql); 
            pstmt.setInt(1, num);
            rs = pstmt.executeQuery();
            
            if(rs.next()){ 
                board = new BoardBean(); 
                board.setBOARD_NUM(rs.getInt("BOARD_NUM")); 
                board.setBOARD_NAME(rs.getString("BOARD_NAME")); 
                board.setBOARD_SUBJECT(rs.getString("BOARD_SUBJECT")); 
                board.setBOARD_CONTENT(rs.getString("BOARD_CONTENT")); 
                board.setBOARD_FILE(rs.getString("BOARD_FILE")); 
                board.setBOARD_RE_REF(rs.getInt("BOARD_RE_REF")); 
                board.setBOARD_RE_LEV(rs.getInt("BOARD_RE_LEV")); 
                board.setBOARD_RE_SEQ(rs.getInt("BOARD_RE_SEQ")); 
                board.setBOARD_READCOUNT(rs.getInt("BOARD_READCOUNT")); 
                board.setBOARD_DATE(rs.getDate("BOARD_DATE"));
            }
            
            System.out.println(board.getBOARD_NUM());
            System.out.println(board.getBOARD_DATE());
            
            

            // ������ ���� ���̺� ������ ���� insert into �Ѵ�
            sql="insert into d_board (BOARD_NUM,BOARD_NAME,BOARD_SUBJECT,"; 
            sql+="BOARD_CONTENT, BOARD_FILE,BOARD_RE_REF,"+ 
                "BOARD_RE_LEV,BOARD_RE_SEQ,BOARD_READCOUNT,BOARD_DATE) values(?,?,?,?,?,?,?,?,?,?)";
            
            pstmt = con.prepareStatement(sql); 
            pstmt.setInt(1, num); 
            pstmt.setString(2, board.getBOARD_NAME()); 
            pstmt.setString(3, board.getBOARD_SUBJECT()); 
            pstmt.setString(4, board.getBOARD_CONTENT()); 
            pstmt.setString(5, board.getBOARD_FILE()); 
            pstmt.setInt(6, board.getBOARD_RE_REF()); 
            pstmt.setInt(7, board.getBOARD_RE_LEV()); 
            pstmt.setInt(8, board.getBOARD_RE_SEQ()); 
            pstmt.setInt(9, board.getBOARD_READCOUNT());
            pstmt.setDate(10, board.getBOARD_DATE());
             
            result=pstmt.executeUpdate();
            

        	
            pstmt=con.prepareStatement(board_delete_sql); 
            pstmt.setInt(1, num); 
            result=pstmt.executeUpdate(); 
            if(result==0)return false;
             
            return true; 
        }catch(Exception ex){ 
            System.out.println("boardDelete ���� : "+ex); 
        }finally{ 
            try{ 
                if(pstmt!=null)pstmt.close(); 
            }catch(Exception ex) {} 
        } 
         
        return false; 
    }
    
    /*�� �亯 ��� */ 
    public int boardReply(BoardBean board){ 
        String board_max_sql="select max(board_num) from board"; 
        String sql=""; 
        int num=0; 
        int result=0;
        
        int re_ref=board.getBOARD_RE_REF(); //������ ��ȣ 
        int re_lev=board.getBOARD_RE_LEV(); // �亯���� ���� 
        int re_seq=board.getBOARD_RE_SEQ(); //�亯���� ���� 
         
        try{
            pstmt=con.prepareStatement(board_max_sql); 
            rs = pstmt.executeQuery(); 
            if(rs.next())num =rs.getInt(1)+1; //�亯���� ������� max�� +1�ؼ� �۹�ȣ�� �Կ��ش�. 
            else num=1;  //�� ����� �Ǿ����� ������ num=1 
             
            /*ref���� seq���� Ȯ���Ͽ� ���� �ۿ� �ٸ� �亯 ���� ������,  
             * �亯 �� �� �亯 �ۺ��� ������ �ִ� ���� seq���� ���� ���� seq���� 1�� ������Ų��.*/ 
            sql="update board set BOARD_RE_SEQ=BOARD_RE_SEQ+1 "; 
            sql+="where BOARD_RE_REF=? and BOARD_RE_SEQ>?"; 
             
            pstmt = con.prepareStatement(sql); 
            pstmt.setInt(1,re_ref); 
            pstmt.setInt(2,re_seq); 
            result=pstmt.executeUpdate(); 
             
            re_seq = re_seq + 1; //�亯 ���� ���� �� ���� �Ʒ��� ��µǾ�� �ϱ� ������ re_seq���� 1������Ų��. 
            re_lev = re_lev+1; // �亯�� �ϴ°��̹Ƿ� ���� �亯 ���� �ܰ迡�� 1�� ������Ų��. 
             
            sql="insert into board (BOARD_NUM,BOARD_NAME,BOARD_SUBJECT,"; 
            sql+="BOARD_CONTENT,BOARD_FILE,BOARD_RE_REF,BOARD_RE_LEV,"; 
            sql+="BOARD_RE_SEQ,BOARD_READCOUNT,BOARD_DATE) "; 
            sql+="values(?,?,?,?,?,?,?,?,?,sysdate)"; 
             
            pstmt = con.prepareStatement(sql); 
            pstmt.setInt(1, num); 
            pstmt.setString(2, board.getBOARD_NAME()); 
            pstmt.setString(3, board.getBOARD_SUBJECT()); 
            pstmt.setString(4, board.getBOARD_CONTENT()); 
            pstmt.setString(5, ""); //���忡�� ������ ���ε����� ����. 
            pstmt.setInt(6, re_ref); 
            pstmt.setInt(7, re_lev); 
            pstmt.setInt(8, re_seq); 
            pstmt.setInt(9, 0); 
            pstmt.executeUpdate(); 
            return num; 
        }catch(SQLException ex){ 
            System.out.println("boardReply ���� : "+ex); 
        }finally{ 
            if(rs!=null)try{rs.close();}catch(SQLException ex){} 
            if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){} 
        } 
        return 0; 
    } 
    
    
    
    
    /* ������ ���̺��� ��ü �� ���� ���ϱ�(��ü ���� ������ ��ȯ�Ѵ�) */ 
    public int d_getListCount() { 
        int x= 0; 
         
        try{ 
            pstmt=con.prepareStatement("select count(*) from d_board"); 
            rs = pstmt.executeQuery();
             
            if(rs.next()){ 
                x=rs.getInt(1);  //count(*)�Ѱ��� x�� �����Ѵ�. 
            } 
        }catch(Exception ex){ 
            System.out.println("d_getListCount ����: " + ex);             
        }finally{ 
            if(rs!=null) try{rs.close();}catch(SQLException ex){} 
            if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
        } 
        return x; 
    }
    
    
    /* ������ ���̺��� ��ü �� ��� ���� 
     * List��ü�� ��ȯ�Ѵ�. �μ�1) ����� ������, �μ�2) �� �������� ǥ���� �� ��*/ 
    public List d_getBoardList(int page,int limit){ 
        String board_list_sql="select * from "+ 
        "(select rownum rnum,BOARD_NUM,BOARD_NAME,BOARD_SUBJECT,"+ 
        "BOARD_CONTENT,BOARD_FILE,BOARD_RE_REF,BOARD_RE_LEV,"+ 
        "BOARD_RE_SEQ,BOARD_READCOUNT,BOARD_DATE from "+ 
        "(select * from d_board order by "+ 
        "BOARD_RE_REF ASC,BOARD_RE_SEQ ASC)) "+ 
        "where rnum>=? and rnum<=?"; 

        List list = new ArrayList();
         
        int startrow=(page-1)*10+1; //�б� ������ row ��ȣ. 
        int endrow=startrow+limit-1; //���� ������ row ��ȣ.         
        try{ 
            pstmt = con.prepareStatement(board_list_sql);
            pstmt.setInt(1, startrow); 
            pstmt.setInt(2, endrow); 
            rs = pstmt.executeQuery(); 
             
            while(rs.next()){ 
                BoardBean board = new BoardBean(); 
                board.setBOARD_NUM(rs.getInt("BOARD_NUM")); 
                board.setBOARD_NAME(rs.getString("BOARD_NAME")); 
                board.setBOARD_SUBJECT(rs.getString("BOARD_SUBJECT")); 
                board.setBOARD_CONTENT(rs.getString("BOARD_CONTENT")); 
                board.setBOARD_FILE(rs.getString("BOARD_FILE")); 
                board.setBOARD_RE_REF(rs.getInt("BOARD_RE_REF")); 
                board.setBOARD_RE_LEV(rs.getInt("BOARD_RE_LEV")); 
                board.setBOARD_RE_SEQ(rs.getInt("BOARD_RE_SEQ")); 
                board.setBOARD_READCOUNT(rs.getInt("BOARD_READCOUNT")); 
                board.setBOARD_DATE(rs.getDate("BOARD_DATE"));
                list.add(board); 
            }
             
            return list;
        }catch(Exception ex){ 
            System.out.println("getBoardList ���� : " + ex); 
        }finally{ 
            if(rs!=null) try{rs.close();}catch(SQLException ex){} 
            if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
        } 
        return null; 
    }

    // ������ �󼼺���
	public BoardBean deleteDetail(int num) {
		// TODO Auto-generated method stub
		BoardBean board = null; 
        try{ 
            pstmt = con.prepareStatement( 
                    "select * from d_board where BOARD_NUM = ?"); 
            pstmt.setInt(1, num); 
             
            rs= pstmt.executeQuery(); 
             
            if(rs.next()){ 
                board = new BoardBean(); 
                board.setBOARD_NUM(rs.getInt("BOARD_NUM")); 
                board.setBOARD_NAME(rs.getString("BOARD_NAME")); 
                board.setBOARD_SUBJECT(rs.getString("BOARD_SUBJECT")); 
                board.setBOARD_CONTENT(rs.getString("BOARD_CONTENT")); 
                board.setBOARD_FILE(rs.getString("BOARD_FILE")); 
                board.setBOARD_RE_REF(rs.getInt("BOARD_RE_REF")); 
                board.setBOARD_RE_LEV(rs.getInt("BOARD_RE_LEV")); 
                board.setBOARD_RE_SEQ(rs.getInt("BOARD_RE_SEQ")); 
                board.setBOARD_READCOUNT(rs.getInt("BOARD_READCOUNT")); 
                board.setBOARD_DATE(rs.getDate("BOARD_DATE")); 
            } 
            return board;
        }catch(Exception ex){ 
            System.out.println("deleteDetail ���� : " + ex); 
        }finally{ 
            if(rs!=null)try{rs.close();}catch(SQLException ex){} 
            if(pstmt !=null)try{pstmt.close();}catch(SQLException ex){} 
        } 
        return null; 
	}
	
	// ������ �����Խ����� ���� ���� �����Ѵ�.
	public Boolean foreverDelete(int num) {
		// TODO Auto-generated method stub
        String sql="delete from d_board where BOARD_num=?"; 
        
        int result=0; 
        
        try {
        
        	pstmt=con.prepareStatement(sql); 
            pstmt.setInt(1, num); 
            result=pstmt.executeUpdate(); 
            if(result==0)return false;
             
            return true; 
        }catch(Exception ex){ 
            System.out.println("foreverDelete ���� : "+ex); 
        }finally{ 
            try{ 
                if(pstmt!=null)pstmt.close(); 
            }catch(Exception ex) {} 
        } 
         
        return false;
        
	}

	// ������ �����Խ����� ���� �����Ѵ�. (d_board���̺��� ������ �Խñ��� board ���̺� ���� ���� �״�� insert into�ϰ�, d_board���̺��� �����Ѵ�.)
	public boolean boardRecovery(int num) {
		// TODO Auto-generated method stub
		String d_board_copy_sql = "select * from d_board where BOARD_NUM=?"; // ������ ���� ���̺��� ������ ���� ��ȸ�ϴ� ����
		String board_insert_sql; 											 // ������ ���� ���� �Խ��� ���̺� �����ϴ� ����
		String d_board_delete_sql = "delete from d_board where BOARD_NUM=?"; // ������ ���� ������ �Ŀ� ������ ���� ���̺����� ���� �ؾ��Ѵ�.
		int result = 0;
		
		BoardBean board = null;
		
		try {
			
			// ���� ������ ���� d_board���̺��� ��ȸ�Ѵ�.(����)
			pstmt = con.prepareStatement(d_board_copy_sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				board = new BoardBean();
				board.setBOARD_NUM(rs.getInt("BOARD_NUM"));
				board.setBOARD_NAME(rs.getString("BOARD_NAME"));
				board.setBOARD_SUBJECT(rs.getString("BOARD_SUBJECT")); 
                board.setBOARD_CONTENT(rs.getString("BOARD_CONTENT")); 
                board.setBOARD_FILE(rs.getString("BOARD_FILE")); 
                board.setBOARD_RE_REF(rs.getInt("BOARD_RE_REF")); 
                board.setBOARD_RE_LEV(rs.getInt("BOARD_RE_LEV")); 
                board.setBOARD_RE_SEQ(rs.getInt("BOARD_RE_SEQ")); 
                board.setBOARD_READCOUNT(rs.getInt("BOARD_READCOUNT")); 
                board.setBOARD_DATE(rs.getDate("BOARD_DATE"));
				
			}
			
			System.out.println(board.getBOARD_NUM());
			System.out.println(board.getBOARD_NAME());
			
			
			
			//  d_board ���̺��� ��ȸ�� ���� �״�� board���̺� insert into �Ѵ�.(�Խñ� ����)
			board_insert_sql = "insert into board (BOARD_NUM,BOARD_NAME,BOARD_SUBJECT, "; 
			board_insert_sql += "BOARD_CONTENT, BOARD_FILE,BOARD_RE_REF, ";
			board_insert_sql += "BOARD_RE_LEV,BOARD_RE_SEQ,BOARD_READCOUNT,BOARD_DATE) values(?,?,?,?,?,?,?,?,?,?)";
			
			pstmt = con.prepareStatement(board_insert_sql); 
            pstmt.setInt(1, num); 
            pstmt.setString(2, board.getBOARD_NAME()); 
            pstmt.setString(3, board.getBOARD_SUBJECT()); 
            pstmt.setString(4, board.getBOARD_CONTENT()); 
            pstmt.setString(5, board.getBOARD_FILE()); 
            pstmt.setInt(6, board.getBOARD_RE_REF()); 
            pstmt.setInt(7, board.getBOARD_RE_LEV()); 
            pstmt.setInt(8, board.getBOARD_RE_SEQ()); 
            pstmt.setInt(9, board.getBOARD_READCOUNT());
            pstmt.setDate(10, board.getBOARD_DATE());
             
            result=pstmt.executeUpdate();
            
            
            
            pstmt=con.prepareStatement(d_board_delete_sql); 
            pstmt.setInt(1, num); 
            result=pstmt.executeUpdate(); 
            if(result==0)return false;
            
            return true;

			
		} catch(Exception e) {
			System.out.println("boardRecovery ���� : " + e);
		} finally {
			try {
				if(pstmt != null) pstmt.close();
			} catch(Exception e) {}
				
			}
		return false;
	}

}

