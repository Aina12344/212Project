public class Album {
   private String name;
   private String condition;
   private PhotoManager manager;
   private int NbComps;

   // Constructor
   public Album(String name, String condition, PhotoManager manager) {
      this.name = name;
      this.condition = condition;
      this.manager = manager;
      NbComps = 0;
   }

   // Return the name of the album
   public String getName() {
      return name;
   }

   // Return the condition with the album
   public String getCondition() {
      return condition;
   }

   // Return the manager
   public PhotoManager getManager() {
      return manager;
   }
  //Return the number of tag comparisons used to find all photos of the album
   public int getNbComps() {
      return NbComps;
   }

   // Return all photos that apply the album condition
   public LinkedList<Photo> getPhotos() {
      LinkedList<Photo> photos1 = manager.getPhotos();
      LinkedList<Photo> photos2 = new LinkedList<Photo>();
   
      if (!photos1.empty()) {
         photos1.findFirst();
         while (true) {
            photos2.insert(new Photo(photos1.retrieve().getPath(), photos1.retrieve().getTags()));
            if (photos1.last()) {
               break;
            }
            photos1.findNext();
         }
      }
      NbComps = 0;
      if (!condition.equals("")) {
         String[] requiredTags = condition.split("AND");
      
         if (!photos2.empty()) {
            photos2.findFirst();
            while (true) {
               Photo photo = photos2.retrieve();
               if (!allAvailable(photo.getTags(), requiredTags)) {
                  photos2.remove();
                  if (photos2.empty()) {
                     break;
                  }
               } else {
                  if (photos2.last()) {
                     break;
                  }
                  photos2.findNext();
               }
            }
         }
      }
   
      return photos2;
   }

   private boolean allAvailable(LinkedList<String> AllTags, String[] arr) {
   
      boolean contin = true;
   
      if (AllTags.empty()) {
         contin = false;
      }
      else {
         for (int i = 0; i < arr.length && contin; i++) {
            boolean tagMatched = false;
            AllTags.findFirst();
            while (!AllTags.last()) {
            
               this.NbComps++;
               if (AllTags.retrieve().compareToIgnoreCase(arr[i]) == 0) {
                  tagMatched = true;
                  break;
               }
               if (AllTags.last()) {
                  break;
               }
               AllTags.findNext();
            }
            if (!tagMatched) {
               contin = false;
            }
         }
      }
      return contin;
   }
}