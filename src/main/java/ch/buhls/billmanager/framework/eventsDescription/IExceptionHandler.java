package ch.buhls.billmanager.framework.eventsDescription;

@FunctionalInterface
public interface IExceptionHandler{
    void handle(Exception ex);
}
