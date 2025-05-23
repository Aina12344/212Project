public class Test {
   public static void main(String[] args) {
    
      InvIndexPhotoManager manager = new InvIndexPhotoManager();
        
      Photo photo1 = new Photo("hedgehog.jpg",toTagsLinkedList("animal, hedgehog, apple, grass, green"));
      manager.addPhoto(photo1);
        
      Photo photo2 = new Photo("bear.jpg",toTagsLinkedList("animal, bear, cab, grass, wind"));
      manager.addPhoto(photo2);
        
      Photo photo3 = new Photo("orange-butterfly.jpg",toTagsLinkedList("insect, butterfly, flower, color"));
      manager.addPhoto(photo3);
        
      InvAlbum album1 = new InvAlbum("Album1", "bear", manager);
      InvAlbum album2 = new InvAlbum("Album2", "animal AND grass", manager);
      InvAlbum album3 = new InvAlbum("Album3", "", manager);
        
        
      System.out.println("Get photo1 path and tags:");
      System.out.println("photo1 path: " + photo1.getPath());
        
      //get the list of tags of photo1 by calling photo1.getTags().
      LinkedList<String> tags = photo1.getTags();
        
      //write a method that prints the list of tags of photo1.
      System.out.println(photo1.getPath());
      PrintTags(tags);
        
      System.out.println("\n\nGet album1 name, condition, and photos:");
      System.out.println("album1 name: " + album1.getName());
      System.out.println("album1 condition: " + album1.getCondition());
        
      //get the list of photos in album2 by calling album2.getPhotos().
      LinkedList<Photo> photos = album1.getPhotos();
        
      //write a method that prints the list of photos in album2.
      System.out.println(album1.getCondition());
      PrintPhotos(photos);
             
      System.out.println("\n\nGet album2 name, condition, and photos:");
      System.out.println("album2 name: " + album2.getName());
      System.out.println("album2 condition: " + album2.getCondition());
        
     //get the list of photos in album2 by calling album2.getPhotos().
      photos = album2.getPhotos();
      
    //write a method that prints the list of photos in album2.
      System.out.println(album2.getCondition());
      PrintPhotos(photos);
      
      System.out.println("\n\nGet album3 name, condition, and photos:");
      System.out.println("album3 name: " + album3.getName());
      System.out.println("album3 condition: " + album3.getCondition());
   //get the list of photos in album2 by calling album2.getPhotos().
      photos = album3.getPhotos();
      
   //write a method that prints the list of photos in album2.
      System.out.println(album3.getCondition());
      PrintPhotos(photos);
        
      System.out.println("Delete the photo �bear.jpg�:");
      manager.deletePhoto("bear.jpg");
   }
    
      
   private static LinkedList<String> toTagsLinkedList(String tags) {
      LinkedList<String> result = new LinkedList<String>();
      String[] tagsArray = tags.split("\\s*,\\s*");
      for (int i = 0; i < tagsArray.length; i++) {
         result.insert(tagsArray[i]);
      }
      return result;
   }
   

     
 //For LinkedList<String>
   private static void PrintTags(LinkedList<String> list){
      if (list.empty()) 
         return;
   
      list.findFirst();
      while (!list.last()) {
         System.out.print(list.retrieve() + " ");
         list.findNext();
      }
    // Print the last element
      System.out.print(list.retrieve());
   }   

//For LinkedList<Photo>
   private static void PrintPhotos(LinkedList<Photo> list){
      if (list.empty()) 
         return;
   
      list.findFirst();
      while (!list.last()) {
         System.out.println(list.retrieve().getPath());
         list.findNext();
      }
    //Print the last photo path
      System.out.println(list.retrieve().getPath());
   } 
}