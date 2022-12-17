package edu.psuti.pe.gui.apps.dolphin;

import edu.psuti.pe.gui.helper.CustomTextLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class FilesViewportPanel extends JPanel {
    // панель прокурутки для вьюпорта с файлами
    private JScrollPane scrollViewportPane = new JScrollPane();
    private JPanel viewportPanel = new JPanel(); // панель с самим списком файлов
    // панель краткой информации внизу окна
    private JPanel viewportStatePanel = new JPanel();
    private CustomTextLabel viewportStateLabel = new CustomTextLabel("placeholder text",
            new Font("Noto Sans Regular", Font.PLAIN, 13), "black", false, 0, JLabel.CENTER, JLabel.LEFT);

    // Список с текущими элементами
    private ArrayList<FilesViewportItemPanel> filesViewportItems = new ArrayList<>(100);
    boolean selectedItemExistenceFlag = false;
    ToolbarPanel toolbarPanel;
    int foldersCount = 0;
    int filesCount = 0;

    public FilesViewportPanel(ToolbarPanel toolbar) {
        toolbarPanel = toolbar;

        setLayout(new GridBagLayout());
        setOpaque(false);
        setBackground(Color.green);

        setMinimumSize(new Dimension(200, 200));
        setPreferredSize(new Dimension(200, 200));
        setMaximumSize(new Dimension(200, 200));

        // Настройка и добавление дочерних панелей
        GridBagConstraints strutConstraints = new GridBagConstraints();
        strutConstraints.gridx = 0;
        strutConstraints.gridy = 0;

        add(Box.createVerticalStrut(1), strutConstraints);
        setupViewportPanel();
        strutConstraints.gridy = 2;
        add(Box.createVerticalStrut(1), strutConstraints);
        setupViewportStatePanel();

        updateList(Paths.get("/home"));
    }

    public boolean isThereSelectedItem() { return selectedItemExistenceFlag; }
    public void setSelectedItemExistenceFlag(boolean bool) { selectedItemExistenceFlag = bool; }

    public void resetSelectionOnAllItems() {
        for (FilesViewportItemPanel item : filesViewportItems) {
            item.setOpaque(false);
            item.repaint();
            item.isSelected = false;
        }
    }

    private void setupViewportPanel() {
        scrollViewportPane.setMinimumSize(new Dimension(100, 100));
        scrollViewportPane.setPreferredSize(new Dimension(100, 100));
        scrollViewportPane.getVerticalScrollBar().setUnitIncrement(16); // увеличение скорости прокрутки
        scrollViewportPane.setViewportView(viewportPanel); // вьюпорт с файлами становится вьюпортом для панели прокрутки

        viewportPanel.setLayout(new BoxLayout(viewportPanel, BoxLayout.PAGE_AXIS));
        viewportPanel.setOpaque(true);
        viewportPanel.setBackground(Color.white);
        viewportPanel.addMouseListener(new FilesViewportMouseListener());
        // Размеры для ViewportPanel не проставлялись, чтобы он ресайзился отностельно своих элементов.
        // Ниже закгруглённая граница, как в Dolphin, но она сейчас не нужна, т.к. ScrollPane сам создал границу.
        //viewportPanel.setBorder(new RoundedBorder(new Color(175, 178, 180), 6, new Insets(0, 0, 0, 0)));

        GridBagConstraints viewportConstraints = new GridBagConstraints();
        viewportConstraints.gridx = 0;
        viewportConstraints.gridy = 1;
        viewportConstraints.weightx = 0.5;
        viewportConstraints.weighty = 0.5;
        viewportConstraints.fill = GridBagConstraints.BOTH;
        add(scrollViewportPane, viewportConstraints); // в основную панель добавляется именно панель прокурутки
    }

    public void updateList(Path path) {
        try {
            /*
             * normalize() приводит путь к обычному виду, без различных переходов типа точки.
             * toAbsolutePath() показывает весь путь от самого корня "\"
             */
            toolbarPanel.updatePath(path.toAbsolutePath().normalize().toString()); // заполняем текстовую строку полным путём к файлу

            /*
             * Cобираем информацию о всех файлах по указанному пути, с помощью потока данных (Stream API) все файлы пропускаем
             * через конструктор FileInfo, затем все экземпляры FileInfo собираем в список.
             */
            List<FileInfo> rawFilesInfo = Files.list(path).map(FileInfo::new).collect(Collectors.toList());

            // Очистка списка, контейнера и счётчиков файлов
            filesViewportItems.clear();
            viewportPanel.removeAll();
            scrollViewportPane.validate();
            foldersCount = 0;
            filesCount = 0;

            // Заполнение списка, счётчиков и панели файлов новыми данными
            for (FileInfo info : rawFilesInfo) {
                if (info.getType() == FileInfo.FileType.DIRECTORY) {
                    filesViewportItems.add(new FilesViewportItemPanel(info.getFileName(), false, this));
                    ++foldersCount;
                } else {
                    filesViewportItems.add(new FilesViewportItemPanel(info.getFileName(), true, this));
                    ++filesCount;
                }
            }
            addItemsToViewport(filesViewportItems);
            updateState();
        } catch (IOException e) {
            System.err.println("Cannot update list of files on provided path.");
        }
    }

    private void addItemsToViewport(Collection<FilesViewportItemPanel> items) {
        for (FilesViewportItemPanel item : items) {
            viewportPanel.add(item);
        }
        scrollViewportPane.validate();
        viewportPanel.repaint();
    }

    private void updateState() {
        viewportStatePanel.remove(viewportStateLabel);
        StringBuilder sb = new StringBuilder();
        sb.append("Папок: ");
        sb.append(foldersCount);
        sb.append(". Файлов: ");
        sb.append(filesCount);
        viewportStateLabel = new CustomTextLabel(sb.toString(), new Font("Noto Sans Regular", Font.PLAIN, 13),
                "black", false, 0,
                JLabel.CENTER, JLabel.LEFT);
        viewportStatePanel.add(viewportStateLabel);
        viewportStatePanel.validate();
    }

    private void setupViewportStatePanel() {
        viewportStatePanel.setLayout(new BoxLayout(viewportStatePanel, BoxLayout.LINE_AXIS));
        viewportStatePanel.setOpaque(false);
        viewportStatePanel.setBackground(Color.orange);

        viewportStatePanel.setMinimumSize(new Dimension(200, 25));
        viewportStatePanel.setPreferredSize(new Dimension(200, 25));
        viewportStatePanel.setMaximumSize(new Dimension(200, 25));

        viewportStateLabel = new CustomTextLabel("placeholder text", new Font("Noto Sans Regular", Font.PLAIN, 13),
                "black", false, 0,
                JLabel.CENTER, JLabel.LEFT);
        viewportStatePanel.add(Box.createHorizontalStrut(8));
        viewportStatePanel.add(viewportStateLabel);

        GridBagConstraints stateConstraints = new GridBagConstraints();
        stateConstraints.gridx = 0;
        stateConstraints.gridy = 3;
        stateConstraints.fill = GridBagConstraints.HORIZONTAL;
        add(viewportStatePanel, stateConstraints);
    }

    class FilesViewportMouseListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {}

        @Override
        public void mousePressed(MouseEvent e) {
            resetSelectionOnAllItems();
        }

        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {}

        @Override
        public void mouseExited(MouseEvent e) {}
    }
}
