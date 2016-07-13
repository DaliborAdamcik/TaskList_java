package task.ui;

import task.core.*;

public class Main {
	public static void main(String[] args) {
		TaskList tl  = TaskList.load();
		CslUI cs=  new CslUI(tl);

		cs.run();
		tl.save();
	}
}
