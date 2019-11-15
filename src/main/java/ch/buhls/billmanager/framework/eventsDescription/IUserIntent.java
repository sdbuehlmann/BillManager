package ch.buhls.billmanager.framework.eventsDescription;

/**
 *
 * @author buhls
 */
@FunctionalInterface
public interface IUserIntent<THandler>
{
    public void DoOnHandler(THandler handler);
}
