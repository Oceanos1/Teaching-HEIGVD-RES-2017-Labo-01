package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;

import java.io.File;
import java.util.LinkedList;

/**
 * This implementation of the IFileExplorer interface performs a depth-first
 * exploration of the file system and invokes the visitor for every encountered
 * node (file and directory). When the explorer reaches a directory, it visits all
 * files in the directory and then moves into the subdirectories.
 *
 * @author Olivier Liechti
 */
public class DFSFileExplorer implements IFileExplorer {

    @Override
    public void explore(File rootDirectory, IFileVisitor visitor) {
        visitor.visit(rootDirectory);

        //Stop condition
        if (!rootDirectory.isDirectory()) {//work when there is no file
            return;
        }
        LinkedList<File> dirs = new LinkedList<>();
        for (File file : rootDirectory.listFiles()) {
            if (file.isDirectory()) {
                dirs.add(file); //to explore recursivly
            } else {
                visitor.visit(file);
            }
        }

        for (File d : dirs) {
            explore(d, visitor);
        }
    }

}
