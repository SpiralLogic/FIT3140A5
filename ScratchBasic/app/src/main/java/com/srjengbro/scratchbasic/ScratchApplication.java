package com.srjengbro.scratchbasic;

import android.app.Application;
import com.srjengbro.scratchbasic.instructions.Instruction;
import java.util.ArrayList;

/**
 * @author: Sol Jennings 26356015
 * @date: 2/05/16
 */
public class ScratchApplication extends Application {
    protected ArrayList<Instruction> instructions = new ArrayList<>();
    protected VariableStore variableStore = new VariableStore();
}
