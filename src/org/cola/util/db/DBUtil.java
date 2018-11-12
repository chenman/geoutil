package org.cola.util.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.cola.bean.BoxBean;

public class DBUtil {

	private ConnectionPool connectionPool = null;

    public DBUtil() {
        this.connectionPool = ConnectionPool.getConnectionPoolInstance();
    }
    

    private void afterQueryProcess(Statement statement, Connection connection, ResultSet result) {
        try {
            if (result != null) {
                result.close();
                result = null;
            }
        } catch (SQLException ex) {

        }

        try {
            if (statement != null) {
                statement.close();
                statement = null;
            }
        } catch (SQLException ex) {

        }

        try {
            if (connection != null) {
                if (!(connection.isClosed()))
                    connection.close();
                connection = null;
            }
        } catch (SQLException ex) {

        }
    }
    
    public void updateBox(String box_id, double mar_lon, double mar_lat) {
    	Connection conn = null;
		PreparedStatement stmt = null;
	    ResultSet result = null;
		try {
			conn = this.connectionPool.getConnection();
			stmt = conn.prepareStatement("update box_tbl set mar_lon = ?, mar_lat = ? where box_id = ? ");
			stmt.setDouble(1, mar_lon);;
			stmt.setDouble(2, mar_lat);
			stmt.setString(3, box_id);
			stmt.executeUpdate();
		} catch (SQLException se) {
			se.printStackTrace();
			afterQueryProcess(stmt, conn, result);
		} finally {
			afterQueryProcess(stmt, conn, result);
		}
    }
    
    public List selectAllBox() {
    	Connection conn = null;
		PreparedStatement stmt = null;
	    ResultSet result = null;
	    List<BoxBean> list = new ArrayList<BoxBean>();
		try {
			conn = this.connectionPool.getConnection();

			stmt = conn.prepareStatement("select box_id, longitude, latitude from box_tbl where longitude is not null and latitude is not null");
			result = stmt.executeQuery();
			while(result.next()) {
				BoxBean box = new BoxBean();
				box.setBox_id(result.getString(1));
				box.setLongitude(result.getDouble(2));
				box.setLatitude(result.getDouble(3));
				list.add(box);
			}
		} catch (SQLException se) {
			se.printStackTrace();
			afterQueryProcess(stmt, conn, result);
		} finally {
			afterQueryProcess(stmt, conn, result);
		}
		return list;
    }


	public void insertAddr(String qx,String village,String address,String addid,String addqr,String latitude,String longitude) {
		Connection conn = null;
		PreparedStatement stmt = null;
	    ResultSet result = null;
		try {


			conn = this.connectionPool.getConnection();

			// �ц��ヨ��
			stmt = conn.prepareStatement("insert into address_tbl (qx,village,address,addid,addqr,latitude,longitude) "
			        + "values(?,?,?,?,?,?,?)");
			stmt.setString(1, qx);
			stmt.setString(2, village);
			stmt.setString(3, address);
			stmt.setString(4, addid);
			stmt.setString(5, addqr);
			stmt.setString(6, latitude);
			stmt.setString(7, longitude);
			stmt.executeUpdate();
		} catch (SQLException se) {
			se.printStackTrace();
			afterQueryProcess(stmt, conn, result);
		} finally {
			afterQueryProcess(stmt, conn, result);
		}
	}
}
