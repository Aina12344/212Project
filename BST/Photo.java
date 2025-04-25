public class Photo {
 private String path;
 LinkedList<String> photoTags = new LinkedList<>();
 //costructor
    public Photo(String path, LinkedList<String> tags){
 this.path = path;     
 if (! tags.empty()) {
  tags.findFirst();
do {                     
photoTags.insert(tags.retrieve());
if (tags.last())
break;
tags.findNext();
} while (true);
 }
} 
 // Return the full file name (the path) of the photo. A photo is uniquely identified by its path.       
 public String getPath(){
 return path;
 }  
  // Return all tags associated with the photo     
public LinkedList<String> getTags(){

            
return photoTags;
}

public String toString() {
String str = "Photo{" + "path=" + path + ", photoTags=" ;
photoTags.findFirst();
while ( ! photoTags.last()) {
str += photoTags.retrieve().toString() + "; ";
photoTags.findNext();
}    
str += photoTags.retrieve().toString()  + "}";
return str ;
} }
