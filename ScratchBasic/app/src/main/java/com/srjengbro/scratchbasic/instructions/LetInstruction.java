package com.srjengbro.scratchbasic.instructions;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.srjengbro.scratchbasic.Expression;
import com.srjengbro.scratchbasic.ExpressionMaker;
import com.srjengbro.scratchbasic.ExpressionParseException;
import com.srjengbro.scratchbasic.R;
import com.srjengbro.scratchbasic.ScratchBasicContext;
import com.srjengbro.scratchbasic.VariableDoesNotExistException;

/**
 * @author      Sol Jennigns & Giles Browne
 * @description LetInstruction This class allows for the creation of a LET instruction
 *              It allows an instruction to change the value of a variable in ScratchBasic
 *              program based on the result of an expression. It inherits from the instruction
 *              class.It handles the layout, running, and processing of any LET instruction.
 */
public class LetInstruction extends Instruction {

    /**
     * text box for instruction
     */
    private transient EditText letVariableText;

    /**
     *
     * @return Name of variable of let
     */

    public String getVariable() {
        return variable;
    }

    /**
     * the variable whos value will change
     */
    private String variable;
    /**
     * The expression of the instruction
     */
    private Expression expression;

    /**
     * constructor
     */
    public LetInstruction() {
        name = "LET";
        expression = new Expression();

    }


    /**
     * @param inflater inflater
     * @param scratchBasicContext
     * @return the layout for the instruction
     */
    @Override
    public View getLayout(LayoutInflater inflater, ScratchBasicContext scratchBasicContext) {
        View layout = inflater.inflate(R.layout.inst_let, null);
        letVariableText = (EditText) layout.findViewById(R.id.let_variable);
        letVariableText.setText(variable);
        expression.layout(layout,scratchBasicContext.getInstructions());
        return layout;
    }

    /**
     * update after edit
     */
    @Override
    public void update() {
        variable = letVariableText.getText().toString();
        String type = expression.opSpinner.getSelectedItem().toString();
        String lhs = expression.lhsText.getText().toString();
        String rhs = expression.rhsText.getText().toString();
        expression = ExpressionMaker.generateExpression(type, lhs, rhs);
        instruction = variable + " = " + expression.toString();
    }

    /**
     *
     * @param scratchBasicContext@return execute and return the result
     * @throws InstructionRunException
     */
    @Override
    public String run(ScratchBasicContext scratchBasicContext) throws InstructionRunException {
        if (expression == null) {
            throw new InstructionRunException("Instruction missing expression");
        }
        if (variable == null) {
            throw new InstructionRunException("Instruction missing variable");
        }
        Double result;
        try {
            result = expression.evaluate(scratchBasicContext.getVariableStore());
            scratchBasicContext.getVariableStore().setVariable(variable, result);
        } catch (VariableDoesNotExistException | ExpressionParseException e) {
            throw new InstructionRunException(e.getMessage());
        }
        return "";
    }
}
