package com.depromeet.finddepro.data;

import java.util.ArrayList;

public class Choice {

	private String id;
	private String name;
	private Boolean checked;
	private Integer personCnt;

	public Choice(String id, String name, Boolean checked, Integer personCnt, ArrayList<User> selectedUserList) {
		this.id = id;
		this.name = name;
		this.checked = checked;
		this.personCnt = personCnt;
		this.selectedUserList = selectedUserList;
	}

	private ArrayList<User> selectedUserList;

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Boolean getChecked() {
		return checked;
	}

	public ArrayList<User> getSelectedUserList() {
		return selectedUserList;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public String getPersonCntStr() {
		return String.valueOf(personCnt);
	}

	public int getSelectionCnt() {
		return selectedUserList == null ? 0 : selectedUserList.size();
	}

}
