
package ch.buhls.billmanager.model.services;

import ch.buhls.billmanager.gui.data.GUIBill;
import ch.buhls.billmanager.model.App;
import ch.buhls.billmanager.model.AppException;
import ch.buhls.billmanager.model.ModelException;
import ch.buhls.billmanager.model.ModelFascade;
import ch.buhls.billmanager.model.Project;
import ch.buhls.billmanager.model.data.TemplateData;
import ch.buhls.billmanager.model.data.TemplatePosition;
import ch.buhls.billmanager.persistance.database.entities.Bill;
import ch.buhls.billmanager.persistance.database.entities.BillTemplate;
import ch.buhls.billmanager.persistance.database.entities.Person;
import ch.buhls.billmanager.persistance.database.entities.PersonBaseData;
import ch.buhls.billmanager.persistance.database.entities.Position;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author simon
 */
public class DocumentService
{
    private ModelFascade modelFascade;
    private Project project;

    public DocumentService(ModelFascade modelFascade, Project project) {
        this.modelFascade = modelFascade;
        this.project = project;
    }
    
    public void createDoc(BillTemplate template, Person person, String location, LocalDate date, int id) throws Exception{
        // manage template data
        File templateFile = project.getTemplateFile(template);

        // create file
        File pdfFile = createBillPDF(person.getPersonBaseData(), person.getBusket(), location, dateToString(date), id, templateFile);
    }
    
    public void showPDF(Bill bill) throws ModelException {
        try {
            File pdfFile = ModelFascade.createPathToBillPDF(project.getLocationBills(), bill.getId());
            modelFascade.openPDF(pdfFile);
        }
        catch (IOException ex) {
            throw new ModelException(ex.getMessage());
        }
    }

    public void printPDFs(List<Bill> bills) throws ModelException {
        List<File> pdfFiles = new ArrayList<>(bills.size());

        for (Bill bill : bills) {
            File pdfFile = ModelFascade.createPathToBillPDF(project.getLocationBills(), bill.getId());
            pdfFiles.add(pdfFile);
        }

        try {
            modelFascade.printPDFs(pdfFiles);
        }
        catch (Exception ex) {
            throw new ModelException(ex.getMessage());
        }
    }
    
    private String dateToString(LocalDate date) {
        StringBuilder sb = new StringBuilder();
        sb.append(Integer.toString(date.getDayOfMonth()));
        sb.append(". ");

        switch (date.getMonth()) {
            case JANUARY:
                sb.append("Januar ");
                break;
            case FEBRUARY:
                sb.append("Februar ");
                break;
            case MARCH:
                sb.append("März ");
                break;
            case APRIL:
                sb.append("April ");
                break;
            case MAY:
                sb.append("Mai ");
                break;
            case JUNE:
                sb.append("Juni ");
                break;
            case JULY:
                sb.append("Juli ");
                break;
            case AUGUST:
                sb.append("August ");
                break;
            case SEPTEMBER:
                sb.append("September ");
                break;
            case OCTOBER:
                sb.append("Oktober ");
                break;
            case NOVEMBER:
                sb.append("November ");
                break;
            case DECEMBER:
                sb.append("Dezember ");
                break;
        }

        sb.append(Integer.toString(date.getYear() + 1900));

        return sb.toString();
    }
    
    private File createBillPDF(PersonBaseData person, List<Position> positions, String location, String date, int billNr, File template) throws ModelException, AppException {
        TemplateData templateData = new TemplateData()
                .setPrename(person.getPrename())
                .setName(person.getName())
                .setStreet(person.getStreet())
                .setPlz(Integer.toString(person.getPostalcode()))
                .setCity(person.getCity())
                .setSalutation(person.getSalutation())
                .setTitle(person.getTitle())
                .setNumber(billNr + "")
                .setLocation(location)
                .setDate(date)
                .setTemplate(template.getAbsolutePath());

        int total = 0;
        for (Position pos : positions) {
            TemplatePosition templatePos = new TemplatePosition()
                    .setPosition(pos.getPosition())
                    .setNumber(pos.getNumber())
                    .setPrice(pos.getArticle().getCosts())
                    .setSum(pos.getArticle().getCosts() * pos.getNumber())
                    .setFirstLine(pos.getArticle().getTitle())
                    .setSecondLine(pos.getArticle().getDescription());

            templateData.getPositions().add(templatePos);

            total = total + templatePos.getSum();
        }

        templateData.setTotal(total);

        // create temp file
        File tempPDF = new File(project.getLocationBills(), ModelFascade.createBillFilename(billNr, ".pdf"));
        modelFascade.createPDF(tempPDF, template, App.INSTANCE.getInkscapePath(), templateData);

        return tempPDF;
    }
}
