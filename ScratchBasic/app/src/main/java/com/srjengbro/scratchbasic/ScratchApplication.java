package com.srjengbro.scratchbasic;

import android.app.Application;
import com.srjengbro.scratchbasic.instructions.Instruction;
import java.util.ArrayList;

/**
 * @author      Sol Jennings
 * @description
 */
public class ScratchApplication extends Application {

    private ScratchBasicContext scratchBasicContext = new ScratchBasicContext();

    public ScratchBasicContext getSratchBasicContext() {
        return this.scratchBasicContext;
    }
    public void setScratchBasicContext(ScratchBasicContext scratchBasicContext) {
        this.scratchBasicContext = scratchBasicContext;
    }
}
