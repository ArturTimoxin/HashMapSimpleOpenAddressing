public class HashMap_Simple_OpenAddressing {
    private DataItem hashArray[]; // Array of hash table cells
    private int count = 0; //Counter of initialized elements of a hash table (method size)
    public int arraySize = 37; // Default value (The number is prime that is suitable for quadratic probing)

    HashMap_Simple_OpenAddressing() { hashArray = new DataItem[arraySize]; } // Default constructor

    private boolean isPrime(int number){ //Check: whether the given number is prime ?
        for(int i = 2; (i * i <= number); i++)
            if (number % i == 0)
                return false;
        return true;
    }
    //You can also set the size of the table, but its size must be a prime number
    HashMap_Simple_OpenAddressing(int capacity) {
        if (isPrime(capacity)) { hashArray = new DataItem[capacity]; arraySize = capacity; } // Yes. Create Hash Table
                else { System.out.println("The size of the hash table must be a prime number!"); } // No
        }

    private int hashFunc(int key) { return (key % arraySize); }//Method of hashing

    private int find(int key){ // Search for item with the given key
        int i=1;
        int hashVal = hashFunc(key);
        while (hashArray[hashVal] != null) { // Until an empty cell is found
            if (hashArray[hashVal].getKey() == key) return hashVal;
            //Quadratic probing
            hashVal += i*i; // Add the step
            hashVal %= arraySize; //For wraparound
            i++;
        }
        return -1; //Can't find item
    }

    //when a hash table is filled with more than 50%, the elements are not added to it.
    private boolean getLoadFactor() { return (((float) count/ (float) arraySize) < 0.5); }

    public void put(int key, long value) {
        int i=1;
        if (getLoadFactor()) { //Check: Is the hash table filled with more than 60%?
            DataItem data = new DataItem(key, value); // Create data object
            int hashKey = hashFunc(key);
            int existingKey = find(key); //Check: is the cell empty?
            if (existingKey != -1) {
                hashArray[existingKey] = data; //Yes
            } else {
                while (hashArray[hashKey] != null) {  //No, find empty cell
                    hashKey += i*i;
                    hashKey %= arraySize;
                    i++;
                }
                hashArray[hashKey] = data; // Insert data in empty cell
                count++;
            }
        } else { //The hash table filled with more than 60%
            System.out.println("The HashMap is overfull.");
        }
    }

    public Long get(int key) { // Method returns Long, because of possibility to return null
        int i = 1;
        int hashKey = hashFunc(key);
        while (hashArray[hashKey] != null) { //Searching of key in HashMap
            if (hashArray[hashKey].getKey() == key) {
                return hashArray[hashKey].getValue();
            }
            hashKey += i*i;
            hashKey %= arraySize;
            i++;
        }
        return null; // Key is not found
    }

    public int size(){
        return count; // Size is number of initialized cells
    }
}
