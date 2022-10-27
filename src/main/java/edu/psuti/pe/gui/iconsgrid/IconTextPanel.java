package edu.psuti.pe.gui.iconsgrid;

import edu.psuti.pe.gui.helper.FilledLayeredPane;

import javax.swing.*;
import java.awt.*;

// Панель, формирующая текст для иконки-ярлыка.
public class IconTextPanel extends JPanel {
    private FilledLayeredPane layeredPane = new FilledLayeredPane();
    private JPanel whiteTextPanel = new JPanel(new BorderLayout());
    private IconTextLayerLabel whiteLabel;
    public JPanel blackTextPanel = new JPanel(null);
    private IconTextLayerLabel blackLabel;

    public IconTextPanel(String text) {
        // Панель, включающая в себя многослойную панель
        setOpaque(false);
        setBackground(Color.RED);
        setLayout(new BorderLayout());

        // Панель с белым текстом для верхнего слоя
        whiteTextPanel.setOpaque(false);
        whiteLabel = new IconTextLayerLabel(text, "white");
        whiteLabel.setBounds(1, 1, 80, 37);
        whiteTextPanel.add(whiteLabel);

        // Панель с чёрным текстом, смещённым на 1 пиксель по диагонали, для нижнего слоя
        blackTextPanel.setOpaque(false);
        blackTextPanel.setBackground(Color.BLUE);
        blackLabel = new IconTextLayerLabel(text, "black");
        blackLabel.setBounds(1, 1, 80, 37);
        blackTextPanel.add(blackLabel);

        // Добавление панелей на соответствующие слои
        layeredPane.add(blackTextPanel, new Integer(0));
        layeredPane.add(whiteTextPanel, new Integer(1));

        // Окончательное формирование панели с текстом названия ярлыка
        add(layeredPane);
    }
}
