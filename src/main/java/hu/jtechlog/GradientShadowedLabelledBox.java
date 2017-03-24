package hu.jtechlog;

import java.awt.*;
import java.util.*;

import com.baselet.control.util.Utils;
import com.baselet.element.old.custom.CustomElement;

public class GradientShadowedLabelledBox extends CustomElement {

    @Override
    public void paint() {
        Vector<String> textlines = Utils.decomposeStrings(this.getPanelAttributes());

        try {
            // Változó inicializációk
            int doNotHideBorder = 1;
            int shadowOffset = (int) (5 * zoom);
            int roundedCorner = 5;
            int space = (int) (4 * zoom);

            Color bg = bgColor;
            if (bg.equals(new Color(255, 255, 255, 0))) {
                bg = new Color(Integer.decode("#58ACFA"));
            }

            // Properties mező feldolgozása
            Properties props = new Properties();
            props.load(new java.io.StringReader(getPanelAttributes()));

            int leftWidth = 0;
            if (props.getProperty("l") != null) {
                leftWidth = (int) (textWidth(props.getProperty("l")) * zoom);
                printLeft(props.getProperty("l"), (int) ((getHeight() / 2.0) / zoom));
            }
            int rightWidth = 0;
            if (props.getProperty("r") != null) {
                rightWidth = (int) (textWidth(props.getProperty("r")) * zoom);
                printRight(props.getProperty("r"), (int) ((getHeight() / 2.0) / zoom));
            }

            // Árnyék
            g2.setColor(new Color(200, 200, 200));
            g2.fillRoundRect(leftWidth + shadowOffset + space, shadowOffset, getWidth() - shadowOffset - leftWidth - rightWidth - doNotHideBorder - 2 * space, getHeight() - shadowOffset - doNotHideBorder, 10, 10);

            // Színátmenettel feltöltött téglalap
            g2.setPaint(new GradientPaint(1, 0, bg, getWidth() + 1, 0, Color.WHITE, false));
            g2.fillRoundRect(doNotHideBorder + leftWidth + space, doNotHideBorder, getWidth() -  leftWidth - rightWidth - shadowOffset - 2 * space, getHeight() -  shadowOffset, roundedCorner, roundedCorner);

            // Keret
            g2.setColor(Color.BLACK);
            g2.drawRoundRect(doNotHideBorder + leftWidth + space, doNotHideBorder, getWidth() -  leftWidth - rightWidth - shadowOffset - 2 * space, getHeight() -  shadowOffset, roundedCorner, roundedCorner);

            // Szöveg
            int y = textHeight();
            for (String textline : textlines) {
                if (!textline.contains("=")) {
                    print(textline, (int) ((((getWidth() / zoom - leftWidth - rightWidth - textWidth(textline)) / 2.0))) + leftWidth, y);
                    y += textHeight();
                }
            }
        } catch (Exception e) {
            printLeft(e.getMessage(), textHeight());
        }

    }
}
