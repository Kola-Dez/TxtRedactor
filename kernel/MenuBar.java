package kernel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class MenuBar extends JMenuBar {
    private final TextEditor editor;

    /**
     * Конструктор класса MenuBar.
     * Инициализирует меню "Файл", "Правка", "Шрифт" и "Стиль".
     * @param textArea JTextArea для настройки меню "Правка" (используется только для вырезания, копирования, вставки и выделения)
     * @param editor Экземпляр TextEditor для доступа к методам открытия, сохранения, отмены и изменения стиля текста
     */
    public MenuBar(JTextArea textArea, TextEditor editor) {
        this.editor = editor;
        setupFileMenu();
        setupEditMenu(textArea);
        setupFontMenu(textArea);
    }

    /**
     * Настраивает меню "Файл".
     * Добавляет пункты "Открыть", "Сохранить" и "Выход" с соответствующими обработчиками событий.
     */
    private void setupFileMenu() {
        JMenu fileMenu = new JMenu("Файл");
        add(fileMenu);

        JMenuItem openItem = new JMenuItem("Открыть");
        fileMenu.add(openItem);
        openItem.addActionListener(e -> editor.openFile());

        JMenuItem saveItem = new JMenuItem("Сохранить");
        fileMenu.add(saveItem);
        saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
        saveItem.addActionListener(e -> editor.saveFile());

        fileMenu.addSeparator();

        JMenuItem exitItem = new JMenuItem("Выход");
        fileMenu.add(exitItem);
        exitItem.addActionListener(e -> editor.exitApplication());
    }

    /**
     * Настраивает меню "Правка".
     * Добавляет пункты "Отменить", "Вырезать", "Копировать", "Вставить" и "Выделить все".
     * @param textArea JTextArea, для которой настраивается меню "Правка"
     */
    private void setupEditMenu(JTextArea textArea) {
        JMenu editMenu = new JMenu("Правка");
        add(editMenu);

        JMenuItem undoItem = new JMenuItem("Отменить");
        editMenu.add(undoItem);
        undoItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK));
        undoItem.addActionListener(e -> undo());

        JMenuItem cutItem = new JMenuItem("Вырезать");
        editMenu.add(cutItem);
        cutItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK));
        cutItem.addActionListener(e -> textArea.cut());

        JMenuItem copyItem = new JMenuItem("Копировать");
        editMenu.add(copyItem);
        copyItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));
        copyItem.addActionListener(e -> textArea.copy());

        JMenuItem pasteItem = new JMenuItem("Вставить");
        editMenu.add(pasteItem);
        pasteItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK));
        pasteItem.addActionListener(e -> textArea.paste());

        editMenu.addSeparator();

        JMenuItem selectAllItem = new JMenuItem("Выделить все");
        editMenu.add(selectAllItem);
        selectAllItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK));
        selectAllItem.addActionListener(e -> textArea.selectAll());
    }

    /**
     * Настраивает меню "Шрифт".
     * Добавляет пункты "Увеличить шрифт" и "Уменьшить шрифт" с соответствующими обработчиками событий.
     * @param textArea JTextArea, для которой настраивается меню "Шрифт"
     */
    private void setupFontMenu(JTextArea textArea) {
        JMenu fontMenu = new JMenu("Шрифт");
        add(fontMenu);

        JMenuItem increaseFontItem = new JMenuItem("Увеличить шрифт");
        fontMenu.add(increaseFontItem);
        increaseFontItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_EQUALS, InputEvent.CTRL_DOWN_MASK));
        increaseFontItem.addActionListener(e -> increaseFontSize(textArea));

        JMenuItem decreaseFontItem = new JMenuItem("Уменьшить шрифт");
        fontMenu.add(decreaseFontItem);
        decreaseFontItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, InputEvent.CTRL_DOWN_MASK));
        decreaseFontItem.addActionListener(e -> decreaseFontSize(textArea));
    }

    /**
     * Отменяет последнее действие в текстовой области.
     * Проверяет возможность отмены и, если возможно, выполняет отмену.
     */
    private void undo() {
        if (editor.getUndoManager().canUndo()) {
            editor.getUndoManager().undo();
        }
    }

    /**
     * Увеличивает размер шрифта текстовой области на 1 пункт.
     * @param textArea JTextArea, чей размер шрифта нужно увеличить
     */
    private void increaseFontSize(JTextArea textArea) {
        Font currentFont = textArea.getFont();
        int newSize = currentFont.getSize() + 1;
        textArea.setFont(new Font(currentFont.getFontName(), currentFont.getStyle(), newSize));
    }

    /**
     * Уменьшает размер шрифта текстовой области на 1 пункт, но не меньше 10 пунктов.
     * @param textArea JTextArea, чей размер шрифта нужно уменьшить
     */
    private void decreaseFontSize(JTextArea textArea) {
        Font currentFont = textArea.getFont();
        int newSize = Math.max(currentFont.getSize() - 1, 10);
        textArea.setFont(new Font(currentFont.getFontName(), currentFont.getStyle(), newSize));
    }
}
