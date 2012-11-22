/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package moteur;

import java.io.Serializable;

/**
 *
 * @author Kévin
 */
public final class Bordure implements Serializable {

    protected static final long serialVersionUID = 1L;
    private int typeBordure;

    public Bordure(int typeBordure) {
        this.typeBordure = typeBordure;
    }

    public void setTypeBordure(int typeBordure) {
        this.typeBordure = typeBordure;
    }

    public int getTypeBordure() {
        return typeBordure;
    }
}
