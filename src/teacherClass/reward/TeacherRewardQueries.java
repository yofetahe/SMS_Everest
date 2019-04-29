package teacherClass.reward;

public class TeacherRewardQueries {
	
	public static final String getRewardList = "SELECT tr_id, tr_type, tr_reason, tr_date, tr_status FROM tech_reward WHERE ti_id = ?";
	
	public static final String insertReward = "INSERT INTO tech_reward (tr_type, tr_reason, tr_date, tr_status, ti_id) values (?, ?, ?, 'A', ?)";
	
	public static final String updateReward = "UPDATE tech_reward set tr_type = ?, tr_reason = ?, tr_date = ?, tr_status = ? WHERE tr_id = ?";

}
