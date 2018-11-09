package com.json.ignore.filter.file;

import com.json.ignore.FieldAccessException;
import com.json.ignore.filter.FilterFields;
import com.json.ignore.mock.MockClasses;
import com.json.ignore.mock.MockHttpRequest;
import com.json.ignore.mock.MockMethods;
import com.json.ignore.request.RequestSession;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.util.Arrays;

public class FileFilterTest {

    @Test
    public void testMethodWithoutAnnotations() {
        FileFilter fileFilter = new FileFilter(MockMethods.methodWithoutAnnotations());
        FilterFields filterFields = fileFilter.getFields(MockClasses.getUserMock(),
                new RequestSession(MockHttpRequest.getMockAdminRequest()));
        Assert.assertEquals(0, filterFields.getFieldsMap().size());
    }

    @Test
    public void testMethodNotExistFile() {
        FileFilter fileFilter = new FileFilter(MockMethods.fileNotExist());
        FilterFields filterFields = fileFilter.getFields(MockClasses.getUserMock(),
                new RequestSession(MockHttpRequest.getMockAdminRequest()));
        Assert.assertEquals(0, filterFields.getFieldsMap().size());
    }

    @Test
    public void testMethodBadConfig() {
        FileFilter fileFilter = new FileFilter(MockMethods.fileBadConfig());
        FilterFields filterFields = fileFilter.getFields(MockClasses.getUserMock(),
                new RequestSession(MockHttpRequest.getMockAdminRequest()));
        Assert.assertEquals(0, filterFields.getFieldsMap().size());
    }

    @Test
    public void testFileAnnotationClassNotFound() {
        FileFilter fileFilter = new FileFilter(MockMethods.fileAnnotationClassNotFound());
        FilterFields filterFields = fileFilter.getFields(MockClasses.getUserMock(),
                new RequestSession(MockHttpRequest.getMockAdminRequest()));
        Assert.assertEquals(Arrays.asList("id", "password"), filterFields.getFieldsMap().get(null));
    }

    @Test
    public void testFileAnnotationEmpty() {
        FileFilter fileFilter = new FileFilter(MockMethods.fileAnnotationEmpty());
        FilterFields filterFields = fileFilter.getFields(MockClasses.getUserMock(),
                new RequestSession(MockHttpRequest.getMockAdminRequest()));

        Assert.assertEquals(0, filterFields.getFieldsMap().size());
    }

    @Test(expected = FieldAccessException.class)
    public void testIOException() throws IOException {
        String fileName = FileFilter.getFileName("config_io_exception.xml");
        File file = new File(fileName);

        try (FileOutputStream in = new FileOutputStream(file)) {
            java.nio.channels.FileLock lock = in.getChannel().lock();
            try {
                FileFilter fileFilter = new FileFilter(MockMethods.fileLocked());
                FilterFields filterFields = fileFilter.getFields(MockClasses.getUserMock(),
                        new RequestSession(MockHttpRequest.getMockAdminRequest()));

                Assert.assertEquals(0, filterFields.getFieldsMap().size());
            } finally {
                lock.release();
            }
        }
    }

}
