package ch.buhls.billmanager.model;

import ch.buhls.billmanager.persistance.PersistanceFascade;
import ch.buhls.billmanager.persistance.files.ProjectInfo;
import java.io.File;

/**
 *
 * @author simon
 */
public class Project
{
    private File location;
    private File locationTemplates;
    private File locationBills;
    private File locationTemp;
    private PersistanceFascade db;
    private ProjectInfo info;

    public Project(File location, File locationTemplates, File locationBills, File locationTemp, PersistanceFascade db, ProjectInfo info) {
        this.location = location;
        this.locationTemplates = locationTemplates;
        this.locationBills = locationBills;
        this.locationTemp = locationTemp;
        this.db = db;
        this.info = info;
    }

    public File getLocation() {
        return location;
    }

    public File getLocationTemplates() {
        return locationTemplates;
    }

    public File getLocationBills() {
        return locationBills;
    }

    public File getLocationTemp() {
        return locationTemp;
    }

    public PersistanceFascade getDb() {
        return db;
    }

    public ProjectInfo getInfo() {
        return info;
    }

    public String createTemplateName(int id){
        return "template_" + id + ".svg";
    }
}
