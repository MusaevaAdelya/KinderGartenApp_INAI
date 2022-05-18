package com.inai.kindergartenapp.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public interface FileStorage {
    String save(InputStream inputStream) throws IOException;

    void rewrite(InputStream inputStream, String identificator) throws IOException;
}
