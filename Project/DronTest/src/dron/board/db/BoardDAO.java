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
            System.out.println("DB 연결 실패 : " + ex); 
            return; 
        } 
    } 
     
    /*글의 개수 구하기(전체 글의 개수를 반환한다) 
     * 글의 개수를 표시 및 페이징처리를 할때 사용된다.*/ 
    public int getListCount() { 
        int x= 0; 
         
        try{ 
            pstmt=con.prepareStatement("select count(*) from board"); 
            rs = pstmt.executeQuery(); 
             
            if(rs.next()){ 
                x=rs.getInt(1);  //count(*)한값을 x에 저장한다. 
            } 
        }catch(Exception ex){ 
            System.out.println("getListCount 에러: " + ex);             
        }finally{ 
            if(rs!=null) try{rs.close();}catch(SQLException ex){} 
            if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
        } 
        return x; 
    } 
     
    /*글 목록 보기 
     * List객체로 반환한다. 인수1) 출력할 페이지, 인수2) 한 페이지당 표시할 글 수*/ 
    public List getBoardList(int page,int limit){ 
        String board_list_sql="select * from "+ 
        "(select rownum rnum,BOARD_NUM,BOARD_NAME,BOARD_SUBJECT,"+ 
        "BOARD_CONTENT,BOARD_FILE,BOARD_RE_REF,BOARD_RE_LEV,"+ 
        "BOARD_RE_SEQ,BOARD_READCOUNT,BOARD_DATE from "+ 
        "(select * from board order by "+ 
        "BOARD_RE_REF ASC,BOARD_RE_SEQ ASC)) "+ 
        "where rnum>=? and rnum<=?"; 

        List list = new ArrayList();
         
        int startrow=(page-1)*10+1; //읽기 시작할 row 번호. 
        int endrow=startrow+limit-1; //읽을 마지막 row 번호.         
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
            System.out.println("getBoardList 에러 : " + ex); 
        }finally{ 
            if(rs!=null) try{rs.close();}catch(SQLException ex){} 
            if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
        } 
        return null; 
    } 
     
    /*글 등록 
     * 1) board_num필드의 최대값을 얻어온다. 이유는 중복되지 않은 값을 가져오기 위해 
     * 2) 글등록쿼리 : 글쓰기는 원글이므로 답변과 관련된 필드는 모두 0으로..ref값(글그룹번호만 새로운 번호로 지정)*/ 
    public boolean boardInsert(BoardBean board){ 
        int num =0; 
        String sql=""; 
         
        int result=0; 
         
        try{ 
            pstmt=con.prepareStatement( 
                    "select max(board_num) from board"); 
            rs = pstmt.executeQuery(); 
             
            if(rs.next()) 
                num =rs.getInt(1)+1;  //글이 등록되어있으면 글 번호 +1 
            else 
                num=1;//글 등록이 되어있지 않으면 num=1로 
             
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
            System.out.println("boardInsert 에러 : "+ex); 
        }finally{ 
            if(rs!=null) try{rs.close();}catch(SQLException ex){} 
            if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
        } 
        return false; 
    } 
  
    //글 내용 보기 //글 레코드 번호를 인수로 받아온다. 
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
            System.out.println("getDetail 에러 : " + ex); 
        }finally{ 
            if(rs!=null)try{rs.close();}catch(SQLException ex){} 
            if(pstmt !=null)try{pstmt.close();}catch(SQLException ex){} 
        } 
        return null; 
    } 
    
  //조회수 업데이트(글 내용을 확인하는 순간 호출된다) 
    public void setReadCountUpdate(int num) throws Exception{ 
        String sql="update board set BOARD_READCOUNT = "+ 
            "BOARD_READCOUNT+1 where BOARD_NUM = "+num; 
         
        try{ 
            pstmt=con.prepareStatement(sql); 
            pstmt.executeUpdate(); 
        }catch(SQLException ex){ 
            System.out.println("setReadCountUpdate 에러 : "+ex); 
        } 
    } 
    
  //글 수정. 
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
            System.out.println("boardModify 에러 : " + ex); 
        }finally{ 
            if(rs!=null)try{rs.close();}catch(SQLException ex){} 
            if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){} 
            } 
        return false; 
    } 
    
  //글쓴이인지 확인(글쓴이를 확인할 글의 정보를 얻는다.) 
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
            System.out.println("isBoardWriter 에러 : "+ex); 
        } 
        return false; 
    }
    
 // 글 삭제(액션 클래스에서 비밀번호 일치 여부 확인후 이 메서드를 수행한다.) 
    public boolean boardDelete(int num){ 
    	String board_copy_sql="select * from board where BOARD_num=?";
    	
    	String sql="";
    	
        String board_delete_sql="delete from board where BOARD_num=?"; 
        
        int result=0;
        
        BoardBean board = null;
        
        try {
        	
        	// 삭제할 글을 조회한다.
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
            
            

            // 삭제글 관리 테이블에 삭제할 글을 insert into 한다
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
            System.out.println("boardDelete 에러 : "+ex); 
        }finally{ 
            try{ 
                if(pstmt!=null)pstmt.close(); 
            }catch(Exception ex) {} 
        } 
         
        return false; 
    }
    
    /*글 답변 등록 */ 
    public int boardReply(BoardBean board){ 
        String board_max_sql="select max(board_num) from board"; 
        String sql=""; 
        int num=0; 
        int result=0;
        
        int re_ref=board.getBOARD_RE_REF(); //원본글 번호 
        int re_lev=board.getBOARD_RE_LEV(); // 답변글의 깊이 
        int re_seq=board.getBOARD_RE_SEQ(); //답변글의 순서 
         
        try{
            pstmt=con.prepareStatement(board_max_sql); 
            rs = pstmt.executeQuery(); 
            if(rs.next())num =rs.getInt(1)+1; //답변글이 있을경우 max에 +1해서 글번호를 먹여준다. 
            else num=1;  //글 등록이 되어있지 않으면 num=1 
             
            /*ref값과 seq값을 확인하여 원본 글에 다른 답변 글이 있으면,  
             * 답변 글 중 답변 글보다 상위에 있는 글의 seq보다 높은 글의 seq값을 1씩 증가시킨다.*/ 
            sql="update board set BOARD_RE_SEQ=BOARD_RE_SEQ+1 "; 
            sql+="where BOARD_RE_REF=? and BOARD_RE_SEQ>?"; 
             
            pstmt = con.prepareStatement(sql); 
            pstmt.setInt(1,re_ref); 
            pstmt.setInt(2,re_seq); 
            result=pstmt.executeUpdate(); 
             
            re_seq = re_seq + 1; //답변 글이 원본 글 보다 아래에 출력되어야 하기 때문에 re_seq값을 1증가시킨다. 
            re_lev = re_lev+1; // 답변을 하는것이므로 현재 답변 레벨 단계에서 1을 증가시킨다. 
             
            sql="insert into board (BOARD_NUM,BOARD_NAME,BOARD_SUBJECT,"; 
            sql+="BOARD_CONTENT,BOARD_FILE,BOARD_RE_REF,BOARD_RE_LEV,"; 
            sql+="BOARD_RE_SEQ,BOARD_READCOUNT,BOARD_DATE) "; 
            sql+="values(?,?,?,?,?,?,?,?,?,sysdate)"; 
             
            pstmt = con.prepareStatement(sql); 
            pstmt.setInt(1, num); 
            pstmt.setString(2, board.getBOARD_NAME()); 
            pstmt.setString(3, board.getBOARD_SUBJECT()); 
            pstmt.setString(4, board.getBOARD_CONTENT()); 
            pstmt.setString(5, ""); //답장에는 파일을 업로드하지 않음. 
            pstmt.setInt(6, re_ref); 
            pstmt.setInt(7, re_lev); 
            pstmt.setInt(8, re_seq); 
            pstmt.setInt(9, 0); 
            pstmt.executeUpdate(); 
            return num; 
        }catch(SQLException ex){ 
            System.out.println("boardReply 에러 : "+ex); 
        }finally{ 
            if(rs!=null)try{rs.close();}catch(SQLException ex){} 
            if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){} 
        } 
        return 0; 
    } 
    
    
    
    
    /* 삭제글 테이블의 전체 글 개수 구하기(전체 글의 개수를 반환한다) */ 
    public int d_getListCount() { 
        int x= 0; 
         
        try{ 
            pstmt=con.prepareStatement("select count(*) from d_board"); 
            rs = pstmt.executeQuery();
             
            if(rs.next()){ 
                x=rs.getInt(1);  //count(*)한값을 x에 저장한다. 
            } 
        }catch(Exception ex){ 
            System.out.println("d_getListCount 에러: " + ex);             
        }finally{ 
            if(rs!=null) try{rs.close();}catch(SQLException ex){} 
            if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
        } 
        return x; 
    }
    
    
    /* 삭제글 테이블의 전체 글 목록 보기 
     * List객체로 반환한다. 인수1) 출력할 페이지, 인수2) 한 페이지당 표시할 글 수*/ 
    public List d_getBoardList(int page,int limit){ 
        String board_list_sql="select * from "+ 
        "(select rownum rnum,BOARD_NUM,BOARD_NAME,BOARD_SUBJECT,"+ 
        "BOARD_CONTENT,BOARD_FILE,BOARD_RE_REF,BOARD_RE_LEV,"+ 
        "BOARD_RE_SEQ,BOARD_READCOUNT,BOARD_DATE from "+ 
        "(select * from d_board order by "+ 
        "BOARD_RE_REF ASC,BOARD_RE_SEQ ASC)) "+ 
        "where rnum>=? and rnum<=?"; 

        List list = new ArrayList();
         
        int startrow=(page-1)*10+1; //읽기 시작할 row 번호. 
        int endrow=startrow+limit-1; //읽을 마지막 row 번호.         
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
            System.out.println("getBoardList 에러 : " + ex); 
        }finally{ 
            if(rs!=null) try{rs.close();}catch(SQLException ex){} 
            if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
        } 
        return null; 
    }

    // 삭제글 상세보기
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
            System.out.println("deleteDetail 에러 : " + ex); 
        }finally{ 
            if(rs!=null)try{rs.close();}catch(SQLException ex){} 
            if(pstmt !=null)try{pstmt.close();}catch(SQLException ex){} 
        } 
        return null; 
	}
	
	// 삭제글 관리게시판의 글을 영구 삭제한다.
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
            System.out.println("foreverDelete 에러 : "+ex); 
        }finally{ 
            try{ 
                if(pstmt!=null)pstmt.close(); 
            }catch(Exception ex) {} 
        } 
         
        return false;
        
	}

	// 삭제글 관리게시판의 글을 복원한다. (d_board테이블에서 복원할 게시글을 board 테이블에 먼저 내용 그대로 insert into하고, d_board테이블에서 삭제한다.)
	public boolean boardRecovery(int num) {
		// TODO Auto-generated method stub
		String d_board_copy_sql = "select * from d_board where BOARD_NUM=?"; // 삭제글 관리 테이블에서 복원할 글을 조회하는 쿼리
		String board_insert_sql; 											 // 복원할 글을 기존 게시판 테이블에 삽입하는 쿼리
		String d_board_delete_sql = "delete from d_board where BOARD_NUM=?"; // 복원할 글을 복원한 후에 삭제글 관리 테이블에서는 삭제 해야한다.
		int result = 0;
		
		BoardBean board = null;
		
		try {
			
			// 먼저 복원할 글을 d_board테이블에서 조회한다.(복사)
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
			
			
			
			//  d_board 테이블에서 조회한 내용 그대로 board테이블에 insert into 한다.(게시글 복원)
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
			System.out.println("boardRecovery 에러 : " + e);
		} finally {
			try {
				if(pstmt != null) pstmt.close();
			} catch(Exception e) {}
				
			}
		return false;
	}

}

