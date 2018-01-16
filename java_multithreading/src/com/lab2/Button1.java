package com.lab2;

public class Button1 {
	
	private OnClickListener onClickListener;
	private String btnValue;
	
	public Button1(String btnValue) {
		super();
		this.btnValue = btnValue;
	}
	
	public Button1() {
		super();
	}
	
	public void setOnClickListener(OnClickListener onClickListener) {
		this.onClickListener = onClickListener;
		
	}
	
	public void setBtnValue(String btnValue) {
		this.btnValue = btnValue;
	}

	public OnClickListener getOnClickListener() {
		return onClickListener;
	}

	public String getBtnValue() {
		return btnValue;
	}
	
	public void onClick() {
		this.onClickListener.onClick(this.btnValue);
	}
	
}
