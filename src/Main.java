
		import java.sql.Connection;
		import java.sql.DriverManager;
		import java.sql.PreparedStatement;
		import java.sql.ResultSet;
		import java.sql.SQLException;

		public class Main {

			public static void main(String[] args) throws ClassNotFoundException, SQLException {
				
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				String user = "user1";
				String password = "1234";
				String url = "jdbc:oracle:thin:@localhost:1521/xe";
				
				
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
				
//					int searchNum = 2;
//					String sql = "select \"NAME\", \"NUM\", \"BIRTH\" from \"INFO\" where \"NUM\"=" + searchNum ;
					
					String searchName = "임꺽정";
			//		String sql = "select \"NAME\", \"NUM\", \"BIRTH\" from \"INFO\" where \"NAME\"='" + searchName + "'";
					String sql = "select \"NAME\", \"NUM\", \"BIRTH\" from \"INFO\" where \"NAME\"= ? ";
					
					System.out.println(sql);
					
					conn = DriverManager.getConnection(url, user, password);
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, searchName);
					
					
					
					rs = pstmt.executeQuery();
					
					if(rs.next()) {
						System.out.println(rs.getString("NAME"));
						System.out.println(rs.getLong(2));
						System.out.println(rs.getDate(3));
						System.out.println();
						
					}
				
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					if(rs != null) {
						try {
							rs.close();
						} catch(SQLException e) {
							e.printStackTrace();
						}
					}
					if(pstmt != null) {
						try {
							pstmt.close();
						} catch(SQLException e) {
							e.printStackTrace();
						}
					}
					if(conn != null) {
						try {
							conn.close();
						} catch(SQLException e) {
							e.printStackTrace();
						}
					}
				}
				
				
			}

		}
