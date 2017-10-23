package ese4.model;
import java.util.ArrayList;

/**
 * Contains all the packages that later will be sent to customers. Has methods to manage its
 * packages.
 * 
 * @author ese4
 * @Param packs ArrayList Contains all the package objects.
 *
 */
public class Warehouse {
	ArrayList<Package> packs;
	
	public Warehouse()
	{
		packs = new ArrayList<Package>();
	}

	/**
	 * Returns the package with the storageIndex which it gets by the parameter input.
	 * @param storageIndex
	 * @return package
	 */
	public Package getPack(int storageIndex)
	{
		return packs.get(storageIndex);
	}
	/**
	 * Adds the package it gets by the parameter input to the package list.
	 * @param pack
	 */
	public void addPack(Package pack)
	{
		packs.add(pack);
	}
	/**
	 * Removes the package at the inputted position.
	 * @param storageIndex
	 */
	public void removePack(int storageIndex)
	{
		packs.remove(storageIndex);
	}
}
