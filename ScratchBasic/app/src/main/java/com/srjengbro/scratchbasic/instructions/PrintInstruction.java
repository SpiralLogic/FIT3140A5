package com.srjengbro.scratchbasic.instructions;

import android.view.LayoutInflater;
import android.view.View;
import com.srjengbro.scratchbasic.Expression;
import com.srjengbro.scratchbasic.ExpressionMaker;
import com.srjengbro.scratchbasic.ExpressionParseException;
import com.srjengbro.scratchbasic.R;
import com.srjengbro.scratchbasic.VariableDoesNotExistException;
import com.srjengbro.scratchbasic.VariableStore;

/**
 * @author      Sol Jennings
 * @description PrintInstruction This class allows for the creation of a PRINT instruction
 *              It allows an instruction to change the print the result of an expression in a
 *              ScratchBasic program based on the result of an expression. It inherits from the
 *              instruction class.It handles the layout, running, and processing of any PRINT
 *              instruction.
 */
public class PrintInstruction extends Instruction {

    /**
     * expression to evaluate
     */
    protected Expression expression;


    /**
     * constructor
     */
    public PrintInstruction() {
        name = "PRINT";
        expression = new Expression();
    }

    /**
     * @param inflater inflater
     * @return get layout for the instruction
     */
    public View getLayout(LayoutInflater inflater) {
        View layout = inflater.inflate(R.layout.inst_print, null);
        expression.layout(layout);
        return layout;
    }


    /**
     * update after edit
     */
    public void update() {
        String type = expression.opSpinner.getSelectedItem().toString();
        String lhs = expression.lhsText.getText().toString();
        String rhs = expression.rhsText.getText().toString();
        expression = ExpressionMaker.generateExpression(type, lhs, rhs);
        instruction = expression.toString();
    }

    /**
     * @param variableStore variables
     * @return execute the instruction
     * @throws InstructionRunException
     */
    @Override
    public String run(VariableStore variableStore) throws InstructionRunException {
        if (expression == null) {
            throw new InstructionRunException("Instruction missing expression");
        }
        Integer result;
        try {
            result = expression.evaluate(variableStore);
        } catch (VariableDoesNotExistException | ExpressionParseException e) {
            throw new InstructionRunException(e.getMessage());
        }
        return result.toString();
    }


}