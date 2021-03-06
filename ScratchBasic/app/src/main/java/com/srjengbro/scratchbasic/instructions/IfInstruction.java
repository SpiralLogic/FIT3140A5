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
 * @author Sol Jennigns & Giles Browne
 * @description GotoInstruction This class allows for the creation of a IF GOTO instruction
 * It allows an instruction to change the next line to be executed in a ScratchBasic
 * program based on the evaluation of an expression. It inherits from the instruction
 * class. It handles the layout, running,
 * and processing of any IF GOTO instruction
 */
public class IfInstruction extends Instruction {

    /**
     * goto line instruction
     */
    protected GotoInstruction gotoInstruction;
    /**
     * Expression of the if statement
     */
    protected Expression expression;
    /**
     * whether the if statement was true or false when executed
     */
    private Boolean evaluatedAs = false;

    /**
     * Constructor
     */
    public IfInstruction() {
        name = "IF";
        gotoInstruction = new GotoInstruction();
        expression = new Expression();
    }

    /**
     * @return name of the instruction
     */
    public String getName() {
        return this.name;
    }

    /**
     * @param inflater        inflater
     * @param scratchBasicContext
     * @return the layout to display the editor
     */
    public View getLayout(LayoutInflater inflater, ScratchBasicContext scratchBasicContext) {
        View layout = inflater.inflate(R.layout.inst_if, null);
        expression.layout(layout, scratchBasicContext.getInstructions());
        //gotoText = (EditText) layout.findViewById(R.id.goto_line);
        // gotoText.setText(gotoInstruction.getInstruction());
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

        updateInstructionText();

    }

    /**
     * @param scratchBasicContext program context
     * @return the result of statement
     * @throws InstructionRunException
     */
    @Override
    public String run(ScratchBasicContext scratchBasicContext) throws InstructionRunException {
        Double result;
        if (expression == null) {
            throw new InstructionRunException("Instruction missing expression");
        }
        try {
            result = expression.evaluate(scratchBasicContext.getVariableStore());

        } catch (VariableDoesNotExistException | ExpressionParseException e) {
            throw new InstructionRunException(e.getMessage());
        }
        if (result > 0) {
            gotoInstruction.run(scratchBasicContext);
            evaluatedAs = true;
        } else {
            evaluatedAs = false;
        }
        return "";
    }

    /**
     * @param scratchBasicContext program context
     * @return get the next line to execute
     */
    public void updatePointer(ScratchBasicContext scratchBasicContext) {
        if (evaluatedAs) {
            gotoInstruction.updatePointer(scratchBasicContext);
        } else {
            scratchBasicContext.setCurrentLine(scratchBasicContext.getCurrentLine() + 1);

        }
    }

    /**
     * update the text representation after edit
     */
    public void updateInstructionText() {
        StringBuilder s = new StringBuilder();
        s.append(expression.toString());
        s.append(" ");
        s.append(gotoInstruction.getName());
        s.append(" ");
        s.append(gotoInstruction.getInstruction());
        instruction = s.toString();
    }

    /**
     * @return goto instruction related to the if statement
     */
    public GotoInstruction getGotoInstruction() {
        return gotoInstruction;
    }

    /**
     * @return background colour for instruction
     */
    @Override
    public int getBackgroundColor() {
        return gotoInstruction.getBackgroundColor();
    }
}
