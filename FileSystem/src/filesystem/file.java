package filesystem;

//This class simulates a file and mainly contains a size and a file name
public class file {
	
private String name;

private int size_file;
	
	
public file(String a, int size){
	
	setName(a);
	setSize_file(size);
		
}


public String getName() {
	return name;
}


public void setName(String name) {
	this.name = name;
}


public int getSize_file() {
	return size_file;
}


public void setSize_file(int size_file) {
	this.size_file = size_file;
}





}
