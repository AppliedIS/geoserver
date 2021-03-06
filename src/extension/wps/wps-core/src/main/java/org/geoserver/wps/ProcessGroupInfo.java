/* (c) 2014 Open Source Geospatial Foundation - all rights reserved
 * (c) 2001 - 2013 OpenPlans
 * This code is licensed under the GPL 2.0 license, available at the root
 * application directory.
 */
package org.geoserver.wps;

import java.util.List;

import org.geoserver.catalog.Info;
import org.geoserver.catalog.MetadataMap;
import org.geotools.process.ProcessFactory;

/**
 * Configuration for a specific process group (backed by a {@link ProcessFactory})
 * 
 * Andrea Aime - GeoSolutions
 */
public interface ProcessGroupInfo extends Info, Cloneable {

    /**
     * The process factory class (only means to identify it)
     */
    public Class<? extends ProcessFactory> getFactoryClass();

    /**
     * Sets the process factory class
     * 
     * @param factoryClass
     */
    public void setFactoryClass(Class<? extends ProcessFactory> factoryClass);

    /**
     * True if the factory is enabled, false otherwise
     * 
     * @return
     */
    public boolean isEnabled();

    /**
     * Enables/disables the factory
     * 
     * @param enabled
     */
    public void setEnabled(boolean enabled);

    /**
     * The list of processes generated by this factory that needs to be filtered out (disabled) or
     * exert access control on
     * 
     * @return
     */
    public List<ProcessInfo> getFilteredProcesses();

    /**
     * The metadata map, can contain any sort of information that non core plugins might use to
     * handle information related to this factory
     * 
     * @return
     */
    MetadataMap getMetadata();
    
    /**
     * Create a copy of this class
     * @return
     */
    ProcessGroupInfo clone();

    /*
     * Return roles granted to works with the WPS on this groups
     */
    List<String> getRoles();
    
    void setRoles(List<String> roles);
}
