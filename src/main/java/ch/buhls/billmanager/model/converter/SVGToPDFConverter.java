package ch.buhls.billmanager.model.converter;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 *
 * @author sdb
 */
public class SVGToPDFConverter
{

    public SVGToPDFConverter()
    {
    }

    public synchronized void convert(File svg, File pdf, File pathInkscape) throws ConverterException
    {
        if (!svg.exists() || !svg.isFile())
        {
            throw new ConverterException("SVG file does not exist" + svg.getAbsolutePath());
        }
        if (pdf.exists())
        {
            throw new ConverterException("PDF file allready exist! " + pdf.getAbsolutePath());
        }

        try
        {
            runInkscapeProcess(svg.getAbsolutePath(), pdf.getAbsolutePath(), pathInkscape.getAbsolutePath());
        }
        catch (Exception ex)
        {
            // clean up
            if (pdf.exists())
            {
                pdf.delete();
            }

            throw new ConverterException("Exception while running process");
        }
    }

    private void runInkscapeProcess(String svg, String pdf, String pathInkscape) throws ConverterException, IOException, InterruptedException
    {
        Process p;
        p = Runtime.getRuntime().exec(pathInkscape + " -f " + svg + " -A " + pdf);
        //p = Runtime.getRuntime().exec("\"" + pathInkscape + "\" -f " + svg + " -A " + pdf); // windows
        //p.waitFor();

        BufferedReader bufferedReader
                = new BufferedReader(
                        new InputStreamReader(p.getErrorStream()));
        String line;
        while ((line = bufferedReader.readLine()) != null)
        {
            System.out.println(line);
        }
        bufferedReader.close();

//        if (p.getInputStream().available() != 0)
//        {
//            throw new ConverterException("Process not successful");
//        }
    }

    static void printWindowsCommand(String command) throws Exception
    {
        System.out.println("Windows command: " + command);
        String line;
        Process process = Runtime.getRuntime().exec("cmd /c " + command);
        Reader r = new InputStreamReader(process.getInputStream());
        BufferedReader in = new BufferedReader(r);
        while ((line = in.readLine()) != null)
        {
            System.out.println(line);
        }
        in.close();
    }
}
