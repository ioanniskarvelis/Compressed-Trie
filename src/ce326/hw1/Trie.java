package ce326.hw1;

public class Trie {
    TrieNode root = new TrieNode();
    
    public Trie(TrieNode initRoot){
        root = initRoot;
    }
    
    int removeWord(TrieNode root, String inputWord){
        StringBuilder word = new StringBuilder();
        word.insert(0, inputWord);
        
        int i = searchChild(word.toString());
        
        if(findWord(root, word.toString()) == 1){
            if(word.toString().matches(root.data[i].toString()) && hasChild(root.children[i]) == 0){
                root.children[i]=null;
                root.data[i].delete(0, root.data[i].length());
                root.data[i].insert(0, "");
                return 1;
            }
            if(word.toString().matches(root.data[i].toString()) && hasChild(root.children[i]) > 1){
                root.children[i].isTerminal = false;
                return 1;
            }
            if(word.toString().matches(root.data[i].toString())){
                int j;
                for(j=0; j<26; j++){
                    if(root.children[i].children[j] !=null){
                        break;
                    }
                }
                root.data[i].append(root.children[i].data[j]);
                root.children[i] = root.children[i].children[j];
                return 1;
            }
            if(!word.toString().matches(root.data[i].toString())){
                int j;
                if(word.toString().contains(root.data[i])){
                    j = root.data[i].length();
                    int k = searchChild(word.toString().substring(j));
                    if(word.toString().substring(j).matches(root.children[i].data[k].toString()) && hasChild(root.children[i]) == 2 && hasChild(root.children[i].children[k]) == 0){
                        root.children[i].children[k]=null;
                        root.children[i].data[k].delete(0, root.children[i].data[j].length());
                        root.children[i].data[k].insert(0, "");
                        for(j=0; j<26; j++){
                            if(root.children[i].children[j] !=null){
                            break;
                            }
                        }
                        root.data[i].append(root.children[i].data[j]);
                        root.children[i] = root.children[i].children[j];
                        return 1;
                    }
                }
                j = root.data[i].length();
                StringBuilder newWord = new StringBuilder("");
                newWord.insert(0, word.toString().substring(j));
                removeWord(root.children[i], newWord.toString());
                return 1;
    
            }
        }
        return 0;
    }
    
    void printPreorder(TrieNode root){
        if(hasChild(root) > 0){
            for(int i=0; i < 26; i++){
                if(!root.data[i].toString().isEmpty()){
                    System.out.print(root.data[i]);
                    if(root.children[i].isTerminal){
                        System.out.print("#");
                    }
                    System.out.print(" ");
                    printPreorder(root.children[i]);
                }
            }
        }
    }
    void dictionary(TrieNode root, StringBuilder word){
        if(hasChild(root) > 0){
            for(int i=0; i < 26; i++){
                if(!root.data[i].toString().isEmpty()){
                    int k = word.length();
                    word.append(root.data[i]);
                    if(root.children[i].isTerminal){
                        System.out.println(word);
                    }
                    dictionary(root.children[i], word);
                    word.delete(k, word.length());
                }
            }
        }
        
    }
    
    void distantWords(TrieNode root, String word, int numOfChars, StringBuilder printWord){
        if(hasChild(root) > 0 && numOfChars >= 0){
            for(int i=0; i < 26; i++){
                if(!root.data[i].toString().isEmpty() && word.length() >= root.data[i].length()){
                    int k = printWord.length();
                    printWord.append(root.data[i]);
                    numOfChars = numOfChars - NotMatchingChars(word, root.data[i].toString());
                    if(root.children[i].isTerminal && root.data[i].length() == word.length() && numOfChars == 0){
                        System.out.println(printWord);
                    }
                    distantWords(root.children[i], word.substring(root.data[i].length()), numOfChars, printWord);
                    printWord.delete(k, printWord.length());
                    numOfChars = numOfChars + NotMatchingChars(word, root.data[i].toString());
                }
            }
        }
    }
    void suffixWords(TrieNode root, String suffix, StringBuilder printWord){
        if(hasChild(root) > 0){
            for(int i=0; i < 26; i++){
                if(!root.data[i].toString().isEmpty()){
                    int k = printWord.length();
                    int j = suffix.length();
                    printWord.append(root.data[i]);
                    if(root.children[i].isTerminal && printWord.length()>= j){
                        if(suffix.matches(printWord.toString().substring(printWord.length()-j))){
                            System.out.println(printWord);
                        }
                    }
                    suffixWords(root.children[i], suffix, printWord);
                    printWord.delete(k, printWord.length());
                }
            }
        }
    }
    
    int findWord(TrieNode root, String inputWord){
        if(!inputWord.isEmpty()){
            int i = searchChild(inputWord);
            if(root.children[i] == null){
                return 0;
            }
            if(root.data[i].toString().matches(inputWord) && root.children[i].isTerminal){
                return 1;
            }
            if(root.data[i].length() > inputWord.length() && root.data[i].toString().contains(inputWord)){
                return 0;
            }
            if(root.data[i].length() < inputWord.length() && inputWord.contains(root.data[i].toString())){
                int j = root.data[i].length();
                StringBuilder prefix = new StringBuilder("");
                prefix.insert(0, inputWord.substring(j));
                return findWord(root.children[i], prefix.toString());
            }
        }
        return 0;
    }
    
    int insertWord(TrieNode root, String inputWord){

        StringBuilder word = new StringBuilder();
        word.insert(0, inputWord);
        
        TrieNode currNode = new TrieNode();
        
        currNode=root;
        int i = searchChild(word.toString());
        if(findWord(root, word.toString()) == 0){
            if(currNode.children[i] == null){
                TrieNode newNode = new TrieNode();
                currNode.data[i].insert(0, word);
                newNode.isTerminal = true;
                currNode.children[i] = newNode;
                return 1;
            }
            if(currNode.children[i] != null){
                if(word.toString().compareTo(currNode.data[i].toString()) > 0 && word.toString().contains(currNode.data[i].toString())){
                    if(word.toString().substring(0, currNode.data[i].length()).matches(currNode.data[i].toString())){
                        int j = currNode.data[i].length();
                        word.replace(0, word.length(), word.toString().substring(j));
                        int k = searchChild(word.toString());
                        insertWord(currNode.children[i], word.toString());
                        return 1;
                    }
                }
                if(word.toString().compareTo(currNode.data[i].toString()) < 0 && currNode.data[i].toString().contains(word)){
                    if(currNode.data[i].toString().substring(0, word.length()).matches(word.toString())){
                        int j = word.length();
                        word.replace(0, word.length(), currNode.data[i].toString().substring(j));
                        currNode.data[i].delete(j, currNode.data[i].length());
                        if(hasChild(currNode.children[i]) == 0){
                            insertWord(currNode.children[i], word.toString());
                            return 1;
                        }
                        else{
                            int k = searchChild(word.toString());
                            TrieNode newNode = new TrieNode();
                            TrieNode tempNode = new TrieNode();
                            tempNode = currNode.children[i];
                            currNode.children[i] = newNode;
                            currNode.children[i].data[k].insert(0, word);
                            currNode.children[i].children[k] = tempNode;
                            currNode.children[i].isTerminal = true;
                            return 1;
                        }
                    }
                }
                if(word.toString().compareTo(currNode.data[i].toString()) == 0 && currNode.children[i].isTerminal == false){
                    currNode.children[i].isTerminal = true;
                    return 1;
                }
                else if(hasChild(currNode.children[i]) == 0){
                    int j = firstUncommonChar(currNode.data[i].toString(), word.toString());
                    StringBuilder newWord = new StringBuilder("");
                    newWord.insert(0, currNode.data[i].toString().substring(j));
                    currNode.data[i].delete(j, currNode.data[i].length());
                    insertWord(currNode.children[i], newWord.toString());
                    insertWord(currNode.children[i], word.toString().substring(j));
                    currNode.children[i].isTerminal=false;
                    return 1;
                }
                else if (hasChild(currNode.children[i]) > 0){
                    int j = firstUncommonChar(currNode.data[i].toString(), word.toString());
                    StringBuilder newWord = new StringBuilder("");
                    newWord.insert(0, currNode.data[i].toString().substring(j));
                    currNode.data[i].delete(j, currNode.data[i].length());
                    TrieNode tempNode = new TrieNode();
                    TrieNode newNode = new TrieNode();
                    tempNode = currNode.children[i];
                    currNode.children[i]= newNode;
                    int k = searchChild(newWord.toString());
                    currNode.children[i].children[k] = tempNode;
                    currNode.children[i].data[k].insert(0, newWord.toString());
                    
                    word.replace(0, word.length(), word.toString().substring(j));
                    insertWord(currNode.children[i], word.toString());
                    return 1;
                }
            }
        }
        return 0;
    }
    
    int hasChild(TrieNode parent){
        int k = 0;
        for(int i=0; i < 26; i++){
            if(parent.children[i] != null){
                k++;
            }
        }
        return k;
    }
    
    int NotMatchingChars(String str1, String str2){
        int i=0, sum=0;
        while(i < str1.length() && i < str2.length()){
            if(str1.charAt(i) != str2.charAt(i)){
                sum++;
            }
            i++;
        }
        return sum;
    }
    
    int firstUncommonChar(String str1, String str2){
        int i = 0;
        while(str1.charAt(i) == str2.charAt(i)){
            i++;
        }
        return i;
    }
    
    int searchChild(String substring){
        char ch = substring.charAt(0);
        int indexNum = ch - 97;
        return indexNum;
    }
}
