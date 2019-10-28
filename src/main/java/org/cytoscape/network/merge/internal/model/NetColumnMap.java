package org.cytoscape.network.merge.internal.model;

/*
 * #%L
 * Cytoscape Merge Impl (network-merge-impl)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2013 The Cytoscape Consortium
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 2.1 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */


import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

import org.cytoscape.model.CyColumn;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyTable;

/**
 * Class to store the information which attribute to be used 
 * for matching nodes
 * 
 * 
 */
public class NetColumnMap  {		//implements MatchingAttribute
    private Map<CyNetwork,CyColumn> attributeForMatching; // network to attribute name
    
    public NetColumnMap() {
        attributeForMatching = new WeakHashMap<CyNetwork,CyColumn>();
    }

//    public void dump(String s)
//    {
//		System.out.print(s + "NetColumnMap -> ");
//    	for (CyNetwork net: attributeForMatching.keySet())
//    		System.out.print(net.getSUID() + ": " + attributeForMatching.get(net) + ", ");
//		System.out.println();
//
//    }
    
    
    public Map<CyNetwork,CyColumn> getNetColumnMap() {        return attributeForMatching;    }
    
    public CyColumn getColumn(final CyNetwork net) {
        if (net == null) 
            throw new java.lang.NullPointerException("getAttributeForMatching: net == null");
          return attributeForMatching.get(net);
    }
    
    public void putAttributeForMatching(final CyNetwork net, final CyColumn col) {
        if (net==null)      throw new java.lang.NullPointerException("putAttributeForMatching: net == null");
        if (col==null)     throw new java.lang.NullPointerException("putAttributeForMatching: col == null");

        attributeForMatching.put(net, col);
    }

    public void addNetwork(final CyNetwork net) {
        if (net == null) 
            throw new java.lang.NullPointerException("addNetwork: net == null");
        
        //putAttributeForMatching(net,net.getDefaultNodeTable().getPrimaryKey());
        CyTable table = net.getDefaultNodeTable();
        CyColumn col = table.getColumn("name");
        putAttributeForMatching(net,col);
    }
            
    public CyColumn removeNetwork(final CyNetwork net) {
        if (net == null)         throw new java.lang.NullPointerException("removeNetwork: net == null");
        return attributeForMatching.remove(net);
    }
    
    public int getSizeNetwork() 			{       return attributeForMatching.size();    }
    public Set<CyNetwork> getNetworkSet() 	{       return attributeForMatching.keySet();    }
    public void clear() 					{       attributeForMatching.clear();    }

	public boolean contains(CyNetwork net, String attribute) {
		CyColumn col = attributeForMatching.get(net);
		return col != null && (col.getName().contentEquals(attribute));
	}
}
