package filesystem;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


//directory class is the B-Tree that contains the specified number of files

public class directory {
	
	
	// Creates two lists, a list of files and a list of children to distinguish between
	//files and directories
	
	private List<file> myList;
	
	private List<directory> children;
	
	private String dir_name;
	
	boolean a;
	
	file b;
	
	private directory var;
	
	//stores the parent directory
	
	private directory parent;
	
	
	int count;
	
	//sets the parent directory in the case we want to traverse back to the previous directory
	public void setParent(directory a)
	{
		parent = new directory(" ");
		parent.setEqual(parent,a);
	}
	
	//returns name of the directory
	public String getName()
	{
		return dir_name;
	}
	
	
	public directory(String name)
	{	
		a = false;
		dir_name = new String();
		dir_name = name;
		myList = new ArrayList<file>();
		children = new LinkedList<directory>();
		
		
	}
	// this function is used to set the first specifed directory equal to the second specified
	//directory
	//It doesn't effect the second directory in any way, only the first one
	//Needed because dir is a custom class which the "=" operator does not support
	//needed in the main function
	
	public void setEqual(directory dir1, directory dir2)
	{
		dir1.dir_name = dir2.dir_name;
		
		
		if(dir1.myList.size() < dir2.myList.size()){
			
			for(int i =0; i<dir1.myList.size();i++)
			{
				dir1.myList.get(i).setName(dir2.myList.get(i).getName());
				dir1.myList.get(i).setSize_file(dir2.myList.get(i).getSize_file());
			
			}
		
			for(int j=dir1.myList.size();j<dir2.myList.size();j++)
			{
				dir1.myList.add(dir2.myList.get(j));
			}
		
		}
		else if(dir1.myList.size() > dir2.myList.size()){
			for(int i=0;i<dir2.myList.size();i++)
			{
				dir1.myList.get(i).setName(dir2.myList.get(i).getName());
				dir1.myList.get(i).setSize_file(dir2.myList.get(i).getSize_file());
			}
			
			for(int j=dir2.myList.size();j<dir1.myList.size();j++)
			{
				dir1.myList.remove(j);
			}
		}
		
		else if(dir1.myList.size()==0 && dir2.myList.size()==0)
		{
			
		}
			
		
		else
		{
			for(int i=0;i<dir1.myList.size();i++)
			{
				dir1.myList.get(i).setName(dir2.myList.get(i).getName());
				dir1.myList.get(i).setSize_file(dir2.myList.get(i).getSize_file());
			}
		}
		
		if(dir1.children.size()<dir2.children.size())
		{
			for(int i=0;i<dir1.children.size();i++)
			{
				dir1.setEqual(dir1.children.get(i), dir2.children.get(i));
			}
			
			for(int j=dir1.children.size();j<dir2.children.size();j++)
			{
				dir1.children.add(dir2.children.get(j));
				
			}
		}
		
		else if(dir1.children.size()>dir2.children.size())
		{
			for(int i=0;i<dir2.children.size();i++)
			{
				dir1.setEqual(dir1.children.get(i), dir2.children.get(i));
			}
			
			for(int j=dir2.children.size();j<dir1.children.size();j++){
				
				dir1.children.remove(j);
			}
		}
		
		else if(dir1.children.size()==0 && dir2.children.size()==0)
		{
			
		}
		
		else
		{
			for(int i =0;i<dir1.children.size();i++)
			{
				dir1.setEqual(dir1.children.get(i), dir2.children.get(i));
			}
			
		}
		
		
		
		
		
		
		
		
		
		
	}
	
	//This function is for adding files to the list of arrays
	
	void add_file(String file_name,int size){
		
	b = new file(file_name,size);
		
	myList.add(b);
		
		
	
	}
	
	//adds a new child directory to the current directory;
	
	void add_directory(String a)
	{
		
		var = new directory(a);
		var.parent = this;
		children.add(var);
		
	}
	
	
//when mkdir is called, need a function that creates a child of the current directory that we are in
	
	
	
//This is for the case when cd is called and we want to change the directory
//Enter the name of the directory that we want to switch to
	
	 public directory traverse_directories(String name)
	{
		
		
		if(name.equals(".."))
		{
			var.setEqual(var,this.parent);
		}
		
		else{
		
		
		for(int i=0;i<children.size();i++)
		{
			if(children.get(i).dir_name.equals(name))
			{
				var.setEqual(var,children.get(i));
					
			}
			
			
			else
			{
				var = null;
			}
			
			
			
			
		}
	
		
		}
		
		return var;
		
		
	}
	 
	 
	 
	 //Function to delete directories
	 public void delete_directories(String name)
	 {
		 count =0;
		 
		 if(name.equals(dir_name))
		 {
			 this.children.removeAll(children);
			 this.myList.removeAll(myList);
		 }
		 
		 else
		 {
			 for(int i=0;i<children.size();i++)
			 {
				 if(name.equals(children.get(i).dir_name))
				 {
					 children.remove(i);
				 }
				 
				 else
				 {
					 count++;
				 }
				 
			
			 }
			 
			 if(count==children.size())
			 {
				 System.out.println("The directory that you specified does not exist \n");
			 }
			
		 }
		 
		 
	 }
	 // This function is for deletion of files
	 public void delete_files(String filename)
	 {
		 for(int i=0;i<myList.size();i++)
		 {
			 if(myList.get(i).getName()== filename)
			 {
				 myList.remove(i);
			 }
		 }
	 }
	
	
	//This function is for ls and prints out all of the files in the named directory
	 public boolean files_directory(String dirname)
	 {
		
		count =0;
		if(dirname.equals(""))
		{
			a = true;
			for(int i=0;i<myList.size();i++)
			{
				System.out.println(myList.get(i).getName());
				System.out.println(myList.get(i).getSize_file());
			}
		}
		
		else if(dir_name.equals(dirname))
		{
			a = true;
			for(int i=0;i<myList.size();i++)
			{
				System.out.println(myList.get(i).getName());
				System.out.println(myList.get(i).getSize_file());
			}
		}
		
		else
		{
		 for(int i=0;i<children.size();i++)
		 {
			 if(children.get(i).dir_name.equals(dirname))
			 {
				 if(children.get(i).myList.isEmpty())
				 {
					 a = true;
					 System.out.println("No files currently in that directory\n");
				 }
				 
				 else {
					 a = true;
					 
					 for(int j =0;j<children.get(i).myList.size();j++)
					 {
						 System.out.println(children.get(i).myList.get(j).getName());
						 System.out.println(children.get(i).myList.get(j).getSize_file());
						 System.out.println("\n");
					 }
					 
				 }
			 }
			 
			 if(children.get(i).dir_name!=dirname)
			 {
				 count++;
			 }
			 
			
			 
			 
		 }
		}
		 
		 if(count == children.size())
		 {
			 a = false;
		 }
		 
		
		 
		return a;
		 
	 }
	 //Function to print file_name and size
	 public void file_name(String filename)
	 {
		 
		 
		 
		for(int i=0;i<myList.size();i++)
		{
		 if(myList.get(i).getName().equals(filename))
		 {
			 
			 System.out.println(filename);
			 System.out.println(myList.get(i).getSize_file());
			 
		 }
		}
		
		
	 }
	 
	 //Function to print file size
	 public void file_size(String filename)
	 {
		 
		for(int i=0;i<myList.size();i++)
		{
		 if(myList.get(i).getName()==filename)
		 {
			 
			 System.out.println(myList.get(i).getSize_file());
			 
		 }
		}
		
		
	 }
	 
	 
	//Function to print the directory size
	public void directory_size(String name)
	{
		count = 0;
		if(dir_name.equals(name))
		{
			if(myList.isEmpty()==false && children.isEmpty())
			{
				for(int i=0;i<myList.size();i++)
				{
					System.out.println("Here are the sizes of the files: \n ");
					myList.get(i).getSize_file();
				}
			}
			
			else if(myList.isEmpty() && children.isEmpty()==false)
			{
				for(int i=0;i<children.size();i++)
				{
					System.out.println("Here are the sizes of the sub directory " + children.get(i).getName() + ": \n");
							
					System.out.println(children.get(i).myList.size());
				}
			}
			
			else if(myList.isEmpty()==false && children.isEmpty()==false)
			{
				for(int i=0;i<myList.size();i++)
				{
					System.out.println("Here are the sizes of the files: \n ");
					myList.get(i).getSize_file();
				}
				
				for(int i=0;i<children.size();i++)
				{
					System.out.println("Here are the sizes of the sub directories: \n");
					System.out.println(children.get(i).myList.size());
				}
			}
			
			else
			{
				System.out.println("There are no files or sub directoies to print: \n");
			}
				
		}
		
		else
		{
			for(int i=0;i<children.size();i++)
			{
				if(name.equals(children.get(i).dir_name))
				{
					
					if(children.get(i).myList.isEmpty() && children.get(i).children.isEmpty()==false)
					{
						for(int j=0;j<children.get(i).children.size();j++)
						{
							System.out.println("Here are the sizes of the sub directories: \n");
							System.out.println(children.get(i).children.get(j).myList.size());
						}
					}
					
					else if(children.get(i).myList.isEmpty()==false && children.get(i).children.isEmpty())
					{

						for(int j=0;i<children.get(i).myList.size();j++)
						{
							System.out.println("Here are the sizes of the files in this dir: \n");
							System.out.println(children.get(i).myList.get(j).getSize_file());
							
						}
					}
					
					else if(children.get(i).myList.isEmpty()==false && children.get(i).children.isEmpty()==false){
					
					
					for(int j=0;i<children.get(i).myList.size();j++)
					{
						System.out.println("Here are the sizes of the files in this dir: \n");
						System.out.println(children.get(i).myList.get(j).getSize_file());
						
					}
					
					for(int j=0;j<children.get(i).children.size();j++)
					{
						System.out.println("Here are the sizes of the sub directories: \n");
						System.out.println(children.get(i).children.get(j).myList.size());
					}
					
					}
					
					else {
						System.out.println("There are no files or sub directoreis in the specified directory");
					}
				}
				
				else{
					count++;
				}
				
				

			}
			
			if(count == children.size())
			{
				System.out.println("The directory was not found \n");
			}
			
		}
		
	}
	}
