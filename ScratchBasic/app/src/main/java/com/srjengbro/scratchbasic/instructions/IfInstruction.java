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
 * @description code for the if instruciton, if the expression is 1 then it will goto the line
 */
public class IfInstruction extends Instruction {

    /**
     * goto line instruction
     */
    protected GotoInstruction gotoInstruction;
    /**
     * text box for the goto instruction
     */
    protected transient EditText gotoText;
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
     * update after edit
     */
    public void update() {
        String type = expression.opSpinner.getSelectedItem().toString();
        String lhs = expression.lhsText.getText().toString();
        String rhs = expression.rhsText.getText().toString();
        expression = ExpressionMaker.generateExpression(type, lhs, rhs);
        gotoInstruction.parse(gotoText.getText().toString());
        updateInstructionText();

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
     * @param inflater inflator
     * @return the layout to display the editor
     */
    public View getLayout(LayoutInflater inflater) {
        View layout = inflater.inflate(R.layout.inst_if, null);
        expression.layout(layout);
        gotoText = (EditText) layout.findViewById(R.id.goto_line);
        gotoText.setText(gotoInstruction.getInstruction());
        return layout;
    }

    /**
     * @return goto instruction related to the if statement
     */
    public GotoInstruction getGotoInstruction() {
        return gotoInstruction;
    }


    /**
     * @param variableStore variable store
     * @return the result of statement
     * @throws InstructionRunException
     */
    @Override
    public String run(VariableStore variableStore) throws InstructionRunException {
        Integer result;
        if (expression == null) {
            throw new InstructionRunException("Instruction missing expression");
        }
        try {
            result = expression.evaluate(variableStore);

        } catch (VariableDoesNotExistException | ExpressionParseException e) {
            throw new InstructionRunException(e.getMessage());
        }
        if (result > 0) {
            gotoInstruction.run(variableStore);
            evaluatedAs = true;
        } else {
            evaluatedAs = false;
        }
        return "";
    }

    /**
     * @return get the next line to execute
     */
    public Integer getNextLine() {
        if (evaluatedAs) {
            return gotoInstruction.getNextLine();
        }
        return null;
    }
}
