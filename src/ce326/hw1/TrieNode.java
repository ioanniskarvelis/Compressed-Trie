package ce326.hw1;

public class TrieNode {
    TrieNode[] children = new TrieNode[26];
    StringBuilder[] data = new StringBuilder[26];
    boolean isTerminal;
    
    public TrieNode(){
        for(int i=0; i<data.length; i++){
            data[i] = new StringBuilder("");
            children[i] = null;
        }
        isTerminal=false;
    }
}
