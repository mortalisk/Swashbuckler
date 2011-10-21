//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.3-hudson-jaxb-ri-2.2.3-3- 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.10.21 at 01:26:34 AM CEST 
//


package no.mamot.swashbuckler.xml;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for obstacleType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="obstacleType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="shape" type="{http://www.example.org/level}shapeType"/>
 *         &lt;element name="particles">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="particle" type="{http://www.example.org/level}particleType" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="texture" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "obstacleType", propOrder = {
    "shape",
    "particles",
    "texture"
})
public class ObstacleType {

    @XmlElement(required = true)
    protected ShapeType shape;
    @XmlElement(required = true)
    protected ObstacleType.Particles particles;
    @XmlElement(required = true)
    protected String texture;

    /**
     * Gets the value of the shape property.
     * 
     * @return
     *     possible object is
     *     {@link ShapeType }
     *     
     */
    public ShapeType getShape() {
        return shape;
    }

    /**
     * Sets the value of the shape property.
     * 
     * @param value
     *     allowed object is
     *     {@link ShapeType }
     *     
     */
    public void setShape(ShapeType value) {
        this.shape = value;
    }

    /**
     * Gets the value of the particles property.
     * 
     * @return
     *     possible object is
     *     {@link ObstacleType.Particles }
     *     
     */
    public ObstacleType.Particles getParticles() {
        return particles;
    }

    /**
     * Sets the value of the particles property.
     * 
     * @param value
     *     allowed object is
     *     {@link ObstacleType.Particles }
     *     
     */
    public void setParticles(ObstacleType.Particles value) {
        this.particles = value;
    }

    /**
     * Gets the value of the texture property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTexture() {
        return texture;
    }

    /**
     * Sets the value of the texture property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTexture(String value) {
        this.texture = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="particle" type="{http://www.example.org/level}particleType" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "particle"
    })
    public static class Particles {

        protected List<ParticleType> particle;

        /**
         * Gets the value of the particle property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the particle property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getParticle().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ParticleType }
         * 
         * 
         */
        public List<ParticleType> getParticle() {
            if (particle == null) {
                particle = new ArrayList<ParticleType>();
            }
            return this.particle;
        }

    }

}
