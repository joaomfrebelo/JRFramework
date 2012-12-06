/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package info.joaorebelo.JRjavaUtils.ui;

import java.util.Objects;

/**
 *
 * @author joao
 */
public class JRcomboOption {

    /**
     * Value and description variables
     */
    private String _value, _descrption;

    public String getValue() {
        return _value;
    }

    public String getDescrption() {
        return _descrption;
    }

    @Override
    public String toString() {
        return this._descrption;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof JRcomboOption) {
            JRcomboOption cb = (JRcomboOption)obj;
            return (cb._value.equals(this._value) && cb._descrption.equals(this._descrption));
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this._value);
        hash = 37 * hash + Objects.hashCode(this._descrption);
        return hash;
    }

    public JRcomboOption(String value, String description) {
        this._value = value;
        this._descrption = description;
    }
}
