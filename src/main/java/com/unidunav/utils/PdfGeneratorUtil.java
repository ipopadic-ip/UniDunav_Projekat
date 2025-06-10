package com.unidunav.utils;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.unidunav.predmet.dto.EvaluacijaZnanjaDTO;
import com.unidunav.predmet.model.PohadjanjePredmeta;
import com.unidunav.student.model.Student;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class PdfGeneratorUtil {

	public static ByteArrayInputStream generateEvaluacijaPdf(
	        EvaluacijaZnanjaDTO evaluacija,
	        PohadjanjePredmeta pohadjanje,
	        String tipEvaluacijeNaziv,
	        Student student) {

	    Document document = new Document();
	    ByteArrayOutputStream out = new ByteArrayOutputStream();

	    try {
	        PdfWriter.getInstance(document, out);
	        document.open();

	        Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
	        Paragraph title = new Paragraph("Evaluacija Znanja", headerFont);
	        title.setAlignment(Element.ALIGN_CENTER);
	        document.add(title);

	        document.add(new Paragraph(" "));

	        String ime = student.getUser().getIme();
	        String prezime = student.getUser().getPrezime();
	        String brojIndeksa = student.getBrojIndeksa();
	        String predmetNaziv = pohadjanje.getPredmet().getNaziv();
	        Integer brojBodova = evaluacija.getBrojBodova();

	        document.add(new Paragraph("Student: " + ime + " " + prezime));
	        document.add(new Paragraph("Broj indeksa: " + brojIndeksa));
	        document.add(new Paragraph("Predmet: " + predmetNaziv));
	        document.add(new Paragraph("Datum i vreme: " + evaluacija.getVremePocetka()));
	        document.add(new Paragraph("Tip evaluacije: " + tipEvaluacijeNaziv));
	        document.add(new Paragraph("Broj bodova: " + (brojBodova != null ? brojBodova : "")));

	        document.close();
	    } catch (DocumentException ex) {
	        ex.printStackTrace();
	    }

	    return new ByteArrayInputStream(out.toByteArray());
	}}