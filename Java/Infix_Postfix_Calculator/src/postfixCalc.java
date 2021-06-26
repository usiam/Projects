import java.util.Arrays;
import java.util.List;

public class postfixCalc {

	public static String eval(String str) {
		String[] arr = str.split(",");
		List<String> arrList = Arrays.asList(arr);
		
		if (arrList.get(0).equals("Error")) {
			return arrList.get(1);
		}
		
		Stack<String> operands = new Stack<String>();

		for (String x : arrList) {
			if (infixCalc.isDigit(x)) {
				operands.push(x);
			}
			if (infixCalc.isOperator(x)) {
				if (binary(x)) {
					if (operands.size() < 2) {
						System.out.println("Expression invalid");
					}
					double right = Double.parseDouble(operands.pop());
					double left = Double.parseDouble(operands.pop());
					String answer = binaryEvaluation(right, left, x);
					operands.push(answer);
				} else if (unary(x)) {
					if (operands.size() < 1) {
						System.out.println("Expression invalid");
					}
					double operand = Double.parseDouble(operands.pop());
					String answer = unaryEvaluation(operand, x);
					operands.push(answer);

				}
			}
		}

		if (operands.size() != 1) {
			return "Bad input expression";
		} else {
			return operands.pop();
		}

	}

	public static boolean unary(String s) {
		return (s.equals("!") || s.equals("sin") || s.equals("cos") || s.equals("tan"));
	}

	public static boolean binary(String s) {
		return !unary(s);
	}

	public static String unaryEvaluation(double operand, String operator) {

		boolean operand_boolean = operand != 0; // 0 means False and 1 means True
		double result;
		switch (operator) {
		case "!":
			if (!operand_boolean) {
				result = 1;
				break;
			} else {
				result = 0;
				break;
			}
		case "sin":
			result = (double) Math.sin(operand);
			break;
		case "cos":
			result = (double) Math.cos(operand);
			break;
		case "tan":
			result = (double) Math.tan(operand);
			break;
		default:
			return "-69420";
		}

		String ret = String.format("%.2f", result);
		return ret;
	}

	public static String binaryEvaluation(double right, double left, String operator) {
		boolean left_boolean = left != 0;
		boolean right_boolean = right != 0;
		double result;
		switch (operator) {
		case "+":
			result = left + right;
			break;
		case "-":
			result = left - right;
			break;
		case "*":
			result = left * right;
			break;
		case "/":
			result = left / right;
			break;
		case "%":
			result = left % right;
			break;
		case "^":
			result = (double) Math.pow(left, right);
			break;
		case ">":
			if (left > right) {
				result = 1;
				break;
			} else {
				result = 0;
				break;
			}
		case "<":
			if (left < right) {
				result = 1;
				break;
			} else {
				result = 0;
				break;
			}
		case "=":
			if (left == right) {
				result = 1;
				break;
			} else {
				result = 0;
				break;
			}
		case "&":
			if (left_boolean && right_boolean)
				result = 1;
			else
				result = 0;
			break;
		case "|":
			if (left_boolean || right_boolean)
				result = 1;
			else
				result = 0;
			break;
		default:
			return "-69420";
		}

		String ret = String.format("%.2f", result);
		return ret;
	}

}
