package com.Concurrent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

import com.alibaba.fastjson.JSON;

public class ThreadPoolDome {
	public static void main(String[] args) {
		ThreadPoolDome tp = new ThreadPoolDome();
		tp.start();
		ThreadPoolDome tps = new ThreadPoolDome();
		tps.starts();
	}
	public void start() {
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		for (int i = 0; i < 50; i++) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("TH", i+"");
			list.add(map);
		}
		final FutuerBean b = new FutuerBean();
		b.setSum(list.size());
		
		for (final Map<String, String> map : list) {
			try{
				Callable<Map<String,String>> callable = new Callable<Map<String,String>>() {
					@Override
					public Map<String,String> call() throws Exception {
						try{
							send(map);
							b.setAddList(map);
						}catch(Exception e){
							
						}finally{
							b.setSize();
						}
						
						if(b.getSize() == b.getSum()){
							print(b);
						}
						return map;
					}
				};
			
				ThreadPoolHelper.getInstance().submit(callable);
			}catch(Exception e){
				System.err.println("-error");
				e.printStackTrace();
			}
		}
		System.out.println("okokokokokokokokokokokoko");
		
	}
	
	public void starts() {
		List<Map<String,String>> lists = new ArrayList<Map<String,String>>();
		for (int i = 0; i < 50; i++) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("TH", "TH "+i);
			lists.add(map);
		}
		final FutuerBean bs = new FutuerBean();
		bs.setSum(lists.size());
		
		List<Future<Map<String,String>>> futurelist = new ArrayList<Future<Map<String,String>>>();
		for (final Map<String, String> map : lists) {
			try{
				Callable<Map<String,String>> callable = new Callable<Map<String,String>>() {

					@Override
					public Map<String, String> call() throws Exception {
						try{
							send(map);
							bs.setAddList(map);
						}catch(Exception e){
							
						}finally{
							bs.setSize();
						}
						
	//					if(bs.getSize() == bs.getSum()){
	//						print(bs);
	//					}
						return map;
					}
					
				};
			
				futurelist.add(ThreadPoolHelper.getInstance().submitByQueue(callable));
			}catch(Exception e){
				System.err.println("-error");
				e.printStackTrace();
			}
		}
		for (Future<Map<String, String>> future : futurelist) {
			Map<String, String> g = ThreadPoolHelper.getInstance().processByQueue(future);
			
		}
		System.out.println("----------------okokokokokokokokokokokoko");
		print(bs);
	}
	
	public Map<String,String> send(Map<String,String> m) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		m.put("a", Thread.currentThread().getName()+ " ok");
		System.out.println(Thread.currentThread().getName() +" "+ m.get("TH"));
		return m;
		
	}
	
	public void print(FutuerBean b){
		System.out.println(JSON.toJSONString(b));
	}
	public class SendCallable implements Callable<Map<String,String>>{
		Map<String,String> map;
		FutuerBean b;
		public SendCallable(Map<String, String> map,FutuerBean b) {
			this.map = map;
			this.b = b;
		}


		@Override
		public Map<String,String> call() throws Exception {
			try{
				send(this.map);
				b.setAddList(this.map);
			}catch(Exception e){
				
			}finally{
				b.setSize();
			}
			
			if(b.getSize() == b.getSum()){
				print(b);
			}
			return map;
		}
		
	}
}
