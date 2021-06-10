package edu.coldrain.domain;

import java.util.Date;

import lombok.Data;

@Data
public class CategoryVO {

	private int cno;
	private int fno;
	private String category_name;
	private Date regdate;
	private Date updatedate;
	private String state;
	
}
