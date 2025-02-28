import java.util.List;		// used by expression evaluator
import java.util.ArrayList;
/**
 *	<Description goes here>
 *
 *	@author	
 *	@since	
 */
public class SimpleCalc {
	
	private ExprUtils utils;	// expression utilities
	private ArrayStack<Double> valueStack;		// value stack
	private ArrayStack<String> operatorStack;	// operator stack

	// constructor	
	public SimpleCalc() {
		utils = new ExprUtils();
		valueStack = new ArrayStack<Double>();
		operatorStack = new ArrayStack<String>();
	}
	
	public static void main(String[] args) {
		SimpleCalc sc = new SimpleCalc();
		sc.run();
	}
	
	public void run() {
		System.out.println("\nWelcome to SimpleCalc!!!");
		runCalc();
		System.out.println("\nThanks for using SimpleCalc! Goodbye.\n");
	}
	
	/**
	 *	Prompt the user for expressions, run the expression evaluator,
	 *	and display the answer.
	 */
	public void runCalc() {
		String inp = "";
		do{
			inp = Prompt.getString("");
			if (inp.equalsIgnoreCase("H")) 
				printHelp();
			else if (!inp.equalsIgnoreCase("q")){
				List<String> terms = utils.tokenizeExpression(inp);
				System.out.println(evaluateExpression(toPostFix(terms)));
			}
		}while (!inp.equalsIgnoreCase("q"));
	}
	
	public List<String> toPostFix(List<String> terms){
		List<String> ans = new ArrayList<>();
		ArrayStack<String> operatorStack = new ArrayStack<String>();
		for (String str:terms){
			if (isNumber(str))
				ans.add(str);
			else if (str.equals("("))
				operatorStack.push(str);
			else if (str.equals(")")){
				while(!operatorStack.peek().equals("(")){
					ans.add(operatorStack.pop());
				}
				operatorStack.pop();
			}
			else{
				if(operatorStack.isEmpty())
					operatorStack.push(str);
				else{
					if (hasPrecedence(operatorStack.peek(), str)) 
						operatorStack.push(str);
					else{
						while(!operatorStack.isEmpty() 
							&& !hasPrecedence(operatorStack.peek(),str) 
							&&!operatorStack.peek().equals("(")){
							ans.add(operatorStack.pop());
						}
						operatorStack.push(str);
					}
				}
			}
		}
		while(!operatorStack.isEmpty())
			ans.add(operatorStack.pop());
		return ans;
	}
	
	public boolean isNumber(String str){
		for (int i = 0; i < str.length(); i++){
			if (Character.isDigit(str.charAt(i))) 
				return true;
		}
		return false;
	}
	
	/**	Print help */
	public void printHelp() {
		System.out.println("Help:");
		System.out.println("  h - this message\n  q - quit\n");
		System.out.println("Expressions can contain:");
		System.out.println("  integers or decimal numbers");
		System.out.println("  arithmetic operators +, -, *, /, %, ^");
		System.out.println("  parentheses '(' and ')'");
	}
	
	/**
	 *	Evaluate expression and return the value
	 *	@param tokens	a List of String tokens making up an arithmetic expression
	 *	@return			a double value of the evaluated expression
	 */
	public double evaluateExpression(List<String> tokens) {
		double value = 0;
		double value1 = 0;
		double value2 = 0;
		for (String token: tokens){
			if (isNumber(token))
				valueStack.push(Double.parseDouble(token));
			else{
				
				value1 = valueStack.pop();
				value2 = valueStack.pop();
				valueStack.push(perfOperation(value2, value1, token));
			}
		}
		value = valueStack.pop();
		return value;
	}
	
	public double perfOperation(double value1, double value2, String operator){
		if (operator.equals("+")){
			return value1 + value2;
		}
		else if (operator.equals("-")){
			return value1 - value2;
		}
		else if (operator.equals("*")){
			return value1*value2;
		}
		else if (operator.equals("/")){
			return value1/value2;
		}
		else if (operator.equals("%")){
			return value1%value2;
		}
		else{
			return Math.pow(value1, value2);
		}
	}
	
	/**
	 *	Precedence of operators
	 *	@param op1	operator 1
	 *	@param op2	operator 2
	 *	@return		true if op2 has higher precedence as op1; false otherwise
	 *	Algorithm:
	 *		if op1 is exponent, then false
	 *		if op2 is either left or right parenthesis, then false
	 *		if op1 is multiplication or division or modulus and 
	 *				op2 is addition or subtraction, then false
	 *		otherwise true
	 */
	private boolean hasPrecedence(String op1, String op2) {
		if (samePrecedence(op1, op2)) return false;
		if (op1.equals("^")) return false;
		if (op2.equals("(") || op2.equals(")")) return false;
		if ((op1.equals("*") || op1.equals("/") || op1.equals("%")) 
				&& (op2.equals("+") || op2.equals("-")))
			return false;
		return true;
	}
	
	private boolean samePrecedence(String op1, String op2){
		if ("*/%".contains(op1) && "*/%".contains(op2)) 
			return true;
		else if ("+-".indexOf(op1) != -1 && "+-".indexOf(op2) != -1)
			return true;
		return false;
	}
	 
}
