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

   public boolean update(String key, T data) {
      if (findkey(key)) { 
         removeKey(key);
      }
      return insert(key, data);
   }
   
// Removes a node with key K
   public boolean removeKey(String k) {
      BSTNode<T> p = root;   // Start searching from the root
      BSTNode<T> q = null;   // q will track the parent of p
   
      while (p != null) {    // Traverse until p becomes null
         if (k.compareToIgnoreCase(p.key) < 0) {
            // If the key to remove is smaller, go left
            q = p;
            p = p.left;
         } else if (k.compareToIgnoreCase(p.key) > 0) {
            // If the key to remove is greater, go right
            q = p;
            p = p.right;
         } else {
            // Found the node to delete
         
            // Case 3: Node has two children
            if (p.left != null && p.right != null) {
                // Find the minimum node in the right subtree
               BSTNode<T> min = find_min(p.right);
            
                // Copy min's key and data to current node
               String minKey = min.key;
               T minData = min.data;
            
                // Recursively remove the minimum node
               removeKey(min.key);
            
                // Update p's key and data
               p.key = minKey;
               p.data = minData;
            }
            // Case 1 or Case 2: Node has only one child or no child
            else {
               BSTNode<T> child;
               if (p.left != null) {
                    // Only left child exists
                  child = p.left;
               } else {
                    // Only right child exists or no child (child = null)
                  child = p.right;
               }
            
               if (q == null) {
                    // Special case: deleting the root
                  root = child;
               } else {
                    // Set the parent's link to bypass p
                  if (p == q.left) {
                     q.left = child;
                  } else {
                     q.right = child;
                  }
               }
            }
            return true; // Node deleted successfully
         }
      }
   
      return false; // Key not found
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
}
