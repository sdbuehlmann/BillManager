package ch.buhls.billmanager.model;

import ch.buhls.billmanager.persistance.PersistanceFascade;
import ch.buhls.billmanager.persistance.files.ProjectInfo;
import ch.buhls.billmanager.persistance.files.XMLHandler;
import java.io.File;

/**
 *
 * @author simon
 */
public class ModelFascade
{
    /*
    - Project
    -- templates (Dir)
    -- bills (Dir)
    -- temp (Dir)
    -- database
    -- info.hbcm (version etc.)
     */

    private final static String FILE_ENDING = ".hbcm";

    public Project createProject(File location) throws ModelException {
        if (location == null
                || location.exists()) {
            throw new ModelException("Can not create project.");
        }
        
        location.mkdir();

        File locationTemplates = new File(location, "templates");
        locationTemplates.mkdir();

        File locationBills = new File(location, "bills");
        locationBills.mkdir();

        File locationTemp = new File(location, "temp");
        locationTemp.mkdir();

        ProjectInfo info = new ProjectInfo();
        info.setVersion("0.2");

        XMLHandler.INSTANCE.storeProjectInfo(new File(location, location.getName() + FILE_ENDING), info);

        return new Project(
                location,
                locationTemplates,
                locationBills,
                locationTemp,
                new PersistanceFascade(new File(location, "data.db")),
                info);
    }

    public Project loadProject(File projectFile) throws ModelException {
        if (projectFile == null
                || !projectFile.isFile()) {
            throw new ModelException("Can not create project.");
        }
        
        ProjectInfo projectInfo = XMLHandler.INSTANCE.loadProjectInfo(projectFile);
        
        File projectDir = projectFile.getParentFile();

        File locationTemplates = new File(projectDir, "templates");
        File locationBills = new File(projectDir, "bills");
        File locationTemp = new File(projectDir, "temp");

        ProjectInfo info = new ProjectInfo();
        info.setVersion("0.2");

        return new Project(
                projectDir,
                locationTemplates,
                locationBills,
                locationTemp,
                new PersistanceFascade(new File(projectDir, "data.db")),
                info);
    }
}
