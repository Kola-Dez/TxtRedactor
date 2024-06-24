package kernel;

import javax.swing.*;
import java.io.*;

public class TextEditorActions {

    /**
     * Метод открывает выбранный пользователем файл и загружает его содержимое в JTextArea.
     * @param fileChooser Диалог выбора файла
     * @param textArea JTextArea, в которую загружается содержимое файла
     */
    public static void openFile(JFileChooser fileChooser, JTextArea textArea) {
        int returnVal = fileChooser.showOpenDialog(null); // Открываем диалог выбора файла
        if (returnVal == JFileChooser.APPROVE_OPTION) { // Если пользователь выбрал файл
            File file = fileChooser.getSelectedFile(); // Получаем выбранный файл
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                textArea.setText(""); // Очищаем текстовую область перед загрузкой нового файла
                String line;
                while ((line = reader.readLine()) != null) {
                    textArea.append(line + "\n"); // Добавляем строки из файла в текстовую область
                }
            } catch (IOException e) {
                showErrorDialog("Ошибка при открытии файла: " + e.getMessage()); // В случае ошибки показываем диалог с ошибкой
            }
        }
    }

    /**
     * Метод сохраняет содержимое JTextArea в выбранный пользователем файл.
     * @param fileChooser Диалог выбора файла
     * @param textArea JTextArea, содержимое которой сохраняется в файл
     */
    public static void saveFile(JFileChooser fileChooser, JTextArea textArea) {
        int returnVal = fileChooser.showSaveDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(textArea.getText());
            } catch (IOException e) {
                showErrorDialog("Ошибка при сохранении файла: " + e.getMessage());
            }
        }
    }

    /**
     * Приватный метод для отображения диалога с ошибкой.
     * @param message Сообщение об ошибке
     */
    private static void showErrorDialog(String message) {
        JOptionPane.showMessageDialog(null, message, "Ошибка", JOptionPane.ERROR_MESSAGE);
    }
}
