package co.simplon.formation.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import co.simplon.formation.model.Agent;
import co.simplon.formation.model.Seance;
import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@NoArgsConstructor
@Service
public class LettreConvocation {

    private static final String RESULT = "src/main/resources/static/convocation.pdf";
    private static final String RESULT2 = "src/main/resources/static/emargement.pdf";
    private static final String IMAGE = "src/main/resources/static/logo.png";
    private static final String SIGNATURE = "Françoise SUEL\n" +
            "Responsable RH & Santé\n" +
            "PIC Bois d’Arcy 78-28";
    private static final String TEXTLOGO = "PIC Bois d’Arcy 78-28\n" +
            "Equipe Ressources & Santé\n";

    private static PdfWriter writer;


    private static String aujourdhui() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }


    public void lettreConvocation(Seance seance) throws DocumentException, IOException {
        Document document = new Document(PageSize.LETTER);

        writer = PdfWriter.getInstance(document, new FileOutputStream(RESULT));

        for (Agent agent : seance.getAgent()) {

            Image img = Image.getInstance(IMAGE);
            img.scaleAbsolute(63, 57);
            img.setAbsolutePosition(30, 700);

            Font fontLogo = new Font(Font.FontFamily.HELVETICA, 7);
            Font fontText = new Font(Font.FontFamily.HELVETICA, 10);
            document.open();

            Paragraph logo = new Paragraph(TEXTLOGO, fontLogo);
            logo.setSpacingBefore(50f);

            Paragraph entete = new Paragraph("Affaire suivie par : \n", fontLogo);
            Phrase phrase1 = new Phrase("     " + seance.getFormateur().getPrenom() + " " + seance.getFormateur().getNom() + "\n", fontLogo);
            Phrase phrase2 = new Phrase("     Formateur\n", fontLogo);
            Phrase phrase3 = new Phrase("     Mail: " + seance.getFormateur().getMail() + "\n", fontLogo);
            Phrase phrase4 = new Phrase("     Tél: " + seance.getFormateur().getTel() + "\n", fontLogo);
            entete.add(phrase1);
            entete.add(phrase2);
            entete.add(phrase3);
            entete.add(phrase4);
            entete.setSpacingBefore(10f);

            Paragraph infoAgent = new Paragraph("Convocation pour " + agent.getNom() + " " + agent.getPrenom());
            infoAgent.setIndentationLeft(300f);
            infoAgent.setSpacingBefore(50f);

            Paragraph date = new Paragraph("Bois d’Arcy, le " + aujourdhui(), fontText);
            date.setIndentationLeft(300f);
            date.setSpacingBefore(50f);


            Paragraph corps = new Paragraph("J’ai le plaisir de vous convier à une formation "
                    + seance.getNom() + " le " + seance.getDateDebut() + "\n\n " +
                    "Cette formation se déroulera sur la PIC de Bois d’Arcy 09h00 à 17h00 en salle de formation "
                    + seance.getSalle().getNom() + " au " + seance.getSalle().getEtage() +
                    "\n\nCe courrier vaut convocation à la formation décrite ci-dessus.", fontText);
            corps.setSpacingBefore(30f);
            corps.setFirstLineIndent(75);

            Paragraph signature = new Paragraph(SIGNATURE, fontText);
            signature.setIndentationLeft(300f);
            signature.setSpacingBefore(50f);

            document.add(img);
            document.add(logo);
            document.add(entete);
            document.add(infoAgent);
            document.add(date);
            document.add(corps);
            document.add(signature);
            document.newPage();


            // step 5
        }
        document.close();
        writer.close();

        Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + RESULT);


    }


    public void feuilleEmargement(Seance seance) throws IOException, DocumentException {
        Document document = new Document(PageSize.LETTER);

        writer = PdfWriter.getInstance(document, new FileOutputStream(RESULT2));
        document.open();

        PdfPTable table = new PdfPTable(3);
        table.setTotalWidth(new float[]{ 144, 144, 144 });
        table.setLockedWidth(true);
        table.addCell("Nom");
        table.addCell("Date");
        table.addCell("Signature");
        for (Agent agent : seance.getAgent()){
            table.addCell(agent.getNom()+" "+agent.getPrenom());
            table.addCell("");
            table.addCell("");
        }

        document.add(table);

        document.close();
        writer.close();

        Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + RESULT2);


    }
}