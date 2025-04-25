public class InvIndexPhotoManager {
        BST<LinkedList<Photo>> TagIndex;
        
        // Constructor
        public InvIndexPhotoManager()
        {
            TagIndex = new BST<LinkedList<Photo>>();
        }
        
        // Add a photo
        public void addPhoto(Photo p)
        {
        LinkedList<String> tags = p.getTags();
            if (!tags.empty()) {
        tags.findFirst();
        do {
            if (TagIndex.findkey(tags.retrieve()) == true) {
                LinkedList<Photo> InvPhotos = TagIndex.retrieve();
                InvPhotos.insert(p);
                TagIndex.update(tags.retrieve(), InvPhotos);
            }
            //new photo new tag
             else {
                LinkedList<Photo> InvPhotos = new LinkedList<Photo>();
               InvPhotos.insert(p);
                TagIndex.insert(tags.retrieve(), InvPhotos);
            }

            tags.findNext();
        } while (!tags.last());
    }
}        
        // Delete a photo
        public void deletePhoto(String path)
        {
      String TagLine = TagIndex.inOrder();
     String[] tags = TagLine.split(" AND ");
           
    for (int i = 0; i < tags.length; i++) {
        if (TagIndex.findkey(tags[i])) {
            LinkedList<Photo> InvPhotos = TagIndex.retrieve();

            InvPhotos.findFirst();
            boolean removed = false;

            do {
                if (InvPhotos.retrieve().getPath().equalsIgnoreCase(path)) {
                   InvPhotos.remove();
                    removed = true;
                    break;
                }
               InvPhotos.findNext();
            } while (!InvPhotos.last());

            // if picture was last item
                        if (!removed && InvPhotos.retrieve().getPath().equalsIgnoreCase(path)) {
                InvPhotos.remove();
            }

            // update the index or delete the tag if it empty
                        if (InvPhotos.getSize() == 0) {
                TagIndex.removeKey(tags[i]);
            } else {
                TagIndex.update(tags[i], InvPhotos);
            }
        }
    }
}        // Return the inverted index of all managed photos
        public BST<LinkedList<Photo>> getPhotos()
        {
            return TagIndex;
        }
 }