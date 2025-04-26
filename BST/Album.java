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

    // Return all photos that apply the album condition
    public LinkedList<Photo> getPhotos() {
        LinkedList<Photo> photos1 = manager.getPhotos();
        LinkedList<Photo> photos2 = new LinkedList<Photo>();

        if (!photos1.empty()) {
            photos1.findFirst();
            while (!photos1.last()) {
                photos2.insert(new Photo(photos1.retrieve().getPath(), photos1.retrieve().getTags()));
                photos1.findNext();
            }
            photos2.insert(new Photo(photos1.retrieve().getPath(), photos1.retrieve().getTags()));
        }

        NbComps = 0;
        if (this.condition.compareTo("") != 0) {
            String[] Array = condition.split("AND");
            photos2.findFirst();
            while (!photos2.last()) {
                Photo photo = photos2.retrieve();
                if (!allAvailable(photo.getTags(), Array)) {
                    photos2.remove();
                } else {
                    photos2.findNext();
                }
            }

            //Handle last photo alone
            Photo lastPhoto = photos2.retrieve();
            if (!allAvailable(lastPhoto.getTags(), Array)) {
                photos2.remove();
            } else {
                photos2.findNext();
            }
        }
        return photos2;
    }

    //Return the number of tag comparisons used to find all photos of the album
    public int getNbComps() {
        return NbComps;
    }

    private boolean allAvailable(LinkedList<String> AllTags, String[] Arr) {

        boolean contin = true;

        if (AllTags.empty()) {
            contin = false;
        }
        else {
            for (int i = 0; i < Arr.length && contin; i++) {
                boolean tagMatched = false;
                AllTags.findFirst();
                while (!AllTags.last()) {

                    this.NbComps++;
                    if (AllTags.retrieve().compareToIgnoreCase(Arr[i]) == 0) {
                        tagMatched = true;
                        break;
                    }
                    AllTags.findNext();
                }

                //Checke the last tag
                if (!tagMatched) {
                    this.NbComps++;
                    if (AllTags.retrieve().compareToIgnoreCase(Arr[i]) == 0) {
                        tagMatched = true;
                    }
                }

                if (!tagMatched) {
                    contin = false;
                }
            }
        }
        return contin;
    }
}
