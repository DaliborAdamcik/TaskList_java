package task.core;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class TaskList implements Serializable{
	private static final long serialVersionUID = 1L;
	private List<Task> list;

	public TaskList() {
		list = new ArrayList<Task>();
	}
	
	
	public void addTask(Task ta){
		list.add(ta);
	}
	
	public void removeTask(Task t)
	{
		int idx = list.indexOf(t); 
		if (idx>=0)
		list.remove(idx);
	}
	
	public Task getTask(int id)
	{
		if(id>=list.size() || id<0)
			return null; 

		return list.get(id);
	}
	
	public int getSize()
	{
		return list.size();
	}
	
	public void removeCompleted()
	{
		int i = 0;
		while(i<list.size())
		{
			if(!list.get(i).isActive())
				list.remove(i);
			else
				i++;
		}
	}
	
	public void removeCompletedIt()
	{
		Iterator<Task> i = list.iterator();
		while(i.hasNext())
		{
			Task t = i.next();
			if(!t.isActive())
				i.remove();
		}
	}
	
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder(String.format("Tasks: %d\r\n--------------------------------------", list.size()));
		int tid= 1;
		for(Task ta: list)
			sb.append(String.format("\r\n%2d %s", tid++, ta.toString()));
		
		return sb.toString();
	}
	
	public static String SaveFile()
	{
		return System.getProperty("user.home")+System.getProperty("file.separator")+"tasklist.sav";
	}
	
	public static TaskList load()
	{
		try(FileInputStream fi = new FileInputStream(SaveFile()))
		{
			try(ObjectInputStream oos = new ObjectInputStream(fi))
			{
				return (TaskList) oos.readObject();
			} 
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			return new TaskList();
		}
	}
	
	public void save()
	{
		try(FileOutputStream fi = new FileOutputStream(SaveFile()))
		{
			try(ObjectOutputStream oos = new ObjectOutputStream(fi))
			{
				oos.writeObject(this);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
}
