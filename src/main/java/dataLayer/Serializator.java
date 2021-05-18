package dataLayer;

import java.io.*;

public class Serializator  implements  Serializable{
    public void serializable(String fileName, Object obj) throws IOException {
        FileOutputStream outputStream = new FileOutputStream(fileName);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(obj);
        outputStream.close();
        objectOutputStream.close();
    }

    public Object deserializable(String fileName) throws IOException, ClassNotFoundException {
        Object obj;
        FileInputStream inputStream = new FileInputStream(fileName);
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        obj = objectInputStream.readObject();
        inputStream.close();
        objectInputStream.close();
        return obj;
    }
}
