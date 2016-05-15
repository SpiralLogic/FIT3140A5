package com.srjengbro.scratchbasic;

import com.srjengbro.scratchbasic.operators.DivOperator;
import com.srjengbro.scratchbasic.operators.EqualOperator;
import com.srjengbro.scratchbasic.operators.GreaterThanOperator;
import com.srjengbro.scratchbasic.operators.LessThanOperator;
import com.srjengbro.scratchbasic.operators.MinusOperator;
import com.srjengbro.scratchbasic.operators.ModOperator;
import com.srjengbro.scratchbasic.operators.Operator;
import com.srjengbro.scratchbasic.operators.PlusOperator;

/**
 * @author      Sol Jennigns & Giles Browne
 * @description Expression factory. Creates an expression object an returns that object
 *              An expression with just a LHS will default to using a NoOpOperator operator for its
 *              operator
 */
public class ExpressionMaker {

    /**
     * @param expressionString the string of the expression
     * @return an expression object
     * @throws ExpressionParseException
     */
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

    /**
     * @param type type of operator to use
     * @param lhs lhs of the pexression
     * @param rhs rhs of the expression
     * @return
     */
    public static Expression generateExpression(String type, String lhs, String rhs) {
        Operator op;
        switch (type) {
            case "+":
                op = new PlusOperator();
                break;
            case "-":
                op = new MinusOperator();
                break;
            case "==":
                op = new EqualOperator();
                break;
            case ">":
                op = new GreaterThanOperator();
                break;
            case "<":
                op = new LessThanOperator();
                break;
            case "%":
                op = new ModOperator();
                break;
            case "/":
                op = new DivOperator();
                break;
            default:
                return new Expression(lhs);
        }

        return new Expression(op, lhs, rhs);
    }

}
