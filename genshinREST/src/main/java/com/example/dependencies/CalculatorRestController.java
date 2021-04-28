package com.example.dependencies;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.text.SimpleDateFormat;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;




@RestController
public class CalculatorRestController {

    @Autowired
    private EquipoRepository er;
	@Autowired
	ResourceLoader resourceLoader;
	
	
	//@RequestMapping(value = "/generarPDF/{id}", method=RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@RequestMapping(value = "/descargarPDF/{id}", method=RequestMethod.GET)
	@ResponseBody
    public ResponseEntity<String> descargarPDF(@PathVariable long id) throws IOException {
    
        Optional<Equipo> equipo = er.findById(id);
        
        String descripcionEquipo =
        "Nombre Personaje " + equipo.get().getPj().getName()+
        "\n\n"+
        "Nombre Arma: " + equipo.get().getArm().getNombre()+
       ("Level Personaje:  "+ equipo.get().getPj().getLevel())+
       "\n"+
       ("ATK Personaje:  "+ equipo.get().getPj().getATK())+
       "\n"+
       ("Atributo Personaje:  "+ equipo.get().getPj().getAtribute())+
       "\n"+
       ("DanyoCrit Personaje:  "+ equipo.get().getPj().getDanyoCrit())+
       "\n"+
        ("DEF Personaje:  "+ equipo.get().getPj().getDEF())+
        "\n"+
        ("Elemental Bonus Personaje:  "+ equipo.get().getPj().getElementalBonus())+
        "\n"+
        ("Energy Recharge Personaje:  "+ equipo.get().getPj().getEnergyRecharge())+
        "\n"+
        ("Mastery Personaje:  "+ equipo.get().getPj().getMastery())+
        "\n"+
        ("MaxHP Personaje:  "+ equipo.get().getPj().getMaxHP())+
        "\n"+
        ("Physical ATK Personaje:  "+ equipo.get().getPj().getPATK())+
        "\n"+
        ("Probabilidad Personaje:  "+ equipo.get().getPj().getProbCrit());
        
        return new ResponseEntity<String>(descripcionEquipo, HttpStatus.OK);
        
        
    }
	

	
    private ByteArrayOutputStream createInvoice(Equipo equipo) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
  
        
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        PDDocument document = new PDDocument();
        PDPage page = new PDPage(PDRectangle.A4);
        document.addPage(page);

        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        contentStream.beginText();
        contentStream.setFont(PDType1Font.TIMES_BOLD, 26);
        contentStream.setLeading(40f);
        contentStream.newLineAtOffset(60, 760);
        contentStream.showText("Nombre Personaje " + equipo.getPj().getName());
        contentStream.newLine();
        contentStream.setFont(PDType1Font.TIMES_ROMAN, 14);
        contentStream.setLeading(16f);
        contentStream.showText("Nombre Arma: " + equipo.getArm().getNombre());
        contentStream.newLine();
        contentStream.showText("Nombre Artefacto: " + formatter.format(equipo.getArtefact1().getNombre()));
        contentStream.newLine();
        contentStream.newLine();
        contentStream.newLine();
        contentStream.showText("Level Personaje:  "+ equipo.getPj().getLevel());
        contentStream.showText("ATK Personaje:  "+ equipo.getPj().getATK());
        contentStream.showText("Atributo Personaje:  "+ equipo.getPj().getAtribute());
        contentStream.showText("DanyoCrit Personaje:  "+ equipo.getPj().getDanyoCrit());
        contentStream.showText("DEF Personaje:  "+ equipo.getPj().getDEF());
        contentStream.showText("Elemental Bonus Personaje:  "+ equipo.getPj().getElementalBonus());
        contentStream.showText("Energy Recharge Personaje:  "+ equipo.getPj().getEnergyRecharge());
        contentStream.showText("Mastery Personaje:  "+ equipo.getPj().getMastery());
        contentStream.showText("MaxHP Personaje:  "+ equipo.getPj().getMaxHP());
        contentStream.showText("Physical ATK Personaje:  "+ equipo.getPj().getPATK());
        contentStream.showText("Probabilidad Personaje:  "+ equipo.getPj().getProbCrit());
        contentStream.endText();
        contentStream.close();

        document.save(output);
        document.close();
        
        
        return output;

    }

}

