import java.io.BufferedReader; // similar to fstream
import java.io.FileReader; // similar to ifstream, reads files
import java.io.IOException; // exception handler for i/o stuff
import java.util.HashMap; // helps us implement hash table
import java.util.Map; // lets us implement a map
public class FindToken {
    private Map<String, Integer> keywords;  // Map that stores KEYWORDS and their token
    // types (token types are denoted by integers)
    HashMap<Integer, String> ruleMap;       // map that has rules that are named differently from what the actual keyword is
    Map<String, Integer> singleCharacterTokens;
    Map<String, Integer> integerTokens;
    Map<Character, Integer> letterTokens;
    Map<String, Integer> whiteSpace;


    public FindToken(){             // constructor for map that contains KEYWORDS
        keywords = new HashMap<>(); // initialize keywords as a hashmap
        ruleMap = new HashMap<>();
        singleCharacterTokens = new HashMap<>();
        integerTokens = new HashMap<>();
        letterTokens = new HashMap<>();
        whiteSpace = new HashMap<>();
        initKeywords();             // initialize the keywords and their tokens into the map
        initRules();
        initSingles();
        initIntegers();
        initLetters();
        initWhiteSpace();
    }

    private void initSingles(){
        // Add single-character tokens to the HashMap
        singleCharacterTokens.put(":", 51); // Check
        singleCharacterTokens.put("[", 53); // Check
        singleCharacterTokens.put("]", 54); // Check
        singleCharacterTokens.put("(", 55); // Check
        singleCharacterTokens.put(")", 56); // Check
    }

    private void initRules() {     // initializes map that contains different rule names
        ruleMap.put(5, "ELSE_IF"); // check
        ruleMap.put(16, "NATIVE"); // check
        ruleMap.put(17, "INHERITS"); // check
        ruleMap.put(23, "PACKAGE_NAME"); // check
        ruleMap.put(34, "COLON"); // check
        ruleMap.put(35, "INTEGER_KEYWORD"); // check
        ruleMap.put(36, "NUMBER_KEYWORD"); // check
        ruleMap.put(37, "TEXT"); // check
        ruleMap.put(38, "BOOLEAN_KEYWORD"); // check
        ruleMap.put(40, "NOTEQUALS"); // check
        ruleMap.put(41, "PERIOD"); // Check
        ruleMap.put(42, "COMMA"); // Check
        ruleMap.put(43, "EQUALITY"); // Check
        ruleMap.put(44, "GREATER"); // Check
        ruleMap.put(45, "GREATER_EQUAL"); // Check
        ruleMap.put(46, "LESS"); // Check
        ruleMap.put(47, "LESS_EQUAL"); // Check
        ruleMap.put(48, "PLUS"); // Check
        ruleMap.put(49, "MINUS"); // Check
        ruleMap.put(50, "MULTIPLY"); // Check
        ruleMap.put(51, "DIVIDE"); // Check
        ruleMap.put(52, "MODULO"); // Check
        ruleMap.put(53, "LEFT_SQR_BRACE"); // Check
        ruleMap.put(54, "RIGHT_SQR_BRACE"); // Check
        ruleMap.put(55, "LEFT_PAREN"); // Check
        ruleMap.put(56, "RIGHT_PAREN"); // Check
        ruleMap.put(57, "DOUBLE_QUOTE"); // Check
        ruleMap.put(61, "BOOLEAN");
        ruleMap.put(100, "INT");
    }

    private void initKeywords(){     // initializes the keyword tokens
        keywords.put("output", 1);    // keyword has been stored into map (e.g. "class" is the value, 1 is the type integer)
        keywords.put("on", 2);
        keywords.put("create", 3);
        keywords.put("constant", 4);
        keywords.put("elseif", 5); // check
        keywords.put("me", 6);
        keywords.put("until", 7);
        keywords.put("public", 8);
        keywords.put("private", 9);
        keywords.put("alert", 10);
        keywords.put("detect", 11);
        keywords.put("always", 12);
        keywords.put("check", 13);
        keywords.put("parent", 14);
        keywords.put("blueprint", 15);
        keywords.put("system", 16); //check
        keywords.put("is", 17); // check
        keywords.put("cast", 18);
        keywords.put("input", 19);
        keywords.put("say", 20);
        keywords.put("now", 21);
        keywords.put("while", 22);
        keywords.put("package", 23); // check
        keywords.put("times", 24);
        keywords.put("repeat", 25);
        keywords.put("else", 26);
        keywords.put("returns", 27);
        keywords.put("return", 28);
        keywords.put("and", 29);
        keywords.put("or", 30);
        keywords.put("undefined", 31);
        keywords.put("shared", 32);
        keywords.put("action", 33);
        keywords.put(":", 34); // check
        keywords.put("integer", 35);
        keywords.put("number", 36);
        keywords.put("text", 37);
        keywords.put("boolean", 38);
        keywords.put("not", 39);
        keywords.put("Not", 39);
        keywords.put("not=", 40); // check
        keywords.put("Not=", 40); // check
        keywords.put(".", 41);    // Check
        keywords.put(",", 42);    // Check
        keywords.put("=", 43);    // Check
        keywords.put(">", 44);    // Check
        keywords.put(">=", 45);   // Check
        keywords.put("<", 46);    // Check
        keywords.put("<=", 47);   // Check
        keywords.put("+", 48);    // Check
        keywords.put("-", 49);    // Check
        keywords.put("*", 50);    // Check
        keywords.put("/", 51);    // Check
        keywords.put("mod", 52);  // Check
        keywords.put("[", 53);    // Check
        keywords.put("]", 54);    // Check
        keywords.put("(", 55);    // Check
        keywords.put(")", 56);    // Check
        // keywords.put("\"", 57);   // Check
        keywords.put("if", 58);
        keywords.put("end", 59);
        keywords.put("class", 60);
        keywords.put("true", 61);  // Check
        keywords.put("false", 61); // Check (shared with "true")
        keywords.put("use", 62);
    }

    private void initIntegers(){
        // Add individual digits to represent the '0'..'9'+ rule
        for (int i = 0; i <= 9; i++) {
            integerTokens.put(String.valueOf(i), 100);  // check
        }
    }

    private void initLetters(){
        // add single-character tokens to the HashMap
        // define a string with lowercase and uppercase letters
        String letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        // assign integer values to each letter and add to the HashMap
        int value = 1;
        for (char letter : letters.toCharArray()) {
            letterTokens.put(letter, value);
            value++;
        }
    }

    private void initWhiteSpace(){
        whiteSpace.put(" ", 51); // Check
        whiteSpace.put("\t", 53); // Check
        whiteSpace.put("\n", 54); // Check
        whiteSpace.put("\r", 55); // Check
    }

    public void processFile(String fileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));   // reader that reads in file
            int data;                                                               // int var to see when end of file has been reached
            StringBuilder currentWord = new StringBuilder();                        // StringBuilder to add chars to the string

            while ((data = reader.read()) != -1) {                                  // loop until end of file
                char character = (char) data;                                       // grabs character
                // System.out.println(character);
                // Character.isWhitespace(character)
                if (whiteSpace.containsKey(String.valueOf(character))) {            // continue if the character is whitespace
                    if(keywords.containsKey(currentWord.toString())){
                        processWord(currentWord.toString());
                    }
                    continue;                                                       // MAY NEED TO ADJUST FOR WHITESPACE RULE
                }
                String charString = String.valueOf(character);                      // Convert char to String
                currentWord.append(character);                                      // adds character to a StringBuilder

                if(keywords.containsKey(currentWord.toString())){                                // IF THE WORD IS IN THE HASH
                    if(singleCharacterTokens.containsKey(charString)){                           // if the current char is a single character token
                        processWord(currentWord.toString());                                     // Process the word, no guarantee it is a token
                        currentWord.setLength(0);                                                // reset StringBuilder
                        continue;                                                                // go to next iteration
                    }

                    reader.mark(1);                                                     // look ahead by 1
                    int nextCharInt = reader.read();

                    if (nextCharInt != -1 && !whiteSpace.containsKey(String.valueOf(character))) {    // If there is another character AND it is NOT whitespace
                        // System.out.println("firstblock: " + currentWord.toString());
                        char nextChar = (char) nextCharInt;                             // convert the integer to the nextChar
                        String nextCharString = String.valueOf(nextChar);               // convert the nextChar to a string
                        if(singleCharacterTokens.containsKey(nextCharString)){          // if the next character is a single char token
                            processWord(currentWord.toString());                        // Process the CURRENT word (single character token will need the stringbuilder)
                            currentWord.setLength(0);                                   // reset StringBuilder
                        }
                        else if(!whiteSpace.containsKey(nextCharString)){
                            if(isValidIdentifier(nextCharString) || keywords.containsKey(nextCharString)){
                                // do nothing
                            }
                            else{
                                processWord(currentWord.toString());                        // Process the CURRENT word (single character token will need the stringbuilder)
                                currentWord.setLength(0);                                   // reset StringBuilder
                                // printError(nextCharString);                              // print error
                                continue;                                                   // error found, skip to next iteration
                            }

                        }
                        else if(whiteSpace.containsKey(nextCharString)){
                            processWord(currentWord.toString());                        // Process the CURRENT word (single character token will need the stringbuilder)
                            currentWord.setLength(0);                                   // reset StringBuilder
                            continue;                                                   // whitespace found, skip to next iteration
                        }
                        reader.reset();                                                 // Reset the reader to the marked position
                    }
                    else {                                                      // if the next character is whitespace
                        processWord(currentWord.toString());                    // Process the word since it's the end of the file
                        currentWord.setLength(0);                               // reset StringBuilder
                    }
                }
                else if(integerTokens.containsKey(String.valueOf(currentWord.toString().charAt(0)))){   // if the first character in the SB is a digit (0-9)
                    // block of code that will check if a number is an INT or DECIMAL
                    // System.out.println("in num block: " + currentWord.toString());
                    reader.mark(1);                                               // look ahead by 1
                    int nextCharInt = reader.read();
                    char nextChar = (char) nextCharInt;                           // convert int to char
                    // System.out.println("next char: " + nextChar);
                    String nextCharString = String.valueOf(nextChar);              // convert the nextChar to a string
                    // if there is another char ahead AND (the next char is a int (0-9) OR a decimal)
//                    if(nextCharInt != -1 && ((integerTokens.containsKey(String.valueOf(currentWord.toString().charAt(0))) || nextChar == '.'))){
//                        reader.reset();                                           // reset reader to marked position
//                    }
                    if(nextCharInt != -1 && (integerTokens.containsKey(nextCharString) || nextChar == '.')){
                        reader.reset();                                           // reset reader to marked position
                    }
                    // if there is another char ahead AND that char is a singlechartoken
                    else if(nextCharInt != -1 && singleCharacterTokens.containsKey(nextCharString)){
                        printNum(currentWord.toString());                        // Process the CURRENT word (single character token will need the stringbuilder)
                        currentWord.setLength(0);                                   // reset StringBuilder
                        reader.reset();                                             // reset reader to marked position
                    }
                    // if there is another char AND that char is not found in keywords
                    else if(nextCharInt != -1 && !keywords.containsKey(nextCharString)){
                        printNum(currentWord.toString());                       // print the current word
                        if(!whiteSpace.containsKey(nextCharString)){
                            printError(nextCharString);                             // the next character is an error, print error
                        }
                        currentWord.setLength(0);                               // reset SB
                        reader.reset();
                    }
                    else{
                        printNum(currentWord.toString());                    // Process the word since it's the end of the file
                        currentWord.setLength(0);                            // reset StringBuilder
                    }
                } else if (currentWord.charAt(0) == '"') {                      // if a DOUBLE QUOTE found, start of String
                    StringBuilder stringValue = new StringBuilder();            // create NEW SB
                    boolean insideDoubleQuotes = true;                         // bool var to keep track of when inside double quotes

                    while ((data = reader.read()) != -1) {
                        char currentChar = (char) data;                         // currentChar
                        if (currentChar == '\"') {                              // if the currentChar is a double quote
                            insideDoubleQuotes = !insideDoubleQuotes;           // set bool var to false
                        }
                        else{                                                   // if currentChar is not a double quote
                            stringValue.append(currentChar);                    // append currentChar to SB
                        }

                        if (!insideDoubleQuotes) {                              // if still inside the double quotes
                            printString(stringValue.toString());                // print the String
                            stringValue.setLength(0);                           // Reset the StringBuilder for the next content inside double quotes
                            break;
                        }
                        currentWord.setLength(0);
                    }
                    if(insideDoubleQuotes){
                        System.out.println("Error: Unfinished string");
                    }
                    currentWord.setLength(0);
                }
                else if(currentWord.charAt(0) == '/'){                           // if a / is found, start of a comment
                    StringBuilder commentValue = new StringBuilder();            // create NEW SB
                    boolean insideMultiLineComment = true;                       // boolean var to keep track if in multi line comment

                    if(currentWord.toString().equals("//") ) {                     // if the currentWord is //, single line comment
                        while((data = reader.read()) != -1){
                            char currentChar = (char) data;                         // currentChar that is read into SB
                            if (currentChar == '\n' || currentChar == '\r') {       // Check for the end of the line
                                break;                                              // Exit the loop when a newline character is encountered
                            }
                            commentValue.append(currentChar);                       // Append the character to the StringBuilder
                        }
                        printSingleComment(commentValue.toString());                // print the comment
                        commentValue.setLength(0);                                  // reset SB
                    }
                    else if(currentWord.toString().equals("/*") ) {                     // if the currentWord is //, multi line comment
                        while((data = reader.read()) != -1){
                            char currentChar = (char) data;                         // currentChar that is read into SB
                            if (currentChar == '*' && reader.read() == '/') {       // End of multiline comment found
                                insideMultiLineComment = false;
                                break;
                            }
                            commentValue.append(currentChar);                       // Append the character to the StringBuilder
                        }
                        if (insideMultiLineComment) {                               // comment not closed
                            System.out.println("Error: Unclosed multiline comment");
                        } else {
                            printSingleComment(commentValue.toString());            // print the comment
                        }
                        commentValue.setLength(0);                                  // reset SB
                    }
                    currentWord.setLength(0);
                }
                else{                                                             // IF THE WORD IS NOT IN THE HASH
                    reader.mark(1);                                                 // look ahead by 1
                    int nextCharInt = reader.read();
                    // System.out.println("else: " + currentWord.toString());

                    if(nextCharInt != -1 && !whiteSpace.containsKey(String.valueOf(character))) {     // if there is another char and it is not whitespace
                        char nextChar = (char) nextCharInt;                             // convert the integer to the nextChar
                        String nextCharString = String.valueOf(nextChar);               // convert the nextChar to a string
                        // System.out.println("next char: " + nextCharString);
                        if(singleCharacterTokens.containsKey(nextCharString)){          // if the next character is a single char token
                            printIdentifier(currentWord.toString());                    // print id info (single character token will need the stringbuilder)
                            currentWord.setLength(0);                                   // reset StringBuilder
                        }
                        else if(!IdScopeCheck(nextChar)){                                   // if the next char is not in the id scope
                            if(isValidIdentifier(currentWord.toString())){                  // if the current word is a valid id
                                printIdentifier(currentWord.toString());                    // print id info (single character token will need the stringbuilder)
                            }
                            if(!whiteSpace.containsKey(nextCharString)){
                                printError(nextCharString);                                     // print error
                            }
                            // System.out.println("1: " + currentWord.toString());
                            currentWord.setLength(0);                                       // reset StringBuilder
                            continue;                                                       // error found skip to next iteration
                        }
                        reader.reset();
                    }
                    else if(nextCharInt != -1 || whiteSpace.containsKey(String.valueOf(character))){  // if end of file OR the next character is whitespace
                        if(isValidIdentifier(currentWord.toString())){                  // check if current word is a valid id
                            printIdentifier(currentWord.toString());                    // print id info
                            currentWord.setLength(0);                                   // reset StringBuilder
                        }
                        else{                                                           // if not valid id
                            if(!keywords.containsKey(currentWord.toString())){          // if currentword is not in keywords
                                printError(currentWord.toString());                     // error
                                currentWord.setLength(0);                               // reset StringBuilder
                            }
                        }
                    }
                }
            }
            if (currentWord.length() > 0) {                             // if something remains in the stringbuilder
                if(keywords.containsKey(currentWord.toString())){       // check if it is a token
                    processWord(currentWord.toString());                // print what token it is
                }
                else if(isValidIdentifier(currentWord.toString())){
                    printIdentifier(currentWord.toString());
                }
                else{
                    printError(currentWord.toString());
                    // System.out.println("here 2" + currentWord.toString());
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Check if the character is a known keyword and print it out, else print out what the word currently looks like
    public void processWord(String wordString) {
        if (keywords.containsKey(wordString)) {
            int tokenType = keywords.get(wordString);                         // print out token information
            String tokenName = "Token Category: " + tokenType + ", ";         // token header
            if (ruleMap.containsKey(tokenType)) {                             // if rule is named differently than keyword, output real rule name
                String ruleName = ruleMap.get(tokenType);
                System.out.println(tokenName + ruleName + " keyword" + ", value \"" + wordString + "\"");
            } else {                                                          // else normal output
                System.out.println(tokenName + wordString.toUpperCase() + " keyword" + ", value \"" + wordString + "\"");
            }
        }
    }

    // function that prints out error prompt
    public void printError(String wordString) {
        String identifierHeader = "Token Category: 101, ERROR, value ";
        System.out.println(identifierHeader  + "\"" + wordString + "\"");
    }

    // function that prints out id prompt
    public void printIdentifier(String wordString) {
        String identifierHeader = "Token Category: 1000, identifier, value ";
        System.out.println(identifierHeader  + "\"" + wordString + "\"");
    }

    // function that prints out string prompt
    public void printString(String wordString) {
        String identifierHeader = "Token Category: 200, STRING, value ";
        System.out.println(identifierHeader  + "\"" + wordString + "\"");
    }

    // function that prints out  comment prompt
    public void printSingleComment(String wordString) {
        String identifierHeader = "Token Category: 300, COMMENT, value ";
        System.out.println(identifierHeader  + "\"" + wordString + "\"");
    }


    // function that print sout number int or decimal prompt
    public void printNum(String wordString) {
        boolean hasDecimal = false;                     // boolean var to see if there is a decimal in the number
        for (int i = 0; i < wordString.length(); i++) { // iterate throug the string
            char currentChar = wordString.charAt(i);
            if (currentChar == '.') {                   // if a decimal is found
                hasDecimal = true;                      // set boolean to true
                break;                                  // break out of loop
            }
        }
        if(hasDecimal){
            String identifierHeader = "Token Category: 150, DECIMAL, value ";
            System.out.println(identifierHeader  + "\"" + wordString + "\"");
        }
        else{
            String identifierHeader = "Token Category: 100, INT, value ";
            System.out.println(identifierHeader  + "\"" + wordString + "\"");
        }
    }

    // function that will check if a word is a valid identifier
    public boolean isValidIdentifier(String wordString) {
        if (wordString.isEmpty()) {
            return false; // An empty string is not a valid identifier
        }
        char firstChar = wordString.charAt(0);
        if (!letterTokens.containsKey(firstChar)) {
            return false; // if the first character is not a digit and not a letter and not an underscore, not id
        }
        // Check the rest of the characters
        for (int i = 1; i < wordString.length(); i++) {
            char currentChar = wordString.charAt(i);
            if (!letterTokens.containsKey(currentChar) && !integerTokens.containsKey(String.valueOf(currentChar))) {
                if(currentChar != '_'){
                    return false;       // if the current character is not a letter or digit and not a underscore, not id
                }
            }
        }
        return true; // The identifier is valid
    }

    // function that checks if a character is in the scope of an id
    public boolean IdScopeCheck(char character){
        if ((!letterTokens.containsKey(character) && !integerTokens.containsKey(String.valueOf(character))) && character != '_') {
            return false; // if the first character is not a digit or not a letter and not an underscore, not id
        }
        return true;
    }

}