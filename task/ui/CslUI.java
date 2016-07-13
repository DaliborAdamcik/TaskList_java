package task.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import task.core.Task;
import task.core.TaskList;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CslUI {
	private BufferedReader input;
	private TaskList tasks;
	private enum MenuItems {ADD_NEW_TASK_WITH_DUE, ADD_NEW_TASK, REMOVE_TASK, CHANGE_STATE, REMOVE_COMPLETED, PRINT_TASKS, EXIT};

	public CslUI(TaskList tasks) {
		 input = new BufferedReader(new InputStreamReader(
					System.in));
		 
		 this.tasks = tasks; 
	}
	
	public void run()
	{
		MenuItems mus;
		Date da = null;
		String desc;
		do
		{
			mus = showMenu();
			System.out.printf("You selected %s\r\n", mus.toString());
			
			switch(mus)
			{
			case ADD_NEW_TASK_WITH_DUE:
				do{
					System.out.print("Due to date: ");
					da = rdDate();	
				}
				while(da == null); // we enetered correct date
			case ADD_NEW_TASK:
				do{
					System.out.print("Please enter task description (cant be empty): ");
					desc = rdLn().trim();
				}
				while(desc.length()==0);
				tasks.addTask(new Task(desc, da));
				break;
			case CHANGE_STATE:
				Task t = tasks.getTask(getIndex()-1);
				t.toggleState(!t.isActive());
				break;
			case EXIT: 
				System.out.println("Bye!");
				break;
			case PRINT_TASKS:
				System.out.println(tasks.toString());
				break;
			case REMOVE_COMPLETED:
				tasks.removeCompletedIt(); // tu by bolo fajn dat ze kolko taskov som vyhodil (pocet)
				break;
			case REMOVE_TASK:
				t = tasks.getTask(getIndex()-1);
				tasks.removeTask(t);
				break;
			default:
				System.out.println("Unimplemented case");
				break;
			}
		}
		while(mus.compareTo(MenuItems.EXIT)!=0);
	}
	

	private MenuItems showMenu()
	{
		System.out.println("MainMenu\r\n--------------");

		for(MenuItems m: MenuItems.values())
			System.out.printf("%d. %s\r\n", m.ordinal()+1, m.toString().replaceAll("_", " ").toLowerCase());
		
		int choice = rdInt(MenuItems.values().length)-1;
		
		return MenuItems.values()[choice];
	}

	
	private String rdLn() {
		try {
			return input.readLine();
		} catch (IOException e) {
			return null;
		}
	}
	
	private int rdInt(int maxint)
	{
		try
		{
			String s = rdLn();
			int ret = Integer.valueOf(s);

			if(ret<1 || ret>maxint)
				return rdInt(maxint);
			
			return ret;
		}
		catch(NumberFormatException e)
		{
			return rdInt(maxint);
		}
	}
	
	private Date rdDate()
	{	
		String dateS= rdLn().trim();
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
			Date date = formatter.parse(dateS);
			return date;
		} catch (ParseException e) {
			return null;
		}	
	}
	
	private int getIndex()
	{
		System.out.print("Enter index: ");
		return rdInt(tasks.getSize());
	}
}
