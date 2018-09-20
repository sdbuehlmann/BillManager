package ch.buhls.billmanager.model;

import ch.buhls.billmanager.model.converter.ConverterException;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author simon
 */
public class System
{

    private static final Logger LOG = Logger.getLogger(System.class.getName());
    

    public static synchronized void openPDF(File path) throws IOException {
        Runtime.getRuntime().exec("xdg-open " + path.getAbsolutePath());
    }

    public synchronized void convertSVG2PDF(File svg, File pdf, String pathInkscape) throws ConverterException {
        if (!svg.exists() || !svg.isFile()) {
            throw new ConverterException("SVG file does not exist" + svg.getAbsolutePath());
        }
        if (pdf.exists()) {
            throw new ConverterException("PDF file allready exist! " + pdf.getAbsolutePath());
        }

        try {
            runInkscapeProcess(svg.getAbsolutePath(), pdf.getAbsolutePath(), pathInkscape);
        }
        catch (Exception ex) {
            // clean up
            if (pdf.exists()) {
                pdf.delete();
            }

            throw new ConverterException("Exception while running process");
        }
    }

    private void runInkscapeProcess(String svg, String pdf, String pathInkscape) throws ConverterException, IOException, InterruptedException {
        Process p;
        p = Runtime.getRuntime().exec(pathInkscape + " -f " + svg + " -A " + pdf);
        //p = Runtime.getRuntime().exec("\"" + pathInkscape + "\" -f " + svg + " -A " + pdf); // windows
        //p.waitFor();

        BufferedReader bufferedReader
                = new BufferedReader(
                        new InputStreamReader(p.getErrorStream()));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            java.lang.System.out.println(line);
        }
        bufferedReader.close();

//        if (p.getInputStream().available() != 0)
//        {
//            throw new ConverterException("Process not successful");
//        }
    }

    public static void printPDF(List<File> files) throws Exception {
        for (File file : files) {
            if (!file.exists() || !file.isFile()) {
                throw new Exception("PDF file does not exist" + file.getAbsolutePath());
            }
        }

        Thread thread = new Thread(() -> {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("hp-print");

            for (File file : files) {
                stringBuilder.append(" " + file.getPath());
            }

            try {
                Process p;
                p = Runtime.getRuntime().exec(stringBuilder.toString());

                BufferedReader bufferedReader
                        = new BufferedReader(
                                new InputStreamReader(p.getErrorStream()));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    java.lang.System.out.println(line);
                }
                bufferedReader.close();
            }
            catch (IOException ex) {
                LOG.log(Level.SEVERE, ex.getMessage());
            }
        });
        
        thread.start();
    }
}
