package task.core;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Task implements Serializable {
	private static final long serialVersionUID = 1L;
	private String description;
	private boolean active;
	private Date dueDate;

	public Task(String description, Date dueDate, boolean active) {
		super();
		this.description = description;
		this.active = active;
		this.dueDate = dueDate;
	}
	
	public Task(String description, Date dueDate)
	{
		this(description, dueDate, true);
	}

	public Task(String description)
	{
		this(description, new Date(), true);
	}
	
	public String toString(){
		String datefmt = "";
		boolean over = false;
		if(dueDate!=null)
		{
			SimpleDateFormat fmtr = new SimpleDateFormat("dd.MM.yyyy");
			datefmt = fmtr.format(dueDate);
			over = dueDate.getTime()<System.currentTimeMillis();
		}
		
		return String.format(" %s '%s' %s", (active?(over?"*!":"* "):"- "), description, datefmt);
	}
	
	public boolean isActive()
	{
		return active;
	}
	
	public void toggleState(boolean act)
	{
		active = act;
	}

	public String getDesc() {
		return description;
	}

	public void setDesc(String description) {
		this.description = description;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
}
