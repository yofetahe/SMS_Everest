package studentClass.reward;

public class RewardQueries {

	public  static final String rewardList = "SELECT a.sr_id, a.sr_type, a.sr_reason, a.sr_date, a.sr_status FROM stud_reward a WHERE a.si_id = ?";
	
	public static final String insertReward = "INSERT INTO stud_reward (sr_type, sr_reason, sr_date, sr_status, si_id) VALUES (?, ?, ?, 'A', ?)";
	
	public static final String updateReward = "UPDATE stud_reward SET sr_type = ?, sr_reason = ?, sr_date = ?, sr_status = ? WHERE sr_id = ?";
	
}
