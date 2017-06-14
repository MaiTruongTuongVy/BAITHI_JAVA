package com.example.mediaplayer;



public class song {
	private String Title;
	private int File;
	
	public song(String title, int file)
	{
		Title =title ;
		File = file;
	}
	 public void setTitle(String title) {
	        Title = title;
	    }

	 public int getFile() {
	        return File;
	    }

	 public void setFile(int file) {
	        File = file;
	    }

	 public String getTitle()
	    {
	        return Title;

	    }
	
}
