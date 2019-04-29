package cRoomClass.cd_registration;

public class CDetailQueries {
	
	public static final String getCDetailList = "SELECT cd_id, cd_name, cd_status FROM class_detail";
	
	public static final String updateCDetail = "UPDATE class_detail SET cd_name  = ?, cd_status = ? WHERE cd_id = ?";
	
	public static final String insertCDetail = "INSERT INTO class_detail (cd_name, cd_status) values (?, 'Active')";

}
