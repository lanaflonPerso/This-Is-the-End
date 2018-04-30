package co.simplon.formation.outils;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.*;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LettreConvocation {

    /** Path to the resulting PDF file. */
    public static final String IMAGE = "src/main/resources/logo.jpeg";
    private static final String RESULT
            = "C:/pdf/hello_letter.pdf";


    public static void lettreConvocation(String nom, String prenom) throws DocumentException, IOException {
        // step 1
        // Specifying the page size
        Document document = new Document(PageSize.LETTER);
        // step 2
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(RESULT));
        // step 3
        document.open();
        // step 4
        Paragraph paragraph = new Paragraph();
        Anchor anchor = new Anchor("jean-yves.poirier@laposte.fr");
        paragraph.setIndentationLeft(50);
        paragraph.add("Affaire suivie par : \n" +
                "Jean-Yves POIRIER\n" +
                "Formateur\n" +
                "Mail : "+ anchor + "\n" +
                "Tél : 01 30 23 84 59\n");

        document.add(paragraph );
        document.add(new Paragraph(prenom));

        Image img = Image.getInstance(IMAGE);
        img.scaleAbsolute(50,50);
        img.setAbsolutePosition(10, 400);
        writer.getDirectContent().addImage(img);
       document.add(img);

        // step 5

        document.close();

        String pdfFile=RESULT;
        if (pdfFile.toString().endsWith(".pdf")) {
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + pdfFile);
        } else {
            //For cross platform use
            Desktop desktop = Desktop.getDesktop();
            desktop.open(new File(pdfFile));
        }

    }
}
