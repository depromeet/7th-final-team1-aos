package com.depromeet.finddepro.data;

import java.util.ArrayList;

public class Vote {
	private Integer id;
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

	public Vote(Integer id, String title, String content, User creator, ArrayList<Choice> choices, Integer likeCnt, Integer replyCnt, Integer totalPersonCnt, Integer selectableCnt, Long createdAt, Long dueAt) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.creator = creator;
		this.choices = choices;
		this.likeCnt = likeCnt;
		this.replyCnt = replyCnt;
		this.totalPersonCnt = totalPersonCnt;
		this.selectableCnt = selectableCnt;
		this.createdAt = createdAt;
		this.dueAt = dueAt;
	}

	public boolean isMultipleSelection() {
		return selectableCnt != null && selectableCnt >= 2;
	}
}
