import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Task1 {
    public static ArrayList<File> fileList = new ArrayList<>();
    public static String resStr = "";
    public static void main(String[] args) throws IOException{
        //путь к папке
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the path to the directory: ");
        String path = input.nextLine();
        fileCollect(new File(path));
        //сортировка по имени файла
        fileList.sort((File a, File b) -> a.getName().compareTo(b.getName()));

        String resPath = path + "/result.txt";
        File result = new File(resPath);
        result.createNewFile();

        //запись данных из файлов в один итоговый файл
        BufferedWriter outfile = new BufferedWriter(new FileWriter(resPath, false));
        for (File file : fileList) {
            BufferedReader infile = new BufferedReader(new FileReader(file));
            String str = infile.readLine();
            while (str != null) {
                outfile.write(str + "\n");
                str = infile.readLine();
            }
        }
        outfile.close();
        System.out.println("The data has been successfully rewritten!");
    }
    public static void fileCollect(File path)
    //получение путей к текстовым файлам
    {
        File[] folderFiles = path.listFiles();
        for (File curFile : folderFiles)
        {
            if (curFile.isDirectory())
            {
                fileCollect(curFile);
                continue;
            }
            fileList.add(curFile);
        }
    }

}