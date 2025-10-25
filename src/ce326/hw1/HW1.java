package ce326.hw1;
import java.util.Scanner;
public class HW1 {
    
    public static void main(String[] args){
        
        Trie compTrie;
        TrieNode compTrieRoot = new TrieNode();
        
        compTrie = new Trie(compTrieRoot);
        String instr= "";
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("?: ");
            int res;
            
            instr = sc.nextLine();
            StringBuilder word = new StringBuilder("");
            StringBuilder printWord = new StringBuilder("");
            switch(instr.charAt(1)){
                
                case 'p':
                    System.out.print("PreOrder: ");
                    compTrie.printPreorder(compTrieRoot);
                    System.out.println("");
                    break;
                case 'd':
                    System.out.print("\n***** Dictionary *****\n");
                    compTrie.dictionary(compTrieRoot, word);
                    System.out.println();
                    break;
                case 'w':
                    int distance=Character.getNumericValue(instr.charAt(instr.length()-1));
                    System.out.println();
                    System.out.print("Distant words of " + instr.substring(3, instr.length()-2).toLowerCase());
                    System.out.println(" (" + distance + "):");
                    compTrie.distantWords(compTrieRoot, instr.substring(3, instr.length()-2).toLowerCase(), distance, printWord);
                    System.out.println();
                    printWord.delete(0, printWord.length());
                    break;
                case 's':
                    System.out.println();
                    System.out.println("Words with suffix " + instr.substring(3).toLowerCase() + ":");
                    compTrie.suffixWords(compTrieRoot, instr.substring(3).toLowerCase(), printWord);
                    System.out.println();
                    printWord.delete(0, printWord.length());
                    break;
                case 'i':
                    res = compTrie.insertWord(compTrieRoot, instr.substring(3).toLowerCase());
                    if(res == 1){
                        System.out.println("ADD " + instr.substring(3).toLowerCase() + " OK");
                    }
                    else{
                        System.out.println("ADD " + instr.substring(3).toLowerCase() + " NOK");
                    }
                    break;
                case 'f':
                    compTrie.findWord(compTrieRoot, instr.substring(3).toLowerCase());
                    res = compTrie.findWord(compTrieRoot, instr.substring(3).toLowerCase());
                    if(res == 1){
                        System.out.println("FND " + instr.substring(3).toLowerCase() + " OK");
                    }
                    else{
                        System.out.println("FND " + instr.substring(3).toLowerCase() + " NOK");
                    }
                    break;
                case 'r':
                    res = compTrie.removeWord(compTrieRoot, instr.substring(3).toLowerCase());
                    if(res == 1){
                        System.out.println("RMV " + instr.substring(3).toLowerCase() + " OK");
                    }
                    else{
                        System.out.println("RMV " + instr.substring(3).toLowerCase() + " NOK");
                    }
                    break;
                case 'q':
                    System.out.println("Bye bye!");
                    System.exit(0);
            }
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
