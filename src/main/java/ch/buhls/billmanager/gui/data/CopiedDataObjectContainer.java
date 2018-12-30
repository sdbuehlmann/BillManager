
package ch.buhls.billmanager.gui.data;

/**
 *
 * @author simon
 * @param <G>
 */
public class CopiedDataObjectContainer<G extends AGUIData>
{
    private final G originalDataObject;
    private final G copiedDataObject;

    public CopiedDataObjectContainer(G originalDataObject, G copiedDataObject) {
        this.originalDataObject = originalDataObject;
        this.copiedDataObject = copiedDataObject;
    }

    public G getOriginalDataObject() {
        return originalDataObject;
    }

    public G getCopiedDataObject() {
        return copiedDataObject;
    }
}
