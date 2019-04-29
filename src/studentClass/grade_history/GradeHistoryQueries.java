package studentClass.grade_history;

public class GradeHistoryQueries {
	
	public static final String getGrdHistoryList = "SELECT c.class_name, b.cl_id FROM stud_registration a, clist_cdetail_rel b, class_list c WHERE a.si_id = ? and a.reg_status = 'A' and c.cl_id = b.cl_id and a.clcd_id = b.clcd_id";
	
	public static final String getSubPerGrd = 	"SELECT a.sub_name, a.sub_id, a.sub_status, b.subcl_id, (select sum(c.result) from exam_result c where c.examsub_id in (select d.exsub_id from exam_sub_rel d where d.subcl_id = b.subcl_id) ) as total_mark, " +
												"(select sum(e.pass_mark) from exam_sub_rel e where e.subcl_id = b.subcl_id) as total_pass_mark FROM subject_list a, subject_class_rel b WHERE b.cl_id = ? and b.rel_status = 'A' and a.sub_id = b.sub_id";

}
