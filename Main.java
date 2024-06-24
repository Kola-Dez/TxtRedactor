import kernel.TextEditor;

import javax.swing.*;

public class Main {
    /**
     * Главный метод для запуска приложения TextEditor.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(TextEditor::new); // приложение в потоке обработки событий
    }
}
