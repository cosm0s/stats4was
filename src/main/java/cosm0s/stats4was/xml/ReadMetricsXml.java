package cosm0s.stats4was.xml;

import cosm0s.stats4was.xml.mapping.XMLStats4was;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class ReadMetricsXml {

    public static void main(String[] args) {

    }

    private static XMLStats4was getMetrics() {
        return unmarshal(new File("resources/conf/metrics.xml"), XMLStats4was.class);
    }

    private static <E> E unmarshal(File file, Class<E> type) {
        JAXBContext jaxbContext;
        Unmarshaller jaxbUnmarshaller;
        Object object = null;
        try {
            jaxbContext = JAXBContext.newInstance(type);
            jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            object = jaxbUnmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            System.out.println(e);
        }
        if (object == null) {
            System.out.println("mm");
        } else {
            return type.cast(object);
        }
        return null;
    }

}
