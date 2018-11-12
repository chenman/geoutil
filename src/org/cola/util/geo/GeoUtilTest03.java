package org.cola.util.geo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.cola.bean.BoxBean;
import org.cola.util.db.DBUtil;

public class GeoUtilTest03 {

	public static void main(String[] args) throws SQLException {
		DBUtil dbUtil = new DBUtil();
		
		List<BoxBean> list = dbUtil.selectAllBox();
		for(int i = 0; i < list.size(); ++i) {
			System.out.println(list.get(i).getBox_id());
			String box_id = list.get(i).getBox_id();
			double lon = list.get(i).getLongitude();
			double lat = list.get(i).getLatitude();
			Point dst = new Point();
	        dst = GeoUtil.convertEarth2Mars(new Point(lon,lat));
	        dbUtil.updateBox(box_id, dst.getLon(), dst.getLat());
		}
	}

}
