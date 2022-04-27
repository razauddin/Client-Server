import java.io.*;
import java.net.*;
import java.util.*;



final class Protocol implements Runnable{
    final static String CRLF = "\r\n";
    Socket socket;
    List<Book>bibliography;
    static PrintWriter out;
    static BufferedReader in;

    //Constructor 
    Protocol(Socket socket,List<Book> bibliography) throws Exception {
        this.socket = socket;
        this.bibliography = bibliography;
    }

    


	
	public void run() {
	try {
	    processRequest();
	} catch (Exception e) {
	    System.out.println(e);
	}


    }

    public void processRequest() throws Exception{

            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(
                    new InputStreamReader(
                    socket.getInputStream()));

            
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                            
           
                String[] dataArray = inputLine.split("-");
                Book book = new Book();


                book.setISBN(dataArray[1]);
                book.setTitle(dataArray[2]);
                book.setAuthor(dataArray[3]);
                book.setPublisher(dataArray[4]);
                book.setYear(dataArray[5]);
                
                int size = bibliography.size();

                if(dataArray[0].equals("SUBMIT")){
                    // if dup is found
                    Boolean isFound = false;
                    for(int i =0 ;i < size;i++ ){
                        Book temp2 = bibliography.get(i);
                        
                        temp2.getISBN();
                        temp2.getTitle();
                        temp2.getAuthor();
                        temp2.getPublisher();
                        temp2.getYear();

                        if(temp2.getISBN().equals(book.getISBN())){
                            isFound = true;
                            break;
                        }
                        
                    }
                    
                    if(isFound) {
                        out.println("Submit fail because duplicate exist.");
                    } else {
                        bibliography.add(book);
                        out.println("Submit Success!");
                    }

                }
                else if(dataArray[0].equals("UPDATE")){
                    Book temp = new Book();
                

                    temp.setISBN(dataArray[1]);
                    temp.setTitle(dataArray[2]);
                    temp.setAuthor(dataArray[3]);
                    temp.setPublisher(dataArray[4]);
                    temp.setYear(dataArray[5]);
                    Boolean isFound = false;

                    for(int i =0 ;i < size;i++ ){
                        Book temp2 = bibliography.get(i);

                        temp2.getISBN();
                        temp2.getTitle();
                        temp2.getAuthor();
                        temp2.getPublisher();
                        temp2.getYear();

                        if((temp2.getISBN().equals(temp.getISBN())) && (temp2.getYear() != temp.getYear())){
                            if(!temp.getYear().isEmpty())
                                isFound = true;
                                temp2.setYear(temp.getYear());
                        }

                        if((temp2.getISBN().equals(temp.getISBN())) && (temp2.getPublisher() != temp.getPublisher())){
                            if(!temp.getPublisher().isEmpty())
                                isFound = true;
                                temp2.setPublisher(temp.getPublisher());
                        }

                        if((temp2.getISBN().equals(temp.getISBN())) && (temp2.getAuthor() != temp.getAuthor())){
                            if(!temp.getAuthor().isEmpty())
                                isFound = true;
                                temp2.setAuthor(temp.getAuthor());
                        }

                        if((temp2.getISBN().equals(temp.getISBN())) && (temp2.getTitle() != temp.getTitle())){
                            if(!temp.getTitle().isEmpty())
                                isFound = true;
                                temp2.setTitle(temp.getTitle());
                        }

                        if (isFound) {
                            out.println("Update Success!");
                        } else {
                            out.println("Update fail.");
                        }
                    }

                }
                else if(dataArray[0].equals("GET")){
                    Book temp = new Book();
                    List<Book> getList= new ArrayList<Book>();
                    Boolean bibtexmode = false;

                    if(dataArray[6].equals("true")) {
                        bibtexmode = true;
                    }

                    temp.setISBN(dataArray[1]);
                    temp.setTitle(dataArray[2]);
                    temp.setAuthor(dataArray[3]);
                    temp.setPublisher(dataArray[4]);
                    temp.setYear(dataArray[5]);


                    
                    for(int i =0;i < size ;i++ ){
                        Book temp2 = bibliography.get(i);
                        temp2.getISBN();
                        temp2.getTitle();
                        temp2.getAuthor();
                        temp2.getPublisher();
                        temp2.getYear();
                        if((temp2.getTitle().equals(temp.getTitle())) && (temp2.getAuthor().equals(""))&& (temp2.getPublisher().equals(""))&& (temp2.getYear().equals(""))){
                            getList.add(temp2);
                        }

                        else if((temp2.getTitle().equals(""))&& (temp2.getAuthor().equals(temp.getAuthor())) && (temp2.getPublisher().equals(""))&& (temp2.getYear().equals(""))){
                            getList.add(temp2);
                        }

                        else if((temp2.getTitle().equals("")) && (temp2.getAuthor().equals("")) && (temp2.getPublisher().equals(temp.getPublisher())) && (temp2.getYear().equals(""))){
                            getList.add(temp2);
                        }

                        else if((temp2.getTitle().equals("")) && (temp2.getAuthor().equals("")) && (temp2.getPublisher().equals("")) && (temp2.getYear().equals(temp.getYear()))){
                            getList.add(temp2);
                        }

                        else if((temp2.getTitle().equals(temp.getTitle())) && (temp2.getAuthor().equals(temp.getAuthor())) && (temp2.getPublisher().equals(""))&& (temp2.getYear().equals(""))){
                            getList.add(temp2);
                        }

                        else if((temp2.getTitle().equals(temp.getTitle())) && (temp2.getAuthor().equals("")) && (temp2.getPublisher().equals(temp.getPublisher()))&& (temp2.getYear().equals(""))){
                            getList.add(temp2);
                        }

                        else if((temp2.getTitle().equals(temp.getTitle())) && (temp2.getAuthor().equals("")) && (temp2.getPublisher().equals(""))&& (temp2.getYear().equals(temp.getYear()))){
                            getList.add(temp2);
                        }

                        else if((temp2.getTitle().equals("")) && (temp2.getAuthor().equals(temp.getAuthor())) && (temp2.getPublisher().equals(temp.getPublisher()))&& (temp2.getYear().equals(""))){
                            getList.add(temp2);
                        }

                        else if((temp2.getTitle().equals("")) && (temp2.getAuthor().equals(temp.getAuthor())) && (temp2.getPublisher().equals(""))&& (temp2.getYear().equals(temp.getYear()))){
                            getList.add(temp2);
                        }

                        else if((temp2.getTitle().equals("")) && (temp2.getAuthor().equals("")) && (temp2.getPublisher().equals(temp.getPublisher()))&& (temp2.getYear().equals(temp.getYear()))){
                            getList.add(temp2);
                        }

                        else if((temp2.getTitle().equals(temp.getTitle())) && (temp2.getAuthor().equals(temp.getAuthor())) && (temp2.getPublisher().equals(temp.getPublisher()))&& (temp2.getYear().equals(""))){
                            getList.add(temp2);
                        }

                        else if((temp2.getTitle().equals(temp.getTitle())) && (temp2.getAuthor().equals(temp.getAuthor())) && (temp2.getPublisher().equals(""))&& (temp2.getYear().equals(temp.getYear()))){
                            getList.add(temp2);
                        }

                        else if((temp2.getTitle().equals(temp.getTitle())) && (temp2.getAuthor().equals("")) && (temp2.getPublisher().equals(temp.getPublisher()))&& (temp2.getYear().equals(temp.getYear()))){
                            getList.add(temp2);
                        }

                        else if((temp2.getTitle().equals("")) && (temp2.getAuthor().equals(temp.getAuthor())) && (temp2.getPublisher().equals(temp.getPublisher()))&& (temp2.getYear().equals(temp.getYear()))){
                            getList.add(temp2);
                        }
                        else if((temp2.getTitle().equals(temp.getTitle())) && (temp2.getAuthor().equals(temp.getAuthor())) && (temp2.getPublisher().equals(temp.getPublisher()))&& (temp2.getYear().equals(temp.getYear()))){
                            getList.add(temp2);
                        }

                    }
                    
                    out.println(String.format("Search Results: %s", getList.size()));
                    for (int i = 0; i <  getList.size(); i++ ) {
                        printBook(getList.get(i), bibtexmode);
                    }
                }

                else if(dataArray[0].equals("REMOVE")){
                    Book temp = new Book();
                

                    temp.setISBN(dataArray[1]);
                    temp.setTitle(dataArray[2]);
                    temp.setAuthor(dataArray[3]);
                    temp.setPublisher(dataArray[4]);
                    temp.setYear(dataArray[5]);

                    int tempsize = bibliography.size();
                    for(int i =0;i < bibliography.size();i++ ){
                        Book temp2 = bibliography.get(i);
                        temp2.getISBN();
                        temp2.getTitle();
                        temp2.getAuthor();
                        temp2.getPublisher();
                        temp2.getYear();
                        if((temp2.getTitle().equals(temp.getTitle())) && (temp2.getAuthor().equals(""))&& (temp2.getPublisher().equals(""))&& (temp2.getYear().equals(""))){
                            bibliography.remove(i);
                        }

                        else if((temp2.getTitle().equals(""))&& (temp2.getAuthor().equals(temp.getAuthor())) && (temp2.getPublisher().equals(""))&& (temp2.getYear().equals(""))){
                            bibliography.remove(i);
                        }

                        else if((temp2.getTitle().equals("")) && (temp2.getAuthor().equals("")) && (temp2.getPublisher().equals(temp.getPublisher())) && (temp2.getYear().equals(""))){
                            bibliography.remove(i);
                        }

                        else if((temp2.getTitle().equals("")) && (temp2.getAuthor().equals("")) && (temp2.getPublisher().equals("")) && (temp2.getYear().equals(temp.getYear()))){
                            bibliography.remove(i);
                        }

                        else if((temp2.getTitle().equals(temp.getTitle())) && (temp2.getAuthor().equals(temp.getAuthor())) && (temp2.getPublisher().equals(""))&& (temp2.getYear().equals(""))){
                            bibliography.remove(i);
                        }

                        else if((temp2.getTitle().equals(temp.getTitle())) && (temp2.getAuthor().equals("")) && (temp2.getPublisher().equals(temp.getPublisher()))&& (temp2.getYear().equals(""))){
                            bibliography.remove(i);
                        }

                        else if((temp2.getTitle().equals(temp.getTitle())) && (temp2.getAuthor().equals("")) && (temp2.getPublisher().equals(""))&& (temp2.getYear().equals(temp.getYear()))){
                            bibliography.remove(i);
                        }

                        else if((temp2.getTitle().equals("")) && (temp2.getAuthor().equals(temp.getAuthor())) && (temp2.getPublisher().equals(temp.getPublisher()))&& (temp2.getYear().equals(""))){
                            bibliography.remove(i);
                        }

                        else if((temp2.getTitle().equals("")) && (temp2.getAuthor().equals(temp.getAuthor())) && (temp2.getPublisher().equals(""))&& (temp2.getYear().equals(temp.getYear()))){
                            bibliography.remove(i);
                        }

                        else if((temp2.getTitle().equals("")) && (temp2.getAuthor().equals("")) && (temp2.getPublisher().equals(temp.getPublisher()))&& (temp2.getYear().equals(temp.getYear()))){
                            bibliography.remove(i);
                        }

                        else if((temp2.getTitle().equals(temp.getTitle())) && (temp2.getAuthor().equals(temp.getAuthor())) && (temp2.getPublisher().equals(temp.getPublisher()))&& (temp2.getYear().equals(""))){
                            bibliography.remove(i);
                        }

                        else if((temp2.getTitle().equals(temp.getTitle())) && (temp2.getAuthor().equals(temp.getAuthor())) && (temp2.getPublisher().equals(""))&& (temp2.getYear().equals(temp.getYear()))){
                            bibliography.remove(i);
                        }

                        else if((temp2.getTitle().equals(temp.getTitle())) && (temp2.getAuthor().equals("")) && (temp2.getPublisher().equals(temp.getPublisher()))&& (temp2.getYear().equals(temp.getYear()))){
                            bibliography.remove(i);
                        }

                        else if((temp2.getTitle().equals("")) && (temp2.getAuthor().equals(temp.getAuthor())) && (temp2.getPublisher().equals(temp.getPublisher()))&& (temp2.getYear().equals(temp.getYear()))){
                            bibliography.remove(i);
                        }
                        else if((temp2.getTitle().equals(temp.getTitle())) && (temp2.getAuthor().equals(temp.getAuthor())) && (temp2.getPublisher().equals(temp.getPublisher()))&& (temp2.getYear().equals(temp.getYear()))){
                            bibliography.remove(i);
                        }

                    }
                    if (tempsize != bibliography.size()) {
                        out.println("Remove Success!");

                    } else {
                        out.println("Remove fail.");
                    }

                }

                else{
                    out.println("Please write a supported command");    
                }
    }
        out.close();
        in.close();
        socket.close();
    }

    void printBook(Book book, boolean bibtexmode) {
        if(bibtexmode) {
            out.println("Incomplete");
        } else {
            out.println("ISBN: " + book.getISBN());
            out.println("TITLE: " + book.getTitle());
            out.println("Author: " + book.getAuthor());
            out.println("Publisher: " + book.getPublisher());
            out.println("Year: " + book.getYear());
            out.println("");
        }

    }
        
	
    }




