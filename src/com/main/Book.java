package com.main;
import java.time.Year;
import static com.validator.StringValidator.validateString;

public class Book {
//	 Instance Variables
	 private String isbn;
	 private String title;
	 private String author;
     private Year publicationYear;
     
//   Constructor
     public Book(String isbn, String title, String author, Year publicationYear) {
    	 validateRequiredAttributes(isbn, title, author, publicationYear);
         this.isbn = isbn;
         this.title = title;
         this.author = author;
         this.publicationYear = publicationYear;
     }
     
//   Methods
     
//   To check whether the book details are NULL or not  
     private void validateRequiredAttributes(String isbn, String title, String author, Year publicationYear) {
         validateString(isbn, "ISBN should not be null");
         validateString(title, "title should not be null");
         validateString(author, "author should not be null");
         if(publicationYear == null){
             throw new IllegalArgumentException("publication year should not be null");
         }
     }
     
//   To retrieve the isbn of book object  
     public String getIsbn() {
         return isbn;
     }
}
