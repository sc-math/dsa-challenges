package algorithms.stack;

public class LC1190 {
    int idx;
    String s;

    public String reverseParentheses(String s) {
        this.idx = 0;
        this.s = s;

        return solution();
    }

    private String solution(){
        StringBuilder word = new StringBuilder();

        while(idx < s.length()){

            if(s.charAt(idx) == '('){
                idx++;
                String word_bracket = solution();
                word.append(word_bracket);
            }
            else if(s.charAt(idx) == ')'){
                word.reverse();
                idx++;
                return word.toString();
            }
            else{
                word.append(s.charAt(idx));
                idx++;
            }
        }
        return word.toString();
    }
}
