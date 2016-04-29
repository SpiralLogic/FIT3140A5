package com.srjengbro.scratchbasic;

/**
 * Created by Maidenii on 29/04/16.
 */
public class ExpressionMaker {

    public static Expression generateExpression(String type, String lhs, String rhs) throws Exception {
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
                throw new Exception("Not a valid instruction");
        }
        return new Expression(op, lhs, rhs);
    }

}
