package cosm0s.stats4was.xml;

import cosm0s.stats4was.core.exception.Stats4WasException;
import cosm0s.stats4was.log.L4j;
import cosm0s.stats4was.utils.DaemonContext;
import cosm0s.stats4was.xml.mapping.XMLMetricGroup;
import cosm0s.stats4was.xml.mapping.XMLPMIStatsType;
import cosm0s.stats4was.xml.mapping.XMLStats4was;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class FindMetricsXml {

    private XMLStats4was xmlStats4was;

    public void getAllMetricsConf() throws Stats4WasException {
        this.xmlStats4was = new XMLStats4was();
        for(File file:findAllFilesXml()){
            XMLStats4was xmlStats4wasFinded = ReadMetricsXml.getMetrics(file);
            L4j.getL4j().debug("Readding: " + file.getPath());
            if(this.xmlStats4was.getXmlpmiStatsType() == null && xmlStats4wasFinded.getXmlpmiStatsType() != null) {
                this.xmlStats4was.setXmlpmiStatsType(xmlStats4wasFinded.getXmlpmiStatsType());
                L4j.getL4j().debug("Setting pmi stats type object ");
            } else if (this.xmlStats4was.getXmlpmiStatsType() != null && xmlStats4wasFinded.getXmlpmiStatsType() != null) {
                throw new Stats4WasException("There can only be one PMIStatsType in all metrics conf XML ");
            }
            if(this.xmlStats4was.getXmlMetricGroups() == null && xmlStats4wasFinded.getXmlMetricGroups() != null){
                L4j.getL4j().debug("Setting metric groups");
                this.xmlStats4was.setXmlMetricGroups(xmlStats4wasFinded.getXmlMetricGroups());
            } else {
                L4j.getL4j().debug("Setting another metrics xml groups");
                List<XMLMetricGroup> xmlMetricGroupses = this.xmlStats4was.getXmlMetricGroups().getXmlMetricGroups();
                xmlMetricGroupses.addAll(xmlStats4wasFinded.getXmlMetricGroups().getXmlMetricGroups());
                this.xmlStats4was.getXmlMetricGroups().setXmlMetricGroups(xmlMetricGroupses);
            }
        }
    }

    public List<File> findAllFilesXml() throws Stats4WasException {
        String path = DaemonContext.instance().getProperty("PathMetricXml");
        File directory = new File(path);
        if(directory.isDirectory()){
            List<File> files = new LinkedList<File>();
            for(File file:Arrays.asList(directory.listFiles())){
                if(file.getName().length() > 3 && "xml".equals(file.getName().substring(file.getName().length() - 3))){
                    files.add(file);
                }
            }
            if(files.size() > 0) {
                return files;
            } else {
                throw new Stats4WasException("Not founds xml files in: " + path);
            }

        } else {
            throw new Stats4WasException("PathMetricXml is a file not directory.");
        }
    }

    public XMLStats4was getXmlStats4was() {
        return xmlStats4was;
    }

    public void setXmlStats4was(XMLStats4was xmlStats4was) {
        this.xmlStats4was = xmlStats4was;
    }

    public XMLPMIStatsType getXMLPMIStatsType(){
        return this.xmlStats4was.getXmlpmiStatsType();
    }

    public List<XMLMetricGroup> getXMLMetricGroup(){
        return this.xmlStats4was.getXmlMetricGroups().getXmlMetricGroups();
    }
}
