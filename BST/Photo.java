public class Photo {
 private String path;
 LinkedList<String> photoTags = new LinkedList<>();
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
public String getPath(){
 return path;
 }       
public LinkedList<String> getTags(){
LinkedList<String> tagsCopy = new LinkedList<String>();
            
if (! photoTags.empty()){          
photoTags.findFirst();
do {
tagsCopy.insert(photoTags.retrieve());
if(photoTags.last())
break;
photoTags.findNext();
} while (true); 
}
return tagsCopy;
}

public String toString() {
String result = "Photo{" + "path=" + path + ", photoTags=" ;
photoTags.findFirst();
while ( ! photoTags.last()) {
result += photoTags.retrieve().toString() + "; ";
photoTags.findNext();
}    
result += photoTags.retrieve().toString()  + "}";
return result ;
} }
