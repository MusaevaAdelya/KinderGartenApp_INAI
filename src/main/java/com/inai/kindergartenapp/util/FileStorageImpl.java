package com.inai.kindergartenapp.util;

import com.inai.kindergartenapp.configurations.FileStorageConfigurator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileStorageImpl implements FileStorage {
    public static final int DEFAULT_BUFFER_SIZE = 8192;
    private final FileStorageConfigurator fileStorageConfigurator;

    @Override
    public String save(InputStream inputStream) throws IOException {
        String key = generateKey()+".jpg";
        File file = new File(String.format("%s/%s", fileStorageConfigurator.getBasePath(), key));

        try (FileOutputStream outputStream = new FileOutputStream(file, false)) {
            int read;
            byte[] bytes = new byte[DEFAULT_BUFFER_SIZE];
            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
        }

        return key;
    }


    @Override
    public void rewrite(InputStream inputStream, String identificator)  throws IOException{
        File file = new File(String.format("%s/%s", fileStorageConfigurator.getBasePath(),identificator));

        try (FileOutputStream outputStream = new FileOutputStream(file, false)) {
            int read;
            byte[] bytes = new byte[DEFAULT_BUFFER_SIZE];
            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
        }
    }

    private String generateKey(){
        return UUID.randomUUID().toString();
    }


}
