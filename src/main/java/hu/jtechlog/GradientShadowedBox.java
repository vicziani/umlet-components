package hu.jtechlog;

import java.awt.*;
import java.util.*;

import com.baselet.control.util.Utils;
import com.baselet.element.old.custom.CustomElement;

public class GradientShadowedBox extends CustomElement {

    @Override
    public void paint() {
        Vector<String> textlines = Utils.decomposeStrings(this.getPanelAttributes());

        // Változó inicializációk
        int doNotHideBorder = 1;
        int shadowOffset = (int) (5 * zoom);
        int roundedCorner = 5;

        Color bg = bgColor;
        if (bg.equals(new Color(255, 255, 255, 0))) {
            bg = new Color(Integer.decode("#58ACFA"));
        }

        // Árnyék
        g2.setColor(new Color(200, 200, 200));
        g2.fillRoundRect(shadowOffset, shadowOffset, getWidth() - shadowOffset - doNotHideBorder, getHeight() - shadowOffset - doNotHideBorder, 10, 10);

        // Színátmenettel feltöltött téglalap
        g2.setPaint(new GradientPaint(1, 0, bg, getWidth() + 1, 0, Color.WHITE, false));
        g2.fillRoundRect(doNotHideBorder, doNotHideBorder, getWidth() - shadowOffset, getHeight() - shadowOffset, roundedCorner, roundedCorner);

        // Keret
        g2.setColor(Color.BLACK);
        g2.drawRoundRect(doNotHideBorder, doNotHideBorder, getWidth() - shadowOffset, getHeight() - shadowOffset, roundedCorner, roundedCorner);

        // Szöveg
        int y = textHeight();
        for (String textLine : textlines) {
            if (!textLine.contains("=")) {
                printCenter(textLine, y);
                y += textHeight();
            }
        }
    }
}
