
package ch.buhls.billmanager.gui.framework;

/**
 *
 * @author simon
 */
public class StringCollections
{
    private final DEAppStringCollection appStringCollection;
    
    private final DEArticleStringCollection articlesStringCollection;
    private final DEBillStringCollection billStringCollection;
    private final DEFinancialYearStringCollection financialYearStringCollection;
    private final DEPersonStringCollection personStringCollection;
    private final DEPersonBaseDataStringCollection personBaseDataStringCollection;
    private final DERoleStringCollection roleStringCollection;
    private final DETemplateStringCollection templateStringCollection;
    private final DEAppSettingsStringCollection appSettingsStringCollection;

    public StringCollections() {
        appStringCollection = new DEAppStringCollection();
        
        articlesStringCollection = new DEArticleStringCollection();
        billStringCollection = new DEBillStringCollection();
        financialYearStringCollection = new DEFinancialYearStringCollection();
        personStringCollection = new DEPersonStringCollection();
        personBaseDataStringCollection = new DEPersonBaseDataStringCollection();
        roleStringCollection = new DERoleStringCollection();
        templateStringCollection = new DETemplateStringCollection();
        appSettingsStringCollection = new DEAppSettingsStringCollection();
    }

    public DEAppStringCollection getAppStringCollection() {
        return appStringCollection;
    }

    public DEArticleStringCollection getArticlesStringCollection() {
        return articlesStringCollection;
    }

    public DEBillStringCollection getBillStringCollection() {
        return billStringCollection;
    }

    public DEFinancialYearStringCollection getFinancialYearStringCollection() {
        return financialYearStringCollection;
    }

    public DEPersonStringCollection getPersonStringCollection() {
        return personStringCollection;
    }

    public DEPersonBaseDataStringCollection getPersonBaseDataStringCollection() {
        return personBaseDataStringCollection;
    }
    
    public DERoleStringCollection getRoleStringCollection() {
        return roleStringCollection;
    }

    public DETemplateStringCollection getTemplateStringCollection() {
        return templateStringCollection;
    }

    public DEAppSettingsStringCollection getAppSettingsStringCollection() {
        return appSettingsStringCollection;
    }
    
    
}
