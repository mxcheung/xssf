package datatable.xssf.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class FileUtil {

    public static List<String> readFileIntoList(String fileName) {
    //	IOUtils.readLines(input)
        InputStream inputStream = null;
        Resource resource = null;
        resource = (Resource) new ClassPathResource(fileName);
        try {
            inputStream = resource.getInputStream();
        } catch (IOException e) {
        }
        Scanner scanner = new Scanner(inputStream);
        
        
        List<String> ret = new ArrayList<String>();

        while (scanner.hasNextLine()) {
        	ret.add(scanner.nextLine());
        }
        scanner.close();
        return ret;
    }
}
