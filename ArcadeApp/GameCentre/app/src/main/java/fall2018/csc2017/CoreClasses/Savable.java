package fall2018.csc2017.CoreClasses;

import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public abstract class Savable {

    /**
     * Save data to a .ser file.
     */
    static void saveToFile(String fileName, Object dataToSave) {
        try {
            File file = new File(AccountManager.contextPath + fileName);
            FileOutputStream output = new FileOutputStream(file);
            ObjectOutputStream outputStream = new ObjectOutputStream(output);
            outputStream.writeObject(dataToSave);
            outputStream.close();
        } catch (IOException e) {
            System.out.println("Hello2");
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    /**
     * Retrieve data from a .ser file.
     *
     * @param saveFileName Name of the file
     */
    @SuppressWarnings("unchecked")
    public static Object loadFromFile(String saveFileName) {
        try {
            File file = new File(AccountManager.contextPath + saveFileName);
            FileInputStream input = new FileInputStream(file);
            ObjectInputStream inputStream = new ObjectInputStream(input);
            return inputStream.readObject();
        } catch (IOException | ClassNotFoundException error) {
            System.out.println("Hello3");
            return error;
        }
    }

    /* Load method used when there's a risk of loading before saving. */
    @SuppressWarnings("unchecked")
    public static Object loadAtStart(String saveFileName, Object initialData) {
        try {
            System.out.println("The path: " + AccountManager.contextPath + saveFileName);
            File file = new File(AccountManager.contextPath + saveFileName);
            FileInputStream input = new FileInputStream(file);
            ObjectInputStream inputStream = new ObjectInputStream(input);
            return inputStream.readObject();
        } catch (IOException e1) {
            System.out.println("Hello!");
            Savable.saveToFile(saveFileName, initialData);
            return initialData;
        } catch (ClassNotFoundException e2) { // Should never happen.
            return null;
        }
    }
}
