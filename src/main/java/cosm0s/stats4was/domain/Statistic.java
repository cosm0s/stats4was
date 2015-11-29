package cosm0s.stats4was.domain;

public class Statistic {

    private String prefix;
    private String node;
    private String metricName;
    private String metricSeparator;
    private String method;
    private String unity;
    private String metric;
    private long timesTamp;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public String getMetricName() {
        return metricName;
    }

    public void setMetricName(String metricName) {
        this.metricName = metricName;
    }

    public String getMetricSeparator() {
        return metricSeparator;
    }

    public void setMetricSeparator(String metricSeparator) {
        this.metricSeparator = metricSeparator;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUnity() {
        return unity;
    }

    public void setUnity(String unity) {
        this.unity = unity;
    }

    public String getMetric() {
        return metric;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }

    public long getTimesTamp() {
        return timesTamp;
    }

    public void setTimesTamp(long timesTamp) {
        this.timesTamp = timesTamp;
    }

    public String standarMetric(){
        return this.prefix + "." + this.metricName + this.metricSeparator + method + this.unity + this.metric + " " + this.timesTamp;
    }
}
