import java.io.*;
import java.net.*;
import java.util.*;

public class Book {
    private String ISBN;
    private String Title;
    private String Author;
    private String Publisher;
    private String YEAR;

  public void setISBN(String newISBN){
      this.ISBN = newISBN;
  }  

  public void setTitle(String newTitle){
    this.Title = newTitle;
}  

  public void setAuthor(String newAuthor){
      this.Author = newAuthor;
  }

  public void setPublisher(String newPublisher){
        this.Publisher= newPublisher;
    }
    public void setYear(String newYEAR){
    this.YEAR= newYEAR;
    }

public String getISBN(){
    return this.ISBN;
}

public String getTitle(){
    return this.Title;   
}

public String getAuthor(){
    return this.Author;
}

public String getPublisher(){
    return this.Publisher;
}

public String getYear(){
    return this.YEAR;
}


  
}
