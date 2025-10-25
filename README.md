## Compressed Trie

A small Java console app that implements a compressed trie and supports commands to insert, find, remove, print in preorder, list dictionary words, list words with a suffix, and list words within a Hamming-like distance from a given word. This mini project was part of the course 'Object-Oriented Programming' of my university school.

### Project structure
- `src/ce326/hw1/` — Sources (`HW1.java`, `Trie.java`, `TrieNode.java`)
- `build.xml` — NetBeans/Ant build script
- `dist/` — Generated jar (when built via NetBeans/Ant)

### Requirements
- Java 8+ (JDK)
- Optional: Apache Ant or NetBeans (project already contains `build.xml`)

### Build
1) Using Ant (or NetBeans Build):
```bash
ant clean jar
```
The jar is produced at `dist/homework1.jar`.

2) Using plain javac:
```bash
javac -d out src/ce326/hw1/*.java
jar --create --file dist/homework1.jar -C out .
```

### Run
1) From sources (no jar):
```bash
java -cp out ce326.hw1.HW1
```

2) From jar:
```bash
java -cp dist/homework1.jar ce326.hw1.HW1
```

The program reads commands from standard input and prints to standard output.

### CLI commands
- `-i <word>` — Insert word (prints `ADD <word> OK` or `NOK`)
- `-f <word>` — Find word (prints `FND <word> OK` or `NOK`)
- `-r <word>` — Remove word (prints `RMV <word> OK` or `NOK`)
- `-p` — Print preorder representation (uses `#` to mark terminal words)
- `-d` — Print dictionary (all words, one per line)
- `-s <suffix>` — Print words that end with `<suffix>`
- `-w <word> <k>` — Print words at distance `k` from `<word>`
- `-q` — Quit

Example session:
```text
-i small
-p
-i smallball
-d
-q
```

### Notes
- Input is treated in lowercase (`HW1` converts input to lowercase).
- The implementation is array-based with 26 children per node (a–z).

### Contributing
PRs welcome for documentation improvements or small fixes.

### License
MIT — see `LICENSE`.
