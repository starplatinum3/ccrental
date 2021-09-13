//package cn.edu.zucc.personplan.model;
//
//public class BeanStep {
//	public static final String[] tblStepTitle={"序号","名称","计划开始时间","计划完成时间","实际开始时间","实际完成时间"};
//	/**
//	 * 请自行根据javabean的设计修改本函数代码，col表示界面表格中的列序号，0开始
//	 */
//	public String getCell(int col){
//		if(col==0) return "1";
//		else if(col==1) return "示例步骤";
//		else if(col==2) return "2015-01-01";
//		else if(col==3) return "2015-08-01";
//		else if(col==4) return "";
//		else if(col==5) return "";
//		
//		else return "";
//	}
//}


package cn.edu.zucc.personplan.model;


import java.util.Date;
import java.util.List;
//import java.io.Serializable;
//import com.baomidou.mybatisplus.annotation.IdType;
//import com.baomidou.mybatisplus.annotation.TableId;
/**
 * @description tbl_step
 * @author mqp
 * @date 2021-08-31
 */

public class BeanStep {
	
	public static final String[] tblStepTitle={"序号","名称","计划开始时间","计划完成时间","实际开始时间","实际完成时间"};
	/**
	 * 请自行根据javabean的设计修改本函数代码，col表示界面表格中的列序号，0开始
	 */
	public String getCell(int col){
//		if(col==0) return "1";
//		else if(col==1) return "示例步骤";
//		else if(col==2) return "2015-01-01";
//		else if(col==3) return "2015-08-01";
//		else if(col==4) return "";
//		else if(col==5) return "";
//		
//		else return "";
		
		if(col==0) return ""+stepId;
		else if(col==1) return stepName;
		else if(col==2) return planBeginTime==null?null: planBeginTime.toString();
		else if(col==3) return planEndTime==null?null:planEndTime.toString();
		else if(col==4) {
			return realBeginTime==null?null:realBeginTime.toString();
		}
		else if(col==5) return realEndTime==null?null: realEndTime.toString();
		
		else return "";
	}
	
	

    /**
    * step_id
    */
    private Integer stepId;

    public Integer getStepId() {
        return stepId;
    }

    public void setStepId(Integer stepId) {
        this.stepId = stepId;
    }
    /**
    * 所属计划编号
    */
    private Integer planId;

    public Integer getPlanId() {
        return planId;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
    }
    /**
    * 步骤序号，要求同一计划下步骤序号不重复
    */
    private Integer stepOrder;

    public Integer getStepOrder() {
        return stepOrder;
    }

    public void setStepOrder(Integer stepOrder) {
        this.stepOrder = stepOrder;
    }
    /**
    * step_name
    */
    private String stepName;

    public String getStepName() {
        return stepName;
    }

    public void setStepName(String stepName) {
        this.stepName = stepName;
    }
    /**
    * 计划开始时间
    */
    private Date planBeginTime;

    public Date getPlanBeginTime() {
        return planBeginTime;
    }

    public void setPlanBeginTime(Date planBeginTime) {
        this.planBeginTime = planBeginTime;
    }
    /**
    * 计划结束时间
    */
    private Date planEndTime;

    public Date getPlanEndTime() {
        return planEndTime;
    }

    public void setPlanEndTime(Date planEndTime) {
        this.planEndTime = planEndTime;
    }
    /**
    * 实际开始时间
    */
    private Date realBeginTime;

    public Date getRealBeginTime() {
        return realBeginTime;
    }

    public void setRealBeginTime(Date realBeginTime) {
        this.realBeginTime = realBeginTime;
    }
    /**
    * 实际结束时间
    */
    private Date realEndTime;

    public Date getRealEndTime() {
        return realEndTime;
    }

    public void setRealEndTime(Date realEndTime) {
        this.realEndTime = realEndTime;
    }
    public BeanStep() {}

	@Override
	public String toString() {
		return "BeanStep [stepId=" + stepId + ", planId=" + planId + ", stepOrder=" + stepOrder + ", stepName="
				+ stepName + ", planBeginTime=" + planBeginTime + ", planEndTime=" + planEndTime + ", realBeginTime="
				+ realBeginTime + ", realEndTime=" + realEndTime + "]";
	}
    
    
}