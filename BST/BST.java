public class BST<T> {
   public class BSTNode <T> {
      public String key;
      public T data;
      public BSTNode<T> left, right;
   
            //Creates a new instance of BSTNode 
      public BSTNode(String key, T val) {
         this.key = key;
         data = val;
         left = right = null;
      }
   
      public BSTNode(String key, T val, BSTNode<T> l, BSTNode<T> r) {
         this.key = key;
         data = val;
         left = l;
         right = r;
      }
   }

   BSTNode<T> root, current;
   String AllKeys;
        
        // Creates a new instance of BST 
   public BST() {
      root = current = null;
   }

   public boolean empty() {
      return root == null;
   }

   public boolean full() {
      return false;
   }

   public T retrieve () {
      return current.data;
   }

   public boolean findkey(String  tkey) {
      BSTNode<T> p = root;
      BSTNode<T> q = root;
   
      if(empty())
         return false;
   
      while(p != null) {
         q = p;
         if(p.key.compareToIgnoreCase(tkey) == 0) {
            current = p;
            return true;
         }
         else if(tkey.compareToIgnoreCase(p.key) < 0 )
            p = p.left;
         else
            p = p.right;
      }
      current = q;
      return false;
   }

   public boolean insert(String key, T val) {
      BSTNode<T> p;
      BSTNode<T> q = current;
   
      if(findkey(key)) {
         current = q;  // findkey() modified current
         return false; // key already in the BST
      }
   
      p = new BSTNode<T>(key, val);
      if (empty()) {
         root = current = p;
         return true;
      }
      else {
                        // current is pointing to parent of the new key
         if (key.compareToIgnoreCase(current.key) < 0)
            current.left = p;
         else
            current.right = p;
         current = p;
         return true;
      }
   }

              
   private BSTNode<T> find_min(BSTNode<T> p)
   {
      if(p == null)
         return null;
   
      while(p.left != null){
         p = p.left;
      }
   
      return p;
   }

       
        //Method removeKey: iterative  
   public boolean removeKey(String k) {
   // Search for k
      BSTNode<T> p = root;
      BSTNode<T> q = null; // Parent of p
      boolean found=false;
      while ((p != null)&&(!found)) {
         int res = k.compareToIgnoreCase(p.key);
         if (res < 0) {
            q = p;
            p = p.left;
         } else if (res > 0) {
            q = p;
            p = p.right;
         } else // Found the key
            found=true;
      }
      if(found){
      // Check the three cases
         if ((p.left != null) && (p.right != null)) { //
         ///Case 3: two children
         // Search for the min in the right subtree
            BSTNode<T> min = p.right;
            q = p;
            while (min.left != null) {
               q = min;
               min = min.left;
            }
            p.key = min.key;
            p.data = min.data;
         }
         else //case 1 and 2
         
            current = root;
         return true;
      }
      return false; // Not found 
   }
   public String inOrder ()
   {
      AllKeys = "" ;
      if ( root != null)
         inorder( root );
      return AllKeys;
   
   }

   private void inorder(BSTNode<T> p )
   {
      if ( p.left !=null)
         inorder ( p.left);
      if (AllKeys == "")
         AllKeys = p.key;
      else
         AllKeys += " AND " + p.key ;
      if ( p.right !=null)
         inorder ( p.right);
   }
   public boolean update(String key, T data) {
      if (findkey(key)) { 
         removeKey(key);
      }
      return insert(key, data);
   }
   
}