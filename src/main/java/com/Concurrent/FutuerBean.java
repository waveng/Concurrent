package com.Concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FutuerBean {
	private int sum;
	private int size;
	List<Map<String,String>> list = new ArrayList<Map<String,String>>();
	public synchronized int getSum() {
		return sum;
	}
	public synchronized void setSum(int sum) {
		this.sum = sum;
	}
	public synchronized int getSize() {
		return size;
	}
	public synchronized void setSize() {
		this.size ++;
	}
	public synchronized List<Map<String, String>> getList() {
		return list;
	}
	public synchronized void setAddList(Map<String, String> m) {
		this.list.add(m);
	}
	
}
