package cn.edu.zucc.personplan.model;

import java.util.Date;

public class BeanPlan {
//	public static final String[] tableTitles = { "序号", "名称", "步骤数", "已完成数" };
	public static final String[] tableTitles = { "序号", "名称", "步骤数", "已完成数","创建时间" };
	Integer plan_id;
	String user_id; // '计划所属用户id',
	Integer plan_order; // '计划序号，要求同一个用户下序号不重复',
//	序号递增 需要是之前的 +1 
	String plan_name; // varchar(255) NOT NULL COMMENT '计划名称',
	Date create_time;// datetime NOT NULL,
	Integer step_count;// int(11) NOT NULL COMMENT '步骤总数据',
	Integer start_step_count;// int(11) NOT NULL COMMENT '已经开始的步骤数',
	Integer finished_step_count;// int(11) NOT NULL COMMENT '已经完成的步骤数',

	
	/**
	 * 请自行根据javabean的设计修改本函数代码，col表示界面表格中的列序号，0开始
	 */
	public String getCell(int col) {
//		if (col == 0)
//			return "1";
//		else if (col == 1)
//			return "示例计划";
//		else if (col == 2)
//			return "2";
//		else if (col == 3)
//			return "1";
//		else
//			return "";
		
		
		if (col == 0)
			return ""+plan_id;
		else if (col == 1)
			return plan_name;
		else if (col == 2)
			return ""+step_count;
		else if (col == 3)
			return ""+finished_step_count;
		else if (col == 4)
			return create_time.toString();
		else
			return "";
	}

	public Integer getPlan_id() {
		return plan_id;
	}

	public void setPlan_id(Integer plan_id) {
		this.plan_id = plan_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public Integer getPlan_order() {
		return plan_order;
	}

	public void setPlan_order(Integer plan_order) {
		this.plan_order = plan_order;
	}

	public String getPlan_name() {
		return plan_name;
	}

	public void setPlan_name(String plan_name) {
		this.plan_name = plan_name;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Integer getStep_count() {
		return step_count;
	}

	public void setStep_count(Integer step_count) {
		this.step_count = step_count;
	}

	public Integer getStart_step_count() {
		return start_step_count;
	}

	public void setStart_step_count(Integer start_step_count) {
		this.start_step_count = start_step_count;
	}

	public Integer getFinished_step_count() {
		return finished_step_count;
	}

	public void setFinished_step_count(Integer finished_step_count) {
		this.finished_step_count = finished_step_count;
	}

	@Override
	public String toString() {
		return "BeanPlan [plan_id=" + plan_id + ", user_id=" + user_id + ", plan_order=" + plan_order + ", plan_name="
				+ plan_name + ", create_time=" + create_time + ", step_count=" + step_count + ", start_step_count="
				+ start_step_count + ", finished_step_count=" + finished_step_count + "]";
	}
	
	

}
