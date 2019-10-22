package kr.retdd.basic;

import lombok.Getter;

@Getter
public enum ELang {
	kr(100, "korean")
	, en(101, "english")
	, jp(103, "japanse")
	, cn(104, "chinese");
	
	private	Integer	value;
	private	String	label;
	
	private ELang(Integer value, String label) {
		this.value = value;
		this.label = label;
	}
}
