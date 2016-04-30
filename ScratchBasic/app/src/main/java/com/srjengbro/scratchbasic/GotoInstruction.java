package com.srjengbro.scratchbasic;

public class GotoInstruction extends Instruction {

    protected Expression expression;

    public GotoInstruction() {
        name = "GOTO";
    }

    @Override
    public String run() {
        return null;
    }

    public void parse(String line) {
        String[] tokens = line.split("\\s+"); // Split on whitespace
        try {
            if (tokens.length < 3) {
                expression = ExpressionMaker.generateExpression(tokens[0]);

            }
            if (tokens.length == 3) {
                expression = ExpressionMaker.generateExpression(tokens[1], tokens[0], tokens[2]);
            }
            instruction = expression.toString();
        } catch (ExpressionParseException e) {
            System.out.print(e.getMessage());
        }
    }
}
