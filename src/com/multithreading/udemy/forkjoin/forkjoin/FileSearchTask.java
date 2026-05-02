package com.multithreading.udemy.forkjoin.forkjoin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class FileSearchTask extends RecursiveTask<Boolean> {

    private File dir;
    private String fileName;

    public FileSearchTask(File dir, String fileName) {
        this.dir = dir;
        this.fileName = fileName;
    }

    protected Boolean compute() {

        File[] files = dir.listFiles();

        if (files == null) return false;

        List<FileSearchTask> tasks = new ArrayList<>();

        for (File file : files) {
            if (file.isDirectory()) {
                FileSearchTask task = new FileSearchTask(file, fileName);
                task.fork();
                tasks.add(task);
            } else if (file.getName().equals(fileName)) {
                return true;
            }
        }

        for (FileSearchTask task : tasks) {
            if (task.join()) return true;
        }

        return false;
    }
}
