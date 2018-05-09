package net.mf;

import org.openqa.selenium.remote.BrowserType;

public class Environment {
    private String browser="firefox";
    private String version = "latest";
    private String platform = "Windows 10";
    private String  testName = "Selenium Burst JAVA - Google";
    private String SRF_CLIENT_ID = "t511780658_oauth2-8zlhcumOar9IdaurvPZ3@hpe.com";
    private String SRF_CLIENT_SECRET = "Fbi8BY3XNjmjgAaek5g";
    private String resolution = "800x600";
    private String FTAAS_URL = "https://ftaas.saas.hpe.com/wd/hub/";

    public void printEnvironment(){
        System.out.printf("Browser: %s\nVersion: %s\nPlatform: %s\nResolution: %s\nTest Name: %s\nSRF Client ID: %s\nSRF Client Secret: %s\n",
                getBrowser(),getVersion(),getPlatform(),getResolution(),getTestName(),getSRF_CLIENT_ID(),getSRF_CLIENT_SECRET());
    }
    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getVersion (){
        return this.version;
    }

    public void setVersion(String version){
        this.version = version;
    }

    public String getPlatform() {
        return this.platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getTestName(){
        return this.testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getSRF_CLIENT_ID() {
        return SRF_CLIENT_ID;
    }

    public void setSRF_CLIENT_ID(String SRF_CLIENT_ID) {
        this.SRF_CLIENT_ID = SRF_CLIENT_ID;
    }

    public String getSRF_CLIENT_SECRET() {
        return SRF_CLIENT_SECRET;
    }

    public void setSRF_CLIENT_SECRET(String SRF_CLIENT_SECRET) {
        this.SRF_CLIENT_SECRET = SRF_CLIENT_SECRET;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getFTAAS_URL() {
        return FTAAS_URL;
    }

    public void setFTAAS_URL(String FTAAS_URL) {
        this.FTAAS_URL = FTAAS_URL;
    }
}
