package filesystem;


import java.util.Scanner;

public class main_class {
	
	
	static directory dirobject;
	
	static directory dirobject2;
	
	static String a;
	
	//This variable is for getting integer input
	static int b;
	
	static boolean var;
	
	
	
	public static  void main(String [] args)
	{
		
		var = true;
		
		//create your root directory
		dirobject = new directory("root");
		//sets the parent to null because this is you root directory
		dirobject.setParent(dirobject);
		dirobject2 = new directory("blank");
		
		
		
		
		
		
		while(true)
	{
		System.out.println("Enter a command \n");
		a =   extracted().nextLine();
		
		if(a.equals("mkdir"))
		{
			System.out.println("Enter a dir name: \n");
			a = extracted().nextLine();
			dirobject.add_directory(a);
		}
		
		else if(a.equals("create"))
		{
			System.out.println("Enter file name, default size is currently 25 \n");
			a = extracted().nextLine();
			//b = in.nextInt();
			dirobject.add_file(a,25);
		}
		
		else if(a.equals("cd"))
		{
			System.out.println("Enter a directory name \n");
			a = extracted().nextLine();
			//sets dirobject2 = dirobject1
			dirobject2.setEqual(dirobject2, dirobject);
			//traverse the sub directories and changes to one of those sub directories
			dirobject.setEqual(dirobject, dirobject.traverse_directories(a));
			dirobject.setParent(dirobject2);
		}
		
		else if(a.equals("del"))
		{
			System.out.println("file_name or directory? \n");
			a = extracted().nextLine();
			
			if(a.equals("file_name"))
			{
				a = extracted().nextLine();
				dirobject.delete_files(a);
			}
			
			else
			{
				a = extracted().nextLine();
				dirobject.delete_directories(a);
			}
			
			
		}
		
		else if(a.equals("size"))
		{
			System.out.println("file_name or directory? \n");
			a = extracted().nextLine();
			
			if(a.equals("file_name"))
			{
				a = extracted().nextLine();
				dirobject.file_size(a);
			}
			
			else
			{
				a = extracted().nextLine();
				dirobject.directory_size(a);
			}
			
			
		}
		
		else if(a.equals("ls"))
		{
			System.out.println("file_name or directory? \n");
			a = extracted().nextLine();
			
			if(a.equals("directory"))
			{   System.out.println("Enter a dir name\n");
				a = extracted().nextLine();
				dirobject.files_directory(a);
			}
			
			else
			{
				System.out.println("Enter the file name");
				a = extracted().nextLine();
				dirobject.file_name(a);
			}
		}
		
		else
		{
			System.out.println("That input was not recognized \n");
			
		}
		
	}
}



	private static Scanner extracted() {
		return new Scanner(System.in);
	}
		
}


