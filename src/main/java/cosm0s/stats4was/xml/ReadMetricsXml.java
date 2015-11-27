package cosm0s.stats4was.xml;

import cosm0s.stats4was.core.exception.Stats4WasException;
import cosm0s.stats4was.xml.mapping.XMLStats4was;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class ReadMetricsXml {

    public static XMLStats4was getMetrics(String path) throws Stats4WasException {
        return unmarshal(new File(path), XMLStats4was.class, path);
    }

    public static XMLStats4was getMetrics(File file) throws Stats4WasException {
        return unmarshal(file, XMLStats4was.class, file.getPath());
    }

    private static <E> E unmarshal(File file, Class<E> type, String path) throws Stats4WasException {
        JAXBContext jaxbContext;
        Unmarshaller jaxbUnmarshaller;
        Object object;
        try {
            jaxbContext = JAXBContext.newInstance(type);
            jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            object = jaxbUnmarshaller.unmarshal(file);
        } catch (JAXBException es) {
            throw new Stats4WasException("JAXB Exception in read metrics xml");
        }
        if (object == null) {
            throw new Stats4WasException("Can't read the configuration file: " + path);
        } else {
            return type.cast(object);
        }
    }

}
