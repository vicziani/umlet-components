package hu.jtechlog;

import com.baselet.control.util.Utils;
import com.baselet.element.old.custom.CustomElement;

import java.util.Vector;

public class StepBox extends CustomElement {

    @Override
    public void paint() {
        Vector<String> textlines = Utils.decomposeStrings(this.getPanelAttributes());

        int x = 0;
        int y = textHeight();
        for (String textLine : textlines) {
            print(textLine, x, y);
            y = y + textHeight();
            x = x + textWidth(textLine);
        }
    }
}
