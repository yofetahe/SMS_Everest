package cRoomClass.class_detail;

public class CRDetailQueries {
	
	public static final String getClassDetailList = "SELECT a.clcd_id, b.cd_name, a.rel_status, a.cl_capacity FROM clist_cdetail_rel a, class_detail b WHERE a.cd_id = b.cd_id and a.cl_id = ?";
	
	public static final String getAllClassDetailList = "SELECT cd_id, cd_name, cd_status FROM class_detail WHERE cd_id not in ( SELECT b.cd_id FROM clist_cdetail_rel a, class_detail b WHERE a.cd_id = b.cd_id and a.cl_id = ? ) and cd_status = 'Active'";
	
	public static final String checkClassDetail = "SELECT clcd_id FROM clist_cdetail_rel WHERE cl_id = ? and cd_id = ?";
	
	public static final String insertClassDetail = "INSERT INTO clist_cdetail_rel (cl_id, cd_id, cl_capacity, rel_status) VALUE (?, ?, ?, 'Active')";
	
	public static final String updateClassDetail = "UPDATE clist_cdetail_rel SET cl_capacity = ?, rel_status = ? WHERE clcd_id = ?";
	
	public static final String getClassDetailName = "SELECT cd_id, cd_name, cd_status FROM class_detail WHERE cd_id = ?";
	
	

}
