package edu.coldrain.domain;

import java.util.Date;

import lombok.Data;

@Data
public class FolderVO {
	private int fno;
	private String folder_name;
	private Date regdate;
	private Date updatedate;
	
}
