package com.srjengbro.scratchbasic.instructions;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.srjengbro.scratchbasic.Expression;
import com.srjengbro.scratchbasic.ExpressionMaker;
import com.srjengbro.scratchbasic.ExpressionParseException;
import com.srjengbro.scratchbasic.R;
import com.srjengbro.scratchbasic.VariableDoesNotExistException;
import com.srjengbro.scratchbasic.VariableStore;
/**
 * @author      Sol Jennings
 * @description
 */
public class LetInstruction extends Instruction {

    /**
     * text box for instruction
     */
    private transient EditText letVariableText;
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
     * @return the layout for the instruction
     */
    @Override
    public View getLayout(LayoutInflater inflater) {
        View layout = inflater.inflate(R.layout.inst_let, null);
        letVariableText = (EditText) layout.findViewById(R.id.let_variable);
        letVariableText.setText(variable);
        expression.layout(layout);
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
     * @param variableStore variables
     * @return execture and return the result
     * @throws InstructionRunException
     */
    @Override
    public String run(VariableStore variableStore) throws InstructionRunException {
        if (expression == null) {
            throw new InstructionRunException("Instruction missing expression");
        }
        if (variable == null) {
            throw new InstructionRunException("Instruction missing variable");
        }
        Integer result;
        try {
            result = expression.evaluate(variableStore);
            variableStore.setVariable(variable, result);
        } catch (VariableDoesNotExistException | ExpressionParseException e) {
            throw new InstructionRunException(e.getMessage());
        }
        return "";
    }
}
