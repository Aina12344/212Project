public class PhotoManager {

   LinkedList<Photo>PhotosList;
 
  // Constructor
   public PhotoManager() {
      PhotosList = new LinkedList<Photo>();
   }
  
  // Return all managed photos
   public LinkedList<Photo> getPhotos(){
      return PhotosList;
   }
  
  // Checking photo Availability
   private boolean PhotoAvailable(String path , LinkedList<Photo>PhotoList) {
      if(PhotoList.empty()) 
         return false;
      PhotoList.findFirst();
      while(!PhotoList.last()) {
         if(PhotoList.retrieve().getPath().compareToIgnoreCase(path)==0)
            return true;
         PhotoList.findNext();
      }
      if(PhotoList.retrieve().getPath().compareToIgnoreCase(path)==0) 
         return true;
      return false ;  
   
   }
  
  
  // Add a photo
   public void addPhoto(Photo p) {
      if(!PhotoAvailable(p.getPath(),PhotosList)) 
         PhotosList.insert(p);
   }
   
   
  // Delete a photo
   public void deletePhoto(String path) {
      if(!PhotoAvailable(path,PhotosList))
         return;
      if(!PhotosList.empty()) {
         boolean exist = false;
         PhotosList.findFirst();
         while(!exist&&!PhotosList.last()) {
            Photo photo = PhotosList.retrieve();
            if(photo.getPath().compareToIgnoreCase(path)==0) {
               exist=true;
               PhotosList.remove();
            }
            PhotosList.findNext();
         }
      //For the last photo
         if(!exist) {
            Photo photo = PhotosList.retrieve();
            if(photo.getPath().compareToIgnoreCase(path)==0) {
               exist = true ;
               PhotosList.remove();
            }
         
         }
      }
   }

  
}