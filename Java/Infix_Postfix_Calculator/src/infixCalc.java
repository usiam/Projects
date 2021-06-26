import java.util.ArrayList;

public class infixCalc {

	public static void main(String[] args) {

		String inputFileName = args[0];
		String outputFileName = args[1];

		ArrayList<String> exprStrings = fileIO.readFile(inputFileName);
		ArrayList<String> postExpr = new ArrayList<String>();

		for (String r : exprStrings) {
			try {
				String data = inToPostConv(r);
				postExpr.add(data);
			} catch (Exception e) {
				postExpr.add("Error," + e.getMessage());
			}

			// data is the arraylist of parsed digits and operators

			// evaluate postfix expressions and store outputs
			ArrayList<String> outputs = new ArrayList<String>();

			for (String e : postExpr) {

				outputs.add(postfixCalc.eval(e));
			}

			fileIO.writingOutput(outputs, outputFileName);

		}

	}

	public static String inToPostConv(String expr) throws Exception {

		Stack<String> operators = new Stack<String>();
		Queue<String> digits = new Queue<String>();
		ArrayList<String> strArrProc = new ArrayList<String>();

		String[] strArr = expr.split("((?<=\\s)|(?=\\s)|(?<=\\p{Punct})|(?=\\p{Punct}))");

		for (int k = 0; k < strArr.length; k++) {
			if (strArr[k].equals(".")) {
				strArrProc.set(strArrProc.size() - 1, strArr[k - 1] + strArr[k] + strArr[k + 1]);
				k++;
			} else if (strArr[k].equals(" ")) {
			} else {
				strArrProc.add(strArr[k]);
			}
		}

		for (String x : strArrProc) {
			if (isDigit(x)) {
				digits.enqueue(x);
			}

			else if (isLeftParen(x)) {
				operators.push(x);
			}

			else if (isRightParen(x)) {

				while (true) {
					if (operators.isEmpty()) {
						throw new Exception("No left parenthesis was found");
					}
					String temp_x = operators.pop();
					if (isLeftParen(temp_x)) {
						break;
					} else {
						digits.enqueue(temp_x);
					}
				}

			} else if (isOperator(x)) {
				while (true) {
					if (operators.isEmpty()) {
						operators.push(x);
						break;
					} else if (isLeftParen(operators.getTop())) {
						operators.push(x);
						break;
					} else {
						boolean leftAss_low = (precedenceVals(x) > precedenceVals(operators.getTop()))
								&& leftAssociative(operators.getTop());
						// check for right-associative operator of equal (or lower) precedence
						boolean rightAss_low_or_eq = (precedenceVals(x) >= precedenceVals(operators.getTop()))
								&& rightAssociative(operators.getTop());

						if (leftAss_low || rightAss_low_or_eq) {
							// lower precedence or right-associative equal precedence found so push current
							// element to stack
							operators.push(x);
							break;
						} else {
							// pop from stack and enqueue to queue
							digits.enqueue(operators.pop());
						}
					}
				}
			} else {
				throw new Exception("Invalid token in the input expression. Cannot evaluate");

			}
		}

		while (!operators.isEmpty()) {

			digits.enqueue(operators.pop());
		}

		String processedStrings = "";

		while (!digits.isEmpty()) {
			processedStrings += digits.dequeue();
			if (digits.size() > 0) {
				processedStrings += ",";
			} else {
				processedStrings += "";
			}
		}

		return processedStrings;

	}

	public static boolean isOperator(String op) {

		return (op.equals("+") || op.equals("-") || op.equals("*") || op.equals("/") || op.equals("%") || op.equals("<")
				|| op.equals("=") || op.equals(">") || op.equals("|") || op.equals("&") || op.equals("^")
				|| op.equals("sin") || op.equals("cos") || op.equals("tan") || op.equals("!"));
	}

	public static boolean isLeftParen(String c) {
		return (c.equals("("));
	}

	public static boolean isRightParen(String c) {
		return (c.equals(")"));
	}

	public static boolean isDigit(String c) {

		try {
			Double.parseDouble(c);
			return true;
		} catch (NumberFormatException e) {
			{
				return false;
			}

		}

	}

	public static int precedenceVals(String str) {

		if (str.equals("(")) {
			return 0;
		} else if (str.equals("|")) {
			return 1;
		} else if (str.equals("&")) {
			return 2;
		} else if (str.equals("=")) {
			return 3;
		} else if (str.equals("<") || str.equals(">")) {
			return 4;
		} else if (str.equals("sin") || str.equals("cos") || str.equals("tan")) {
			return 5;
		} else if (str.equals("+") || str.equals("-")) {
			return 6;
		} else if (str.equals("*") || str.equals("/") || str.equals("%")) {
			return 7;
		} else if (str.equals("^")) {
			return 8;
		} else if (str.equals("!")) {
			return 9;
		} else if (str.equals(")")) {
			return 10;
		} else {
			return -1;
		}
	}

	public static boolean rightAssociative(String str) {
		if (str.equals("!") || str.equals("%") || str.equals("^")) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean leftAssociative(String str) {
		if (rightAssociative(str)) {
			return false;
		} else {
			return true;
		}
	}

}
