package com.srjengbro.scratchbasic;

import com.srjengbro.scratchbasic.operators.Equal;
import com.srjengbro.scratchbasic.operators.GreaterThan;
import com.srjengbro.scratchbasic.operators.LessThan;
import com.srjengbro.scratchbasic.operators.Minus;
import com.srjengbro.scratchbasic.operators.Operator;
import com.srjengbro.scratchbasic.operators.Plus;

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
        return generateExpression(type, lhs, rhs);
    }

    public static Expression generateExpression(String type, String lhs, String rhs) {
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
                return new Expression(lhs);
        }

        return new Expression(op, lhs, rhs);
    }

}
