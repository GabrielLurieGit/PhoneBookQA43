package experiments;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.thoughtworks.qdox.model.expression.Lambda;
import org.etsi.uri.x01903.v13.GenericTimeStampType;

import javax.swing.text.html.parser.Entity;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoRunner {
    public static void testLottoFiles(){
        String filePath = "src\\ile\\Lotto.csv";
        int startRow = 2;
        int startColumn = 3;
        int endRow = 100;
        int endColumn = 8;
        if(filePath.endsWith(".csv")){

        }else if(filePath.endsWith(".xls")){

        }
    }


    private static void readCSV(String filePath, int startRow, int startColumn, int endRow, int endColumn) throws FileNotFoundException {
        Map<Integer, Integer> valueCounts = new HashMap<>();
        try (CSVReader reader = new CSVReader(new BufferedReader(new FileReader(filePath)))){
            List<String[]> lines = reader.readAll();
            for (int i = startRow-1; i < endRow; i++){
                String[] line = lines.get(i);
                for (int j = startColumn-1; j < endColumn; j++){
                    if (!line[j].isEmpty()){
                        int value = Integer.parseInt(line[j]);
                        valueCounts.put(value, valueCounts.getOrDefault(value,0)+1);
                    }
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (CsvException e) {
            throw new RuntimeException(e);
        }
        printMap(valueCounts);

    }


    public static void printMap(Map<Integer,Integer>valueCounts){
        valueCounts.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEach(entity -> System.out.println(entity.getKey()+" : " + entity.getValue()));
    }


    public static void main(String[] args) {
        testLottoFiles();
    }

}
