package adminClass.certificateDefaultComment;

public class CertDefaultComQueries {
	
	public static final String getDefaultCommentList = "SELECT edc_id, edc_content, rank_from, rank_to, edc_status FROM exam_default_comment";
	
	public static final String insertDefaultComment = "INSERT INTO exam_default_comment(edc_content, rank_from, rank_to, edc_status) VALUES(?, ?, ?, 'A')";

	public static final String updateDefaultComment = "UPDATE exam_default_comment SET edc_content = ?, rank_from = ?, rank_to = ?, edc_status = ? WHERE edc_id = ?";
	
	public static final String getDefaultCommentPerRank = "SELECT edc_content FROM exam_default_comment WHERE rank_from >= ? and rank_to < ? and edc_status = 'A'";
	
	public static final String getActiveDefaultComment = "SELECT edc_content, rank_from, rank_to FROM exam_default_comment WHERE edc_status = 'A'";
	
	public static final String validateRankRang = "SELECT edc_id FROM exam_default_comment WHERE (rank_from <= ? and rank_to >= ?) or  (rank_from <= ? and rank_to >= ?)";
	
}
