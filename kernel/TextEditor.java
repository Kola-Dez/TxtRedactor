package kernel;

import javax.swing.*;
import javax.swing.undo.UndoManager;
import java.awt.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class TextEditor extends JFrame {

    private JTextArea textArea; // Текстовая область, где пользователь вводит и редактирует текст
    private JFileChooser fileChooser; // Диалог выбора файлов для открытия и сохранения
    private UndoManager undoManager; // Управляет операциями отмены и повтора для редактирования текста

    /**
     * Конструктор для инициализации приложения текстового редактора.
     * Настраивает пользовательский интерфейс, меню, диалог выбора файлов и менеджер отмены.
     */
    public TextEditor() {
        initializeUI();
        setupMenu();
        setupFileChooser();
        setupUndoManager();
        setVisible(true);
    }

    /**
     * Инициализирует компоненты пользовательского интерфейса текстового редактора.
     * Устанавливает заголовок, иконку, размер, операцию закрытия и текстовую область с полосой прокрутки.
     */
    private void initializeUI() {
        setTitle("TxtRedactor");
        setIconImage(new ImageIcon("img/icon.png").getImage());

        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        textArea = new JTextArea(); // Создаем новую текстовую область для ввода текста
        textArea.setFont(new Font("Arial", Font.PLAIN, 14)); // Устанавливаем шрифт по умолчанию для текстовой области

        undoManager = new UndoManager(); // Инициализируем менеджер отмены операций
        textArea.getDocument().addUndoableEditListener(undoManager); // Подключаем менеджер отмены к документу текстовой области

        JScrollPane scrollPane = new JScrollPane(textArea); // Создаем полосу прокрутки для текстовой области
        add(scrollPane, BorderLayout.CENTER); // Добавляем полосу прокрутки в центр JFrame
    }

    /**
     * Настраивает меню для текстового редактора.
     * Создает экземпляр MenuBar и устанавливает его как менюбар JFrame.
     */
    private void setupMenu() {
        MenuBar menuBar = new MenuBar(textArea, this); // Создаем экземпляр MenuBar
        setJMenuBar(menuBar); // Устанавливаем MenuBar как менюбар JFrame
    }

    /**
     * Настраивает диалог выбора файлов с фильтром для .txt файлов.
     */
    private void setupFileChooser() {
        fileChooser = new JFileChooser(); // Создаем новый экземпляр JFileChooser
        fileChooser.setFileFilter(new FileNameExtensionFilter("Текстовые файлы (*.txt)", "txt")); // Устанавливаем фильтр файлов
    }

    /**
     * Настраивает менеджер отмены для текстовой области.
     * Инициализирует менеджер отмены и подключает его к документу текстовой области.
     */
    private void setupUndoManager() {
        undoManager = new UndoManager(); // Создаем новый экземпляр UndoManager
        textArea.getDocument().addUndoableEditListener(undoManager); // Подключаем менеджер отмены к документу текстовой области
    }

    /**
     * Возвращает экземпляр менеджера отмены.
     * @return Экземпляр UndoManager, используемый для управления операциями отмены и повтора.
     */
    public UndoManager getUndoManager() {
        return undoManager; // Возвращаем экземпляр UndoManager
    }

    /**
     * Открывает файл с помощью статического метода из класса TextEditorActions.
     * Делегирует операцию открытия файла классу TextEditorActions.
     */
    public void openFile() {
        TextEditorActions.openFile(fileChooser, textArea);
    }

    /**
     * Сохраняет текущее содержимое текстовой области в файл.
     * Делегирует операцию сохранения файла классу TextEditorActions.
     */
    public void saveFile() {
        TextEditorActions.saveFile(fileChooser, textArea);
    }

    public void exitApplication() {
        System.exit(1);
    }
}
