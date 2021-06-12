package edu.coldrain.domain;

import java.util.Date;

import lombok.Data;

@Data
public class WordVO {
	private int wno;
	private int cno;
	
	private String word_name;
	private String word_meaning;
	private Date regdate;
	private Date updatedate;
	private String state;
	
}
