public class InvAlbum {
        private String name;
        private String condition;
        private InvIndexPhotoManager invmanager;
        private int NbComps;

        public InvAlbum(String name, String condition, InvIndexPhotoManager manager)
        {
            this.name = name;
            this.condition = condition;
            this.invmanager = manager;
            NbComps =0 ;
        }
        
        public String getName()
        {
            return name;
        }
        
        public String getCondition()
        {
            return condition;
        }

        public InvIndexPhotoManager getManager()
        {
            return invmanager;
        }
        
        public LinkedList<Photo> getPhotos()
        {
            BST<LinkedList<Photo>> photosBST = invmanager.getPhotos();
            LinkedList<Photo> Cphotos = new LinkedList<Photo>();
            NbComps =0 ;
            String [] Tag;
            
            if (this.condition.compareTo("") != 0)
                Tag = condition.split(" AND ");
            else
                Tag = photosBST.inOrder().split(" AND ");

            for ( int i = 0 ; i < Tag.length ; i++)
            {    
                if ( photosBST.findkey(Tag[i]) == true)
        {
                    if (i == 0)
                    {
                        LinkedList<Photo > MinTag = photosBST.retrieve();
                        MinTag.findFirst();
                        while ( ! MinTag.last())  
              {
                            Cphotos.insert(MinTag.retrieve());
                            MinTag.findNext(); }   
                        Cphotos.insert(MinTag.retrieve());
                    }
                    else
                    {
                        if (condition.compareToIgnoreCase("") != 0 )
                             Cphotos  = Fun1 ( Cphotos , photosBST.retrieve());
                        else
                            Cphotos  = Fun2 ( Cphotos , photosBST.retrieve());    
                    }
                }
                else
                {
                    Cphotos = new LinkedList<Photo>();
                    break;
                }
            }
            return Cphotos;
        }
       
        public int getNbComps()
        {
            return NbComps;
        }

        private LinkedList<Photo> Fun1 ( LinkedList<Photo> L1 ,LinkedList<Photo> L2)
        {
            LinkedList<Photo> result = new LinkedList<Photo>();
            
            if (! L2.empty())
            {
                L2.findFirst();
                while (! L2.last())
                {
                    if (! L1.empty())
                    {
                        boolean exist = false;
                        L1.findFirst();
                        while (! L1.last() && ! exist)
                        {
                            if (L2.retrieve().getPath().compareToIgnoreCase(L1.retrieve().getPath()) == 0)
                            {
                                NbComps++;
                                exist = true;
                            }
                            L1.findNext();
                        }
                        if (! exist && L2.retrieve().getPath().compareToIgnoreCase(L1.retrieve().getPath()) == 0)
                        {
                            NbComps++;
                            exist = true;
                        }
                        if (exist )
                            result.insert(L2.retrieve());
                            
                    }
                    L2.findNext();
                }
                
                boolean exist = false;
                L1.findFirst();
                while (! L1.last() && ! exist)
                {
                    if (L2.retrieve().getPath().compareToIgnoreCase(L1.retrieve().getPath()) == 0)
                    {
                        NbComps++;
                        exist = true;
                    }
                    L1.findNext();
                }
                if (! exist && L2.retrieve().getPath().compareToIgnoreCase(L1.retrieve().getPath()) == 0)
                {
                    NbComps++;
                    exist = true;
                }
                if (exist )
                    result.insert(L2.retrieve());
                                  
            }
            return result;
        }
        private LinkedList<Photo> Fun2 ( LinkedList<Photo> L1 ,LinkedList<Photo> L2)
        {
            if (! L2.empty())
            {
                L2.findFirst();
                while (! L2.last())
                {
                    if (! L1.empty())
                    {
                        boolean exist = false;
                        L1.findFirst();
                        while (! L1.last() && ! exist)
                        {
                            if (L2.retrieve().getPath().compareToIgnoreCase(L1.retrieve().getPath()) == 0)
                            {
                                NbComps++;
                                exist = true;
                            }
                            L1.findNext();
                        }
                        if (! exist && L2.retrieve().getPath().compareToIgnoreCase(L1.retrieve().getPath()) == 0)
                        {
                            NbComps++;
                            exist = true;
                        }
                        if (!exist )
                            L1.insert(L2.retrieve());
                            
                    }
                    L2.findNext();
                }
                
                boolean exist = false;
                L1.findFirst();
                while (! L1.last() && ! exist)
                {
                    if (L2.retrieve().getPath().compareToIgnoreCase(L1.retrieve().getPath()) == 0)
                    {
                        NbComps++;
                        exist = true;
                    }
                    L1.findNext();
                }
                if (! exist && L2.retrieve().getPath().compareToIgnoreCase(L1.retrieve().getPath()) == 0)
                {
                    NbComps++;
                    exist = true;
                }
                if (!exist )
                    L1.insert(L2.retrieve());
                                  
            }
            return L1;
        }

        
       
}

