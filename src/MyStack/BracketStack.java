package MyStack;

import java.beans.Expression;
import java.io.IOException;
import java.util.Stack;
//bug粗心把stc.pop() !=”{“，改了之后还要该栈的泛型为Character
public class BracketStack {

    public static void main(String args[]) {

       Stack<Character> stc = new Stack<Character>();
        boolean failed = false;
        String Expression = new String("{[[(5+2)]]}");

        for (int i = 0; !failed && (i < Expression.length()); i++) {
            switch (Expression.charAt(i)) {
                case '{':                           //是冒号

                case '[':

                case '(':

                    stc.push(Expression.charAt(i));
                    break;
                case '}':
                    if (stc.isEmpty() || (stc.pop() !='{'))
                        failed = true;
                    break;
                case ']':
                    if (stc.isEmpty() || (stc.pop() != '['))
                        failed = true;
                    break;
                case ')':
                    if (stc.isEmpty() || (stc.pop() != '('))
                        failed = true;
                    break;
                    default:
                        break;

            }
            System.out.println(!failed);              //在循环体输出可以作为一种调试方法


            }
        System.out.println("表达式是否匹配"+!failed);

    }}

