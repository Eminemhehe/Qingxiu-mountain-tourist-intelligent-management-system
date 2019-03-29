/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "travel", catalog = "salesmanagement", schema = "")
@NamedQueries({
    @NamedQuery(name = "Travel.findAll", query = "SELECT t FROM Travel t")
    , @NamedQuery(name = "Travel.findByName", query = "SELECT t FROM Travel t WHERE t.name = :name")
    , @NamedQuery(name = "Travel.findByCartId", query = "SELECT t FROM Travel t WHERE t.cartId = :cartId")
    , @NamedQuery(name = "Travel.findByTel", query = "SELECT t FROM Travel t WHERE t.tel = :tel")
    , @NamedQuery(name = "Travel.findByCost", query = "SELECT t FROM Travel t WHERE t.cost = :cost")
    , @NamedQuery(name = "Travel.findByType", query = "SELECT t FROM Travel t WHERE t.type = :type")
    , @NamedQuery(name = "Travel.findByLocal", query = "SELECT t FROM Travel t WHERE t.local = :local")
    , @NamedQuery(name = "Travel.findByOld", query = "SELECT t FROM Travel t WHERE t.old = :old")
    , @NamedQuery(name = "Travel.findByRemarks", query = "SELECT t FROM Travel t WHERE t.remarks = :remarks")})
public class Travel implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Column(name = "name")
    private String name;
    @Id
    @Basic(optional = false)
    @Column(name = "cartId")
    private Integer cartId;
    @Column(name = "tel")
    private String tel;
    @Column(name = "cost")
    private Long cost;
    @Column(name = "type")
    private String type;
    @Column(name = "local")
    private String local;
    @Column(name = "old")
    private String old;
    @Column(name = "remarks")
    private String remarks;

    public Travel() {
    }

    public Travel(Integer cartId) {
        this.cartId = cartId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        String oldName = this.name;
        this.name = name;
        changeSupport.firePropertyChange("name", oldName, name);
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        Integer oldCartId = this.cartId;
        this.cartId = cartId;
        changeSupport.firePropertyChange("cartId", oldCartId, cartId);
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        String oldTel = this.tel;
        this.tel = tel;
        changeSupport.firePropertyChange("tel", oldTel, tel);
    }

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        Long oldCost = this.cost;
        this.cost = cost;
        changeSupport.firePropertyChange("cost", oldCost, cost);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        String oldType = this.type;
        this.type = type;
        changeSupport.firePropertyChange("type", oldType, type);
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        String oldLocal = this.local;
        this.local = local;
        changeSupport.firePropertyChange("local", oldLocal, local);
    }

    public String getOld() {
        return old;
    }

    public void setOld(String old) {
        String oldOld = this.old;
        this.old = old;
        changeSupport.firePropertyChange("old", oldOld, old);
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        String oldRemarks = this.remarks;
        this.remarks = remarks;
        changeSupport.firePropertyChange("remarks", oldRemarks, remarks);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cartId != null ? cartId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Travel)) {
            return false;
        }
        Travel other = (Travel) object;
        if ((this.cartId == null && other.cartId != null) || (this.cartId != null && !this.cartId.equals(other.cartId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "GUI.Travel[ cartId=" + cartId + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
