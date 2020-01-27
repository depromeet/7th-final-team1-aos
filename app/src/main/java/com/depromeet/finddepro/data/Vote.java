package com.depromeet.finddepro.data;

import java.util.ArrayList;

public class Vote {
	private String id;
	private String title;
	private String content;

	private User creator;

	private ArrayList<Choice> choices;

	private Integer likeCnt;
	private Integer replyCnt;

	private Integer totalPersonCnt;

	private Integer selectableCnt;

	private Long createdAt;
	private Long dueAt;

	public boolean isMultipleSelection() {
		return selectableCnt != null && selectableCnt >= 2;
	}
}
