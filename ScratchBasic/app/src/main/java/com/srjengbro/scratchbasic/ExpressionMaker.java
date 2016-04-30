package com.srjengbro.scratchbasic;

/**
 * Created by Maidenii on 29/04/16.
 */
public class ExpressionMaker {

    public static Expression generateExpression(String expressionString) throws ExpressionParseException {
        String[] tokens = expressionString.split("\\s+"); // Split on whitespace
        String type;
        String lhs;
        String rhs;
        if (tokens.length < 3) {
            lhs = tokens[0];
            return new Expression(lhs);
        }
        type = tokens[1];
        lhs = tokens[0];
        rhs = tokens[2];
        Operator op;
        switch (type) {
            case "+":
                op = new Plus();
                break;
            case "-":
                op = new Minus();
                break;
            case "==":
                op = new Equal();
                break;
            case ">":
                op = new GreaterThan();
                break;
            case "<":
                op = new LessThan();
                break;
            default:
                throw new ExpressionParseException("Not a valid instruction");
        }

        return new Expression(op, lhs, rhs);
    }

}
