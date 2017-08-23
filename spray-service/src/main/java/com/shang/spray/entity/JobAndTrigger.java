package com.shang.spray.entity;

public class JobAndTrigger {

	private String JOB_NAME;

	private String JOB_GROUP;

	private String JOB_CLASS_NAME;

	private String TRIGGER_NAME;

	private String TRIGGER_STATE;//状态

	private String TRIGGER_GROUP;

	private String CRON_EXPRESSION;

	private String TIME_ZONE_ID;

	public String getJOB_NAME() {
		return JOB_NAME;
	}

	public void setJOB_NAME(String JOB_NAME) {
		this.JOB_NAME = JOB_NAME;
	}

	public String getJOB_GROUP() {
		return JOB_GROUP;
	}

	public void setJOB_GROUP(String JOB_GROUP) {
		this.JOB_GROUP = JOB_GROUP;
	}

	public String getJOB_CLASS_NAME() {
		return JOB_CLASS_NAME;
	}

	public void setJOB_CLASS_NAME(String JOB_CLASS_NAME) {
		this.JOB_CLASS_NAME = JOB_CLASS_NAME;
	}

	public String getTRIGGER_NAME() {
		return TRIGGER_NAME;
	}

	public void setTRIGGER_NAME(String TRIGGER_NAME) {
		this.TRIGGER_NAME = TRIGGER_NAME;
	}

	public String getTRIGGER_GROUP() {
		return TRIGGER_GROUP;
	}

	public void setTRIGGER_GROUP(String TRIGGER_GROUP) {
		this.TRIGGER_GROUP = TRIGGER_GROUP;
	}

	public String getCRON_EXPRESSION() {
		return CRON_EXPRESSION;
	}

	public void setCRON_EXPRESSION(String CRON_EXPRESSION) {
		this.CRON_EXPRESSION = CRON_EXPRESSION;
	}

	public String getTIME_ZONE_ID() {
		return TIME_ZONE_ID;
	}

	public void setTIME_ZONE_ID(String TIME_ZONE_ID) {
		this.TIME_ZONE_ID = TIME_ZONE_ID;
	}

	public String getTRIGGER_STATE() {
		return TRIGGER_STATE;
	}

	public void setTRIGGER_STATE(String TRIGGER_STATE) {
		this.TRIGGER_STATE = TRIGGER_STATE;
	}

	public String getTRIGGER_STATE_NAME() {
		if (TRIGGER_STATE.equals("ACQUIRED")) {
			return "正常执行";
		} else if (TRIGGER_STATE.equals("PAUSED")) {
			return "已暂停";
		} else if (TRIGGER_STATE.equals("WAITING")) {
			return "等待中";
		} else if (TRIGGER_STATE.equals("BLOCKED")) {
			return "阻塞中";
		} else if (TRIGGER_STATE.equals("ERROR")) {
			return "错误";
		}
		return "未知状态:" + TRIGGER_STATE;
	}
}
