package Model;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import java.io.File;

import java.io.FileOutputStream;
import java.io.StringReader;

public class PDFConverter {

    public static void convertToPDF(String htmlContent, String outputPath) {
        try {
            // Create a Document object
            Document document = new Document();

            // Create a PdfWriter instance to write the document to a file
            File file = new File(outputPath);
            file.getParentFile().mkdirs(); // Will create parent directories if not exists
            file.createNewFile();
            FileOutputStream s = new FileOutputStream(file,false);
            
            PdfWriter writer = PdfWriter.getInstance(document, s);

            // Open the document
            document.open();

            // Create a StringReader to read the HTML content
            StringReader reader = new StringReader(htmlContent);

            // Parse the HTML and write it to the PDF
            XMLWorkerHelper.getInstance().parseXHtml(writer, document, reader);

            // Close the document
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
